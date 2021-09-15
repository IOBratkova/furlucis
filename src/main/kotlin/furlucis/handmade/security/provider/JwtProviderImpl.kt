package furlucis.handmade.security.provider

import furlucis.handmade.security.FurUserDetails
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtProviderImpl : JwtProvider {
    @Value("\${jwt.secret}")
    private val jwtSecret: String? = null

    @Value("\${jwt.expiration}")
    private val jwtExpiration: Int = 0

    override fun generateToken(login: String): String {
        val now = Date()
        val expiryDate = Date(now.time + jwtExpiration)
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
    }

    override fun generateToken(authentication: Authentication): String {
        val now = Date()
        val expiryDate = Date(now.time + jwtExpiration)
        val user = authentication.principal as FurUserDetails
        return Jwts.builder()
                .setSubject(user.username)
                .setIssuedAt(Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
    }

    override fun validateToken(authToken: String): Boolean {
        return try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken)
            true
        } catch (ex: Exception) {
            false
        }
    }

    override fun getLoginFromToken(token: String): String {
        val claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .body
        return claims.subject
    }
}