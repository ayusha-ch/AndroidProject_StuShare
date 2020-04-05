<?php
// #9 Take user ID from App and query joined events
 require 'connection.php';
 $nameJ=json_decode(file_get_contents('php://input'));
 $userID=$nameJ->{'user_id'};
 $title=$nameJ->{'keyword'};
 $today=date('Ymd');
// echo $today;
// echo $title;
    if(empty($title)){
    $sql="SELECT e.*,(SELECT COUNT(el.user_id) FROM event_liked el WHERE el.event_id = e.id) as sum,(SELECT COUNT(el2.user_id) FROM event_liked el2 WHERE el2.event_id = e.id and el2.user_id='$userID') as isLike FROM  event e WHERE status LIKE 'active' and endDate > '$today' ORDER BY createdAt DESC;";
    }else{
    if(strcmp($title,"Most recent")==0){
    $title="createdAt DESC";
    }
    else if(strcmp($title,"Start Date")==0){
               $title="startDate";
               }
	$sql = "SELECT e.*,(SELECT COUNT(el.user_id) FROM event_liked el WHERE el.event_id = e.id) as sum,(SELECT COUNT(el2.user_id) FROM event_liked el2 WHERE el2.event_id = e.id and el2.user_id=2) as isLike FROM  event e WHERE status LIKE 'active' and endDate > '$today' ORDER BY $title ;";
	}

//echo $sql.'sort';
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