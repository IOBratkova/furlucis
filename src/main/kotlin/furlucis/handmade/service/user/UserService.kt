package furlucis.handmade.service.user

import furlucis.handmade.entity.User
import org.springframework.web.multipart.MultipartFile

interface UserService {
    fun save(user: User) : User
    fun findUserInfoById(id: Long) : User
    fun findByUserCredentialsId(id: Long) : User
    fun saveAvatar(file: MultipartFile, userId: Long) : User
}