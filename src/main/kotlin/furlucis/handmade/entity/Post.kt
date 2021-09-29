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

    @Column(name = "type", length = 1000)
    var type: String? = null,

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    var userInfo: UserInfo? = null,

    @Column(name = "created")
    var created: Date? = null,

    @Column(name = "updated")
    var updated: Date? = null
)