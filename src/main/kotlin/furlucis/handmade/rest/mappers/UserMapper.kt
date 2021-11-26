package furlucis.handmade.rest.mappers

import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.User
import furlucis.handmade.rest.dto.UserDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", uses = [AuthMapper::class])
interface UserMapper {

    @Mapping(source = "userCredentialsId", target = "userCredentials.id")
    fun toUser(userDto: UserDto) : User

    @Mapping(source = "userCredentials.id", target = "userCredentialsId")
    fun toUserDto(user: User) : UserDto

    @Mappings(
        Mapping(source = "userCredentials", target = "userCredentials"),
        Mapping(source = "userDto.firstName", target = "firstName"),
        Mapping(source = "userDto.secondName", target = "secondName"),
        Mapping(source = "userDto.patronymic", target = "patronymic"),
        Mapping(source = "userDto.description", target = "description"),
        Mapping(target = "avatar", ignore = true),
        Mapping(source = "userCredentials.user.id", target = "id")
    )
    fun toUser(userDto: UserDto, userCredentials: UserCredentials) : User

    fun toUser(userCredentials: UserCredentials) : User

}