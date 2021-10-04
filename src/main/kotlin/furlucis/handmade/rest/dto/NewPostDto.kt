package furlucis.handmade.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

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
    val userInfoId: Long? = null

)