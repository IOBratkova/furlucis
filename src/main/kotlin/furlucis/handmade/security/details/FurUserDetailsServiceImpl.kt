package furlucis.handmade.security.details

import furlucis.handmade.security.UserPrincipal
import furlucis.handmade.service.user.UserCridentialsService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*

@Service
class FurUserDetailsServiceImpl @Autowired constructor(
        private val userCridentialsService: UserCridentialsService
) : FurUserDetailService, UserDetailsService {

    override fun loadUserById(id: Long): UserDetails {
        val user = userCridentialsService.findById(id)
        return UserPrincipal.create(user)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userCridentialsService.findByUsername(username)
        return UserPrincipal.create(user)
    }

}