package furlucis.handmade.service.auth

import furlucis.handmade.entity.User
import furlucis.handmade.security.provider.JwtProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl @Autowired constructor(
        private val jwtProvider: JwtProvider,
        private val authenticationManager: AuthenticationManager
) : AuthService {
    override fun authenticate(credentials: User): String {
        val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        credentials.login,
                        credentials.password
                )
        )
        SecurityContextHolder.getContext().authentication = authentication
        return jwtProvider.generateToken(authentication)
    }
}