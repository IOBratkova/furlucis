package furlucis.handmade.service.auth

import furlucis.handmade.entity.User

interface AuthService {
    fun authenticate(credentials: User): String
}