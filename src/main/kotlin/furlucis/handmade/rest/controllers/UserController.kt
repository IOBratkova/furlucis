package furlucis.handmade.rest.controllers

import furlucis.handmade.rest.dto.AvatarDto
import furlucis.handmade.rest.dto.IdentifierDto
import furlucis.handmade.rest.dto.UserInfoDto
import furlucis.handmade.rest.mappers.UserMapper
import furlucis.handmade.service.image.ImageService
import furlucis.handmade.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("v1/user")
class UserController @Autowired constructor(
    private val userMapper: UserMapper,
    private val userService: UserService,
    private val imageService: ImageService
) {

    @PostMapping("/save")
    fun saveUserInfo(@RequestBody userInfoDto: UserInfoDto) : IdentifierDto<Long> {
        val credentials = userService.findByUserCredentialsId(userInfoDto.userCredentialsId).userCredentials!!
        val userInfo = userMapper.toUserInfo(userInfoDto, credentials)
        return IdentifierDto(userService.save(userInfo).id!!)
    }

    @PostMapping("/{userId}/avatar")
    fun saveAvatar(@RequestParam multiPartFile: MultipartFile, @PathVariable userId: Long) : AvatarDto {
        val res = userService.saveAvatar(multiPartFile, userId)
        return AvatarDto(res.avatar!!, userId)
    }


}