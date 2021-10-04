package furlucis.handmade.exceptions

class PostIdException(id: Long) :
    Exception("Поста с id $id не существует.")
