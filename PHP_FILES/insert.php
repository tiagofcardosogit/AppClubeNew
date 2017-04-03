<?php

include ("connect.php");

$nome = "Dougras Clube de Regatas";
$cidade = "Rio de Janeiro";
$ano = 2017;

$sql = "INSERT INTO clube (nome_clube, cidade_clube, ano_clube)
VALUES ('$nome', '$cidade', $ano)";

if ($conn->query($sql) === TRUE) {
	$myObj = array('status' => '1', 'message' => 'Inserido com sucesso!');
    echo json_encode($myObj);
} else {
	$myObj = array('status' => '0', 'message' => 'Falha ao inserir...');
    echo json_encode($myObj);
}

$conn->close();

?>