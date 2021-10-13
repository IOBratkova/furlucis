package furlucis.handmade.service

import furlucis.handmade.HandmadeApplicationTests
import furlucis.handmade.entity.HandmadeTag
import furlucis.handmade.exceptions.ExistsHandmadeTagException
import furlucis.handmade.exceptions.HandmadeTagNotFoundException
import furlucis.handmade.exceptions.IncorrectHandmadeTagException
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

    @Test
    fun `save tag` () {
        val tag = HandmadeTag(null, "вышивка гладью", "красивый элемент")
        val result = handmadeTagService.save(tag)
        Assertions.assertNotNull(result)
    }

    @Test
    fun `do not save tag` () {
        val tag = HandmadeTag(null, "вышивка крестом", "красивый элемент")
        handmadeTagRepo.save(tag)
        val thrown = Assertions.assertThrows(ExistsHandmadeTagException::class.java) {
            handmadeTagService.save(tag)
        }
        Assertions.assertEquals(thrown.message, "Тег вышивка крестом уже существует.")
    }

    @Test
    fun `do not save tag with incorrect title` () {
        val tag = HandmadeTag(null, null, "красивый элемент")
        val thrown = Assertions.assertThrows(IncorrectHandmadeTagException::class.java) {
            handmadeTagService.save(tag)
        }
        Assertions.assertEquals(thrown.message, "Некорректные данные для тега.")
    }

    @Test
    fun `find all tags` () {
        var tags = handmadeTagService.findAllTags()
        Assertions.assertNotNull(tags)
        Assertions.assertTrue(tags.isNotEmpty())

        tags = handmadeTagService.findAllTagsOrderByTitle()
        Assertions.assertNotNull(tags)
        Assertions.assertTrue(tags.isNotEmpty())
    }

    @Test
    fun `find tag by title` () {
        val tag = handmadeTagService.findByTitle("вышивка крестом")
        Assertions.assertNotNull(tag)
        Assertions.assertEquals(tag.title, "вышивка крестом")
    }

    @Test
    fun `do not find tag by title` () {
        val thrown = Assertions.assertThrows(HandmadeTagNotFoundException::class.java) {
            handmadeTagService.findByTitle("биба и блоба")
        }
        Assertions.assertEquals(thrown.message, "Тега биба и блоба не существует")
    }

    @Test
    fun `exists tag by title` () {
        var tag = handmadeTagService.existsByTitle("вышивка крестом")
        Assertions.assertNotNull(tag)
        Assertions.assertTrue(tag)

        tag = handmadeTagService.existsByTitle("биба_и_блоба")
        Assertions.assertNotNull(tag)
        Assertions.assertFalse(tag)
    }

}