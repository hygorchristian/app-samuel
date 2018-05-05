<?php

include "conexao.php";
$nome = isset($_GET['nome']) ? $_GET['nome'] : null ;

if($nome == null){
	$sql_read = "SELECT * FROM contatos";
}else{
	$sql_read = "SELECT * FROM contatos WHERE nome = '"  . $nome . "'";
}

 //$sql_read = "SELECT * FROM contatos";

$dados = $PDO->query($sql_read);
$resultado = array();

while ($contatos = $dados->fetch(PDO::FETCH_OBJ)) {
	

	$resultado[] = array("id"=>$contatos ->id, "nome"=>$contatos->nome, "email"=>$contatos->email, "telefone"=>$contatos->telefone ,"foto"=>$contatos->foto);
}

echo json_encode($resultado);


?>