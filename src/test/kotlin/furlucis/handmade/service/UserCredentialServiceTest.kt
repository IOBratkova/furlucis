package furlucis.handmade.service

import furlucis.handmade.HandmadeApplicationTests
import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.enums.RoleEnum
import furlucis.handmade.exceptions.*
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
            1L,
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
    fun `find user by email and password` () {
        val userCredentials = UserCredentials(
            null,
            "testname__111",
            "password",
            "emailemailemail@email.ru",
            RoleEnum.USER.text,
            null,
            null
        )
        val result = service.save(userCredentials)
        Assertions.assertNotNull(result)

        val user = service.findByEmilAndPassword("emailemailemail@email.ru", "password")
        Assertions.assertNotNull(user)
        Assertions.assertEquals(user.email, "emailemailemail@email.ru")
        Assertions.assertEquals(user.username, "testname__111")
    }

    @Test
    fun `do not find user by email and password because email not exists` () {
        val userCredentials = UserCredentials(
            null,
            "testname__111",
            "password",
            "emailemailemail@email.ru",
            RoleEnum.USER.text,
            null,
            null
        )
        val result = service.save(userCredentials)
        Assertions.assertNotNull(result)

        val thrown = Assertions.assertThrows(EmailException::class.java) {
            service.findByEmilAndPassword("el@email.ru", "password")
        }
        Assertions.assertEquals(thrown.message, "Пользователь с email el@email.ru не существует.")
    }

    @Test
    fun `save user` () {
        val userCredentials = UserCredentials(
            null,
            "testname__",
            "password_new",
            "emailemailemail@email.ru",
            RoleEnum.USER.text,
            null,
            null
        )
        val result = service.save(userCredentials)
        Assertions.assertEquals(result.email, userCredentials.email)
        Assertions.assertEquals(result.username, userCredentials.username)
    }

    @Test
    fun `do not save user by email` () {
        val userCredentials = UserCredentials(
            null,
            "testname__10",
            "password_new",
            "email@email.ru",
            RoleEnum.USER.text,
            null,
            null
        )
        val thrown = Assertions.assertThrows(EmailRegistrationException::class.java) {
            service.save(userCredentials)
        }
        Assertions.assertEquals(thrown.message, "Пользователь с email email@email.ru существует. Выберите другой email.")
    }

    @Test
    fun `do not save user by username` () {
        val userCredentials = UserCredentials(
            null,
            "testname",
            "password_new",
            "emailemail@email.ru",
            RoleEnum.USER.text,
            null,
            null
        )
        val thrown = Assertions.assertThrows(UsernameRegistrationException::class.java) {
            service.save(userCredentials)
        }
        Assertions.assertEquals(thrown.message, "Пользователь с username testname существует. Выберите другой логин.")
    }

    @Test
    fun `user found in by id` () {
        val result = service.findById(1L)
        Assertions.assertEquals(result.username, "testname")
        Assertions.assertEquals(result.password, "password")
        Assertions.assertEquals(result.email, "email@email.ru")
    }

    @Test
    fun `user not found in by id` () {
        val thrown = Assertions.assertThrows(UserIdException::class.java) {
            service.findById(999L)
        }
        Assertions.assertNotNull(thrown)
        Assertions.assertEquals(thrown.message, "Пользователя с id 999 не существует.")
    }

    @Test
    fun `user found in by name` () {
        val result = service.findByUsername("testname")
        Assertions.assertEquals(result.username, "testname")
        Assertions.assertEquals(result.password, "password")
        Assertions.assertEquals(result.email, "email@email.ru")
    }

    @Test
    fun `user not found in by name` () {
        val thrown = Assertions.assertThrows(UsernameException::class.java) {
            service.findByUsername("notname")
        }
        Assertions.assertNotNull(thrown)
        Assertions.assertEquals(thrown.message, "Пользователь с username notname не существует.")
    }

    @Test
    fun `user found in by email` () {
        val result = service.findByEmail("email@email.ru")
        Assertions.assertEquals(result.username, "testname")
        Assertions.assertEquals(result.password, "password")
        Assertions.assertEquals(result.email, "email@email.ru")
    }

    @Test
    fun `user not found in by email` () {
        val thrown = Assertions.assertThrows(EmailException::class.java) {
            service.findByEmail("email@email.com")
        }
        Assertions.assertNotNull(thrown)
        Assertions.assertEquals(thrown.message, "Пользователь с email email@email.com не существует.")
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