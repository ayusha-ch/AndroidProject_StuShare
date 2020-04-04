<?php
//read all the events where status = "active"

require 'connection.php';
$nameJ=json_decode(file_get_contents('php://input'));
$userID=$nameJ->{'userid'};
 
// Select all of our stocks from table 'stock_tracker'
$sql = "SELECT * FROM event WHERE organizerId='$userID' AND status like 'active';";
 //echo $sql;
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