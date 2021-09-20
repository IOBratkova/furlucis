package furlucis.handmade.exceptions

class EmailException(email: String) :
    Exception("Пользователь с email $email не существует.")
