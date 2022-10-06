package org.serratec.calculadora.controllers;

import org.serratec.calculadora.services.CalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operacoes")
public class CalculadoraController {
	
	@Autowired
	CalculadoraService calculadoraService;
	
	@GetMapping("/soma")
	public Double somar(@RequestParam Double x, @RequestParam Double y) {
		
		return x + y;
	}
	
	@GetMapping("/sub/{x}/{y}")
	public Double subtrair(@PathVariable Double x, @PathVariable Double y) {
		
		return x - y;
	}
	
	@GetMapping("/mult/{x}/{y}")
	public String multiplicar(@PathVariable Double x, @PathVariable Double y) {
		
		String resultado = x + " x " + y + " = " + calcularMultiplicacao(x, y);
		
		return resultado;
	}

	@GetMapping("/div/{x}/{y}")
	public Double dividir(@PathVariable Double x, @PathVariable Double y) {
		
		Double resultado = calculadoraService.dividir(x, y);
		
		return resultado;
	}
	
	private Double calcularMultiplicacao(Double x, Double y) {
	
		return x * y;
	}
}
