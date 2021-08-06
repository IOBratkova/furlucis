package furlucis.handmade.service.handmadetype

import furlucis.handmade.dto.handmadetype.HandmadeTypeDto
import furlucis.handmade.entity.HandmadeType

interface HandmadeTypeService {
    fun save(handmadeType: HandmadeType) : Long?
    fun saveDto(handmadeTypeDto: HandmadeTypeDto) : Long?
    fun getById(id: Long) : HandmadeType
    fun getDtoById(id: Long): HandmadeTypeDto
    fun getAll() : List<HandmadeType>
    fun getAllDto() : List<HandmadeTypeDto>
    fun update(handmadeType: HandmadeType)
    fun update(handmadeTypeDto: HandmadeTypeDto)
    fun delete(id: Long)
}