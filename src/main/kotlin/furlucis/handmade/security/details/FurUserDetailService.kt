package furlucis.handmade.security.details

import org.springframework.security.core.userdetails.UserDetails

interface FurUserDetailService {
    fun loadUserById(id: Long) : UserDetails
    fun loadUserByUsername(username: String) : UserDetails
}