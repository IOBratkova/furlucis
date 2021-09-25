package furlucis.handmade.enums

enum class RoleEnum(val text: String) {
    ADMIN("ROLE_ADMIN") {
        override fun getCode(): Int = 0
    },
    MODERATOR("ROLE_MODERATOR") {
        override fun getCode(): Int = 1
    },
    USER("ROLE_USER") {
        override fun getCode(): Int = 2
    },
    UNKNOWN("ROLE_UNKNOWN") {
        override fun getCode(): Int = -1
    };

    abstract fun getCode(): Int
}