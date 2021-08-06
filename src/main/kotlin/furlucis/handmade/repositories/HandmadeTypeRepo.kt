package furlucis.handmade.repositories

import furlucis.handmade.entity.HandmadeType
import org.springframework.data.jpa.repository.JpaRepository

interface HandmadeTypeRepo : JpaRepository<HandmadeType, Long>{
}