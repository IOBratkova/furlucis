package furlucis.handmade.entity

import javax.persistence.*

@Entity
@Table(name = "handmade_type")
data class HandmadeType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = 0L,

    @Column(name = "title", length = 30)
    var title: String? = "",

    @Column(name = "description", length = 1000)
    var description: String? = ""
)