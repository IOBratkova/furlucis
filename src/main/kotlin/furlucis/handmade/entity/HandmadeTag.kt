package furlucis.handmade.entity

import javax.persistence.*

@Entity
@Table(name = "handmade_tag")
data class HandmadeTag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "title", length = 30)
    var title: String? = null,

    @Column(name = "description", length = 1000)
    var description: String? = null,

    @ManyToMany(mappedBy = "tags")
    var posts: Set<Post>? = null
)