package furlucis.handmade.rest.controllers

import furlucis.handmade.rest.dto.UserInfoDto
import furlucis.handmade.rest.mappers.UserMapper
import furlucis.handmade.service.user.UserCredentialsService
import furlucis.handmade.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/user")
class UserController @Autowired constructor(
    private val userMapper: UserMapper,
    private val userService: UserService,
    private val userCredentialService: UserCredentialsService
) {

    @PostMapping("/save")
    fun saveUserInfo(@RequestBody userInfoDto: UserInfoDto) : Long {
        var userInfo = userMapper.toUserInfo(userInfoDto)
        userInfo.userCredentials = userCredentialService.findById(userInfoDto.userCredentials)
        return userService.save(userInfo).id!!
    }

}