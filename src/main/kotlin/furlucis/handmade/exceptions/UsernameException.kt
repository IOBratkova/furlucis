package furlucis.handmade.exceptions

class UsernameException(username: String) :
    Exception("Пользователь с username $username не существует.")
