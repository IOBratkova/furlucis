package furlucis.handmade.repositories

import furlucis.handmade.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HandmadeTagRepo : JpaRepository<Post, Long>{

}