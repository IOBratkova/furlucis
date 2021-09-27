package furlucis.handmade.service.user

import furlucis.handmade.entity.UserInfo

interface UserService {
    fun findById(id: Long) : UserInfo
    fun save(userInfo: UserInfo) : Long
}