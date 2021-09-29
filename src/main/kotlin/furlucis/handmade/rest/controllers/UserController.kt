package furlucis.handmade.rest.controllers

import furlucis.handmade.rest.dto.IdentifierDto
import furlucis.handmade.rest.dto.UserInfoDto
import furlucis.handmade.rest.mappers.UserMapper
import furlucis.handmade.service.user.UserCredentialsService
import furlucis.handmade.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/user")
class UserController @Autowired constructor(
    private val userMapper: UserMapper,
    private val userService: UserService,
    private val userCredentialService: UserCredentialsService
) {

    @PostMapping("/save")
    fun saveUserInfo(@RequestBody userInfoDto: UserInfoDto) : IdentifierDto<Long> {
        val credentials = userCredentialService.findById(userInfoDto.userCredentialsId)
        val userInfo = userMapper.toUserInfo(userInfoDto, credentials)
        return IdentifierDto(userService.save(userInfo).id!!)
    }

}