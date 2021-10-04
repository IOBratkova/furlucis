package furlucis.handmade.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

class PostDto (

    @JsonProperty("id")
    val id: Long,

    @JsonProperty("title")
    val title: String,

    @JsonProperty("text")
    val text: String,

    @JsonProperty("type")
    val type: String,

    @JsonProperty("userInfoId")
    val userInfoId: Long

)