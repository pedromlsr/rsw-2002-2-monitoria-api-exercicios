package org.serratec.calculadora.services;

import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {
	
	public Double dividir(Double x, Double y) {
		
		return x / y;
	}
	
}
