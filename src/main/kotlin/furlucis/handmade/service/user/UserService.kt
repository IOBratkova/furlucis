package furlucis.handmade.service.user

import furlucis.handmade.dto.user.UserDto
import furlucis.handmade.entity.User
import java.util.*

interface UserService {
    fun save(user: User) : UUID?
    fun saveDto(userDto: UserDto) : UUID?
    fun getById(id: UUID) : User
    fun getDtoById(id: UUID): UserDto
    fun getAll() : List<User>
    fun getAllDto() : List<UserDto>
    fun update(user: User)
    fun update(userDto: UserDto)
    fun delete(id: UUID)
    fun getByLogin(login : String) : User
}