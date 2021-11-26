package furlucis.handmade.service.user

import furlucis.handmade.entity.User
import furlucis.handmade.exceptions.UserIdException
import furlucis.handmade.repositories.UserRepo
import furlucis.handmade.service.image.ImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepo: UserRepo,
    private val imageService: ImageService
): UserService {

    override fun save(user: User): User {
        user.created = Date()
        user.updated = user.created
        return userRepo.save(user)
    }

    override fun findUserById(id: Long): User {
        return userRepo.findById(id).orElseThrow {
            throw UserIdException(id)
        }
    }

    override fun findByUserCredentialsId(id: Long): User {
        return userRepo.findByUserCredentialsId(id).orElseThrow {
            throw UserIdException(id)
        }
    }

    override fun saveAvatar(file: MultipartFile, userId: Long): User {
        val user = findByUserCredentialsId(userId)
        val path = imageService.saveAvatar(file)
        user.avatar = path
        return save(user)
    }


}