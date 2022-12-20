package rg.cdisuspend

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api"], consumes = ["application/json"], produces = ["application/json"])
class KeyboardController(
    private val assignKeyboard: AssignKeyboard,
) {


    @PostMapping("/keyboard")
    suspend fun switch(@RequestBody switchActiveComputer: SwitchKeyboardDto): ResponseEntity<Unit> {
        return assignKeyboard(switchActiveComputer.computer).fold(
            { ResponseEntity.badRequest() },
            { ResponseEntity.accepted() }
        ).build()
    }

}

data class SwitchKeyboardDto(
    val computer: String,
)
