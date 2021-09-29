package furlucis.handmade

import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.UserInfo
import furlucis.handmade.enums.RoleEnum
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

	@BeforeAll
	fun setData() {
		createUserCredentials()
		createUserInfo()
	}

	fun createUserInfo() {
		val userCredentials2 = userCredentialsRepo.findById(2L).get()
		val userInfo2 = UserInfo(
			2L,
			userCredentials2,
			"Толя",
			null,
			null,
			"desc",
			null,
			Date(),
			Date()
		)
		userCredentials2.userInfo = userInfo2
		userInfoRepo.save(userInfo2)
	}

	fun createUserCredentials() {
		val userCredentials1 = UserCredentials(
			1L,
			"testname",
			"password",
			"email@email.ru",
			RoleEnum.USER.text,
			null,
			null
		)

		val userCredentials2 = UserCredentials(
			2L,
			"testname2",
			"password2",
			"email2@email.ru",
			RoleEnum.USER.text,
			null,
			null
		)
		userCredentialsRepo.save(userCredentials1)
		userCredentialsRepo.save(userCredentials2)
	}


}
