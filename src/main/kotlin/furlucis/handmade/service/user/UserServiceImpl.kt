package furlucis.handmade.service.user

import furlucis.handmade.entity.UserInfo
import furlucis.handmade.exceptions.UserIdException
import furlucis.handmade.repositories.UserInfoRepo
import furlucis.handmade.service.image.ImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class UserServiceImpl @Autowired constructor(
        private val userInfoRepo: UserInfoRepo,
        private val imageService: ImageService
): UserService {

    override fun save(userInfo: UserInfo): UserInfo {
        userInfo.created = Date()
        userInfo.updated = userInfo.created
        return userInfoRepo.save(userInfo)
    }

    override fun findUserInfoById(id: Long): UserInfo {
        return userInfoRepo.findById(id).orElseThrow {
            throw UserIdException(id)
        }
    }

    override fun findByUserCredentialsId(id: Long): UserInfo {
        return userInfoRepo.findByUserCredentialsId(id).orElseThrow {
            throw UserIdException(id)
        }
    }

    override fun saveAvatar(file: MultipartFile, userId: Long): UserInfo {
        val userInfo = findByUserCredentialsId(userId)
        val path = imageService.saveAvatar(file)
        userInfo.avatar = path
        return save(userInfo)
    }


}