package furlucis.handmade.rest.controllers

import furlucis.handmade.rest.dto.IdentifierDto
import furlucis.handmade.rest.dto.ListDto
import furlucis.handmade.rest.dto.NewPostDto
import furlucis.handmade.rest.dto.PostDto
import furlucis.handmade.rest.mappers.PostMapper
import furlucis.handmade.service.handmadetag.HandmadeTagService
import furlucis.handmade.service.post.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/post")
class PostController @Autowired constructor(
    private val postService: PostService,
    private val postMapper: PostMapper
) {

    @PostMapping("/add")
    fun addNewPost(@RequestBody request: NewPostDto) : IdentifierDto<Long> {
        val post = postMapper.toPost(request)
        val result = postService.save(post)
        return IdentifierDto(result.id!!)
    }

    @GetMapping("/user/{userId}")
    fun getUsersPosts(@PathVariable userId: Long) : ListDto<PostDto> {
        val posts = postService.findAllByUserId(userId)
        return ListDto(postMapper.toListPostDto(posts))
    }

    @GetMapping("/{postId}")
    fun getPostById(@PathVariable postId: Long) : PostDto {
        val post = postService.findById(postId)
        return postMapper.toPostDto(post)
    }
}