package br.exemplojsonparser;

public class Potencia {//cria a classe potencia no app
	private double motor;//variavel de ambiente
	private int cavalos;//variavel de ambiente
	
	
	
	public Potencia(){//contrutor de potencia vazio
		super();
	}
	public Potencia(float motor, int cavalos){//contrutor de potencia recebendo valores
		this.motor = motor;
		this.cavalos = cavalos;
	}
	
	
	public double getMotor() {//pega o valor de motor
		return motor;
	}
	public void setMotor(double motor) {//seta valor de motor
		this.motor = motor;
	}
	
	
	public int getCavalos() {//pega cavalor
		return cavalos;
	}
	public void setCavalos(int cavalos) {//seta cavalos
		this.cavalos = cavalos;
	}
}
