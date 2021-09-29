package furlucis.handmade.enums

enum class PostEnum(val text: String) {
    LESSON("LESSON") {
        override fun getCode(): Int = 0
    },
    STORY("STORY") {
        override fun getCode(): Int = 1
    };
    abstract fun getCode(): Int
}