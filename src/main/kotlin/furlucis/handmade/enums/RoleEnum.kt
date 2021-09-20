package furlucis.handmade.enums

enum class RoleEnum(val text: String) {
    ADMIN("ADMIN") {
        override fun getCode(): Int = 0
    },
    MODERATOR("MODERATOR") {
        override fun getCode(): Int = 1
    },
    USER("USER") {
        override fun getCode(): Int = 2
    },
    UNKNOWN("UNKNOWN") {
        override fun getCode(): Int = -1
    };

    abstract fun getCode(): Int
}