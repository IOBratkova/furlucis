package furlucis.handmade.security.filter

import furlucis.handmade.security.details.FurUserDetailService
import furlucis.handmade.security.provider.JwtProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Service
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class JwtFilter @Autowired constructor(
        private val jwtProvider: JwtProvider,
        private val furUserDetailService: FurUserDetailService
): OncePerRequestFilter() {

    fun getJwtFromRequest(request: HttpServletRequest): String? {
        return try {
            request.getHeader("Authorization")
        } catch (ex: NullPointerException) {
            null
        }
    }

    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
    ) {
        val jwt = getJwtFromRequest(request)
        if (!jwt.isNullOrEmpty() && jwtProvider.validateToken(jwt)) {
            val username = jwtProvider.getUsernameFromToken(jwt)
            val furUserDetails = furUserDetailService.loadUserByUsername(username)
            val authentication = UsernamePasswordAuthenticationToken(furUserDetails, null, furUserDetails.authorities)
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

}