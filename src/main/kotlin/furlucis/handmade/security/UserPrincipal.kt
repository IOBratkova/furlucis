package furlucis.handmade.security

import furlucis.handmade.entity.UserCredentials
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal constructor(
        private val username: String,
        private val password: String,
        private val id: Long
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        fun create(user: UserCredentials) : UserPrincipal {
            return UserPrincipal(user.username, user.password, user.id!!)
        }

        fun create (login: String, password: String): FurUserDetails {
            return FurUserDetails(login, password)
        }
    }

}