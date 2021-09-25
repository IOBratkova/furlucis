package furlucis.handmade.exceptions

class EmailRegistrationException(email: String) :
    Exception("Пользователь с email $email существует. Выберите другой email.")
