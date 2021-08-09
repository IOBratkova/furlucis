package furlucis.handmade.mappers

import furlucis.handmade.dto.user.UserDto
import furlucis.handmade.entity.User
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface  UserMapper : GenericMapper<User, UserDto> {
}
