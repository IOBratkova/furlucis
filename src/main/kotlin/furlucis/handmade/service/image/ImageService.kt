package furlucis.handmade.service.image

import org.springframework.web.multipart.MultipartFile

interface ImageService {
    fun saveAvatar(file: MultipartFile, userId: Long) : String
}