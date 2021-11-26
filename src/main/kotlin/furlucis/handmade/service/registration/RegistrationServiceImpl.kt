package furlucis.handmade.service.registration

import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.User
import furlucis.handmade.enums.RoleEnum
import furlucis.handmade.exceptions.*
import furlucis.handmade.repositories.UserCredentialsRepo
import furlucis.handmade.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class RegistrationServiceImpl @Autowired constructor(
        private val passwordEncoder: PasswordEncoder,
        private val userCredentialsRepo: UserCredentialsRepo,
        private val userService: UserService
): RegistrationService {

        override fun save(userCredentials: UserCredentials): UserCredentials {
                if (userCredentialsRepo.existsByUsername(userCredentials.username)) {
                        throw UsernameRegistrationException(userCredentials.username)
                }
                if (userCredentialsRepo.existsByEmail(userCredentials.email)) {
                        throw EmailRegistrationException(userCredentials.email)
                }
                val user = User(userCredentials.id, userCredentials, null, null, null, null, null, null, null, null)
                return setUserCredentials(userCredentials, user)
        }

        override fun save(userCredentials: UserCredentials, user: User): UserCredentials {
                if (userCredentialsRepo.existsByUsername(userCredentials.username)) {
                        throw UsernameRegistrationException(userCredentials.username)
                }
                if (userCredentialsRepo.existsByEmail(userCredentials.email)) {
                        throw EmailRegistrationException(userCredentials.email)
                }
                return setUserCredentials(userCredentials, user)
        }

        private fun setUserCredentials(
            userCredentials: UserCredentials,
            user: User
        ): UserCredentials {
                userCredentials.role = RoleEnum.USER.text
                userCredentials.password = passwordEncoder.encode(userCredentials.password)
                userCredentials.created = Date()
                userCredentials.updated = userCredentials.created
                user.created = userCredentials.created
                user.updated = userCredentials.updated
                userCredentialsRepo.save(userCredentials)
                userService.save(user)
                return userCredentials
        }

        override fun existsByUsername(username: String): Boolean {
                return userCredentialsRepo.existsByUsername(username)
        }

        override fun existsByEmail(email: String): Boolean {
                return userCredentialsRepo.existsByEmail(email)
        }


        override fun findIncompleteRegistration(id: Long): UserCredentials {
                val credentials = findById(id)
                if (credentials.user != null) {
                        throw CompleteRegistrationException(credentials.username)
                }
                return credentials
        }

        override fun findCompleteRegistration(id: Long): UserCredentials {
                val credentials = findById(id)
                if (credentials.user == null) {
                        throw IncompleteRegistrationException(credentials.username)
                }
                return credentials
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

        private fun validatePassword(password: String, userCridentials: UserCredentials): UserCredentials {
                if (passwordEncoder.matches(password, userCridentials.password)) {
                        return userCridentials
                }
                throw ValidatePasswordException()
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
}