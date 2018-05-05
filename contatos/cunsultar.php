<?php

    include "conexao.php";

    $id = $_POST['id'];
    $nome = $_POST['nome'];

    $sql_consultar = "SELECT * FROM contatos WHERE nome LIKE '%$nome%'";



     $stmt->bindParam(':id', $id);
     $stmt->bindParam(':nome', $nome);


    if ($stmt->exccute()) {
    	
    	$dados = array("CONSULTA"=>"OK");

    }else{

     $dados = array("CONSULTAR"=>"ERROR");

    }
    echo json_encode($dados);




    ?>