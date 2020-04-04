<?php

	require 'connection.php';
	
	$nameJ=json_decode(file_get_contents('php://input'));
	

    $id = $nameJ->{'userID'};

   
				
	$sql = "SELECT * FROM message where receiver_id like '$id';";
			//echo $sql;
if ($result = mysqli_query($con, $sql))
{
 // We have results, create an array to hold the results
        // and an array to hold the data
 $resultArray = array();
 $tempArray = array();

 // Loop through each result
 while($row = $result->fetch_object())
 {
 // Add each result into the results array
 $tempArray = $row;
     array_push($resultArray, $tempArray);
 }
 // Encode the array to JSON and output the results
 echo json_encode($resultArray);
}
// Close connections
mysqli_close($con);
?>