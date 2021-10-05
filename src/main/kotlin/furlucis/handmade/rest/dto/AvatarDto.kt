package furlucis.handmade.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty

class AvatarDto (
    @JsonProperty("avatar")
    val avatar: String,

    @JsonProperty("id")
    var id: Long
)