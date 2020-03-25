<?php
//read all the events where status = "active"

require 'connection.php';



$con = $con = mysqli_connect($host, $user_name,$user_password, $db_name);
 $today=date('Ymd');
// Select all of our stocks from table 'stock_tracker'
$sql="SELECT * FROM  event WHERE status LIKE 'active' and endDate > '$today' ORDER BY createdAt DESC;";
 
// Confirm there are results
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