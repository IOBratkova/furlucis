package furlucis.handmade.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ListDto <I> (
    @JsonProperty("list")
    val list: List<I>
)