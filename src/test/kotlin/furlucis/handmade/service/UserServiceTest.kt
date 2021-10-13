package furlucis.handmade.service

import furlucis.handmade.HandmadeApplicationTests
import furlucis.handmade.entity.User
import furlucis.handmade.exceptions.*
import furlucis.handmade.service.user.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest @Autowired constructor(
    private val userService: UserService
) : HandmadeApplicationTests() {

    private val testImage = "/Users/bratckovaio/Documents/Projects/furlucis/src/test/resources/test_ava.png"

    fun getMultipartFile() : MultipartFile {
        val inputStream = FileInputStream(testImage)
        return MockMultipartFile(testImage, testImage, null, inputStream)
    }

    @Test
    fun `save user info` () {
        val credentials = userCredentialsRepo.findById(1L).get()
        val user = User(
            credentials.user!!.id!!,
            credentials,
            "firdt",
            "second",
            "patr",
            "desc",
            "kijk",
            null,
            null
        )
        val result = userService.save(user)
        Assertions.assertEquals(result.id, 1L)
    }

    @Test
    fun `do not save user avatar` () {
        val avatar = getMultipartFile()
        val thrown = Assertions.assertThrows(UserIdException::class.java) {
            userService.saveAvatar(avatar, 0L)
        }
        Assertions.assertEquals(thrown.message, "Пользователя с id 0 не существует.")
    }

    @Test
    fun `save user avatar` () {
        val avatar = getMultipartFile()
        val result = userService.saveAvatar(avatar, 1L)
        Assertions.assertNotNull(result)
    }

}