package furlucis.handmade.service.user.impl

import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.UserInfo
import furlucis.handmade.enums.RoleEnum
import furlucis.handmade.exceptions.*
import furlucis.handmade.repositories.UserCredentialsRepo
import furlucis.handmade.repositories.UserInfoRepo
import furlucis.handmade.service.user.UserCredentialsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserCredentialsServiceImpl @Autowired constructor(
        private val passwordEncoder: PasswordEncoder,
        private val userCredentialsRepo: UserCredentialsRepo,
        private val userInfoRepo: UserInfoRepo
): UserCredentialsService {

    override fun save(userInfo: UserInfo): UserInfo {
        userInfo.created = Date()
        userInfo.updated = userInfo.created
        return userInfoRepo.save(userInfo)
    }

    override fun save(userCredentials: UserCredentials): UserCredentials {
        if (userCredentialsRepo.existsByUsername(userCredentials.username)) {
            throw UsernameRegistrationException(userCredentials.username)
        }
        if (userCredentialsRepo.existsByEmail(userCredentials.email)) {
            throw EmailRegistrationException(userCredentials.email)
        }
        val userInfo = UserInfo(userCredentials.id, userCredentials, null, null, null, null, null, null, null, null)
        return setUserCredentials(userCredentials, userInfo)
    }

    override fun save(userCredentials: UserCredentials, userInfo: UserInfo): UserCredentials {
        if (userCredentialsRepo.existsByUsername(userCredentials.username)) {
            throw UsernameRegistrationException(userCredentials.username)
        }
        if (userCredentialsRepo.existsByEmail(userCredentials.email)) {
            throw EmailRegistrationException(userCredentials.email)
        }
        return setUserCredentials(userCredentials, userInfo)
    }

    private fun setUserCredentials(
        userCredentials: UserCredentials,
        userInfo: UserInfo
    ): UserCredentials {
        userCredentials.role = RoleEnum.USER.text
        userCredentials.password = passwordEncoder.encode(userCredentials.password)
        userCredentials.created = Date()
        userCredentials.updated = userCredentials.created
        userInfo.created = userCredentials.created
        userInfo.updated = userCredentials.updated
        userCredentialsRepo.save(userCredentials)
        save(userInfo)
        return userCredentials
    }

    override fun existsByUsername(username: String): Boolean {
        return userCredentialsRepo.existsByUsername(username)
    }

    override fun existsByEmail(email: String): Boolean {
        return userCredentialsRepo.existsByEmail(email)
    }

    override fun findByUsername(username: String): UserCredentials {
        return userCredentialsRepo.findFirstByUsername(username)
                .orElseThrow {
                   UsernameException(username)
                }
    }

    override fun findByEmail(email: String): UserCredentials {
        return userCredentialsRepo.findFirstByEmail(email)
            .orElseThrow {
                EmailException(email)
            }
    }

    override fun findById(id: Long): UserCredentials {
        return userCredentialsRepo.findById(id)
            .orElseThrow{
                UserIdException(id)
            }
    }

    override fun updateUsername(id: Long, username: String): UserCredentials {
        val userCredentials = findById(id)
        if (!existsByUsername(username)) {
            throw UsernameRegistrationException(username)
        }
        userCredentials.updated = Date()
        userCredentials.username = username
        return save(userCredentials)
    }

    override fun updateEmail(id: Long, email: String): UserCredentials {
        val userCredentials = findById(id)
        if (!existsByEmail(email)) {
            throw UsernameRegistrationException(email)
        }
        userCredentials.updated = Date()
        userCredentials.email = email
        return save(userCredentials)
    }

    override fun updatePassword(id: Long, newPassword: String, oldPassword: String): UserCredentials {
        val userCredentials = validatePassword(oldPassword, findById(id))
        userCredentials.password = passwordEncoder.encode(userCredentials.password)
        userCredentials.updated = Date()
        return save(userCredentials)
    }

    override fun findIncompleteRegistration(id: Long): UserCredentials {
        val credentials = findById(id)
        if (credentials.userInfo != null) {
            throw CompleteRegistrationException(credentials.username)
        }
        return credentials
    }

    override fun findCompleteRegistration(id: Long): UserCredentials {
        val credentials = findById(id)
        if (credentials.userInfo == null) {
            throw IncompleteRegistrationException(credentials.username)
        }
        return credentials
    }

    override fun findByUsernameAndPassword(username: String, password: String): UserCredentials {
        val userCredentials = findByUsername(username)
        return validatePassword(password, userCredentials)
    }

    override fun findByEmilAndPassword(email: String, password: String): UserCredentials {
        val userCridentials = findByEmail(email)
        return validatePassword(password, userCridentials)
    }

    override fun findUserByCredentialDate(email: String?, username: String?, password: String?): UserCredentials {
        if (email == null && username == null || password === null) {
            throw IncorrectCredentialsException()
        }
        val userCridentials = if (email == null) findByUsername(username!!) else findByEmail(email)
        return validatePassword(password, userCridentials)
    }

    private fun validatePassword(password: String, userCridentials: UserCredentials): UserCredentials {
        if (passwordEncoder.matches(password, userCridentials.password)) {
            return userCridentials
        }
        throw ValidatePasswordException()
    }

}