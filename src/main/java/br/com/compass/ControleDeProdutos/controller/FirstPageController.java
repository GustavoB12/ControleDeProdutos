package br.com.compass.ControleDeProdutos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class FirstPageController {

	@RequestMapping("/")
	@ResponseBody
	public String hello() {
		return "Hello World!";
	}

}
