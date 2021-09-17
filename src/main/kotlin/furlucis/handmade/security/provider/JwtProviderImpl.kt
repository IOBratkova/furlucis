package furlucis.handmade.security.provider

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*

@Service
class JwtProviderImpl : JwtProvider {
    @Value("$(jwt.secret)")
    private val jwtSecret: String = ""

//    @Value("$(jwt.expiration)")
    private val jwtExpiration: Int = 604800000


    override fun generateToken(username: String): String {
        val now = Date()
        val expiryDate = Date(now.time + jwtExpiration)
        val jwtByte = jwtSecret.toByteArray(StandardCharsets.UTF_8)

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(jwtByte), SignatureAlgorithm.HS512)
                .compact()
    }

    override fun validateToken(token: String): Boolean {
        val jwtByte = jwtSecret.toByteArray(StandardCharsets.UTF_8)
        val jwtParser =  Jwts.parserBuilder().setSigningKey(jwtByte)
        return try {
            jwtParser.build().parseClaimsJws(token)
            true
        } catch (ex: Exception) {
            false
        }
    }

    override fun getUserIdFromToken(token: String): Long {
        val jwtByte = jwtSecret.toByteArray(StandardCharsets.UTF_8)
        val jwtParser =  Jwts.parserBuilder().setSigningKey(jwtByte)
        var claims = jwtParser.build().parseClaimsJws(token).body
        return claims.subject.toLong()
    }

}