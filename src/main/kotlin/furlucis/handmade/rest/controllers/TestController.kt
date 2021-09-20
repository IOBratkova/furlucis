package furlucis.handmade.rest.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/test")
class TestController {
    @GetMapping("/full-permit")
    fun getAdmin(): String {
        return "full-permit"
    }


}