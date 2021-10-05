package furlucis.handmade.service

import furlucis.handmade.HandmadeApplicationTests
import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.UserInfo
import furlucis.handmade.enums.RoleEnum
import furlucis.handmade.exceptions.*
import furlucis.handmade.service.image.ImageService
import furlucis.handmade.service.user.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import org.xmlunit.builder.Input
import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ImageServiceTest @Autowired constructor(
    private val imageService: ImageService
) : HandmadeApplicationTests() {

    @Value("\${images.path}")
    private val testImage: String = ""

    fun getMultipartFile() : MultipartFile {
        val inputStream = FileInputStream(testImage)
        return MockMultipartFile(testImage, "avatar_test.png", null, inputStream)
    }

    @Test
    fun `do not save user avatar` () {
        val avatar = getMultipartFile()
        val thrown = Assertions.assertThrows(UserIdException::class.java) {
            imageService.saveAvatar(avatar, 0L)
        }
        Assertions.assertEquals(thrown.message, "Пользователя с id 0 не существует.")
    }

    @Test
    fun `save user avatar` () {
        val avatar = getMultipartFile()
        val result = imageService.saveAvatar(avatar, 1L)
        Assertions.assertNotNull(result)
    }
}