<?php

include ("connect.php");

$sql = "SELECT * FROM clube";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
	$array_clube = Array();
    while($row = $result->fetch_assoc()) {
        $array_clube[] = $row;
    }
	echo json_encode($array_clube);
} else {
	$myObj = new stdClass();
	$myObj->status = "0";
    echo json_encode($myObj);
}
$conn->close();

?>