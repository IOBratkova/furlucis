package furlucis.handmade.service.image

import furlucis.handmade.service.user.UserService
import furlucis.handmade.utils.loadFile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ImageServiceImpl @Autowired constructor(
    private val userService: UserService
): ImageService {

    @Value("\${images.avatar}")
    private val avatarPath: String = ""

    @Value("\${images.save}")
    private val save: Boolean = true

    override fun saveAvatar(file: MultipartFile, userId: Long): String {
        val userInfo = userService.findUserInfoById(userId)
        val path = loadFile(file, avatarPath, save)
        userInfo.avatar = path
        userService.save(userInfo).id!!
        return path
    }


}