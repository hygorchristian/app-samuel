<?php

    include "conexao.php";

	$nome = $_POST['nome'];
	$telefone = $_POST['telefone'];
	$email = $_POST['email'];

    $whatsapp = $_POST['whatsapp'];
    $fecebook = $_POST['fecebook'];
    $instagran = $_POST['instagran'];
    $endereco = $_POST['endereco'];
    $cidade= $_POST['cidade'];
    $categotia = $_POST['categotia'];
    $descricao = $_POST['descricao'];

    $foto_origem = $_FILES['foto']['tmp_name'];
    $foto_destino = "";

	$sql_insert = "INSERT INTO contato_loja (nome,telefone,email,whatsapp,fecebook,instagran,endereco,cidade,categotia,descricao,foto) 
    VALUES (:NOME, :TELEFONE,:EMAIL, :WHATSAPP, :FECEBOOK, :INSTAGRAN, :ENDERECO, :CIDADE, :CATEGOTIA, :DESCRICAO, :FOTO)";

	$stmt = $PDO->prepare($sql_insert);

    $stmt->bindParam(':NOME', $nome);
    $stmt->bindParam(':TELEFONE', $telefone);
    $stmt->bindParam(':EMAIL', $email); 
    $stmt->bindParam(':WHATSAPP', $whatsapp);
    $stmt->bindParam(':FECEBOOK', $fecebook);
    $stmt->bindParam(':INSTAGRAN', $instagran); 
    $stmt->bindParam(':ENDERECO', $endereco); 
    $stmt->bindParam(':CIDADE', $cidade);
    $stmt->bindParam(':CATEGOTIA', $categotia);
    $stmt->bindParam(':DESCRICAO', $descricao); 
    $stmt->bindParam(':FOTO', $foto_destino); 



    if ($stmt->execute()) {
        $id = $PDO->lastInsertId();

        $foto_destino = "foto_loja/" . $id . "_" . time() . ".jpg";

        if (move_uploaded_file($foto_origem, $foto_destino)) {

           $sql_update = "UPDATE contato_loja SET foto=:foto WHERE id=:id";

           $stmt = $PDO->prepare($sql_update);

            $stmt->bindParam(':foto', $foto_destino); 
            $stmt->bindParam(':id', $id);  

            $stmt-> execute();

        }

    	$dados = array("CREATE"=>"OK","ID"=> $id,"FOTO"=> $foto_destino);
    	//echo "Salvo com sucesso !";
    }else{
    	$dados = array("CREATE"=>"ERROR");
    	//echo "Erro ao efetuar cadastro !";
    }
    echo json_encode($dados);


?>