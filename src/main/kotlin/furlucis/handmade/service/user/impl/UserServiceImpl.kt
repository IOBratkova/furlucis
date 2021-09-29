package furlucis.handmade.service.user.impl

import furlucis.handmade.entity.UserInfo
import furlucis.handmade.repositories.UserInfoRepo
import furlucis.handmade.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl @Autowired constructor(
        private val userInfoRepo: UserInfoRepo,
): UserService {

        override fun save(userInfo: UserInfo): UserInfo {
                userInfo.created = Date()
                userInfo.updated = userInfo.created
                return userInfoRepo.save(userInfo)
        }

}