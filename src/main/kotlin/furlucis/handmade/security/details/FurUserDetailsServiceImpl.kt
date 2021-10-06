package furlucis.handmade.security.details

import furlucis.handmade.security.UserPrincipal
import furlucis.handmade.service.registration.RegistrationService
import furlucis.handmade.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class FurUserDetailsServiceImpl @Autowired constructor(
        private val registrationService: RegistrationService
) : FurUserDetailService, UserDetailsService {

    override fun loadUserById(id: Long): UserDetails {
        val user = registrationService.findById(id)
        return UserPrincipal.create(user)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = registrationService.findByUsername(username)
        return UserPrincipal.create(user)
    }

}