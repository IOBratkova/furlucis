package furlucis.handmade.rest.v1.auth

import furlucis.handmade.service.auth.AuthService
import furlucis.handmade.service.user.UserService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController constructor(
        private val userService: UserService,
        private val authService: AuthService
){
    f
}