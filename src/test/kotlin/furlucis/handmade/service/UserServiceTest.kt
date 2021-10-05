package furlucis.handmade.service

import furlucis.handmade.HandmadeApplicationTests
import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.UserInfo
import furlucis.handmade.enums.RoleEnum
import furlucis.handmade.exceptions.*
import furlucis.handmade.service.user.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest @Autowired constructor(
    private val userService: UserService
) : HandmadeApplicationTests() {

    @Test
    fun `do not find incomplete registration user` () {
        val thrown = Assertions.assertThrows(CompleteRegistrationException::class.java) {
            userService.findIncompleteRegistration(1L)
        }
        Assertions.assertEquals(thrown.message, "Пользователь с username testname полностью завершил регистрацию.")
    }

    @Test
    fun `find complete registration user` () {
        val result = userService.findById(1L)
        val completeRegistration = userService.findCompleteRegistration(1L)
        Assertions.assertEquals(result.username, completeRegistration.username)
        Assertions.assertEquals(result.password, completeRegistration.password)
        Assertions.assertEquals(result.email, completeRegistration.email)
        Assertions.assertNotNull(result.userInfo)
    }

    @Test
    fun `find user by credentials` () {
        val userCredentials = UserCredentials(
            null,
            "testname__",
            "password_new",
            "emaieee@email.ru",
            RoleEnum.USER.text,
            null,
            null
        )
        userService.save(userCredentials)

        var user = userService.findUserByCredentialDate("emaieee@email.ru", "testname__", "password_new")
        Assertions.assertNotNull(user)

        user = userService.findUserByCredentialDate(null, "testname__", "password_new")
        Assertions.assertNotNull(user)

        user = userService.findUserByCredentialDate("emaieee@email.ru", null, "password_new")
        Assertions.assertNotNull(user)
    }

    @Test
    fun `find user by email and password` () {
        val userCredentials = UserCredentials(
            null,
            "testname__1",
            "password",
            "emailemailemail@email.ru",
            RoleEnum.USER.text,
            null,
            null
        )
        val result = userService.save(userCredentials)
        Assertions.assertNotNull(result)

        val user = userService.findByEmilAndPassword("emailemailemail@email.ru", "password")
        Assertions.assertNotNull(user)
        Assertions.assertEquals(user.email, "emailemailemail@email.ru")
        Assertions.assertEquals(user.username, "testname__1")
    }

    @Test
    fun `find user by username and password` () {
        val userCredentials = UserCredentials(
            null,
            "testname__2",
            "password",
            "emailemailemail6@email.ru",
            RoleEnum.USER.text,
            null,
            null
        )
        val result = userService.save(userCredentials)
        Assertions.assertNotNull(result)

        val user = userService.findByUsernameAndPassword("testname__2", "password")
        Assertions.assertNotNull(user)
        Assertions.assertEquals(user.email, "emailemailemail6@email.ru")
        Assertions.assertEquals(user.username, "testname__2")
    }

    @Test
    fun `do not find user by email and password because email not exists` () {
        val userCredentials = UserCredentials(
            null,
            "testname__3",
            "password",
            "emailemailemail9@email.ru",
            RoleEnum.USER.text,
            null,
            null
        )
        val result = userService.save(userCredentials)
        Assertions.assertNotNull(result)

        val thrown = Assertions.assertThrows(EmailException::class.java) {
            userService.findByEmilAndPassword("el@email.ru", "password")
        }
        Assertions.assertEquals(thrown.message, "Пользователь с email el@email.ru не существует.")
    }

    @Test
    fun `do not find user by email and password because username not exists` () {
        val userCredentials = UserCredentials(
            null,
            "testname__4",
            "password",
            "emailemailemail0@email.ru",
            RoleEnum.USER.text,
            null,
            null
        )
        val result = userService.save(userCredentials)
        Assertions.assertNotNull(result)

        val thrown = Assertions.assertThrows(UsernameException::class.java) {
            userService.findByUsernameAndPassword("testname__11112", "password")
        }
        Assertions.assertEquals(thrown.message, "Пользователь с username testname__11112 не существует.")
    }

    @Test
    fun `do not find user by credentials` () {
        var thrown = Assertions.assertThrows(IncorrectCredentialsException::class.java) {
            userService.findUserByCredentialDate(null, null, null)
        }
        Assertions.assertEquals(thrown.message, "Некорректные параметры запроса")

        thrown = Assertions.assertThrows(IncorrectCredentialsException::class.java) {
            userService.findUserByCredentialDate("lalala@lalal.com", null, null)
        }
        Assertions.assertEquals(thrown.message, "Некорректные параметры запроса")

        thrown = Assertions.assertThrows(IncorrectCredentialsException::class.java) {
            userService.findUserByCredentialDate(null, "namenamename", null)
        }
        Assertions.assertEquals(thrown.message, "Некорректные параметры запроса")

        thrown = Assertions.assertThrows(IncorrectCredentialsException::class.java) {
            userService.findUserByCredentialDate("lalala@lalal.com", "namenamename", null)
        }
        Assertions.assertEquals(thrown.message, "Некорректные параметры запроса")

        var emailException = Assertions.assertThrows(EmailException::class.java) {
            userService.findUserByCredentialDate("lalala@lalal.com", "namenamename", "passs")
        }
        Assertions.assertEquals(emailException.message, "Пользователь с email lalala@lalal.com не существует.")

        emailException = Assertions.assertThrows(EmailException::class.java) {
            userService.findUserByCredentialDate("lalala@lalal.com", null, "passs")
        }
        Assertions.assertEquals(emailException.message, "Пользователь с email lalala@lalal.com не существует.")

       val usernameException = Assertions.assertThrows(UsernameException::class.java) {
           userService.findUserByCredentialDate(null, "namenamename", "passs")
       }
        Assertions.assertEquals(usernameException.message, "Пользователь с username namenamename не существует.")
    }


    @Test
    fun `save user` () {
        val userCredentials = UserCredentials(
            null,
            "testname__5",
            "password_new",
            "emailemailemail5@email.ru",
            RoleEnum.USER.text,
            null,
            null
        )
        val result = userService.save(userCredentials)
        Assertions.assertEquals(result.email, userCredentials.email)
        Assertions.assertEquals(result.username, userCredentials.username)
    }

    @Test
    fun `do not save user by email` () {
        val userCredentials = UserCredentials(
            null,
            "testname__6",
            "password_new",
            "email@email.ru",
            RoleEnum.USER.text,
            null,
            null
        )
        val thrown = Assertions.assertThrows(EmailRegistrationException::class.java) {
            userService.save(userCredentials)
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
            userService.save(userCredentials)
        }
        Assertions.assertEquals(thrown.message, "Пользователь с username testname существует. Выберите другой логин.")
    }

    @Test
    fun `user found in db by id` () {
        val result = userService.findById(1L)
        Assertions.assertEquals(result.username, "testname")
        Assertions.assertEquals(result.password, "password")
        Assertions.assertEquals(result.email, "email@email.ru")
    }

    @Test
    fun `user not found in by id` () {
        val thrown = Assertions.assertThrows(UserIdException::class.java) {
            userService.findById(999L)
        }
        Assertions.assertNotNull(thrown)
        Assertions.assertEquals(thrown.message, "Пользователя с id 999 не существует.")
    }

    @Test
    fun `user found in by name` () {
        val result = userService.findByUsername("testname")
        Assertions.assertEquals(result.username, "testname")
        Assertions.assertEquals(result.password, "password")
        Assertions.assertEquals(result.email, "email@email.ru")
    }

    @Test
    fun `user not found in by name` () {
        val thrown = Assertions.assertThrows(UsernameException::class.java) {
            userService.findByUsername("notname")
        }
        Assertions.assertNotNull(thrown)
        Assertions.assertEquals(thrown.message, "Пользователь с username notname не существует.")
    }

    @Test
    fun `user found in by email` () {
        val result = userService.findByEmail("email@email.ru")
        Assertions.assertEquals(result.username, "testname")
        Assertions.assertEquals(result.password, "password")
        Assertions.assertEquals(result.email, "email@email.ru")
    }

    @Test
    fun `user not found in by email` () {
        val thrown = Assertions.assertThrows(EmailException::class.java) {
            userService.findByEmail("email@email.com")
        }
        Assertions.assertNotNull(thrown)
        Assertions.assertEquals(thrown.message, "Пользователь с email email@email.com не существует.")
    }

    @Test
    fun `user does not exist by name`() {
        val result = userService.existsByUsername("notname")
        Assertions.assertFalse(result)
    }

    @Test
    fun `user does not exist by email`() {
        val result = userService.existsByEmail("notmail")
        Assertions.assertFalse(result)
    }

    @Test
    fun `user exist by name`() {
        val result = userService.existsByUsername("testname")
        Assertions.assertTrue(result)
    }

    @Test
    fun `user exist by email`() {
        val result = userService.existsByEmail("email@email.ru")
        Assertions.assertTrue(result)
    }

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