<?php
//read all the events

require 'connection.php';

$host = "gblearn.com";
$user_name = "w0044421_w0044421";
$user_password = "Stushare127";
$db_name = "w0044421_stushare";

$con = $con = mysqli_connect($host, $user_name,$user_password, $db_name);
 
// Select all of our stocks from table 'stock_tracker'
$sql = "SELECT * FROM event";
 
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