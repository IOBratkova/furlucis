package furlucis.handmade.service

import furlucis.handmade.HandmadeApplicationTests
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
    fun `save user info` () {
        val credentials = userCredentialsRepo.findById(1L).get()
        val userInfo = UserInfo(
            credentials.userInfo!!.id!!,
            credentials,
            "firdt",
            "second",
            "patr",
            "desc",
            "kijk",
            null,
            null
        )
        val result = userService.save(userInfo)
        Assertions.assertEquals(result.id, 1L)
    }

}