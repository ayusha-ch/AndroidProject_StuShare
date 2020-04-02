<?php

	require 'connection.php';
	$nameJ=json_decode(file_get_contents('php://input'));
    $organizer_id = $nameJ->{'organizerId'};
    $status = $nameJ->{'status'};	
	$start_date = $nameJ->{'startDate'};	
	$start_time = $nameJ->{'startTime'};
	$end_date = $nameJ->{'endDate'};
	$end_time = $nameJ->{'endTime'};	
	$title = $nameJ->{'eventTitle'};
	$detail = $nameJ->{'eventDetail'};
	$eventCode= $nameJ->{'eventCode'};
	$orgEmail= $nameJ->{'orgEmail'};
    $imagePath='https://cdn.pixabay.com/photo/2016/11/19/14/00/code-1839406_960_720.jpg';
	
				
	$sql = "INSERT INTO event (organizerId,status,startDate,startTime,endDate,endTime,title,detail,eventCode,orgEmail,imagePath)
			VALUES('$organizer_id','active','$start_date','$start_time','$end_date','$end_time','$title','$detail','$eventCode','$orgEmail','$imagePath')";
			echo $sql;
	if(mysqli_query($con,$sql)){

		echo 'successfully registered';	
	}
	else{				
		echo 'oops! Please try again!';		
	}
	
	
    mysqli_close($con);
				
		
?>