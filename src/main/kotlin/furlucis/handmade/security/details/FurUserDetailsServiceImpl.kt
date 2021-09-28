package furlucis.handmade.security.details

import furlucis.handmade.security.UserPrincipal
import furlucis.handmade.service.user.UserCredentialsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class FurUserDetailsServiceImpl @Autowired constructor(
        private val userCredentialsService: UserCredentialsService
) : FurUserDetailService, UserDetailsService {

    override fun loadUserById(id: Long): UserDetails {
        val user = userCredentialsService.findById(id)
        return UserPrincipal.create(user)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userCredentialsService.findByUsername(username)
        return UserPrincipal.create(user)
    }

}