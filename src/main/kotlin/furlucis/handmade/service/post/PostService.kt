package furlucis.handmade.service.post

import furlucis.handmade.entity.Post


interface PostService {
    fun save(post: Post) : Post
    fun findAllByUserId(id: Long) : List<Post>
    fun findById(id: Long) : Post
}