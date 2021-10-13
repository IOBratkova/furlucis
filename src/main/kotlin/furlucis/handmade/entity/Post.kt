package furlucis.handmade.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "post")
data class Post(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "title", length = 30)
    var title: String? = null,

    @Column(name = "text", length = 1000)
    var text: String? = null,

    @Column(name = "type")
    var type: String? = null,

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    var user: User? = null,

    @Column(name = "created")
    var created: Date? = null,

    @Column(name = "updated")
    var updated: Date? = null,

    @ManyToMany
    @JoinTable(
        name = "post_tags",
        joinColumns = [JoinColumn(name = "post_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "handmade_tag_id", referencedColumnName = "id")]
    )
    var tags: Set<HandmadeTag>? = null,
)