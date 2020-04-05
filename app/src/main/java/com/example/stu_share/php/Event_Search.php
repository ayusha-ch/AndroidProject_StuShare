<?php
// #9 Take user ID from App and query joined events
    require 'connection.php';
        $nameJ=json_decode(file_get_contents('php://input'));
 $title=$nameJ->{'keyword'};
    if(empty($title)){
    $sql="SELECT * FROM event WHERE status LIKE 'active';";
    }else{
	$sql = "SELECT *,(SELECT COUNT(el.user_id) FROM event_liked el WHERE el.event_id = e.id) as sum,(SELECT COUNT(el2.user_id)  FROM event_liked el2 WHERE el2.event_id = e.id and el2.user_id='$userID') as isLike FROM  event e WHERE title like '%$title%' AND status LIKE 'active' or detail like '%$title%' or eventCode like '%$title%';";}

// echo $sql;
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