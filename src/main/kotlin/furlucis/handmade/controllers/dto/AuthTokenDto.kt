package furlucis.handmade.controllers.dto

import com.fasterxml.jackson.annotation.JsonProperty

class AuthTokenDto (
    @JsonProperty("token")
    val token: String
)