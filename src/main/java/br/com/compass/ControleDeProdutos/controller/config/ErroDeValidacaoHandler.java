package br.com.compass.ControleDeProdutos.controller.config;

import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	private String errors = "";

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ExceptionResponse handle(MethodArgumentNotValidException exception) {

		exception.getBindingResult().getFieldErrors().forEach((error) -> {
			this.errors += error.getField() + ": " + error.getDefaultMessage() + " ";
		});
		ExceptionResponse exceptionResponse = new ExceptionResponse(errors, HttpStatus.BAD_REQUEST.value());
		this.errors = "";
		return exceptionResponse;
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public ExceptionResponse handle(EntityNotFoundException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse("Product not Found", HttpStatus.NOT_FOUND.value());
		return exceptionResponse;
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ExceptionResponse handle(EmptyResultDataAccessException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse("Product not Found", HttpStatus.NOT_FOUND.value());
		return exceptionResponse;
	}

	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ExceptionResponse handle(HttpRequestMethodNotSupportedException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse("Method not allowed", HttpStatus.METHOD_NOT_ALLOWED.value());
		return exceptionResponse;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ExceptionResponse handle(HttpMessageNotReadableException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse("Required request body is missing", HttpStatus.BAD_REQUEST.value());
		return exceptionResponse;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ExceptionResponse handle(MethodArgumentTypeMismatchException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse("Incorrect type value", HttpStatus.BAD_REQUEST.value());
		return exceptionResponse;
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public ExceptionResponse handle(NoSuchElementException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse("Product not Found", HttpStatus.NOT_FOUND.value());
		return exceptionResponse;
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ExceptionResponse handle(Exception exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse("Erro interno", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return exceptionResponse;
	}
    // 404
    @ExceptionHandler({ NoHandlerFoundException.class })
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFound(final NoHandlerFoundException ex) {
        return new ExceptionResponse("page not found", HttpStatus.NOT_FOUND.value());
    }

}