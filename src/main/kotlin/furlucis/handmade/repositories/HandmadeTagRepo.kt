package furlucis.handmade.repositories

import furlucis.handmade.entity.HandmadeTag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HandmadeTagRepo : JpaRepository<HandmadeTag, Long>{
    fun findByTitle(title: String) : Optional<HandmadeTag>
    fun findAllByOrderByTitle() : List<HandmadeTag>
    fun existsByTitle(title: String) : Boolean
}