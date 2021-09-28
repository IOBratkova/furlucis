package furlucis.handmade.rest.mappers

import furlucis.handmade.entity.UserInfo
import furlucis.handmade.rest.dto.UserInfoDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface UserMapper {

    @Mapping(source = "userCredentials", target = "userCredentials.id")
    fun toUserInfo(userInfoDto: UserInfoDto) : UserInfo

    @InheritInverseConfiguration
    fun toUserInfoDto(userInfo: UserInfo) : UserInfoDto


}