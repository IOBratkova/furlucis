package furlucis.handmade.rest.v1.auth.dto

import com.fasterxml.jackson.annotation.JsonProperty

class TokenDto (
        @field:JsonProperty("accessToken")
        @param:JsonProperty("accessToken")
        val accessToken: String
)