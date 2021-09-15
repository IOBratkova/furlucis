package furlucis.handmade.security.details

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.*

interface FurUserDetailsService : UserDetailsService {
    fun loadUserById(id: UUID) : UserDetails?
}