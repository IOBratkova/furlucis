package furlucis.handmade.service.user

import furlucis.handmade.entity.UserInfo
import org.springframework.web.multipart.MultipartFile

interface UserService {
    fun save(userInfo: UserInfo) : UserInfo
    fun findUserInfoById(id: Long) : UserInfo
    fun findByUserCredentialsId(id: Long) : UserInfo
    fun saveAvatar(file: MultipartFile, userId: Long) : UserInfo
}