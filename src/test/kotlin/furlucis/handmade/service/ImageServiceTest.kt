package furlucis.handmade.service

import furlucis.handmade.HandmadeApplicationTests
import furlucis.handmade.exceptions.UserIdException
import furlucis.handmade.service.image.ImageService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ImageServiceTest @Autowired constructor(
    private val imageService: ImageService
) : HandmadeApplicationTests() {

    private val testImage = "/Users/bratckovaio/Documents/Projects/furlucis/src/test/resources/test_ava.png"

    fun getMultipartFile() : MultipartFile {
        val inputStream = FileInputStream(testImage)
        return MockMultipartFile(testImage, testImage, null, inputStream)
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