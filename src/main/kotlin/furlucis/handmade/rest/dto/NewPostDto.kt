package furlucis.handmade.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty

class NewPostDto (

    @JsonProperty("id")
    val id: Long? = null,

    @JsonProperty("title")
    val title: String? = null,

    @JsonProperty("text")
    val text: String? = null,

    @JsonProperty("type")
    val type: String? = null,

    @JsonProperty("userInfoId")
    val userInfoId: Long? = null,

    @JsonProperty("tags")
    val tags: List<HandmadeTagDto>? = null

)