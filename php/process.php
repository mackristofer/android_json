<?php
	require_once('Carro.php');//adiciona os objetos que seram manipulados ao seu escopo
	require_once('con_sql.php');
	
	
	if(strcmp('send-json', $_POST['method']) == 0){ // SEND checa se chegou atraves do post um send-jason
		$carro = utf8_encode($_POST['json']);//adiciona alguma codificacao que ainda n sei para a variavel carro
		$carro = json_decode($carro);//replace carro com a funcao json_decode da variavel carro, que no caso é o objeto que vai ser criado la no android e que tem as mesmas funcoes e atributos que nossas classe emplementasdas aki no php
		
		
		$marca = $carro->marca;
		$modelo = $carro->modelo;
		
		
		$sql = "INSERT INTO carros (marca,modelo,id) VALUES ('$marca','$modelo')";
		
		$con->query($sql);
		
		$id = $con->query("SELECT id FROM carros WHERE marca='$marca' AND modelo='$modelo'");
		
		foreach($carro->potencias as $potencia){//a potencia é um vetor, entao e preciso um loop para correr todos os camos
		
		$motor = $potencia->motor;
		$cavalos = $potencia->cavalos;
		
		
		$sql = "INSERT INTO potencia (id_carro,motor,cavalos) VALUES ('$id','$motor','$cavalos')";
		
		$con->query($sql);
		
		}
		echo '1';//retorna 1 informando que tudo deu certo.
	}
	
	
	else if(strcmp('get-json', $_POST['method']) == 0){ // GET checa se a solicitcacao para mandar as informacoes para o android, nesse caso vamos criar o objeto carro e encodar o metodo getDataJSON do objeto carro.
	
	        $sqlc = "SELECT * FROM carros";
	        
	        
	        $resultc = $con->query($sqlc);
	        
	        $potencias = array();//cria um vetor para armazenar varios objetos potencia
		
	        while($dados = $resultc->fetch_array()){
	        
	        $marca = $dados["marca"];
	        $modelo = $dados["modelos"];
	        $idcarro = $dados["id"];
	        	        
	        $carro = new Carro();//cria objeto carro
		$carro->setMarca(utf8_encode($marca));//seta a marca para o objeto, porem passando um tipo de encode que ainda vou descobir o que e rsrsr
		$carro->setModelo($modelo);//seta o modelo do carro
		
		$sqlp = "SELECT * FROM potencia WHERE id_carro = '$idcarro'";
		$resultp = $con->query($sqlp);
		
		while($dados = $resultp->fetch_array()){
		$motor = $dados["motor"];
		$cavalos = $dados["cavalos"];
		$potencias[] = new Potencia($motor, $cavalos);//cria uma nova potencia
		}
		$carro->setPotencias($potencias);//seta a potencia no carro
		}
		echo json_encode($carro->getDataJSON());//codifica em json o getDataJSON de carro, assim ele pode ser processada la no android
	}
?>
