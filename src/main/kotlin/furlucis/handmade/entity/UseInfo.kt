package furlucis.handmade.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_info")
@EntityListeners(AuditingEntityListener::class)
data class UseInfo(
        @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

        @OneToOne
    @JoinColumn(name = "user_credentials_id")
    val userCredentials: UserCredentials,

        @Column(name = "first_name", nullable = false)
    val firstName : String,

        @Column(name = "second_name")
    val secondName : String?,

        @Column(name = "patronymic")
    val patronymic : String?,

        @Column(name = "description")
    val description : String?,

        @CreatedDate
    @Column(name = "created", updatable = false, nullable = false)
    val created: Date? = null,

        @LastModifiedDate
    @Column(name = "updated", nullable = false)
    val updated: Date? = null,

        @Column(name = "avatar")
    val avatar: String? = null

)