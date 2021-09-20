package furlucis.handmade.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

class RegisterResponceDto (
    @NotNull
    @JsonProperty("id")
    val id: Long
)