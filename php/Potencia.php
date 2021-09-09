<?php
class Potencia {//declaracao da classe
private $motor;//atributo motor exemplo 100 cilidradas
private $cavalos;//atributo cavalo exemplo 20cv


public function __construct($motor=0, $cavalos=0){//contrutor do objeto potencia, recebe motor e cavalo na sua chamada, ou seja obrigado a passar esses valores ao cria-lo
$this->motor = $motor;//passa o valor vindo do contrutor para o atributo motor do objeto
$this->cavalos = $cavalos;//passa o valor vindo do construtor para o atributo cavalo do objeto
}


public function getMotor(){//pega o valor de motor
return($this->motor);
}
public function setMotor($motor){//seta um valor para motor
$this->motor = $motor;
}


public function setCavalos($cavalos){//seta um valor para cavalos
$this->cavalos = $cavalos;
}
public function getCavalos(){//pega o valor de valos
return($this->cavalos);
}


public function getDataJSON(){//formata modelo para formato JSON
$aux = array(//cria array para receber os valore dos atributos
'motor'=>$this->motor,
'cavalos'=>$this->cavalos);

return($aux);//retorno da funcao, array de string com os valores dos atributos
}
}
?>
