<?php

    include "conexao.php";

	$nome = $_POST['nome'];
	$telefone = $_POST['telefone'];
	$email = $_POST['email'];
    
    $foto_origem = $_FILES['foto']['tmp_name'];
    $foto_destino = "";

	$sql_insert = "INSERT INTO contatos (nome,telefone,email,foto) VALUES (:NOME, :TELEFONE, :EMAIL, :FOTO)";

	$stmt = $PDO->prepare($sql_insert);

    $stmt->bindParam(':NOME', $nome);
    $stmt->bindParam(':TELEFONE', $telefone);
    $stmt->bindParam(':EMAIL', $email); 
    $stmt->bindParam(':FOTO', $foto_destino); 

    if ($stmt->execute()) {
        $id = $PDO->lastInsertId();

        $foto_destino = "fotos/" . $id . "_" . time() . ".jpg";

        if (move_uploaded_file($foto_origem, $foto_destino)) {

           $sql_update = "UPDATE contatos SET foto=:foto WHERE id=:id";

           $stmt = $PDO->prepare($sql_update);

            $stmt->bindParam(':foto', $foto_destino); 
            $stmt->bindParam(':id', $id);  

            $stmt-> execute();

        }

    	$dados = array("CREATE"=>"OK","ID"=> $id);
    	//echo "Salvo com sucesso !";
    }else{
    	$dados = array("CREATE"=>"ERROR");
    	//echo "Erro ao efetuar cadastro !";
    }
    echo json_encode($dados);


?>