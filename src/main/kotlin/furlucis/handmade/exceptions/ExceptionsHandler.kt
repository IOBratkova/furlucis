package furlucis.handmade.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
class ExceptionsHandler : ResponseEntityExceptionHandler() {

    data class ExceptionModel(val message: String, val exceptionTime: Date)

    @ExceptionHandler(value = [
        EmailRegistrationException::class,
        UsernameRegistrationException::class,
        IncorrectCredentialsException::class,
        EmailException::class, UserAuthException::class,
        UsernameException::class,
        ValidatePasswordException::class
    ])
    fun handleException(e: Exception) : ResponseEntity<ExceptionModel> {
        val ex = ExceptionModel(e.message!!, Date())
        return ResponseEntity(ex, HttpStatus.BAD_REQUEST)
    }

}