package furlucis.handmade.service.image

import furlucis.handmade.service.user.UserService
import furlucis.handmade.utils.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths

@Service
class ImageServiceImpl @Autowired constructor(
    private val userService: UserService
): ImageService {

    @Value("$(images.avatar.path)")
    private val avatarPath: String = ""

    override fun saveAvatar(file: MultipartFile, userId: Long): String {
        val userInfo = userService.findUserInfoById(userId)
        val path = prepareImageFile(file, avatarPath)
        writeFile(path, file)
        userInfo.avatar = path.toUri().toString()
        userService.save(userInfo).id!!
        return path.toUri().toString()
    }


}