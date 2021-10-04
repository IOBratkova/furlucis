package furlucis.handmade.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

class HandmadeTagDto (

    @JsonProperty("id")
    val id: Long,

    @JsonProperty("title")
    val title: String? = null,

    @JsonProperty("description")
    val description: String? = null

)