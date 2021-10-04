package furlucis.handmade.rest.mappers

import furlucis.handmade.entity.HandmadeTag
import furlucis.handmade.rest.dto.HandmadeTagDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", uses = [UserMapper::class])
interface TagMapper {

    fun toHandmadeTag(tag: HandmadeTagDto) : HandmadeTag

    fun toSetHandmadeTag(tag: List<HandmadeTagDto>) : Set<HandmadeTag>

}