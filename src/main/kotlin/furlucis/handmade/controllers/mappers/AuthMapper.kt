package furlucis.handmade.controllers.mappers

import furlucis.handmade.controllers.dto.RegisterRequestDto
import furlucis.handmade.entity.UserCredentials
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AuthMapper {

    fun toUserCredential(requestDto: RegisterRequestDto) : UserCredentials

}