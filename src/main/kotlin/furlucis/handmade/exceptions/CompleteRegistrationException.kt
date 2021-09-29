package furlucis.handmade.exceptions

class CompleteRegistrationException(username: String) :
    Exception("Пользователь с username $username полностью завершил регистрацию.")
