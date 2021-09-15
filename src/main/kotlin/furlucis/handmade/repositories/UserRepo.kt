package furlucis.handmade.repositories

import furlucis.handmade.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepo : JpaRepository<User, UUID> {
    fun findByLogin(login : String) : User
    fun existsByLogin(login: String) : Boolean
}