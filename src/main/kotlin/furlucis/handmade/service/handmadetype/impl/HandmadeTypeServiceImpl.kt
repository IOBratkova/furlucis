package furlucis.handmade.service.handmadetype.impl

import furlucis.handmade.entity.HandmadeType
import furlucis.handmade.repositories.HandmadeTypeRepo
import furlucis.handmade.service.handmadetype.HandmadeTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HandmadeTypeServiceImpl @Autowired constructor(
    private val handmadeTypeRepo: HandmadeTypeRepo
): HandmadeTypeService {

    override fun save(handmadeType: HandmadeType): Long {
//        TODO: NullPointer!!!!
        return handmadeTypeRepo.save(handmadeType).id ?: throw NullPointerException()
    }

}