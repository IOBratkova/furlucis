package furlucis.handmade.controllers.mappers

interface GenericMapper<E, D> {
    fun toDto(e: E) : D
    fun toEntity(d: D) : E
    fun toListDto(es : List<E>) : List<D>
    fun toListEntity(ds : List<D>) : List<E>
}