package furlucis.handmade.controllers.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

class AuthRequestDto (
    @JsonProperty("username")
    val username: String?,

    @JsonProperty("email")
    val email: String?,

    @NotNull
    @JsonProperty("password")
    val password: String
)