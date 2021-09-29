package furlucis.handmade.exceptions

class IncompleteRegistrationException(username: String) :
    Exception("Пользователь с username $username не завершил регистрацию.")
