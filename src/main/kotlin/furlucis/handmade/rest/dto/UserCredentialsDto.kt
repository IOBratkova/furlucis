package furlucis.handmade.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty

class UserCredentialsDto (
    @JsonProperty("id")
    val id: Long,

    @JsonProperty("username")
    val username: String,

    @JsonProperty("email")
    val email: String,

    @JsonProperty("password")
    val password: String,

    @JsonProperty("role")
    val role: String
)