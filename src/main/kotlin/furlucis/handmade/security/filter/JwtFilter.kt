package furlucis.handmade.security.filter

import javax.servlet.http.HttpServletRequest

interface JwtFilter {
    fun getJwtFromRequest(request: HttpServletRequest): String?
}