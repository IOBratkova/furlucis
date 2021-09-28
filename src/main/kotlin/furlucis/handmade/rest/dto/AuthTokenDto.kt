package furlucis.handmade.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty

class AuthTokenDto (
    @JsonProperty("token")
    val token: String,

    @JsonProperty("id")
    val id: Long
)