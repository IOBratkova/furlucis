package furlucis.handmade.security.details

import furlucis.handmade.entity.User
import furlucis.handmade.security.FurUserDetails
import furlucis.handmade.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
open class FurUserDetailsServiceImpl @Autowired constructor(
        private val userService: UserService
) : FurUserDetailsService {

    override fun loadUserById(id: UUID): FurUserDetails? {
        val user = userService.getById(id)
        return FurUserDetails.create(user)
    }

    override fun loadUserByUsername(username: String): FurUserDetails? {
        val user = userService.getByLogin(username)
        return FurUserDetails.create(user)
    }

}