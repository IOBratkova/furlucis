package furlucis.handmade.rest.mappers

import furlucis.handmade.entity.HandmadeTag
import furlucis.handmade.rest.dto.HandmadeTagDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", uses = [UserMapper::class])
interface HandmadeTagMapper {
    fun toHandmadeTag(tag: HandmadeTagDto) : HandmadeTag
    fun toSetHandmadeTag(tags: List<HandmadeTagDto>) : Set<HandmadeTag>
    fun toListHandmadeTagDto(tags: List<HandmadeTag>) : List<HandmadeTagDto>
}