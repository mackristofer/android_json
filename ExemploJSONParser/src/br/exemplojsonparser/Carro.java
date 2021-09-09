package br.exemplojsonparser;

import java.util.*;

public class Carro {//cria a classe carro
	private String marca;//marco do carro
	private String modelo;//modelo do carro
	private List<Potencia> potencias;//lista de potencias do veiculo, atributo referente a classe potencias
	
	
	public Carro() {//cria construtor de carro vazio
		super();
	}
	public Carro(String marca, String modelo, List<Potencia> potencias) {//contrutor para criar carro, o mesmo modelo pode ter varias potencias
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.potencias = potencias;
	}
	
	
	public String getMarca() {//pega marca do carro
		return marca;
	}
	public void setMarca(String marca) {//seta marca do carro
		this.marca = marca;
	}
	public String getModelo() {//pega modelo do carro
		return modelo;
	}
	public void setModelo(String modelo) {//seta modelo do carro
		this.modelo = modelo;
	}
	public List<Potencia> getPotencias() {//pega lista de potencias
		return potencias;
	}
	public void setPotencias(List<Potencia> potencias) {//seta lista de potencia
		this.potencias = potencias;
	}
}
