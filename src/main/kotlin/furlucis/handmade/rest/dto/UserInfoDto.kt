package furlucis.handmade.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty

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

    @JsonProperty("id")
    var id: Long?,

    @JsonProperty("userCredentialsId")
    val userCredentialsId: Long
)