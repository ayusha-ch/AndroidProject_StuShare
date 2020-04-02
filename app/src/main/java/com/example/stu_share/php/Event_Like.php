<?php
// #9 Take user ID from App and query joined events
 require 'connection.php';
 $nameJ=json_decode(file_get_contents('php://input'));
 $eventID=$nameJ->{'event_id'};
 $userID=$nameJ->{'user_id'};
 $flag=$nameJ->{'flag'};
 print_r($nameJ);
 echo("after $namej");
 $checkSQL="SELECT * FROM event_liked WHERE event_id like '$eventID' AND user_ID like '$userID';";
 echo $checkSQL;
 $qry=mysqli_query($con,$checkSQL) or die(mysqli_error($con));
  $rowCheck=mysqli_num_rows($qry);
  $data=mysqli_fetch_assoc($qry);
 echo $rowCheck;
 if ($rowCheck>0) { // if data exist delete the data
  $sql="DELETE FROM event_liked WHERE event_id='$eventID' AND user_ID='$userID';";
    }
    else{ // insert the data if data is not exist
    $sql = "INSERT INTO event_liked (event_id,user_id) VALUES($eventID,$userID)";

        }

        print_r(mysqli_query($con,$sql));

	mysqli_close($con);
?>