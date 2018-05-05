<?php

include "conexao.php";


$nomeCidade = isset($_GET['nomeCidade']) ? $_GET['nomeCidade'] : null ;

if($nomeCidade == null){
	$sql_read = "SELECT * FROM tb_cidades";
}else{
	$sql_read = "SELECT * FROM tb_cidades WHERE nomeCidade = '"  . $nomeCidade . "'";
}



// $sql_read = "SELECT * FROM tb_cidades";

$dados = $PDO->query($sql_read);
$resultado = array();

while ($tb_cidades = $dados->fetch(PDO::FETCH_OBJ)) {
	

	$resultado[] = array("id"=>$tb_cidades ->id, "nomeCidade"=>$tb_cidades->nomeCidade);
}

echo json_encode($resultado);


?>