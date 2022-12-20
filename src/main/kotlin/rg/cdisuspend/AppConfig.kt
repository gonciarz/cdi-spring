package rg.cdisuspend

import arrow.core.Either
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.enterprise.context.ApplicationScoped
import javax.inject.Named

typealias AssignKeyboard = suspend (String) -> Either<KeyboardError, Unit>
typealias AssignMouse = suspend (String) -> Either<MouseError, Unit>

sealed interface KeyboardError {
    object ExecutionError : KeyboardError
}

sealed interface MouseError {
    object ExecutionError : MouseError
}

@Configuration
class AppConfig {

    @Bean
    fun assignLocalKeyboardBean(shellAdapter: ShellAdapter): AssignKeyboard =
        shellAdapter::assignKeyboard

    @Bean
    fun assignLocalMouseBean(shellAdapter: ShellAdapter): AssignMouse =
        shellAdapter::assignMouse
}
