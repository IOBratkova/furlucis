package furlucis.handmade.service.image

import furlucis.handmade.exceptions.EmptyFileException
import furlucis.handmade.service.user.UserService
import furlucis.handmade.utils.loadFile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ImageServiceImpl @Autowired constructor(
): ImageService {

    @Value("\${images.avatar}")
    private val avatarPath: String = ""

    @Value("\${images.save}")
    private val save: Boolean = true

    override fun saveAvatar(file: MultipartFile): String {
        if (file.isEmpty) {
            throw EmptyFileException()
        }
        return loadFile(file, avatarPath, save)
    }


}