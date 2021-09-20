package furlucis.handmade.service.user

import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.enums.RoleEnum
import furlucis.handmade.exceptions.*
import furlucis.handmade.repositories.UserCridentialsRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserCridentialsServiceImpl @Autowired constructor(
        private val passwordEncoder: PasswordEncoder,
        private val userCridentialsRepo: UserCridentialsRepo
): UserCridentialsService {

    override fun save(userCredentials: UserCredentials): UserCredentials {
        return if (userCridentialsRepo.existsByUsername(userCredentials.username)) {
            throw UsernameRegistrationException(userCredentials.username)
        } else if (userCridentialsRepo.existsByEmail(userCredentials.email)) {
            throw EmailRegistrationException(userCredentials.email)
        } else {
            userCredentials.role = RoleEnum.USER.text
            userCredentials.password = passwordEncoder.encode(userCredentials.password)
            userCridentialsRepo.save(userCredentials)
        }
    }

    override fun existsByUsername(username: String): Boolean {
        return userCridentialsRepo.existsByUsername(username)
    }

    override fun existsByEmail(email: String): Boolean {
        return userCridentialsRepo.existsByEmail(email)
    }

    override fun findByUsername(username: String): UserCredentials {
        return userCridentialsRepo.findByUsername(username)
                .orElseThrow {
                   UsernameException(username)
                }
    }

    override fun findByEmail(email: String): UserCredentials {
        return userCridentialsRepo.findByEmail(email)
            .orElseThrow {
                EmailException(email)
            }
    }

    override fun findById(id: Long): UserCredentials {
        return userCridentialsRepo.getById(id)
    }

    override fun findByUsernameAndPassword(username: String, password: String): UserCredentials {
        val userCridentials = findByUsername(username)
        return validatePassword(password, userCridentials)
    }

    override fun findByEmilAndPassword(email: String, password: String): UserCredentials {
        val userCridentials = findByEmail(email)
        return validatePassword(password, userCridentials)
    }

    override fun finsUserByCredentialDate(email: String?, username: String?, password: String): UserCredentials {
        if (email == null && username == null) {
            throw IncorrectRequestException()
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