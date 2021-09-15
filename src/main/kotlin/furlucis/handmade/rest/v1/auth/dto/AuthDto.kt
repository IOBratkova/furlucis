package furlucis.handmade.rest.v1.auth.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

class AuthDto (
    @JsonProperty("login")
    @NotNull
    val login: String,

    @JsonProperty("password")
    @NotNull
    val password: String
)