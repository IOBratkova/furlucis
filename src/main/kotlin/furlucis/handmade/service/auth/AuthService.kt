package furlucis.handmade.service.auth

import furlucis.handmade.entity.User
import furlucis.handmade.security.FurUserDetails

interface AuthService {
    fun authenticate(credentials: User): String

    fun authenticate(credentials: FurUserDetails): String
}