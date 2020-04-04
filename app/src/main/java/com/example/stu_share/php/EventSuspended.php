<?php
//#7.	remove reord in evnetreg
    require 'connection.php';
    
    $nameJ=json_decode(file_get_contents('php://input'));
    $eventID = $nameJ->{'eventId'};
    $status = $nameJ->{'status'};
    echo $status."status";
    if (strcmp($status,"suspended")==0){
    echo("active");
        $status="active";
    }else  if (strcmp($status,"active")==0){
                  $status="suspended";
                  echo("suspended");
              }
	$sql = "UPDATE event SET status='$status' where id='$eventID';";

	echo $sql;
    mysqli_query($con, $sql);

	mysqli_close($con);
    
?>    