package furlucis.handmade.service.handmadetype

import furlucis.handmade.entity.HandmadeType

interface HandmadeTypeService {
    fun save(handmadeType: HandmadeType) : Long
//    fun getById(id: Long) : HandmadeType
//    fun getAll() : List<HandmadeType>
//    fun update(handmadeType: HandmadeType)
//    fun update(handmadeTypeDto: HandmadeTypeDto)
//    fun delete(id: Long)
}