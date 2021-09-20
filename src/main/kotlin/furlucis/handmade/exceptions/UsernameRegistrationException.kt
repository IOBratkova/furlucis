package furlucis.handmade.exceptions

class UsernameRegistrationException (username: String) :
    Exception("Пользователь с username $username существует. Выберите другой логин.")
