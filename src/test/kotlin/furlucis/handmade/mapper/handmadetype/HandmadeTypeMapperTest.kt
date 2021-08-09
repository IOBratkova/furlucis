package furlucis.handmade.mapper.handmadetype

import furlucis.handmade.HandmadeApplicationTests
import furlucis.handmade.dto.handmadetype.HandmadeTypeDto
import furlucis.handmade.entity.HandmadeType
import furlucis.handmade.mappers.HandmadeTypeMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.Assertions.assertTrue
import java.util.stream.IntStream
import kotlin.streams.toList


class HandmadeTypeMapperTest : HandmadeApplicationTests() {

    @Autowired
    private lateinit var handmadeTypeMapper : HandmadeTypeMapper

    fun createEntity(id: Long) : HandmadeType {
        return HandmadeType(id, "text$id", "desc$id")
    }

    fun createDto(id: Long) : HandmadeTypeDto {
        return HandmadeTypeDto(id, "text$id", "desk$id")
    }

    @Test
    fun `Will map entity to dto`(){
        val entity = createEntity(1L)

        val dto = handmadeTypeMapper.toDto(entity)

        assertTrue(dto.id == 1L)
        assertTrue(dto.description == entity.description)
        assertTrue(dto.title == entity.title)
    }

    @Test
    fun `Will map dto to entity`(){
        val dto = createDto(1L)

        val entity = handmadeTypeMapper.toEntity(dto)

        assertTrue(dto.id == 1L)
        assertTrue(dto.description == entity.description)
        assertTrue(dto.title == entity.title)
    }


    @Test
    fun `Will map list dto to entity`(){
        val entities = IntStream.range(0, 10).mapToObj { i -> createEntity(i.toLong()) }.toList()
        val dtos = handmadeTypeMapper.toListDto(entities)
        dtos.stream().forEach { d -> assertTrue(d != null) }
    }

    @Test
    fun `Will map list entity to dto`(){
        val dtos = IntStream.range(0, 10).mapToObj { i -> createDto(i.toLong()) }.toList()
        val entityes = handmadeTypeMapper.toListEntity(dtos)
        entityes.stream().forEach { d -> assertTrue(d != null) }
    }

}