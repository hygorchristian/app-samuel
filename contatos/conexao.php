<?php 
$dsn = "mysql:host=localhost; dbname=crud; charset=utf8";

//$db_name = "contatos";
$mysql_user = "root";
$mysql_pass = "";
//$server_name = "localhost";

//$con = mysql_connect($server_name,$mysql_user,$mysql_pass,$db_name);
try {
	
	$PDO = NEW PDO ($dsn,$mysql_user,$mysql_pass);
	//echo "Conectado com sucesso !";

} catch (PDOException $erro) {
	echo "Ocorreu um erro !";
	//echo $erro -> getMessage();

	
}


?>