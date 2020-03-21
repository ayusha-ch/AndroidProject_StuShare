<?php
    
    require 'connection.php';
    
// 	require 'user_email_pass.php';
	$nameJ=json_decode(file_get_contents('php://input'));
	$email = $nameJ->{'email'};    	
	$password = $nameJ->{'password'};
	$firstName = $nameJ->{'firstName'};	
	$lastName = $nameJ->{'lastName'};
	$collegeCode =$nameJ->{'collegeCode'};
	$programCode = $nameJ->{'programCode'};	
	$registeredYear = $nameJ->{'registeredYear'};
	$exprireYear = $nameJ->{'expireYear'};
	$status = $nameJ->{'status'};
	$question = $nameJ->{'question'};	
	$answer = $nameJ->{'answer'};
	$role = $nameJ->{'role'};
	$activationCode=$nameJ->{'activationCode'};

			$sql = "INSERT INTO user(email, password, firstName, lastName,collegeCode, programCode, registeredYear, expireYear, status, question, answer, role,activationCode) VALUES ('$email','$password','$firstName','$lastName','$collegeCode','$programCode','$registeredYear','$exprireYear','pending','$question','$answer','student','$activationCode')";

			if(mysqli_query($con,$sql)){
			    echo $sql;
				echo 'successfully registered';	
			}
			else{				
				echo 'oops! Please try again!';		
			}

			
	        mysqli_close($con);
		
		
?>