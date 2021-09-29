package furlucis.handmade.service

import furlucis.handmade.HandmadeApplicationTests
import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.UserInfo
import furlucis.handmade.enums.RoleEnum
import furlucis.handmade.exceptions.*
import furlucis.handmade.service.user.UserCredentialsService
import furlucis.handmade.service.user.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.event.annotation.AfterTestMethod

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserInfoServiceTest @Autowired constructor(
    private val userService: UserService
) : HandmadeApplicationTests() {

    @Test
    fun `update user info` () {
        val credentials = userCredentialsRepo.findById(2L).get()
        val userInfo = UserInfo(
            2L,
            credentials,
            "newname",
            "newwcond",
            "newpatr",
            "newdesc",
            null,
            null,
            null
        )
        val result = userService.save(userInfo)
        Assertions.assertEquals(result.id, 2L)
        Assertions.assertEquals(result.firstName, "newname")
        Assertions.assertEquals(result.secondName, "newwcond")
        Assertions.assertEquals(result.patronymic, "newpatr")
        Assertions.assertEquals(result.description, "newdesc")
    }

    @Test
    fun `save user info` () {
        val credentials = userCredentialsRepo.findById(2L).get()
        val userInfo = UserInfo(
            null,
            credentials,
            "firdt",
            "second",
            "patr",
            "desc",
            null,
            null,
            null
        )
        val result = userService.save(userInfo)
        Assertions.assertEquals(result.id, 2L)
    }

}