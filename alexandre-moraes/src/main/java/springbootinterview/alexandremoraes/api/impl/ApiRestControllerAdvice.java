package springbootinterview.alexandremoraes.api.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import springbootinterview.alexandremoraes.exceptions.CidadeNotFoundException;
import springbootinterview.alexandremoraes.exceptions.ClienteNaoEncontradoException;

@RestControllerAdvice
@Slf4j
public class ApiRestControllerAdvice {

	@ExceptionHandler(ClienteNaoEncontradoException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "id cliente não encontrado.")
	public void clienteNotFound(final ClienteNaoEncontradoException clienteNaoEncontradoException) {
		log.error("m=clienteNotFound, id={}", clienteNaoEncontradoException.getId(), clienteNaoEncontradoException);
	}

	@ExceptionHandler(CidadeNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "id cidade não encontrado.")
	public void cidadeNotFound(final CidadeNotFoundException cidadeNotFoundException) {
		log.error("m=cidadeNotFound, id={}", cidadeNotFoundException.getId(), cidadeNotFoundException);
	}

}
