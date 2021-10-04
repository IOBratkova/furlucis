package furlucis.handmade.rest.mappers

import furlucis.handmade.entity.Post
import furlucis.handmade.entity.UserCredentials
import furlucis.handmade.entity.UserInfo
import furlucis.handmade.rest.dto.NewPostDto
import furlucis.handmade.rest.dto.PostDto
import furlucis.handmade.rest.dto.UserInfoDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", uses = [UserMapper::class])
interface PostMapper {

    @Mapping(source = "userInfo.id", target = "userInfoId")
    fun toPostDto(post: Post) : PostDto

    @Mapping(target = "userInfo.id", source = "userInfoId")
    fun toPost(newPostDto: NewPostDto) : Post

    fun toListPostDto(posts: List<Post>) : List<PostDto>

}