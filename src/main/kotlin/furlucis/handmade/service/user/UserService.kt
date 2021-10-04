package furlucis.handmade.service.user

import furlucis.handmade.entity.UserInfo

interface UserService {
    fun save(userInfo: UserInfo) : UserInfo
    fun findById(id: Long) : UserInfo
}