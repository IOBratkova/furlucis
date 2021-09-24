package furlucis.handmade.service

import furlucis.handmade.HandmadeApplicationTests
import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.enums.RoleEnum
import furlucis.handmade.repositories.UserCridentialsRepo
import furlucis.handmade.service.user.UserCridentialsService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach

class UserCredentialServiceTest @Autowired constructor(
    private val service: UserCridentialsService,
    private val repo: UserCridentialsRepo
) : HandmadeApplicationTests() {

    @BeforeEach
    fun createUserCredentials() {
        val userCredentials = UserCredentials(
            null,
            "testname",
            "password",
            "email@email.ru",
            RoleEnum.USER.text,
            null,
            null
        )
        repo.save(userCredentials)
    }

    @Test
    fun `user does not exist by name`() {
        val result = service.existsByUsername("notname")
        Assertions.assertFalse(result)
    }

    @Test
    fun `user does not exist by email`() {
        val result = service.existsByEmail("notmail")
        Assertions.assertFalse(result)
    }

    @Test
    fun `user exist by name`() {
        val result = service.existsByUsername("testname")
        Assertions.assertTrue(result)
    }

    @Test
    fun `user exist by email`() {
        val result = service.existsByEmail("email@email.ru")
        Assertions.assertTrue(result)
    }

}