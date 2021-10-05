package furlucis.handmade.exceptions

class ExistsHandmadeTagException(title: String) :
    Exception("Тег $title уже существует.")
