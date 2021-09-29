package furlucis.handmade.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

class IdentifierDto <I> (
    @JsonProperty("id")
    val id: I
)