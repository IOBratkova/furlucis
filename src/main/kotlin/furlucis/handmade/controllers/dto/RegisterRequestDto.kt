package furlucis.handmade.controllers.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

class RegisterRequestDto (
    @NotNull
    @JsonProperty("username")
    val username: String,

    @NotNull
    @JsonProperty("password")
    val password: String,

    @NotNull
    @JsonProperty("email")
    val email: String
)