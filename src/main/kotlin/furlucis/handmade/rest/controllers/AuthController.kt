package furlucis.handmade.rest.controllers

import furlucis.handmade.enums.RoleEnum
import furlucis.handmade.rest.dto.AuthRequestDto
import furlucis.handmade.rest.dto.AuthTokenDto
import furlucis.handmade.rest.dto.RegisterResponceDto
import furlucis.handmade.rest.dto.RegisterRequestDto
import furlucis.handmade.rest.mappers.AuthMapper
import furlucis.handmade.security.provider.JwtProvider
import furlucis.handmade.service.user.UserCridentialsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/auth")
class AuthController @Autowired constructor(
        private val userCridentialsService: UserCridentialsService,
        private val authMapper: AuthMapper,
        private val jwtProvider: JwtProvider
) {
    @PostMapping("/registration")
    fun register(@RequestBody request: RegisterRequestDto): RegisterResponceDto {
//        val userCredentials = authMapper.toUserCredential(request, RoleEnum.USER)
        val userCredentials = authMapper.toUserCredential(request)
        return RegisterResponceDto(userCridentialsService.save(userCredentials).id!!)
    }

    @PostMapping("/auth")
    fun auth (@RequestBody request: AuthRequestDto) : AuthTokenDto {
        val userCredentials = userCridentialsService.finsUserByCredentialDate(
            request.email,
            request.username,
            request.password
        )
        val token = jwtProvider.generateToken(userCredentials.username)
        return AuthTokenDto(token)
    }
}