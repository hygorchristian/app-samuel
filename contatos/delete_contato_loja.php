<?php

    include "conexao.php";

    $id = $_POST['id'];

    $sql_delete = "DELETE FROM $contato_loja WHERE id=:id";

     $stmt = $PDO->prepare($sql_delete);

     $stmt->bindParam(':id', $id);


    if ($stmt->exccute()) {
    	
    	$dados = array("DELETE"=>"OK");

    }else{

     $dados = array("DELETE"=>"ERROR");

    }
    echo json_encode($dados);


    ?>