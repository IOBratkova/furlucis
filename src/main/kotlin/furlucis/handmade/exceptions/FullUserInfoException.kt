package furlucis.handmade.exceptions

class FullUserInfoException(username: String) :
    Exception("Пользователь с username $username полностью завершил регистрацию.")
