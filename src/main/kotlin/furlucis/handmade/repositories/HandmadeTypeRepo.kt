package furlucis.handmade.repositories

import furlucis.handmade.entity.HandmadeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HandmadeTypeRepo : JpaRepository<HandmadeType, Long>{
}