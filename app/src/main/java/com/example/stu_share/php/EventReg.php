


<?php
//#7.1.	insert reord in evnetreg
    require 'connection.php';
    $nameJ=json_decode(file_get_contents('php://input'));
    $userID=$nameJ->{'userId'};

    $eventID = $nameJ->{'eventId'};


     $checkSQL="SELECT * FROM event_reg WHERE eventId like '$eventID' AND userId like '$userID';";
     echo $checkSQL;
     $qry=mysqli_query($con,$checkSQL) or die(mysqli_error($con));
      $rowCheck=mysqli_num_rows($qry);
      $data=mysqli_fetch_assoc($qry);
     echo $rowCheck;
     if ($rowCheck>0) { // if data exist delete the data
      echo "record exists";
        }
        else{ // insert the data if data is not exist
        $sql =  "INSERT INTO `event_reg` ( `eventId`, `userId`, `status`) VALUES ( '$eventID', '$userID', 'registered');";

            }

            print_r(mysqli_query($con,$sql));

    	mysqli_close($con);

?>