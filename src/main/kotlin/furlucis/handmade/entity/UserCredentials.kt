package furlucis.handmade.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
@Table(name = "user_credentials")
@EntityListeners(AuditingEntityListener::class)
data class UserCredentials(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "username", nullable = false)
    var username: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Email
    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "role", nullable = false)
    var role: String,

    @CreatedDate
    @Column(name = "created") //, columnDefinition = "DATE")
    var created: Date? = null,

    @LastModifiedDate
    @Column(name = "updated") //, columnDefinition = "DATE")
    var updated: Date? = null

)
