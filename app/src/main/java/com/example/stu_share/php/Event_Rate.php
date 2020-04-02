<?php
// #9 Take user ID from App and query joined events
 require 'connection.php';
 $nameJ=json_decode(file_get_contents('php://input'));
 $eventID=$nameJ->{'event_id'};
 $userID=$nameJ->{'user_id'};
 $rating=$nameJ->{'rating'};
 $insertSQL="INSERT INTO event_rated (event_id,user_id,rating) VALUES($eventID,$userID,$rating)";
 $updateSql="UPDATE `event_rated` SET rating='$rating' WHERE event_id='$eventID' AND user_id='$userID';";
 $sql="SELECT * FROM event_rated WHERE event_id='$eventID' AND user_id='$userID';";
 $qry=mysqli_query($con,$sql);
 $rowCheck=mysqli_num_rows($qry);
 $data=mysqli_fetch_assoc($qry);
 if ($rowCheck>0) { // if data exist update the data
       $qry=mysqli_query($con,$updateSql);
       }
       else{ // insert the data if data is not exist
           $qry=mysqli_query($con,"INSERT INTO event_rated (event_id,user_id,rating) VALUES($eventID,$userID,$rating)");
       }

 $getSumSQL="SELECT sum(rating) as sum,count(rating) as count,avg(rating) as avg from event_rated WHERE event_id='$eventID' ;";
 $qry2=mysqli_query($con,$getSumSQL);
 $data=mysqli_fetch_assoc($qry2);
 $rate_updated=floatval($data[avg]);
 function updateEventTable($con,$event_ID,$rating){
    $updateSQL="UPDATE `event` SET rating='$rating' WHERE id='$event_ID' ;";

 	$qry=mysqli_query($con,$updateSQL);
 }
 updateEventTable($con,$eventID,$rate_updated);
 echo $rate_updated;
mysqli_close($con);

?>