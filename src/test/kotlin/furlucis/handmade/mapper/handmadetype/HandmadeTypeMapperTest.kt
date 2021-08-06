package furlucis.handmade.mapper.handmadetype

import furlucis.handmade.HandmadeApplicationTests
import furlucis.handmade.dto.handmadetype.HandmadeTypeDto
import furlucis.handmade.entity.HandmadeType
import furlucis.handmade.mappers.HandmadeTypeMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.Assertions.assertTrue


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
    fun `Will map to entity`(){
        val entity = createEntity(1L)

        val dto = handmadeTypeMapper.toDto(entity)

        assertTrue(dto.id == 1L)
        assertTrue(dto.description == entity.description)
        assertTrue(dto.title == entity.title)
    }

    @Test
    fun `Will map to dto`(){
        val dto = createDto(1L)

        val entity = handmadeTypeMapper.toEntity(dto)

        assertTrue(dto.id == 1L)
        assertTrue(dto.description == entity.description)
        assertTrue(dto.title == entity.title)
    }


}