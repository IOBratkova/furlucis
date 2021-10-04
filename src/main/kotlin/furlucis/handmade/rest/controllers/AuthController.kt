package furlucis.handmade.rest.controllers

import furlucis.handmade.enums.RoleEnum
import furlucis.handmade.rest.dto.AuthRequestDto
import furlucis.handmade.rest.dto.AuthTokenDto
import furlucis.handmade.rest.dto.RegisterResponceDto
import furlucis.handmade.rest.dto.RegisterRequestDto
import furlucis.handmade.rest.mappers.AuthMapper
import furlucis.handmade.security.provider.JwtProvider
import furlucis.handmade.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/auth")
class AuthController @Autowired constructor(
    private val userService: UserService,
    private val authMapper: AuthMapper,
    private val jwtProvider: JwtProvider
) {
    @PostMapping("/registration")
    fun register(@RequestBody request: RegisterRequestDto): RegisterResponceDto {
        val userCredentials = authMapper.toUserCredential(request, RoleEnum.USER)
        return RegisterResponceDto(userService.save(userCredentials).id!!)
    }

    @PostMapping("/auth")
    fun auth (@RequestBody request: AuthRequestDto) : AuthTokenDto {
        val userCredentials = userService.findUserByCredentialDate(
            request.email,
            request.username,
            request.password
        )
        val token = jwtProvider.generateToken(userCredentials.username)
        return AuthTokenDto(token, userCredentials.id!!)
    }
}