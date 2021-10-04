package furlucis.handmade.rest.mappers

import furlucis.handmade.entity.Post
import furlucis.handmade.rest.dto.NewPostDto
import furlucis.handmade.rest.dto.PostDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [UserMapper::class, HandmadeTagMapper::class])
interface PostMapper {

    @Mapping(source = "userInfo.id", target = "userInfoId")
    fun toPostDto(post: Post) : PostDto

    @Mapping(target = "userInfo.id", source = "userInfoId")
    fun toPost(newPostDto: NewPostDto) : Post

    fun toListPostDto(posts: List<Post>) : List<PostDto>

}