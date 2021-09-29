package furlucis.handmade.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty

class IdentifierDto <I> (
    @JsonProperty("id")
    val id: I
)