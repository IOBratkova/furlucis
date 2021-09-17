package furlucis.handmade.service.user

import furlucis.handmade.entity.UserCredentials

interface UserCridentialsService {
    fun findByUsername(username: String) : UserCredentials
    fun findByEmail(email: String) : UserCredentials
    fun findById(id: Long) : UserCredentials
    fun findByUsernameAndPassword(username: String, password: String) : UserCredentials
    fun findByEmilAndPassword(email: String, password: String) : UserCredentials
    fun finsUserByCredentialDate(email: String?, username: String?, password: String) : UserCredentials
    fun save(userCredentials: UserCredentials) : UserCredentials
//    fun getById(id: Long) : UserCridentials
//    fun getAll() : List<UserCridentials>
//    fun update(userCridentials: UserCridentials) : Long
//    fun delete(id: Long)
}