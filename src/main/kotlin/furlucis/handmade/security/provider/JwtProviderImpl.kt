package furlucis.handmade.security.provider

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtProviderImpl : JwtProvider {
    @Value("$(jwt.secret)")
    private val jwtSecret: String = ""

//    @Value("$(jwt.expiration)")
    private val jwtExpiration: Int = 604800000

    private val secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)


    override fun generateToken(username: String): String {
        val now = Date()
        val expiryDate = Date(now.time + jwtExpiration)

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact()
    }

    override fun validateToken(token: String): Boolean {
        val jwtParser =  Jwts.parserBuilder().setSigningKey(secretKey)
        return try {
            jwtParser.build().parseClaimsJws(token)
            true
        } catch (ex: Exception) {
            false
        }
    }

    override fun getUserIdFromToken(token: String): Long {
        val jwtParser =  Jwts.parserBuilder().setSigningKey(secretKey)
        var claims = jwtParser.build().parseClaimsJws(token).body
        return claims.subject.toLong()
    }

    override fun getUsernameFromToken(token: String): String {
        val jwtParser =  Jwts.parserBuilder().setSigningKey(secretKey)
        var claims = jwtParser.build().parseClaimsJws(token).body
        return claims.subject
    }

}