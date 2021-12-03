package br.com.compass.ControleDeProdutos.controller.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	private String errors = "";
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ExceptionResponse handle(MethodArgumentNotValidException exception) {		
		
		exception.getBindingResult().getFieldErrors().forEach((error) ->  
		{this.errors += error.getField()+": "+error.getDefaultMessage()+" ";
		});
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(errors, HttpStatus.BAD_REQUEST.value());
		this.errors = "";
		return exceptionResponse;        
}

}
