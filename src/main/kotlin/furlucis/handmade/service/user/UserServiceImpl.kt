package furlucis.handmade.service.user

import furlucis.handmade.dto.user.UserDto
import furlucis.handmade.entity.User
import furlucis.handmade.mappers.UserMapper
import furlucis.handmade.repositories.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepo: UserRepo,
    private val passwordEncoder: PasswordEncoder
): UserService
{

    override fun save(user: User): UUID? {
        var password = passwordEncoder.encode(user.password)
        user.password = password
        return userRepo.save(user).id
    }

    override fun getById(id: UUID): User {
        if (userRepo.existsById(id)) {
            return userRepo.getById(id)
        }
        throw NullPointerException()
    }
    override fun getAll(): List<User> {
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
        if (existsByLogin(login))
            return userRepo.findByLogin(login)
        throw NullPointerException()
    }

    override fun getByLoginAndPassword(login: String, password: String): User? {
        val user = getByLogin(login)
        if (passwordEncoder.matches(password, user.password)) {
            return user;
        }
        throw NullPointerException()
    }

    override fun existsByLogin(login: String) : Boolean {
        return userRepo.existsByLogin(login)
    }

}