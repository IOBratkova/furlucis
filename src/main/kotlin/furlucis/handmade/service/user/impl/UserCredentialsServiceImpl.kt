package furlucis.handmade.service.user.impl

import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.enums.RoleEnum
import furlucis.handmade.exceptions.*
import furlucis.handmade.repositories.UserCredentialsRepo
import furlucis.handmade.service.user.UserCredentialsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserCredentialsServiceImpl @Autowired constructor(
        private val passwordEncoder: PasswordEncoder,
        private val userCredentialsRepo: UserCredentialsRepo
): UserCredentialsService {

    override fun save(userCredentials: UserCredentials): UserCredentials {
        return if (userCredentialsRepo.existsByUsername(userCredentials.username)) {
            throw UsernameRegistrationException(userCredentials.username)
        } else if (userCredentialsRepo.existsByEmail(userCredentials.email)) {
            throw EmailRegistrationException(userCredentials.email)
        } else {
            userCredentials.role = RoleEnum.USER.text
            userCredentials.password = passwordEncoder.encode(userCredentials.password)
            userCredentials.created = Date()
            userCredentials.updated = userCredentials.created
            userCredentialsRepo.save(userCredentials)
        }
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