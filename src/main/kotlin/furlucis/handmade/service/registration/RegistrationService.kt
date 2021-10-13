package furlucis.handmade.service.registration

import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.User

interface RegistrationService {
    fun save(userCredentials: UserCredentials) : UserCredentials
    fun save(userCredentials: UserCredentials, user: User) : UserCredentials

    fun existsByUsername(username: String) : Boolean
    fun existsByEmail(email: String) : Boolean

    fun findById(id: Long) : UserCredentials
    fun findIncompleteRegistration(id: Long) : UserCredentials
    fun findCompleteRegistration(id: Long) : UserCredentials
    fun findByUsernameAndPassword(username: String, password: String) : UserCredentials
    fun findByEmilAndPassword(email: String, password: String) : UserCredentials
    fun findUserByCredentialDate(email: String?, username: String?, password: String?) : UserCredentials
    fun findByUsername(username: String) : UserCredentials
    fun findByEmail(email: String) : UserCredentials

    fun updateUsername(id: Long, username: String) : UserCredentials
    fun updateEmail(id: Long, email: String) : UserCredentials
    fun updatePassword(id: Long, newPassword: String, oldPassword: String) : UserCredentials
}