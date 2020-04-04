<?php

	require 'connection.php';
	
	$nameJ=json_decode(file_get_contents('php://input'));
	
	$messageCode= $nameJ->{'messageCode'};
    $title = $nameJ->{'title'};
    $email = $nameJ->{'email'};
	$details = $nameJ->{'details'};
	$type = $nameJ->{'type'};
    $status = $nameJ->{'status'};
   $receiverID = $nameJ->{'receiver'};
				
	$sql = "INSERT INTO message (messageCode,title,sender_id,receiver_id,details,type,status)
			VALUES('$messageCode', '$title','$email','$receiverID', '$details', 'event', 'active')";
			echo $sql;
	if(mysqli_query($con,$sql)){

		echo 'Message successfully sent';	
	}
	else{				
		echo 'Oops! Please try again!';		
	}
	
	
    mysqli_close($con);
				
		
?>