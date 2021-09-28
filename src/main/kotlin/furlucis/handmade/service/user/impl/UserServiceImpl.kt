package furlucis.handmade.service.user.impl

import furlucis.handmade.entity.UserInfo
import furlucis.handmade.exceptions.UserIdException
import furlucis.handmade.repositories.UserInfoRepo
import furlucis.handmade.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.validation.constraints.Null

@Service
class UserServiceImpl @Autowired constructor(
        private val userInfoRepo: UserInfoRepo
): UserService {

}