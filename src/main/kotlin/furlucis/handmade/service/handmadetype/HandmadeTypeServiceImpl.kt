package furlucis.handmade.service.handmadetype

import furlucis.handmade.dto.handmadetype.HandmadeTypeDto
import furlucis.handmade.entity.HandmadeType
import furlucis.handmade.mappers.HandmadeTypeMapper
import furlucis.handmade.repositories.HandmadeTypeRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HandmadeTypeServiceImpl @Autowired constructor(
    private val handmadeTypeRepo: HandmadeTypeRepo,
    private val handmadeTypeMapper: HandmadeTypeMapper
): HandmadeTypeService {

    override fun save(handmadeType: HandmadeType): Long? {
        return handmadeTypeRepo.save(handmadeType).id
    }

    override fun saveDto(handmadeTypeDto: HandmadeTypeDto) : Long? {
        return save(handmadeTypeMapper.toEntity(handmadeTypeDto))
    }

    override fun getById(id: Long): HandmadeType {
        TODO("Not yet implemented")
    }

    override fun getDtoById(id: Long): HandmadeTypeDto {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<HandmadeType> {
        TODO("Not yet implemented")
    }

    override fun getAllDto(): List<HandmadeTypeDto> {
        TODO("Not yet implemented")
    }

    override fun update(handmadeType: HandmadeType) {
        TODO("Not yet implemented")
    }

    override fun update(handmadeTypeDto: HandmadeTypeDto) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }


}