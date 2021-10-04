package furlucis.handmade.rest.mappers

import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.UserInfo
import furlucis.handmade.enums.RoleEnum
import furlucis.handmade.rest.dto.RegisterRequestDto
import furlucis.handmade.rest.dto.UserCredentialsDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface AuthMapper {

    @Mapping(target = "role", source = "roleEnum")
    fun toUserCredential(requestDto: RegisterRequestDto, roleEnum: RoleEnum) : UserCredentials

    fun toUserCredential(requestDto: RegisterRequestDto) : UserCredentials

    fun toUserCredentialsDto(userCredentials: UserCredentials) : UserCredentialsDto

    fun toUserCredentials(userCredentialsDto: UserCredentialsDto) : UserCredentials

}