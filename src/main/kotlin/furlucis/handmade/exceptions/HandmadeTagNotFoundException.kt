package furlucis.handmade.exceptions

class HandmadeTagNotFoundException(title: String) :
    Exception("Тега $title не существует")
