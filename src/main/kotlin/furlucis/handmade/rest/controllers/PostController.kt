package furlucis.handmade.rest.controllers

import furlucis.handmade.rest.dto.IdentifierDto
import furlucis.handmade.rest.dto.ListDto
import furlucis.handmade.rest.dto.NewPostDto
import furlucis.handmade.rest.dto.PostDto
import furlucis.handmade.rest.mappers.PostMapper
import furlucis.handmade.service.handmadetag.HandmadeTagService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/post")
class PostController @Autowired constructor(
    private val handmadeTagService: HandmadeTagService,
    private val postMapper: PostMapper
) {

    @PostMapping("/add")
    fun addNewPost(@RequestBody request: NewPostDto) : IdentifierDto<Long> {
        val post = postMapper.toPost(request)
        val result = handmadeTagService.save(post)
        return IdentifierDto(result.id!!)
    }

    @GetMapping("/user/{userId}")
    fun getUsersPosts(@PathVariable userId: Long) : ListDto<PostDto> {
        val posts = handmadeTagService.findAllByUserId(userId)
        return ListDto(postMapper.toListPostDto(posts))
    }

    @GetMapping("/{postId}")
    fun getPostById(@PathVariable postId: Long) : PostDto {
        val post = handmadeTagService.findById(postId)
        return postMapper.toPostDto(post)
    }
}