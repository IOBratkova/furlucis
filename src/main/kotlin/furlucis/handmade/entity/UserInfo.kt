package furlucis.handmade.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_info")
@EntityListeners(AuditingEntityListener::class)
data class UserInfo(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToOne
    @JoinColumn(name = "user_credentials_id", referencedColumnName = "id")
    var userCredentials: UserCredentials? = null,

    @Column(name = "first_name")
    val firstName: String? = "UNKNOWN",

    @Column(name = "second_name")
    val secondName: String?,

    @Column(name = "patronymic")
    val patronymic: String?,

    @Column(name = "description")
    val description: String?,

    @Column(name = "avatar")
    var avatar: String? = null,

    @OneToMany(mappedBy = "userInfo")
    val posts: List<Post>? = null,

    @CreatedDate
    @Column(name = "created", updatable = false)
    var created: Date? = null,

    @LastModifiedDate
    @Column(name = "updated")
    var updated: Date? = null

)
