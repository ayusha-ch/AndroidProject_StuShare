<?php
    
    require 'connection.php';
    
// 	require 'user_email_pass.php';
	$nameJ=json_decode(file_get_contents('php://input'));
	$email = $nameJ->{'email'};    	


			$sql = "SELECT * FROM user WHERE `email`='$email'";
			if($result=mysqli_query($con,$sql)){
				echo(mysqli_num_rows($result));
			}
			else{				
				echo 'oops! Please try again!';		
			}

	        mysqli_close($con);
		
		
?>