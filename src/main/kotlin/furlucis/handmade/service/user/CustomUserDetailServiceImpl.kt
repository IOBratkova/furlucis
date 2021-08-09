package furlucis.handmade.service.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetailServiceImpl @Autowired constructor(
        private val userService: UserService
): CustomUserDetailService {
    override fun loadUserByUsername(username: String?): UserDetails {
        if(username == null){
            throw NullPointerException()
        }
        val user = userService.getByLogin(username)

        val authorities = ArrayList<GrantedAuthority>()
//        user.roles!!.forEach { role -> authorities.add(SimpleGrantedAuthority(role.roleName)) }

        return User(
                user.login,
                user.password,
                authorities
        )
    }
}