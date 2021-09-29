package furlucis.handmade.entity

import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
@Table(name = "user_credentials")
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

    @OneToOne(mappedBy = "userCredentials")
    var userInfo: UserInfo? = null,

    @Column(name = "created")
    var created: Date? = null,

    @Column(name = "updated")
    var updated: Date? = null

)
