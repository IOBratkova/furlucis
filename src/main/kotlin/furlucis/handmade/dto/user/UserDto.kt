package furlucis.handmade.dto.user

import furlucis.handmade.enums.Role
import java.util.*

data class UserDto (
    var id: UUID? = null,
    var login: String = "",
    var firstName: String = "",
    var secondName: String? = "",
    var patronymic: String? = "",
    var description: String? = "",
    var password: String = "",
    var email: String = "",
    var avatar: String? = null,
    var role: String = Role.UNKNOWN.text
)