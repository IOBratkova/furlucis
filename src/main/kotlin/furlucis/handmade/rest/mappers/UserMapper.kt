package furlucis.handmade.rest.mappers

import furlucis.handmade.entity.UserInfo
import furlucis.handmade.rest.dto.UserInfoDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface UserMapper {

    @Mapping(source = "userCredentialsId", target = "userCredentials.id")
    fun toUserInfo(userInfoDto: UserInfoDto) : UserInfo

    @Mapping(source = "userCredentials.id", target = "userCredentialsId")
    fun toUserInfoDto(userInfo: UserInfo) : UserInfoDto

}