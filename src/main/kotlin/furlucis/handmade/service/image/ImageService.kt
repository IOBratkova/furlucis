package furlucis.handmade.service.image

import furlucis.handmade.entity.HandmadeTag
import org.springframework.web.multipart.MultipartFile

interface ImageService {
    fun saveAvatar(file: MultipartFile, userId: Long) : String
}