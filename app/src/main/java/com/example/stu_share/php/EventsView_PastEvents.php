<?php

require 'connection.php';

$nameJ=json_decode(file_get_contents('php://input'));
$userID=$nameJ->{'userid'};
 $today=date('Ymd');
$sql="SELECT e.*,(SELECT COUNT(el.user_id) FROM event_liked el WHERE el.event_id = e.id) as sum,(SELECT COUNT(el2.user_id) FROM event_liked el2 WHERE el2.event_id = e.id and el2.user_id='$userID') as isLike FROM  event e WHERE status LIKE 'active' and organizerId='$userID' and endDate< '$today' ORDER BY createdAt DESC;";
// Confirm there are results
if ($result = mysqli_query($con, $sql)){
		 $resultArray = array();
		 $tempArray = array();
		 while($row = $result->fetch_object())
		 {
		 $tempArray = $row;
			 array_push($resultArray, $tempArray);
		 }
		 echo json_encode($resultArray);
	}

	mysqli_close($con);
?>	