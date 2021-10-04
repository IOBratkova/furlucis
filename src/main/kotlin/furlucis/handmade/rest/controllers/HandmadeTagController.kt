package furlucis.handmade.rest.controllers

import furlucis.handmade.rest.dto.*
import furlucis.handmade.rest.mappers.HandmadeTagMapper
import furlucis.handmade.rest.mappers.PostMapper
import furlucis.handmade.service.handmadetag.HandmadeTagService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/tag")
class HandmadeTagController @Autowired constructor(
    private val handmadeTagService: HandmadeTagService,
    private val handmadeTagMapper: HandmadeTagMapper
) {

    @PostMapping("/add")
    fun addNewTag(@RequestBody request: HandmadeTagDto) : IdentifierDto<Long> {
        val entity = handmadeTagMapper.toHandmadeTag(request)
        val result = handmadeTagService.save(entity)
        return IdentifierDto(result.id!!)
    }

    @GetMapping("/all")
    fun getUsersPosts() : ListDto<HandmadeTagDto> {
        val entities = handmadeTagService.findAllTagsOrderByTitle()
        return ListDto(handmadeTagMapper.toListHandmadeTagDto(entities))
    }

}