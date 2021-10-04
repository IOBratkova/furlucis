package furlucis.handmade.service.handmadetag

import furlucis.handmade.entity.HandmadeTag

interface HandmadeTagService {
    fun save(tag: HandmadeTag) : HandmadeTag
    fun findAllTags() : List<HandmadeTag>
    fun findAllTagsOrderByTitle() : List<HandmadeTag>
    fun findByTitle(title: String) : HandmadeTag
    fun existsByTitle(title: String) : Boolean
}