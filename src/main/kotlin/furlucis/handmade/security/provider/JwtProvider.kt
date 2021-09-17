package furlucis.handmade.security.provider

interface JwtProvider {

    fun generateToken(username: String) : String

    fun validateToken(token: String): Boolean

    fun getUserIdFromToken(token: String) : Long
}