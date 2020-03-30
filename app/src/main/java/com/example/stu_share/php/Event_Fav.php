<?php
// #9 Take user ID from App and query joined events
    require 'connection.php';
        $nameJ=json_decode(file_get_contents('php://input'));
 $title=$nameJ->{'keyword'};
    if(empty($title)){
    $sql="SELECT * FROM event WHERE status LIKE 'active';";
    }else{
	$sql = "SELECT * FROM  event WHERE title like '%$title%' AND status LIKE 'active' or detail like '%$title%' or eventCode like '%$title%';";}

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