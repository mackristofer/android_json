<?php
	require_once('Potencia.php');//adiciona a classe potencia na classe casso para que possa ser utilizada como atributo de carro, podendo utilizar varios atributos do tipo potencia para o mesmo modelo de carro

	class Carro {
		private $marca;//atributo marca do carro
		private $modelo;//atributo modelo do carro
		private $potencias;//atributo potencias do carro
		
		
		public function __construct($marca='', $modelo='', $potencias=array()){//contrutor do objeto carro, tem que ser passado a marca, modelo, e possiveis potencias
			$this->marca = $marca;//alimenta o atributo marca do carro
			$this->modelo = $modelo;//alimenta o atributo modelo do carro
			$this->potencias = $potencias;//recebe as potencias em um array
		}
		
		
		public function getMarca(){//pega a marca
			return($this->marca);
		}
		public function setMarca($marca){//seta a marca
			$this->marca = $marca;
		}
		
		
		public function setModelo($modelo){//seta modelo
			$this->modelo = $modelo;
		}
		public function getModelo(){//pega modelo
			return($this->modelo);
		}
		
		
		public function setPotencias($potencias){//seta potencias
			$this->potencias = $potencias;
		}
		public function getPotencias(){//pega potencias
			return($this->potencias);
		}
		
                
		public function getDataJSON(){//cria modelo getDataJSON do carro para ser manipulado
			$aux = array(//cria array para guardar os valores
				'marca'=>$this->marca,//pega marca
				'modelo'=>$this->modelo,//pega modelo
				'potencias'=>$this->getPotenciasJSON());//pega jason de potencia atraves de um metodo auxiliar descrito abaixo
			
			return($aux);//retorna o vetor JSON do objeto carro.
		}
		
		
		private function getPotenciasJSON(){//obtem as patencias atraves do metodo getDataJSON do objeto potencia.
			$aux = array();//cria um array
			for($i = 0, $tam = count($this->potencias); $i < $tam; $i++){//crie um loop de 0 ate o tamanho da minha array de potencias.
				$aux[] = $this->potencias[$i]->getDataJSON();//armazena no array aux uma a uma o resultado de getDataJSON do obeto potencia.
			}
			return($aux);//retorna vetor JSON no padrao getDataJSON do objeto potencia
		}
	}
?>
