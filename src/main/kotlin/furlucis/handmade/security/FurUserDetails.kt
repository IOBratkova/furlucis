package furlucis.handmade.security

import furlucis.handmade.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class FurUserDetails constructor(
        private val login: String,
        private val password: String
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return login
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    companion object {
        fun create (user: User): FurUserDetails {
            return FurUserDetails(user.login, user.password)
        }

        fun create (login: String, password: String): FurUserDetails {
            return FurUserDetails(login, password)
        }
    }

}