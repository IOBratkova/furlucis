package furlucis.handmade.service.post

import furlucis.handmade.entity.Post
import furlucis.handmade.exceptions.PostIdException
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

        override fun findAllByUserId(id: Long): List<Post> {
                return postRepo.findAllByUserId(id)
        }

        override fun findById(id: Long) : Post {
                return postRepo.findById(id).orElseThrow{
                        throw PostIdException(id)
                }
        }
}