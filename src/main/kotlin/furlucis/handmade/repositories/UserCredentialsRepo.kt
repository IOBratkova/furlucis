package furlucis.handmade.repositories

import furlucis.handmade.entity.UserCredentials
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface UserCredentialsRepo : JpaRepository<UserCredentials, Long> {
    override fun findById(id: Long) : Optional<UserCredentials>
    fun findFirstByUsername(username: String) : Optional<UserCredentials>
    fun findFirstByEmail(email: String) : Optional<UserCredentials>
    fun existsByUsername(username: String) : Boolean
    fun existsByEmail(email: String) : Boolean
}