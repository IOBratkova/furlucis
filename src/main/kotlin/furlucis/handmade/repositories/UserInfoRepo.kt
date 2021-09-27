package furlucis.handmade.repositories

import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.UserInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.validation.constraints.Email


@Repository
interface UserInfoRepo : JpaRepository<UserInfo, Long> {
    override fun findById(id: Long) : Optional<UserInfo>
}