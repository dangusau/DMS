<?php

include("config.php"); 

if(isset($_SESSION[PRE_FIX.'id']))
{
    
    if(isset($_GET['action'])){

        if($_GET['action']=="addUser") 
        {
    
            $first_name=$_POST['first_name'];
            $last_name=$_POST['last_name'];
            $email=$_POST['email'];
            $password=$_POST['password'];
            $phone=$_POST['phone'];
            $country_id=$_POST['country_id'];


            $data = [
                "first_name" => $first_name,
                "last_name" => $last_name,
                "email" => $email,
                "role" => 'user',
                "password" => $password,
                "phone" =>$phone,
                "country_id" => $country_id
            ];
                
                $url=$baseurl . 'addUser';
                
                $json_data=@curl_request($data,$url);
            
                if($json_data['code'] == "200")
                {
                    
                    echo "<script>window.location='dashboard.php?p=users&action=success'</script>";
                }
                else
                {
                    echo "<script>window.location='dashboard.php?p=users&action=error'</script>";
                }
        }
        
        if($_GET['action']=="editUser") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            $first_name=$_POST['first_name'];
            $last_name=$_POST['last_name'];
            $email=$_POST['email'];
            $user_id = $_POST['user_id'];
            $phone=$_POST['phone'];
            $country_id=$_POST['country_id'];


            $data = [
                "first_name" => $first_name,
                "last_name" => $last_name,
                "email" => $email,
                "user_id" => $user_id,
                "role" => "user",
                "phone" =>$phone,
                "country_id" => $country_id
            ];
                
                $url=$baseurl . 'editUser';
                
                $json_data=@curl_request($data,$url);
            
                if($json_data['code'] == "200")
                {
                    
                    echo "<script>window.location='dashboard.php?p=users&action=success'</script>";
                }
                else
                {
                    echo "<script>window.location='dashboard.php?p=users&action=error'</script>";
                }
        }
        
        if($_GET['action']=="changeUserPassword") 
        {
            
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $password = $_POST['password'];
            $user_id = $_POST['user_id'];
			
            $data = 
                array(
                    "user_id" => $user_id,
                    "password" => $password
                    );
                
                $url=$baseurl . 'changePassword';
                
                $json_data=@curl_request($data,$url);
            
                if($json_data['code'] == "200")
                {
                    
                    echo "<script>window.location='dashboard.php?p=users&action=success'</script>";
                }
                else
                {
                    echo "<script>window.location='dashboard.php?p=users&action=error'</script>";
                }
        }

        if($_GET['action']=="deleteUser") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $id = $_GET['id'];

            $data = [
                "user_id" => $id
            ];
                
            $url=$baseurl . 'deleteUser';
            
            $json_data=@curl_request($data,$url);
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=users&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=users&action=error'</script>";
            }
        }

        if($_GET['action']=="addAdminUser") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $email=$_POST['email'];
            $password=$_POST['password'];
            $first_name=$_POST['first_name'];
            $last_name=$_POST['last_name'];


            $data = [
                "email" => $email,
                "password" => $password,
                "first_name" => $first_name,
                "last_name" => $last_name,
                "role" => "admin"
            ];  
                $url=$baseurl . 'addAdminUser';
                
                $json_data=@curl_request($data,$url);
            
                if($json_data['code'] == "200")
                {
                    
                    echo "<script>window.location='dashboard.php?p=adminUsers&action=success'</script>";
                }
                else
                {
                    echo "<script>window.location='dashboard.php?p=adminUsers&action=error'</script>";
                }
        }

        if($_GET['action']=="editAdminUser") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $email=$_POST['email'];
            $first_name=$_POST['first_name'];
            $last_name=$_POST['last_name'];
            $admin_id =$_POST['admin_id'];

            $data = [
                "email" => $email,
                "first_name" => $first_name,
                "last_name" => $last_name,
                "role" => "admin",
                "id" => $admin_id
            ];
                $url=$baseurl . 'editAdminUser';
                
                $json_data=@curl_request($data,$url);
            
                if($json_data['code'] == "200")
                {
                    
                    echo "<script>window.location='dashboard.php?p=adminUsers&action=success'</script>";
                }
                else
                {
                    echo "<script>window.location='dashboard.php?p=adminUsers&action=error'</script>";
                }
        }
        if($_GET['action']=="changeAdminPassword") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $password = $_POST['password'];
            $user_id = $_POST['user_id'];
			
            $data = 
                array(
                    "user_id" => $user_id,
                    "password" => $password
                    );
                
                $url=$baseurl . 'changeAdminPassword';
                
                $json_data=@curl_request($data,$url);
            
                if($json_data['code'] == "200")
                {
                    
                    echo "<script>window.location='dashboard.php?p=adminUsers&action=success'</script>";
                }
                else
                {
                    echo "<script>window.location='dashboard.php?p=adminUsers&action=error'</script>";
                }
        }

        if($_GET['action']=="deleteAdmin") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            $id = $_GET['id'];

            $data = [
                "user_id" => $id
            ];
                
            $url=$baseurl . 'deleteAdmin';
            
            $json_data=@curl_request($data,$url);
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=adminUsers&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=adminUsers&action=error'</script>";
            }
        }

        if($_GET['action']=="addRider") 
        {
    
            $first_name=$_POST['first_name'];
            $last_name=$_POST['last_name'];
            $email=$_POST['email'];
            $password=$_POST['password'];
            $phone=$_POST['phone'];
            $country_id=$_POST['country_id'];
            $rider_comission=$_POST['rider_comission'];


            $data = [
                "first_name" => $first_name,
                "last_name" => $last_name,
                "email" => $email,
                "role" => 'rider',
                "password" => $password,
                "phone" =>$phone,
                "country_id" => $country_id,
                "rider_comission"=>$rider_comission
            ];
        
                
                $url=$baseurl . 'addUser';
                
                $json_data=@curl_request($data,$url);
            
                if($json_data['code'] == "200")
                {
                    
                    echo "<script>window.location='dashboard.php?p=rider&action=success'</script>";
                }
                else
                {
                    echo "<script>window.location='dashboard.php?p=rider&action=error'</script>";
                }
        }

        if($_GET['action']=="editRider") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            $first_name=$_POST['first_name'];
            $last_name=$_POST['last_name'];
            $email=$_POST['email'];
            $user_id = $_POST['user_id'];
            $phone=$_POST['phone'];
            $country_id=$_POST['country_id'];
            $rider_comission=$_POST['rider_comission'];


            $data = [
                "first_name" => $first_name,
                "last_name" => $last_name,
                "email" => $email,
                "user_id" => $user_id,
                "role" => 'rider',
                "phone" =>$phone,
                "country_id" => $country_id,
                "rider_comission"=>$rider_comission
            ];
                $url=$baseurl . 'editUser';
                
                $json_data=@curl_request($data,$url);
            
                if($json_data['code'] == "200")
                {
                    
                    echo "<script>window.location='dashboard.php?p=rider&action=success'</script>";
                }
                else
                {
                    echo "<script>window.location='dashboard.php?p=rider&action=error'</script>";
                }
        }

        if($_GET['action']=="changeRiderPassword") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $password = $_POST['password'];
            $user_id = $_POST['user_id'];
			
            $data = 
                array(
                    "user_id" => $user_id,
                    "password" => $password
                    );
                
                $url=$baseurl . 'changePassword';
                
                $json_data=@curl_request($data,$url);
            
                if($json_data['code'] == "200")
                {
                    
                    echo "<script>window.location='dashboard.php?p=rider&action=success'</script>";
                }
                else
                {
                    echo "<script>window.location='dashboard.php?p=rider&action=error'</script>";
                }
        }

        if($_GET['action']=="deleteRider") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $id = $_GET['id'];

            $data = [
                "user_id" => $id
            ];
                
            $url=$baseurl . 'deleteUser';
            
            $json_data=@curl_request($data,$url);
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=rider&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=rider&action=error'</script>";
            }
        }

        if($_GET['action']=="addCountry") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $name=$_POST['name'];
            $currency_symbol=$_POST['currency_symbol'];
            $country_code=$_POST['country_code'];       
            
            $data = [
                "iso"=>'',
                "name" => $name,
                "iso3" => '',
                "country_code" => $country_code,
                "currency_code"=>'',
                "currency_symbol"=>$currency_symbol,
                "active"=>'0'
            ];
                
            $url=$baseurl . 'addCountry';
            
            $json_data=@curl_request($data,$url);
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=country&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=country&action=error'</script>";
            }
        }

        if($_GET['action']=="editCountry") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $iso=$_POST['iso'];
            $name=$_POST['name'];
            $currency_symbol=$_POST['currency_symbol'];
            $country_code=$_POST['country_code']; 
            $id=$_POST['id'];      
            
            $data = [
                "iso"=>$iso,
                "name" => $name,
                "iso3" => '',
                "country_code" => $country_code,
                "currency_code"=>'',
                "currency_symbol"=>$currency_symbol,
                "active"=>'0',
                "id"=>$id
            ];
                
            $url=$baseurl . 'addCountry';
            
            $json_data=@curl_request($data,$url);
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=country&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=country&action=error'</script>";
            }
        }

        if($_GET['action']=="deleteCountry") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $id = $_GET['id'];

            $data = [
                "country_id" => $id
            ];
                
            $url=$baseurl . 'deleteCountry';
            
            $json_data=@curl_request($data,$url);
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=country&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=country&action=error'</script>";
            }
        }

        if($_GET['action']=="addCoupon") 
        {
            
            $coupon_code=$_POST['coupon_code'];
            $limit_users=$_POST['limit_users'];
            $discount=$_POST['discount'];
            $expiry_date=$_POST['expiry_date'];
            $expiry_date = replacedateformate($expiry_date);
            $data = [
                "coupon_code" => $coupon_code,
                "limit_users" => $limit_users,
                "discount" => $discount,
                "expiry_date" => $expiry_date
            ];
            
                
            $url=$baseurl . 'addCoupon';
            
            $json_data=@curl_request($data,$url);
            
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=couponCode&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=couponCode&action=error'</script>";
            }
        }

        if($_GET['action']=="editCoupon") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            $coupon_code=$_POST['coupon_code'];
            $limit_users=$_POST['limit_users'];
            $discount=$_POST['discount'];
            $expiry_date=$_POST['expiry_date'];
            $coupon_id=$_POST['coupon_id'];
            // $expiry_date = replacedateformate($expiry_date);
            

            $data = [
                "coupon_code" => $coupon_code,
                "limit_users" => $limit_users,
                "discount" => $discount,
                "expiry_date" => $expiry_date,
                "id" => $coupon_id
            ];
                
            $url=$baseurl . 'addCoupon';
            
            $json_data=@curl_request($data,$url);
            
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=couponCode&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=couponCode&action=error'</script>";
            }
        }

        if($_GET['action']=="deleteCoupon") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            $id = $_GET['id'];

            $data = [
                "coupon_id" => $id
            ];
                
            $url=$baseurl . 'deleteCoupon';
            
            $json_data=@curl_request($data,$url);
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=couponCode&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=couponCode&action=error'</script>";
            }
        }

        if($_GET['action']=="addVehicleType") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            $name=$_POST['name'];
            $description=$_POST['description'];
            $per_km_mile_charge=$_POST['per_km_mile_charge'];
            $imagedata = file_get_contents($_FILES['image']['tmp_name']);
            $base64 = base64_encode($imagedata);
    
            $data = [
                "name" => $name,
                "description" => $description,
                "per_km_mile_charge" => $per_km_mile_charge,
                "image" =>  $base64
            ];

            $url=$baseurl . 'addVehicleType';
            $json_data=@curl_request($data,$url);
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=vehicleType&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=vehicleType&action=error'</script>";
            }
        }

        if($_GET['action']=="editVehicleType") 
        {
        
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $name=$_POST['vehicle_name'];
            $description=$_POST['description'];
            $per_km_mile_charge=$_POST['per_km_mile_charge'];
            $imagedata = file_get_contents($_FILES['image']['tmp_name']);
            $base64 = base64_encode($imagedata);
            $id = $_POST['id'];

            $data = [
                "name" => $name,
                "description" => $description,
                "per_km_mile_charge" => $per_km_mile_charge,
                "image" => $base64,
                "id"=> $id
            ];
                
            $url=$baseurl . 'addVehicleType';
            
            $json_data=@curl_request($data,$url);
            
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=vehicleType&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=vehicleType&action=error'</script>";
            }
        }

        if($_GET['action']=="deleteVehicleType") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $id = $_GET['id'];

            $data = [
                "id" => $id
            ];
                
            $url=$baseurl . 'deleteVehicleType';
            
            $json_data=@curl_request($data,$url);
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=vehicleType&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=vehicleType&action=error'</script>";
            }
        }

        if($_GET['action']=="addGoodType") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $name=$_POST['name'];
    
            $data = [
                "name" => $name
            ];
    
                
            $url=$baseurl . 'addGoodType';
            
            $json_data=@curl_request($data,$url);
            
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=goodType&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=goodType&action=error'</script>";
            }
        }

        if($_GET['action']=="editGoodType") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $id=$_POST['id'];
            $name=$_POST['name'];
    
            $data = [
                "name" => $name,
                "id" => $id
            ];
    
                
            $url=$baseurl . 'addGoodType';
            
            $json_data=@curl_request($data,$url);
            
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=goodType&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=goodType&action=error'</script>";
            }
        }

        if($_GET['action']=="deleteGoodType") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            $id = $_GET['id'];

            $data = [
                "id" => $id
            ];
                
            $url=$baseurl . 'deleteGoodType';
            
            $json_data=@curl_request($data,$url);
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=goodType&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=goodType&action=error'</script>";
            }
        }

        if($_GET['action']=="currentAdminChangePassword") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $previous_password = $_POST['password_previous'];
            $new_password = $_POST['new_password'];
            $renewpas = $_POST['password'];
            $id=$_POST['user_id'];
			
            $data =array(
                    "user_id"=>$id,
                    "old_password" => $previous_password,
                    "new_password" => $new_password
                );

                
                $url=$baseurl . 'currentAdminChangePassword';
                
                $json_data=@curl_request($data,$url);
            
                if($json_data['code'] == "200")
                {
                    
                    echo "<script>window.location='dashboard.php?p=changePassword&action=success'</script>";
                }
                else
                {
                    echo "<script>window.location='dashboard.php?p=changePassword&action=error'</script>";
                }
        }

        if($_GET['action']=="addPackageSize") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $name=$_POST['name'];
            $description=$_POST['description'];
            $price=$_POST['price'];
            $imagedata = file_get_contents($_FILES['image']['tmp_name']);
            $base64 = base64_encode($imagedata);
    
            $data = [
                "title" => $name,
                "description" => $description,
                "price" => $price,
                "image" =>  $base64
            ];

            $url=$baseurl . 'addPackageSize';
            $json_data=@curl_request($data,$url);
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=package&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=package&action=error'</script>";
            }
        }

        if($_GET['action']=="editPackageSize") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $name=$_POST['name'];
            $description=$_POST['description'];
            $price=$_POST['price'];
            $imagedata = file_get_contents($_FILES['image']['tmp_name']); 
            $base64 = base64_encode($imagedata);
            $id = $_POST['id'];
    
            $data = [
                "title" => $name,
                "description" => $description,
                "price" => $price,
                "image" =>  $base64,
                "id" => $id
            ];

            $url=$baseurl . 'addPackageSize';
            $json_data=@curl_request($data,$url);
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=package&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=package&action=error'</script>";
            }
        }
        
        if($_GET['action']=="deletePackageSize") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            
            $id = $_GET['id'];

            $data = [
                "id" => $id
            ];
                
            $url=$baseurl . 'deletePackageSize';
            
            $json_data=@curl_request($data,$url);
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=package&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=package&action=error'</script>";
            }
        }

        if($_GET['action']=="assignRider") 
        {
            $id=$_GET['id'];
            $orderID=$_GET['orderID'];
    
            $data = [
                "rider_user_id" => $id,
                "order_id" => $orderID,
            ];

            $url=$baseurl . 'assignOrderToRider';
            $json_data=@curl_request($data,$url);
            
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=manageOrder&status=activeOrder&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=manageOrder&status=activeOrder&action=error'</script>";
            }
        }
        
        if($_GET['action']=="acceptOrder") 
        {
            $order_id=$_GET['orderID'];
            $status="1";
            
            $data = [
                "order_id" => $order_id,
                "status"=> $status
            ];

            $url=$baseurl . 'adminResponseAgainstOrder';
            $json_data=@curl_request($data,$url);
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=manageOrder&status=pendingOrder&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=manageOrder&status=pendingOrder&action=error'</script>";
            }
        }
        
        
        if($_GET['action']=="changeRiderStatus") 
        {
            if(status=="demo")
            {
                echo "<script>window.location='dashboard.php?p=users&action=notAllowDemo'</script>";
                die();
            }
            
            $user_id=$_POST['user_id'];
            $status=$_POST['status'];
            
            $data = [
                "user_id" => $user_id,
                "active"=> $status
            ];

            $url=$baseurl . 'updateUserStatus';
            $json_data=@curl_request($data,$url);
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=rider&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=rider&action=error'</script>";
            }
        }
        
        if($_GET['action']=="changeDocumentStatus") 
        {
            
            $user_id=$_GET['user_id'];
            $status=$_GET['status'];
            $user_document_id=$_GET['id'];
            
            $data = [
                "user_id" => $user_id,
                "status"=> $status,
                "user_document_id" => $user_document_id
            ];

            
            $url=$baseurl . 'approveDocument';
            $json_data=@curl_request($data,$url);
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=rider&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=rider&action=error'</script>";
            }
        }
        if($_GET['action']=="changeWithdrawRequestStatus") 
        {
            
            $id=$_GET['id'];
            $status=$_GET['status'];
            $data = [
                "id" => $id,
                "status" => $status
            ];  
            $url = $baseurl . 'withdrawRequestApproval';
            
            
            $json_data=@curl_request($data,$url);
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=withdrawRequest&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=withdrawRequest&action=error'</script>";
            }
        }
    }

    if($_GET['action']=="CreateOrder") 
    {
        // 1:18:00","sender_name":"zainoor","sender_email":"zain09afzal@gmail.com"
        $user_id=$_POST['user_id'];
        $good_type_id=$_POST['good_type_id'];
        $title=$_POST['title'];
        $description = $_POST['description'];
        $date = date_create($_POST['date']);
        $date_format = date_format($date,"Y/m/d H:i");
        $sender_phone = $_POST['sender_phone'];
        $sendName=$_POST['sender_name'];
        $sender_email=$_POST['sender_email'];
        $receiver_name=$_POST['receiver_name'];
        $receiver_email = $_POST['receiver_email'];
        $receiver_phone = $_POST['receiver_phone'];
        $sender_location_lat = $_POST['sender_location_lat'];
        $sender_location_long = $_POST['sender_location_long'];
        $sender_address_detail = $_POST['sender_address'];
        $receiver_location_lat = $_POST['receiver_location_lat'];
        $receiver_location_long = $_POST['receiver_location_long'];
        $receiver_address_detail = $_POST['receiver_address'];
        $package_size_id = $_POST['package_size_id'];
        $price = $_POST['price'];
        $discount = $_POST['discount'];
        $delivery_fee = $_POST['delivery_fee'];
        $coupon_id = $_POST['coupon_id'];
        $total = $_POST['total'];
        $sender_location_string = $_POST['sender_location_string'];
        $receiver_location_string = $_POST['receiver_location_string'];
        $cod = $_POST['cod'];

        $data = [
            "user_id" => $user_id,
            "good_type_id" => $good_type_id,
            "delivery_type_id" => "",
            "item_title" => $title,
            "item_description" => $description,
            "pickup_datetime" => $date_format,
            "sender_name" => $sendName,
            "sender_email"=> $sender_email,
            "sender_phone"=> $sender_phone,
            "receiver_name"=> $receiver_name,
            "receiver_email"=> $receiver_email,
            "receiver_phone"=> $receiver_phone,
            "sender_location_lat"=> $sender_location_lat,
            "sender_location_long"=> $sender_location_long,
            "sender_location_string"=> $sender_location_string,
            "sender_address_detail"=> $sender_address_detail,
            "receiver_location_lat"=> $receiver_location_lat,
            "receiver_location_long"=> $receiver_location_long,
            "receiver_location_string"=> $receiver_location_string,
            "receiver_address_detail"=> $receiver_address_detail,
            "package_size_id"=> $package_size_id,
            "price"=> $price,
            "discount"=> $discount,
            "delivery_fee"=> $delivery_fee,
            "coupon_id"=> $coupon_id,
            "cod"=> $cod,
            "total"=> $total
        ];
            
            $url=$baseurl . 'placeOrder';
            
            $json_data=@curl_request($data,$url);
        
            if($json_data['code'] == "200")
            {
                
                echo "<script>window.location='dashboard.php?p=manageOrder&action=success'</script>";
            }
            else
            {
                echo "<script>window.location='dashboard.php?p=manageOrder&action=error'</script>";
            }
    }
     
    
}



?>