package furlucis.handmade.controllers

import furlucis.handmade.controllers.dto.RegisterResponceDto
import furlucis.handmade.controllers.dto.RegisterRequestDto
import furlucis.handmade.controllers.mappers.AuthMapper
import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.enums.Role
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
        private val authMapper: AuthMapper
) {
    @PostMapping("/registration")
    fun register(@RequestBody request: RegisterRequestDto): RegisterResponceDto {
        val userCredentials = authMapper.toUserCredential(request)
        return RegisterResponceDto(userCridentialsService.save(userCredentials).id!!)
    }
}