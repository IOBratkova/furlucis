package furlucis.handmade.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

class UserInfoDto (
    @JsonProperty("firstName")
    val firstName: String?,

    @JsonProperty("secondName")
    val secondName: String?,

    @JsonProperty("patronymic")
    val patronymic: String?,

    @JsonProperty("description")
    val description: String?,

    @JsonProperty("avatar")
    val avatar: String?,

    @JsonProperty("user_credentials_id")
    val userCredentials: Long?
)