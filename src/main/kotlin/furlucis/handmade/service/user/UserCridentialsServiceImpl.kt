package furlucis.handmade.service.user

import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.enums.Role
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
        userCredentials.role = Role.USER.text
        userCredentials.password = passwordEncoder.encode(userCredentials.password)
        return userCridentialsRepo.save(userCredentials)
    }

    override fun findByUsername(username: String): UserCredentials {
        return userCridentialsRepo.findByUsername(username)
                .orElseThrow {
                    NullPointerException()
                }
    }

    override fun findByEmail(email: String): UserCredentials {
        return userCridentialsRepo.findByEmail(email)
            .orElseThrow {
                NullPointerException()
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
            throw NullPointerException()
        }
        val userCridentials = if (email == null) findByUsername(username!!) else findByEmail(email)
        return validatePassword(password, userCridentials)
    }

    private fun validatePassword(password: String, userCridentials: UserCredentials): UserCredentials {
        if (passwordEncoder.matches(password, userCridentials.password)) {
            return userCridentials
        }
        throw NullPointerException()
    }

}