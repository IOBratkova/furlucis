package furlucis.handmade.rest.controllers

import furlucis.handmade.rest.dto.IdentifierDto
import furlucis.handmade.rest.dto.NewPostDto
import furlucis.handmade.rest.mappers.PostMapper
import furlucis.handmade.service.post.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}