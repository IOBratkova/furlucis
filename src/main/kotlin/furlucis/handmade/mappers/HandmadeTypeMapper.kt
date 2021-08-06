package furlucis.handmade.mappers

import furlucis.handmade.dto.handmadetype.HandmadeTypeDto
import furlucis.handmade.entity.HandmadeType
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface  HandmadeTypeMapper : GenericMapper<HandmadeType, HandmadeTypeDto> {

}
