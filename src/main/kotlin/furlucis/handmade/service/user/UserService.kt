package furlucis.handmade.service.user

import furlucis.handmade.dto.user.UserDto
import furlucis.handmade.entity.User
import java.util.*

interface UserService {
    fun save(user: User) : UUID?
    fun getById(id: UUID) : User
    fun getAll() : List<User>
    fun update(user: User)
    fun update(userDto: UserDto)
    fun delete(id: UUID)
    fun existsByLogin(login: String) : Boolean
    fun getByLogin(login: String) : User
    fun getByLoginAndPassword(login: String, password: String) : User?
}