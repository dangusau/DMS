<?php

@session_start();
@ini_set('session.gc_maxlifetime',12*60*60);
@ini_set('session.cookie_lifetime',12*60*60);

error_reporting(E_ALL);
ini_set('display_errors', 1);
date_default_timezone_set('Asia/Karachi');

define('PRE_FIX' , "godelivery_");

//Access Your API URL in browser and you will get a code to put over here from the admin panel config section
    
$baseurl= "";
define("status" , "live");
define("API_KEY" , "");
define("GOOGLE_MAPS_API" , "");


//dont change any thing here
$imagebaseurl= $baseurl;
$baseurl = $baseurl."admin/";
define('imagebaseurl' , $imagebaseurl);


$url=$baseurl . 'showDefaultCurrency'; 
$data =array();

$showDefaultCurrency=@curl_request($data,$url);
define('DEFAULT_CURRENCY' , $showDefaultCurrency['msg']['Country']['currency_symbol']);


if (@$_GET['p'] == "login") {
      
      $email = $_POST['email'];
      $password = $_POST['password'];
      
      
      $headers = [
          "Accept: application/json",
          "Content-Type: application/json",
          "api-key: ".API_KEY." "
      ];
      
      $data = [
          "email" => $email,
          "password" => $password
      ];
      
      $ch = curl_init($baseurl . 'login');

      curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
      curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'POST');
      curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
      curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

      $return = curl_exec($ch);

      $json_data = json_decode($return, true);
        
      $curl_error = curl_error($ch);
      $http_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
      $data = $json_data['msg'];
      if ($json_data['code'] == 200) 
      {
        $_SESSION[PRE_FIX.'id'] = $data['Admin']['id'];
        $_SESSION[PRE_FIX.'email'] = $email;

        echo "<script>window.location='dashboard.php?p=users'</script>";
      }
      else 
      {
        echo "<script>window.location='index.php?action=error'</script>";
      }
}
 
if(@$_GET['p'] == "logout" ) 
{ 
	@session_destroy();
	echo "<script>window.location='index.php'</script>";
}


function curl_request($data,$url)
{
    $headers = [
          "Accept: application/json",
          "Content-Type: application/json",
          "api-key: ".API_KEY." "
      ];

    $data = $data;

    $ch = curl_init($url);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'POST');
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    $return = curl_exec($ch);
    $json_data = json_decode($return, true);
    $curl_error = curl_error($ch);
    $http_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
    
    return $json_data;
}

function curl_request_firebase($data,$request,$url)
{
    $headers = [
          "Accept: application/json",
          "Content-Type: application/json",
          "api-key: ".API_KEY." "
      ];

    $data = $data;

    $ch = curl_init($url);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, $request);
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    $return = curl_exec($ch);
    $json_data = json_decode($return, true);
    $curl_error = curl_error($ch);
    $http_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
    
    return $json_data;
}


function customError()
{
    ?>
        <div align="center">
            <img src="frontend_public/assets-minified/images/nodata.png" alt="no data found">
            <h2>Not Found</h2>
            <p>Sorry No data found on this page</p>
        </div>
    <?php
}

function checkImageExist($external_link)

{
    if (@getimagesize($external_link)) 
    {
        return $external_link;
    } 
    else 
    {
        return "frontend_public/assets-minified/images/noProfile.png";
    }
}



function checkVideoUrl($url)
{
    $aws=strpos($url,'amazonaws');
    $cdn=strpos($url,'cdn');
    if($aws==true || $cdn==true)
    {
        return $url;
    }
    else
    {
        return imagebaseurl."/".$url;
    }
}

function time_elapsed_string($datetime, $full = false) {
    $now = new DateTime;
    $ago = new DateTime($datetime);
    $diff = $now->diff($ago);

    $diff->w = floor($diff->d / 7);
    $diff->d -= $diff->w * 7;

    $string = array(
        'y' => 'year',
        'm' => 'month',
        'w' => 'week',
        'd' => 'day',
        'h' => 'hour',
        'i' => 'min',
        's' => 'second',
    );
    foreach ($string as $k => &$v) {
        if ($diff->$k) {
            $v = $diff->$k . ' ' . $v . ($diff->$k > 1 ? 's' : '');
        } else {
            unset($string[$k]);
        }
    }

    if (!$full) $string = array_slice($string, 0, 1);
    //print_r($string);
    return $string ? implode(', ', $string) . ' ago' : ' 1 seconds ago';
}

function replacedateformate($date){
    $newDate = date("Y-m-d");
    return $newDate;
}

?>
