package furlucis.handmade.repositories

import furlucis.handmade.entity.UserInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserInfoRepo : JpaRepository<UserInfo, Long> {
}