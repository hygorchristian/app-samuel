<?php
include "conexao.php";

$categoria = isset($_GET['categoria']) ? $_GET['categoria'] : null ;

if($categoria == null){
	$sql_read_loja = "SELECT * FROM contato_loja";
}
else{
	$sql_read_loja = "SELECT * FROM contato_loja WHERE categotia = '" . $categoria . "'";
}


$dados = $PDO->query($sql_read_loja);
$resultado = array();

while ($contato_loja = $dados->fetch(PDO::FETCH_OBJ)) {
	

	$resultado[] = array("id"=>$contato_loja ->id, "nome"=>$contato_loja->nome, "email"=>$contato_loja->email, "telefone"=>$contato_loja->telefone, 
		"whatsapp"=>$contato_loja->whatsapp , "fecebook"=>$contato_loja->fecebook , "instagran"=>$contato_loja->instagran, "endereco"=>$contato_loja->endereco, 
		"cidade"=>$contato_loja->cidade, "categotia"=>$contato_loja->categotia, "descricao"=>$contato_loja->descricao, "foto"=>$contato_loja->foto);
}

echo json_encode($resultado);


?>