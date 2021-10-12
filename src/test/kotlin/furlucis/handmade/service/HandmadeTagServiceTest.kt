package furlucis.handmade.service

import furlucis.handmade.HandmadeApplicationTests
import furlucis.handmade.exceptions.UserIdException
import furlucis.handmade.service.handmadetag.HandmadeTagService
import furlucis.handmade.service.image.ImageService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HandmadeTagServiceTest @Autowired constructor(
    private val handmadeTagService: HandmadeTagService
) : HandmadeApplicationTests() {

    //    fun save(tag: HandmadeTag) : HandmadeTag
    //    fun findAllTags() : List<HandmadeTag>
    //    fun findAllTagsOrderByTitle() : List<HandmadeTag>
    //    fun findByTitle(title: String) : HandmadeTag
    //    fun existsByTitle(title: String) : Boolean

}