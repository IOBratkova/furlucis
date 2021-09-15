package furlucis.handmade.entity

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener::class)
data class User(
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UserUUIDGenerator")
    @GenericGenerator(name = "UserUUIDGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    val id: UUID? = null,

    @Column(name = "login", nullable = false)
    val login : String,

    @Column(name = "first_name", nullable = false)
    val firstName : String,

    @Column(name = "second_name")
    val secondName : String?,

    @Column(name = "patronymic")
    val patronymic : String?,

    @Column(name = "description")
    val description : String?,

    @Column(name = "password", nullable = false)
    var password: String,

    @Email
    @Column(name = "email", nullable = false)
    val email : String,

    @CreatedDate
    @Column(name = "created")
    val created: Date? = null,

    @LastModifiedDate
    @Column(name = "updated")
    val updated: Date? = null,

    @Column(name = "avatar")
    val avatar: String? = null,

    @Column(name = "role", nullable = false)
    val role: String

)
