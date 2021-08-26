<?php


$connection = mysqli_connect("localhost","root","");

mysqli_select_db($connection,"apiidb");

$email=trim($_POST['email']);
$password=trim($_POST['password']);

$qry="select * from tbl_user where email='$email' and password='$password'";
$raw=mysqli_query($connection,$qry);
$count=mysqli_num_rows($raw);

if($count>0)
	{
		$response['message'] = "exist";

	}else{

		$response['message'] = "failed";
	}

	echo json_encode($response);


?>