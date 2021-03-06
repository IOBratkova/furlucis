package furlucis.handmade.service.handmadetag

import furlucis.handmade.entity.HandmadeTag
import furlucis.handmade.repositories.HandmadeTagRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HandmadeTagServiceImpl @Autowired constructor(
        private val handmadeTagRepo: HandmadeTagRepo
): HandmadeTagService {

        override fun save(tag: HandmadeTag): HandmadeTag {
                if (tag.title == null) {
                        throw NullPointerException() //TODO: ex
                }
                tag.title = tag.title!!.replace("\\pP", " ")
                if (existsByTitle(tag.title!!)) {
                        throw NullPointerException() //TODO: ex
                }
                return handmadeTagRepo.save(tag)
        }

        override fun findAllTags(): List<HandmadeTag> {
                return handmadeTagRepo.findAll()
        }

        override fun findAllTagsOrderByTitle(): List<HandmadeTag> {
                return handmadeTagRepo.findAllByOrderByTitle()
        }

        override fun findByTitle(title: String): HandmadeTag {
                return handmadeTagRepo.findByTitle(title)
        }

        override fun existsByTitle(title: String): Boolean {
                return handmadeTagRepo.existsByTitle(title)
        }


}