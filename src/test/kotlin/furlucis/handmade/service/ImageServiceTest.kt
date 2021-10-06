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

    fun getMultipartFile(inputStream: FileInputStream) : MultipartFile {
        return MockMultipartFile(testImage, testImage, null, inputStream)
    }

//    TODO: как-то записать замоканный пустой файл
//    @Test
//    fun `do not save user avatar` () {
//        val inputStream = FileInputStream(null as String)
//        val avatar = getMultipartFile(inputStream)
//        val thrown = Assertions.assertThrows(UserIdException::class.java) {
//            imageService.saveAvatar(avatar)
//        }
//        Assertions.assertEquals(thrown.message, "Пользователя с id 0 не существует.")
//    }

    @Test
    fun `save avatar` () {
        val inputStream = FileInputStream(testImage)
        val avatar = getMultipartFile(inputStream)
        val result = imageService.saveAvatar(avatar)
        Assertions.assertNotNull(result)
    }
}