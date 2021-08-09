package furlucis.handmade.service.user

import furlucis.handmade.dto.user.UserDto
import furlucis.handmade.entity.User
import furlucis.handmade.mappers.UserMapper
import furlucis.handmade.repositories.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepo: UserRepo,
    private val userMapper: UserMapper
): UserService
{

    override fun save(user: User): UUID? {
        return userRepo.save(user).id
    }

    override fun saveDto(userDto: UserDto): UUID? {
        return save(userMapper.toEntity(userDto))
    }

    override fun getById(id: UUID): User {
        TODO("Not yet implemented")
    }

    override fun getDtoById(id: UUID): UserDto {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<User> {
        TODO("Not yet implemented")
    }

    override fun getAllDto(): List<UserDto> {
        TODO("Not yet implemented")
    }

    override fun update(user: User) {
        TODO("Not yet implemented")
    }

    override fun update(userDto: UserDto) {
        TODO("Not yet implemented")
    }

    override fun delete(id: UUID) {
        TODO("Not yet implemented")
    }

    override fun getByLogin(login: String): User {
        return userRepo.findByLogin(login)
    }
}