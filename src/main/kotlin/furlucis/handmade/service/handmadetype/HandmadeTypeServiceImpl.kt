package furlucis.handmade.service.handmadetype

import furlucis.handmade.entity.HandmadeType
import furlucis.handmade.repositories.HandmadeTypeRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HandmadeTypeServiceImpl @Autowired constructor(
    private val handmadeTypeRepo: HandmadeTypeRepo
): HandmadeTypeService {

    override fun save(handmadeType: HandmadeType): Long {
        return handmadeTypeRepo.save(handmadeType).id ?: throw NullPointerException()
    }

}