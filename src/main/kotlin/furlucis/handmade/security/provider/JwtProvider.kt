package furlucis.handmade.security.provider

import org.springframework.security.core.Authentication

interface JwtProvider {
    fun generateToken(login: String): String

    fun generateToken(authentication: Authentication): String

    fun validateToken(authToken: String): Boolean

    fun getLoginFromToken(token: String): String
}