package furlucis.handmade.service.user

import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.UserInfo
import org.springframework.web.multipart.MultipartFile

interface UserService {
    fun save(userInfo: UserInfo) : UserInfo
    fun save(userCredentials: UserCredentials) : UserCredentials
    fun save(userCredentials: UserCredentials, userInfo: UserInfo) : UserCredentials

    fun existsByUsername(username: String) : Boolean
    fun existsByEmail(email: String) : Boolean

    fun updateUsername(id: Long, username: String) : UserCredentials
    fun updateEmail(id: Long, email: String) : UserCredentials
    fun updatePassword(id: Long, newPassword: String, oldPassword: String) : UserCredentials

    fun findIncompleteRegistration(id: Long) : UserCredentials
    fun findCompleteRegistration(id: Long) : UserCredentials
    fun findByUsernameAndPassword(username: String, password: String) : UserCredentials
    fun findByEmilAndPassword(email: String, password: String) : UserCredentials
    fun findUserByCredentialDate(email: String?, username: String?, password: String?) : UserCredentials
    fun findByUsername(username: String) : UserCredentials
    fun findByEmail(email: String) : UserCredentials
    fun findById(id: Long) : UserCredentials
    fun findUserInfoById(id: Long) : UserInfo
}