<?php
	require 'connection.php';
	$nameJ=json_decode(file_get_contents('php://input'));
    $id = $nameJ->{'messageID'};
    $sql = "DELETE FROM message where _id like '$id';";
    echo $sql;
    mysqli_query($con, $sql);
    mysqli_close($con);
?>