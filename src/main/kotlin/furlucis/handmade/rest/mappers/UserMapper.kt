package furlucis.handmade.rest.mappers

import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.UserInfo
import furlucis.handmade.rest.dto.UserInfoDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", uses = [AuthMapper::class])
interface UserMapper {

    @Mapping(source = "userCredentialsId", target = "userCredentials.id")
    fun toUserInfo(userInfoDto: UserInfoDto) : UserInfo

    @Mapping(source = "userCredentials.id", target = "userCredentialsId")
    fun toUserInfoDto(userInfo: UserInfo) : UserInfoDto

    @Mappings(
        Mapping(source = "userCredentials", target = "userCredentials"),
        Mapping(source = "userInfoDto.firstName", target = "firstName"),
        Mapping(source = "userInfoDto.secondName", target = "secondName"),
        Mapping(source = "userInfoDto.patronymic", target = "patronymic"),
        Mapping(source = "userInfoDto.description", target = "description"),
        Mapping(source = "userInfoDto.avatar", target = "avatar"),
        Mapping(source = "userInfoDto.id", target = "id")
    )
    fun toUserInfo(userInfoDto: UserInfoDto, userCredentials: UserCredentials) : UserInfo

}