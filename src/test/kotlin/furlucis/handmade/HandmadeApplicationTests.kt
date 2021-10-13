package furlucis.handmade

import furlucis.handmade.entity.HandmadeTag
import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.User
import furlucis.handmade.enums.RoleEnum
import furlucis.handmade.repositories.HandmadeTagRepo
import furlucis.handmade.repositories.UserCredentialsRepo
import furlucis.handmade.repositories.UserInfoRepo
import org.junit.jupiter.api.BeforeAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
class HandmadeApplicationTests {

	@Autowired
	lateinit var userCredentialsRepo: UserCredentialsRepo

	@Autowired
	lateinit var userInfoRepo: UserInfoRepo

	@Autowired
	lateinit var handmadeTagRepo: HandmadeTagRepo

	@BeforeAll
	fun setData() {
		createUserCredentials()
		createUserInfo()
		createHandmadeTag()
	}

	fun createUserInfo() {
		var userCredentials = userCredentialsRepo.findById(1L).get()
		var user = User(
			userCredentials.id,
			userCredentials,
			"Толя1",
			null,
			null,
			"desc",
			null,
			null,
			Date(),
			Date()
		)
		userCredentials.user = user
		userInfoRepo.save(user)

		userCredentials = userCredentialsRepo.findById(2L).get()
		user = User(
			userCredentials.id,
			userCredentials,
			"Толя2",
			null,
			null,
			"desc",
			null,
			null,
			Date(),
			Date()
		)
		userCredentials.user = user
		userInfoRepo.save(user)

		userCredentials = userCredentialsRepo.findById(3L).get()
		user = User(
			userCredentials.id,
			userCredentials,
			"Толя3",
			null,
			null,
			"desc",
			null,
			null,
			Date(),
			Date()
		)
		userCredentials.user = user
		userInfoRepo.save(user)
	}

	fun createUserCredentials() {
		var userCredentials = UserCredentials(
			1L,
			"testname",
			"password",
			"email@email.ru",
			RoleEnum.USER.text,
			null,
			null
		)
		userCredentialsRepo.save(userCredentials)

		userCredentials = UserCredentials(
			2L,
			"testname2",
			"password2",
			"email2@email.ru",
			RoleEnum.USER.text,
			null,
			null
		)
		userCredentialsRepo.save(userCredentials)

		userCredentials = UserCredentials(
			3L,
			"testname3",
			"password3",
			"email3@email.ru",
			RoleEnum.USER.text,
			null,
			null
		)
		userCredentialsRepo.save(userCredentials)
	}

	fun createHandmadeTag() {
		var tag = HandmadeTag(null, "ловец снов", "амулет", null)
		handmadeTagRepo.save(tag)

		tag = HandmadeTag(null, "вышивка крестом", "картина", null)
		handmadeTagRepo.save(tag)

		tag = HandmadeTag(null, "значок", "можно носить на одежде", null)
		handmadeTagRepo.save(tag)
	}
}
