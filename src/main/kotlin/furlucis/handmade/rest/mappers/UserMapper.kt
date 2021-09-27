package furlucis.handmade.rest.mappers

import furlucis.handmade.rest.dto.RegisterRequestDto
import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.UserInfo
import furlucis.handmade.enums.RoleEnum
import furlucis.handmade.rest.dto.UserInfoDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface UserMapper {
    @Mapping(source = "userCredentials", target = "userCredentials.id")
    fun toUserInfo(userInfoDto: UserInfoDto) : UserInfo
}