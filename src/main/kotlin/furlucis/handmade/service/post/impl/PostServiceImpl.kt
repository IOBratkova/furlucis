package furlucis.handmade.service.post.impl

import furlucis.handmade.entity.Post
import furlucis.handmade.repositories.PostRepo
import furlucis.handmade.service.post.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostServiceImpl @Autowired constructor(
        private val postRepo: PostRepo
): PostService {
        override fun save(post: Post): Post {
                post.created = Date()
                post.updated = post.created
                return postRepo.save(post)
        }
}