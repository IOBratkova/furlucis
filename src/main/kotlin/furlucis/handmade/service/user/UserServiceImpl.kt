package furlucis.handmade.service.user

import furlucis.handmade.entity.User
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

    override fun save(user: User): User {
        user.created = Date()
        user.updated = user.created
        return userInfoRepo.save(user)
    }

    override fun findUserInfoById(id: Long): User {
        return userInfoRepo.findById(id).orElseThrow {
            throw UserIdException(id)
        }
    }

    override fun findByUserCredentialsId(id: Long): User {
        return userInfoRepo.findByUserCredentialsId(id).orElseThrow {
            throw UserIdException(id)
        }
    }

    override fun saveAvatar(file: MultipartFile, userId: Long): User {
        val userInfo = findByUserCredentialsId(userId)
        val path = imageService.saveAvatar(file)
        userInfo.avatar = path
        return save(userInfo)
    }


}