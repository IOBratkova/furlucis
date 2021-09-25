package furlucis.handmade.exceptions

class UserIdException(id: Long) :
    Exception("Пользователя с id $id не существует.")
