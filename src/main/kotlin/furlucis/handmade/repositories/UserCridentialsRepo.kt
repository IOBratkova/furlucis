package furlucis.handmade.repositories

import furlucis.handmade.entity.UserCredentials
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.validation.constraints.Email


@Repository
interface UserCridentialsRepo : JpaRepository<UserCredentials, Long> {
    fun findByUsername(username: String) : Optional<UserCredentials>
    fun findByEmail(email: String) : Optional<UserCredentials>
}