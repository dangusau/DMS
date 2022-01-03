
<?php
include("config.php");

if(isset($_GET['q']))
{
    if (@$_GET['q'] == "editUser") 
    {
        $id=$_GET['id'];
        $url = $baseurl . 'showUsers';
        $data = array(
            'user_id' => $id
        );
        $json_data=@curl_request($data,$url);
        $json_data =$json_data['msg'];
        // get all countries 
        $url = $baseurl . 'showCountries';
        $data = array();
        $allcountrirs=@curl_request($data,$url);
        
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Edit User</h2>
                    </div><div style="height:410px; overflow:scroll;">
                    <form action="process.php?action=editUser" method="post">
                        <input type="hidden" name="user_id" id="" value="<?php echo $json_data['User']['id'];?>">
                        <div class="full_width">
                            <label class="field_title">First Name</label>
                            <input name="first_name" type="text" required="" value="<?php echo $json_data['User']['first_name'];?>">
                        </div>
        
                        <div class="full_width">
                            <label class="field_title">Last Name</label>
                            <input name="last_name" type="text" required="" value="<?php echo $json_data['User']['last_name'];?>">
                        </div>
        
                        <div class="full_width">
                            <label class="field_title">Email</label>
                            <input name="email" type="mail" required="" value="<?php  echo $json_data['User']['email'];?>">
                        </div>
                        
                        <div class="full_width">
                            <label class="field_title">Phone</label>
                            <input name="phone" type="number" required="" value="<?php echo $json_data['User']['phone'];?>">
                        </div>
                        <div class="full_width">
                            <label class="field_title">Country</label>
                            <select name="country_id" class="form-control" required="">
                                <?php 
                                    foreach($allcountrirs['msg'] as $row):
                                ?>
                                    <option value="<?php echo $row['Country']['id'];?>" <?php if($row['Country']['id'] == $json_data['Country']['id'] ){ echo ' selected="selected"';}?>><?php $countryName= strtolower($row['Country']['name']); echo ucwords($countryName);?></option>
                                <?php 
                                    endforeach;
                                ?>
                            </select>
                        </div>
                        <div class="full_width">
                            <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                               Submit
                            </button>
                        </div>
                    </form>
                </div>
                    
                </div>
            </div>
        <?php
    }
    else
    if (@$_GET['q'] == "addrider")
    {
        $url = $baseurl . 'showCountries';
        $data = array();
        $json_data=@curl_request($data,$url);
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Add Rider</h2>
                    </div>
                    <div style="height:400px; overflow:scroll;">
                        <form class="addcategory" action="process.php?action=addRider" method="post">
                            
                            <div class="full_width">
                                <label class="field_title">First Name</label>
                                <input name="first_name" type="text" required="">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Last Name</label>
                                <input name="last_name" type="text" required="">
                            </div>   
                            <div class="full_width">
                                <label class="field_title">Email</label>
                                <input name="email" type="text" required="">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Password</label>
                                <input name="password" type="password" required="">
                            </div>
        
                           
        
                            <div class="full_width">
                                <label class="field_title">Phone No</label>
                                <input name="phone" type="number" required="">
                            </div>
                            
                            <div class="full_width">
                                <label class="field_title">Rider Comission</label>
                                <input name="rider_comission" type="number" required="">
                            </div>
                            
                            <div class="full_width">
                                <label class="field_title">Country</label>
                                <select name="country_id" class="form-control" required="">
                                    <option value="">Select Country</option>
                                    <?php foreach($json_data['msg'] as $row):?>
                                        <option value="<?php echo $row['Country']['id'];?>"><?php $countryName= strtolower($row['Country']['name']); echo ucwords($countryName);?></option>
                                    <?php endforeach;?>
                                </select>
                            </div>
                            <div class="full_width">
                                <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                                Submit
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        <?php
    }
    else
    if (@$_GET['q'] == "editRider")
    {
        $id=$_GET['id'];
        $url = $baseurl . 'showUsers';
        $data = array(
            'user_id' => $id
        );
        $json_data=@curl_request($data,$url);
        $json_data =$json_data['msg'];
        // get all countries 
        $url = $baseurl . 'showCountries';
        $data = array();
        $allcountrirs=@curl_request($data,$url);
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Edit Rider</h2>
                    </div>
                    <div style="height:407px; overflow:scroll;">
                    <form action="process.php?action=editRider" method="post">
                        <input name="user_id" type="hidden" value="<?php echo $json_data['User']['id'];?>" required="">
                        <div class="full_width">
                            <label class="field_title">First Name</label>
                            <input name="first_name" type="text" value="<?php echo $json_data['User']['first_name'];?>" required="">
                        </div>
        
                        <div class="full_width">
                            <label class="field_title">Last Name</label>
                            <input name="last_name" type="text" value="<?php echo $json_data['User']['last_name'];?>" required="">
                        </div>
        
                        <div class="full_width">
                            <label class="field_title">Phone #</label>
                            <input name="phone" type="text" value="<?php echo $json_data['User']['phone'];?>" required="">
                        </div>
        
                        <div class="full_width">
                            <label class="field_title">Email</label>
                            <input name="email" type="text" value="<?php echo $json_data['User']['email'];?>" required="">
                        </div>
        
                        <div class="full_width">
                            <label class="field_title">Country</label>
                            <select name="country_id" class="form-control" required="">
                                <?php 
                                    foreach($allcountrirs['msg'] as $row):
                                ?>
                                    <option value="<?php echo $row['Country']['id'];?>" <?php if($row['Country']['id'] == $json_data['Country']['id'] ){ echo ' selected="selected"';}?>><?php $countryName= strtolower($row['Country']['name']); echo ucwords($countryName);?></option>
                                <?php 
                                    endforeach;
                                ?>
                            </select>
                        </div>
        
                            <div class="full_width">
                                <label class="field_title">Rider Comission</label>
                                <input name="rider_comission" type="number" required="" value="<?php echo $json_data['User']['rider_comission'];?>">
                            </div>
                                                
                        <div class="full_width">
                            <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                               Submit
                            </button>
                        </div>
                    </form>
                </div>
                </div>
            </div>
        <?php
    }
    else
    if (@$_GET['q'] == "adduser")
    {   
        $url = $baseurl . 'showCountries';
        $data = array();
        $json_data=@curl_request($data,$url);
        
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Add User</h2>
                    </div>
                    <div style="height:auto;height:400px; overflow:scroll;">
                        <form action="process.php?action=addUser" method="post">
                            <div class="full_width">
                                <label class="field_title">First Name</label>
                                <input name="first_name" type="text" required="">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Last Name</label>
                                <input name="last_name" type="text" required="">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Email</label>
                                <input name="email" type="mail" required="">
                            </div>
                            
                            <div class="full_width">
                                <label class="field_title">Phone</label>
                                <input name="phone" type="number" required="">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Password</label>
                                <input name="password" type="password" required="">
                            </div>
                            <div class="full_width">
                                <label class="field_title">Country</label>
                                <select name="country_id" class="form-control" required="">
                                    <option value="">Select Country</option>
                                    <?php foreach($json_data['msg'] as $row):?>
                                        <option value="<?php echo $row['Country']['id'];?>"><?php $countryName= strtolower($row['Country']['name']); echo ucwords($countryName);?></option>
                                    <?php endforeach;?>
                                </select>
                            </div>
                            <div class="full_width">
                                <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                                Submit
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        <?php
    }
    else
    if (@$_GET['q'] == "productDetail")
    {
        $id=@$_GET['id'];
        $data =array(
            "order_id" => $id
        );
        
        $url=$baseurl . 'showOrders'; 
        
        $json_data=@curl_request($data,$url);
        
        // echo json_encode($json_data);
        
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <h2 style="font-weight: 300;" align="center">Order Details (#<?php echo $json_data['msg']['Order']['id']; ?>)</h2>
                    <div style="height:400px; overflow:scroll;">
                        
                            <table id="table_view" class="display" style="width:100%; text-align:left;">
                                <tr class="circleUser-td">
                                    <td style="width:150px;">Order ID</td>
                                    <td><?php echo $json_data['msg']['Order']['id']; ?></td>
                                </tr>
                                <tr class="circleUser-td">
                                    <td style="width:150px;">Item Title</td>
                                    <td><?php echo $json_data['msg']['Order']['item_title']; ?></td>
                                </tr>
                                <tr class="circleUser-td">
                                    <td style="width:150px;">Description</td>
                                    <td><?php echo $json_data['msg']['Order']['item_description']; ?></td>
                                </tr>
                                
                                <tr class="circleUser-td">
                                    <td style="width:150px;">Status</td>
                                    <td>
                                        <?php
                                            if($json_data['msg']['Order']['status']=="0")
                                            {
                                                echo "Pending";
                                            }
                                            else
                                            if($json_data['msg']['Order']['status']=="1")
                                            {
                                                
                                                echo "Ready To Assign";
                                            }
                                            else
                                            if($json_data['msg']['Order']['status']=="2")
                                            {
                                                echo "Completed";
                                            }
                                            else
                                            if($json_data['msg']['Order']['status']=="3")
                                            {
                                                echo "Cancelled";
                                            }
                                            else
                                            if($json_data['msg']['Order']['status']=="4")
                                            {
                                                echo "Order Assigned To Rider";
                                            }
                                            
                                        ?>
                                    </td>
                                </tr>
                                
                                <tr class="circleUser-td">
                                    <td style="width:150px;">Good Type</td>
                                    <td>
                                        <?php echo $json_data['msg']['GoodType']['name'];?>
                                    </td>
                                </tr>
                                
                                <tr class="circleUser-td">
                                    <td style="width:150px;">Package Size</td>
                                    <td>
                                        <?php echo $json_data['msg']['PackageSize']['title'];?>
                                    </td>
                                </tr>
                                
                                <tr class="circleUser-td">
                                    <td style="width:150px;">Total Price</td>
                                    <td>
                                        <?php echo $json_data['msg']['Order']['total'];?>
                                    </td>
                                </tr>
                                
                                <tr class="circleUser-td">
                                    <td style="width:150px;">Order Created</td>
                                    <td>
                                        <?php echo $json_data['msg']['Order']['created'];?>
                                    </td>
                                </tr>
                                
                            </table>
                        
                            <br>
                            <h3 style="font-weight: 400;" align="left">Sender Details</h3>
                            <div style="line-height: 25px;margin-top: 10px;">
                                <div class="full_width" style="font-size:13px;">
                                    <span class="fa fa-user" aria-hidden="true"></span>&nbsp;
                                    <?php echo $json_data['msg']['Order']['sender_name'];?>                
                                </div>
                                <div class="full_width" style="font-size:13px;">
                                    <span class="fa fa-envelope" aria-hidden="true"></span>&nbsp;
                                    <?php echo $json_data['msg']['Order']['sender_email'];?>            
                                </div>
                                <div class="full_width" style="font-size:13px;">
                                    <span class="fa fa-phone" aria-hidden="true"></span>&nbsp;
                                    <?php echo $json_data['msg']['Order']['sender_phone'];?>
                                </div>
        
                                <div class="full_width" style="font-size:13px;">
                                    <span class="fa fa-street-view" aria-hidden="true"></span>&nbsp;
                                    <a href="https://maps.google.com/maps?q=<?php echo $json_data['msg']['Order']['sender_location_lat'];?>,<?php echo $json_data['msg']['Order']['sender_location_long'];?>" target="_blank"><?php echo $json_data['msg']['Order']['sender_location_string'];?></a>
                                </div>
                            </div>
                            
                            
                            <br>
                            <h3 style="font-weight: 400;" align="left">Receiver Details</h3>
                            <div style="line-height: 25px;margin-top: 10px;">
                                <div class="full_width" style="font-size:13px;">
                                    <span class="fa fa-user" aria-hidden="true"></span>&nbsp;
                                    <?php echo $json_data['msg']['Order']['receiver_name'];?>                
                                </div>
                                <div class="full_width" style="font-size:13px;">
                                    <span class="fa fa-envelope" aria-hidden="true"></span>&nbsp;
                                    <?php echo $json_data['msg']['Order']['receiver_email'];?>            
                                </div>
                                <div class="full_width" style="font-size:13px;">
                                    <span class="fa fa-phone" aria-hidden="true"></span>&nbsp;
                                    <?php echo $json_data['msg']['Order']['receiver_phone'];?>
                                </div>
        
                                <div class="full_width" style="font-size:13px;">
                                    <span class="fa fa-street-view" aria-hidden="true"></span>&nbsp;
                                    <a href="https://maps.google.com/maps?q=<?php echo $json_data['msg']['Order']['receiver_location_lat'];?>,<?php echo $json_data['msg']['Order']['receiver_location_long'];?>" target="_blank"><?php echo $json_data['msg']['Order']['receiver_location_string'];?></a>
                                </div>
                            </div>
                            
                            <?php
                                if(is_array(@$json_data['msg']['Order']['RiderOrder']) || is_object(@$json_data['msg']['Order']['RiderOrder']))
                                {
                                    ?>
                                        
                                        <br>
                                        <h3 style="font-weight: 400;" align="left">Rider Details</h3>
                                        <div style="line-height: 25px;margin-top: 10px;">
                                            <div class="full_width" style="font-size:13px;">
                                                <span class="fa fa-user" aria-hidden="true"></span>&nbsp;
                                                <?php echo $json_data['msg']['Order']['RiderOrder']['Rider']['first_name']." ".$json_data['msg']['Order']['RiderOrder']['Rider']['last_name'];?>                
                                            </div>
                                            <div class="full_width" style="font-size:13px;">
                                                <span class="fa fa-envelope" aria-hidden="true"></span>&nbsp;
                                                <?php echo $json_data['msg']['Order']['RiderOrder']['Rider']['email'];?>            
                                            </div>
                                            <div class="full_width" style="font-size:13px;">
                                                <span class="fa fa-phone" aria-hidden="true"></span>&nbsp;
                                                <?php echo $json_data['msg']['Order']['RiderOrder']['Rider']['phone'];?>
                                            </div>
                    
                                            <div class="full_width" style="font-size:13px;">
                                                <span class="fa fa-street-view" aria-hidden="true"></span>&nbsp;
                                                <a href="https://maps.google.com/maps?q=<?php echo $json_data['msg']['Order']['RiderOrder']['Rider']['lat'];?>,<?php echo $json_data['msg']['Order']['RiderOrder']['Rider']['long'];?>" target="_blank">Track Rider</a>
                                            </div>
                                        </div>
                                        
                                    <?php
                                }
                            ?>
                            
                            
                            
                        
                    </div>
                </div>
            </div>
        
        
        <?php
    }
    elseif (@$_GET['q'] == "editCountry")
    {
        $id=$_GET['id'];
        $url = $baseurl . 'showCountries';
        $data = array(
            'country_id' => $id
        );
        $json_data=@curl_request($data,$url);
        $json_data = $json_data['msg'];
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto; overflow:scroll; overflow-x: hidden;">
                    <div class="popup-tile">
                        <h2>Edit Country</h2>
                    </div>
                    <form action="process.php?action=editCountry" method="post">
                        <input type="hidden" name="id" value="<?php echo $json_data['Country']['id'];?>">
        
                        <div class="full_width">
                            <label class="field_title">Iso</label>
                            <input name="iso" type="text" value="<?php echo $json_data['Country']['iso'];?>" required="">
                        </div>
                        <div class="full_width">
                            <label class="field_title">Country</label>
                            <input name="name" type="text" value="<?php echo $json_data['Country']['name'];?>" required="">
                        </div>
        
                        <div class="full_width">
                            <label class="field_title">Currency</label>
                            <input name="currency_symbol" type="text" value="<?php echo $json_data['Country']['currency_symbol'];?>" required="">
                        </div>
        
                        <div class="full_width">
                            <label class="field_title">Country Code</label>
                            <input name="country_code" type="text" value="<?php echo $json_data['Country']['country_code'];?>" required="">
                        </div>
                        
                        <div class="full_width">
                            <label class="field_title">Country</label>
                            <select name="country_id" class="form-control" required="">
                                <option value="">Select Default Currency</option>
                                <option value="1" <?php if($json_data['Country']['country_code'] == "1"){ echo ' selected="selected"';}?>>Default</option>
                            </select>
                        </div>
                        
                        <div class="full_width">
                            <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                                Submit
                            </button>
                        </div>
                    </form>
                    
                </div>
            </div>
        
        <?php   
    }
    elseif (@$_GET['q'] == "addCountry")
    {
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto; overflow:scroll; overflow-x: hidden;">
                    <div class="popup-tile">
                        <h2>Add Country</h2>
                    </div>
                    <form action="process.php?action=addCountry" method="post">
        
                        <div class="full_width">
                            <label class="field_title">Country</label>
                            <input name="name" type="text" value="" required="">
                        </div>
        
                        <div class="full_width">
                            <label class="field_title">Currency</label>
                            <input name="currency_symbol" type="text" value="" required="">
                        </div>
        
        
                        <div class="full_width">
                            <label class="field_title">Country Code</label>
                            <input name="country_code" type="text" value="" required="">
                        </div>
        
                        <div class="full_width">
                            <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                                Submit
                            </button>
                        </div>
                    </form>
                    
                </div>
            </div>
        <?php
    }
    elseif (@$_GET['q'] == "addAdminUser")
    {
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Add Admin User</h2>
                    </div><div style="height:340px; overflow:scroll;">
                    <form action="process.php?action=addAdminUser" method="post">
                        <div class="full_width">
                            <label class="field_title">First Name</label>
                            <input name="first_name" type="text" required="">
                        </div>
        
                        <div class="full_width">
                            <label class="field_title">Last Name</label>
                            <input name="last_name" type="text" required="">
                        </div>
        
                        <div class="full_width">
                            <label class="field_title">Email</label>
                            <input name="email" type="mail" required="">
                        </div>
        
                        <div class="full_width">
                            <label class="field_title">Password</label>
                            <input name="password" type="password" required="">
                        </div>
                        <div class="full_width">
                            <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                               Submit
                            </button>
                        </div> 
                    </form>
                </div>
                    
                </div>
            </div>
        <?php
    }
    elseif (@$_GET['q'] == "editAdminUser")
    {
        $id=$_GET['id'];
        $url = $baseurl . 'showAdminUsers';
        $data = array(
            'id' => $id
        );
        $json_data=@curl_request($data,$url);
        $json_data =$json_data['msg'];
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Edit Admin User</h2>
                    </div><div style="height:270px; overflow:scroll;">
                    <form action="process.php?action=editAdminUser" method="post">
        
                        <input type="hidden" name="admin_id" id="" value="<?php echo $json_data['Admin']['id'];?>">
                        <div class="full_width">
                            <label class="field_title">First Name</label>
                            <input name="first_name" type="text" required="" value="<?php echo $json_data['Admin']['first_name'];?>">
                        </div>
        
                        <div class="full_width">
                            <label class="field_title">Last Name</label>
                            <input name="last_name" type="text" required="" value="<?php echo $json_data['Admin']['last_name'];?>">
                        </div>
        
                        <div class="full_width">
                            <label class="field_title">Email</label>
                            <input name="email" type="mail" required="" value="<?php echo $json_data['Admin']['email'];?>">
                        </div>
        
                        <div class="full_width">
                            <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                               Submit
                            </button>
                        </div>
                    </form>
                </div>
                    
                </div>
            </div>
        <?php
    }
    elseif (@$_GET['q'] == "addCoupon")
    {
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Add Coupon</h2>
                    </div>
                    <div style="height:340px; overflow:scroll;">
                        <form action="process.php?action=addCoupon" method="post">
                            <div class="full_width">
                                <label class="field_title">Coupon Code</label>
                                <input name="coupon_code" type="text" required="">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Limit Users</label>
                                <input name="limit_users" type="number" required="">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Discount</label>
                                <input name="discount" type="number" required="">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Expiry Date</label>
                                <input name="expiry_date" type="date" required="">
                            </div>
        
                            <div class="full_width">
                                <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                                Submit
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        <?php
    }
    elseif (@$_GET['q'] == "addVehicle")
    {
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Add Vehicle</h2>
                    </div>
                    <div style="height:340px; overflow:scroll;">
                        <form action="process.php?action=addVehicleType" method="post" enctype="multipart/form-data">
                            <div class="full_width">
                                <label class="field_title">Name</label>
                                <input  type="text" required="" name="name">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Description</label>
                                <input name="description" type="text" required="" name="description">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Per km Mile Charge</label>
                                <input name="per_km_mile_charge" type="number" required="" name="per_km_mile_charge">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Image</label>
                                <input  type="file"  style="padding-top: 14px;" name="image">
                            </div>
        
                            <div class="full_width">
                                <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                                Submit
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        <?php
    }
    elseif (@$_GET['q'] == "editVehicle")
    {
        $id=$_GET['id'];
        $url = $baseurl . 'showVehicleTypes';
        $data = array(
            'id' => $id
        );
        $json_data=@curl_request($data,$url);
        $json_data =$json_data['msg'];
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Edit Vehicle</h2>
                    </div>
                    <div style="height:330px; overflow:scroll;">
                        <form action="process.php?action=editVehicleType" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="id" value="<?php echo $json_data['VehicleType']['id'];?>">
                            <div class="full_width">
                                <label class="field_title">Name</label>
                                <input name="vehicle_name" type="text" required="" value="<?php echo $json_data['VehicleType']['name'];?>">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Description</label>
                                <input name="description" type="text" required="" value="<?php echo $json_data['VehicleType']['description'];?>">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Per km Mile Charge</label>
                                <input name="per_km_mile_charge" type="number" required="" value="<?php echo $json_data['VehicleType']['per_km_mile_charge'];?>">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Image</label>
                                <input name="image" type="file" required="" style="padding-top: 14px;" value="">
                                <!-- <?php $Image = checkImageExist($imagebaseurl.$json_data['VehicleType']['image']);?>
                                <img src="<?php echo $Image;?>" alt="" width="50px" height="50px"> -->
                            </div>
        
                            <div class="full_width">
                                <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                                Submit
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        
        <?php
   }
   elseif (@$_GET['q'] == "editPackageSize")
   {
       $id=$_GET['id'];
       $url = $baseurl . 'showPackageSize';
       $data = array(
           'id' => $id
       );
       $json_data=@curl_request($data,$url);
       $json_data =$json_data['msg'];
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Edit Package Size</h2>
                    </div>
                    <div style="height:330px; overflow:scroll;">
                        <form action="process.php?action=editPackageSize" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="id" value="<?php echo $json_data['PackageSize']['id'];?>">
                            <div class="full_width">
                                <label class="field_title">Name</label>
                                <input name="name" type="text" required="" value="<?php echo $json_data['PackageSize']['title'];?>">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Description</label>
                                <input name="description" type="text" required="" value="<?php echo $json_data['PackageSize']['description'];?>">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Price</label>
                                <input name="price" type="number" required="" value="<?php echo $json_data['PackageSize']['price'];?>">
                            </div>
        
                            <div class="full_width">
                                <label class="field_title">Image</label>
                                <input name="image" type="file" required="" style="padding-top: 14px;" value="">
                                <!-- <?php $Image = checkImageExist($imagebaseurl.$json_data['PackageSize']['image']);?>
                                <img src="<?php echo $Image;?>" alt="" width="50px" height="50px"> -->
                            </div>
        
                            <div class="full_width">
                                <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                                Submit
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        <?php
    }
    elseif (@$_GET['q'] == "changeAdminPassword")
    {
        $id=$_GET['id'];
        $url = $baseurl . 'showAdminUsers';
        $data = array(
            'id' => $id
        );
        $json_data=@curl_request($data,$url);
        $json_data =$json_data['msg'];
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Change Password</h2>
                    </div>
                    <div style="height:140px; overflow:scroll;">
                        <form action="process.php?action=changeAdminPassword" method="post">
                            <input type="hidden" value="<?php echo $json_data['Admin']['id'];?>" name="user_id">
                            <div class="full_width">
                                <label class="field_title">New Password</label>
                                <input name="password" type="password" required="" value="">
                            </div>
        
                            <div class="full_width">
                                <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                                Submit
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        <?php
    }
    elseif (@$_GET['q'] == "changeUserPassword")
    {
        $id=$_GET['id'];
        $url = $baseurl . 'showUsers';
        $data = array(
            'user_id' => $id
        );
        $json_data=@curl_request($data,$url);
        $json_data =$json_data['msg'];
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Change Password</h2>
                    </div>
                    <div style="height:140px; overflow:scroll;">
                        <form action="process.php?action=changeUserPassword" method="post">
                            <input type="hidden" value="<?php echo $json_data['User']['id'];?>" name="user_id">
                            <div class="full_width">
                                <label class="field_title">New Password</label>
                                <input name="password" type="password" required="" value="">
                            </div>
        
                            <div class="full_width">
                                <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                                Submit
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        
        <?php
    }
    elseif (@$_GET['q'] == "changeRiderPassword")
    {
        $id=$_GET['id'];
        $url = $baseurl . 'showUsers';
        $data = array(
            'user_id' => $id
        );
        $json_data=@curl_request($data,$url);
        $json_data =$json_data['msg'];
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Change Password</h2>
                    </div>
                    <div style="height:140px; overflow:scroll;">
                        <form action="process.php?action=changeRiderPassword" method="post">
                            <input type="hidden" value="<?php echo $json_data['User']['id'];?>" name="user_id">
                            <div class="full_width">
                                <label class="field_title">New Password</label>
                                <input name="password" type="password" required="" value="">
                            </div>
        
                            <div class="full_width">
                                <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                                Submit
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        <?php
    }

    elseif (@$_GET['q'] == "editCoupon")
    {

        $id=$_GET['id'];
        $url = $baseurl . 'showCoupons';
        $data = array(
            'coupon_id' => $id
        );
        $json_data=@curl_request($data,$url);
        $json_data =$json_data['msg'];
?>
    <div class="main-container dataTables_wrapper" id="table_view_wrapper">
        <div style="height:auto;">
            <div class="popup-tile">
                <h2>Edit Coupon</h2>
            </div>
            <div style="height:340px; overflow:scroll;">
                <form action="process.php?action=editCoupon" method="post">
                    <input type="hidden" name="coupon_id" value="<?php echo $json_data['Coupon']['id'];?>">
                    <div class="full_width">
                        <label class="field_title">Coupon Code</label>
                        <input name="coupon_code" type="text" required="" value="<?php echo $json_data['Coupon']['coupon_code'];?>">
                    </div>

                    <div class="full_width">
                        <label class="field_title">Limit Users</label>
                        <input name="limit_users" type="number" required="" value="<?php echo $json_data['Coupon']['limit_users'];?>">
                    </div>

                    <div class="full_width">
                        <label class="field_title">Discount</label>
                        <input name="discount" type="number" required="" value="<?php echo $json_data['Coupon']['discount'];?>">
                    </div>

                    <div class="full_width">
                        <label class="field_title">Expiry Date</label>
                        <input name="expiry_date" type="date" required="" value="<?php echo $json_data['Coupon']['expiry_date'];?>">
                    </div>

                    <div class="full_width">
                        <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                        Submit
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<?php
    } 
    elseif (@$_GET['q'] == "addGoodType")
    {
?>
    <div class="main-container dataTables_wrapper" id="table_view_wrapper">
        <div style="height:auto;">
            <div class="popup-tile">
                <h2>Add Good Type</h2>
            </div>
            <div style="height:140px; overflow:scroll;">
                <form action="process.php?action=addGoodType" method="post">
                    <div class="full_width">
                        <label class="field_title">Name</label>
                        <input  type="text" required="" name="name">
                    </div>

                    <div class="full_width">
                        <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                        Submit
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<?php
    }  
    elseif (@$_GET['q'] == "editGoodType")
    {

        $id=$_GET['id'];
        $url = $baseurl . 'showGoodTypes';
        $data = array(
            'id' => $id
        );
        $json_data=@curl_request($data,$url);
        $json_data =$json_data['msg'];
?>
    <div class="main-container dataTables_wrapper" id="table_view_wrapper">
        <div style="height:auto;">
            <div class="popup-tile">
                <h2>Edit Good Type</h2>
            </div>
            <div style="height:140px; overflow:scroll;">
                <form action="process.php?action=editGoodType" method="post">
                    <input type="hidden" value="<?php echo $json_data['GoodType']['id'];?>" name="id">
                    <div class="full_width">
                        <label class="field_title">Name</label>
                        <input  type="text" required="" name="name" value="<?php echo $json_data['GoodType']['name'];?>">
                    </div>

                    <div class="full_width">
                        <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                        Submit
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<?php
    } 
    elseif (@$_GET['q'] == "assignOrder")
    {
        $url = $baseurl . 'showUsers';
        $data = array(
            'role' => 'user'
        );
        $json_data=@curl_request($data,$url);
        $json_data =$json_data['msg'];
    
?>
    <div class="main-container dataTables_wrapper" id="table_view_wrapper">
        <div style="height:auto;">
            <div class="popup-tile">
                <h2>Show Users</h2>
            </div>
            <div style="height:300px; overflow:scroll;">
                <table id="table_view" class="display" style="width:100%; margin-top: 30px;">

                    <thead>
                        <tr>
                            <th class="user-th">ID</th>
                            <th class="user-th">Name</th>
                            <th class="user-th">Phone</th>
                            <th class="user-th">Country</th>
                            <th class="user-th">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php 
                            if($json_data!="")
                            {
                                foreach ($json_data as $singleRow): 
                        ?>
                        <tr class="circleUser-td">
                            <td><?php echo $singleRow['User']['id'] ?></td>
                            <td style="width:150px; overflow:hidden;"><?php echo $singleRow['User']['first_name'].' '. $singleRow['User']['last_name']; ?></td>
                            <td><?php echo $singleRow['User']['phone'] ?></td>
                            <td><?php echo $singleRow['Country']['name']; ?></td>
                            <td>
                                <div class="more">
                                    <button id="more-btn" class="more-btn">
                                        <span class="more-dot"></span>
                                        <span class="more-dot"></span>
                                        <span class="more-dot"></span>
                                    </button>
                                    <div class="more-menu">
                                        <div class="more-menu-caret">
                                            <div class="more-menu-caret-outer"></div>
                                            <div class="more-menu-caret-inner"></div>
                                        </div>
                                        <ul class="more-menu-items" tabindex="-1" role="menu" aria-labelledby="more-btn" aria-hidden="true">
                                            <li class="more-menu-item" role="presentation" onclick="">
                                                <button type="button" class="more-menu-btn" role="menuitem">Order Assigned</button>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <?php
                            endforeach; 
                        }
                        ?>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th class="user-th">ID</th>
                            <th class="user-th">Name</th>
                            <th class="user-th">Phone</th>
                            <th class="user-th">Country</th>
                            <th class="user-th">Action</th>
                        </tr>
                    </tfoot>

                </table>

                
            </div>
        </div>
    </div>
<?php
    }
    elseif (@$_GET['q'] == "addPackageSize")
    {
?>
    <div class="main-container dataTables_wrapper" id="table_view_wrapper">
        <div style="height:auto;">
            <div class="popup-tile">
                <h2>Add Package Size</h2>
            </div>
            <div style="height:340px; overflow:scroll;">
                <form action="process.php?action=addPackageSize" method="post" enctype="multipart/form-data">
                    <div class="full_width">
                        <label class="field_title">Name</label>
                        <input  type="text" required="" name="name">
                    </div>

                    <div class="full_width">
                        <label class="field_title">Description</label>
                        <input  type="text" required="" name="description">
                    </div>
                    <div class="full_width">
                        <label class="field_title">Price</label>
                        <input name="price" type="number" required="" >
                    </div>

                    <div class="full_width">
                        <label class="field_title">Image</label>
                        <input  type="file"  style="padding-top: 14px;" name="image">
                    </div>

                    <div class="full_width">
                        <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                        Submit
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<?php
    }
    else
    if (@$_GET['q'] == "changeStatus")
    {
        
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Change Rider Status</h2>
                    </div>
                    
                    <form action="process.php?action=changeRiderStatus" method="post">
                        <input type="hidden" name="user_id" id="" value="<?php echo @$_GET['id']; ?>">
                        <div class="full_width">
                            <label class="field_title">Country</label>
                            <select name="status" class="form-control" required="">
                                 <option value="0">Active</option>
                                 <option value="1">Blocked</option>
                                 <option value="2">Pending</option>
                            </select>
                        </div>
                        <div class="full_width">
                            <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                               Submit
                            </button>
                        </div>
                    </form>
                </div>
                    
                </div>
            </div>
        <?php
    }
    else
    if (@$_GET['q'] == "CreateOrder")
        {
            // show User 
            $url = $baseurl . 'showUsers';
            $data = array(
                'role' => 'user'
            );
            $json_data=@curl_request($data,$url);
            $json_data =$json_data['msg'];
            // goodsType
            $url_showGoodTypes = $baseurl . 'showGoodTypes';
            $data_showGoodTypes = array(
            );
            $json_data_showGoodTypes=@curl_request($data_showGoodTypes,$url_showGoodTypes);
            $json_data_showGoodTypes =$json_data_showGoodTypes['msg'];
            // Package Size 
            $url_showPackageSize = $baseurl . 'showPackageSize';
            $data_showPackageSize = array(
            );
            $json_data_showPackageSize=@curl_request($data_showPackageSize,$url_showPackageSize);
            $json_data_showPackageSize =$json_data_showPackageSize['msg'];
            // Coupon Code 
            $url_showCoupons = $baseurl . 'showCoupons';
            $data_showCoupons = array(
            );
            $json_data_showCoupons=@curl_request($data_showCoupons,$url_showCoupons);
            $json_data_showCoupons =$json_data_showCoupons['msg'];
    ?>
        <div class="main-container dataTables_wrapper" id="table_view_wrapper">
            <div style="height:auto;">
                <div class="popup-tile">
                    <h2>Create Order</h2>
                </div>
                <div style="height:440px; overflow:scroll;">
                    <form action="process.php?action=CreateOrder" method="post" enctype="multipart/form-data">
                        <div class="full_width">
                            <label class="field_title">Select User</label>
                            <select name="user_id" class="form-control" required="">
                                <option value="">Select User</option>
                                <?php 
                                    foreach($json_data as $row):
                                ?>
                                    <option value="<?php echo $row['User']['id'];?>" ><?php echo ucwords($row['User']['first_name'].' '. $row['User']['last_name']); ?></option>
                                <?php 
                                    endforeach;
                                ?>
                            </select>
                        </div>

                        <div class="full_width">
                            <label class="field_title">Goods Type</label>
                            <select name="good_type_id" class="form-control" required="">
                                <option value="">Select Goods Type</option>
                                <?php 
                                    foreach($json_data_showGoodTypes as $row):
                                ?>
                                    <option value="<?php echo $row['GoodType']['id'];?>" ><?php echo ucwords($row['GoodType']['name']); ?></option>
                                <?php 
                                    endforeach;
                                ?>
                            </select>
                        </div>
                        <div class="full_width">
                            <label class="field_title">Title</label>
                            <input  type="text" required="" name="title">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Description</label>
                            <input  type="text" required="" name="description">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Pickup date</label>
                            <input  type="datetime-local" required="" name="date">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Sender Name</label>
                            <input  type="text" required="" name="sender_name">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Sender Email</label>
                            <input  type="mail" required="" name="sender_email">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Sender Phone</label>
                            <input  type="number" required="" name="sender_phone">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Receiver Name</label>
                            <input  type="text" required="" name="receiver_name">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Receiver Email</label>
                            <input  type="mail" required="" name="receiver_email">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Receiver Phone</label>
                            <input  type="number" required="" name="receiver_phone">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Sender_location_lat</label>
                            <input  type="text" required="" name="sender_location_lat">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Sender_location_long</label>
                            <input  type="text" required="" name="sender_location_long">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Sender_location_string</label>
                            <input  type="text" required="" name="sender_location_string">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Sender Address</label>
                            <input  type="text" required="" name="sender_address">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Receiver_location_lat</label>
                            <input  type="text" required="" name="receiver_location_lat">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Receiver_location_long</label>
                            <input  type="text" required="" name="receiver_location_long">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Receiver_location_string</label>
                            <input  type="text" required="" name="receiver_location_string">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Receiver Address</label>
                            <input  type="text" required="" name="receiver_address">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Package Size</label>
                            <select name="package_size_id" class="form-control" required="">
                            <option value="">Select Package Size</option>
                                <?php 
                                    foreach($json_data_showPackageSize as $row):
                                ?>
                                    <option value="<?php echo $row['PackageSize']['id'];?>" ><?php echo ucwords($row['PackageSize']['title']); ?></option>
                                <?php 
                                    endforeach;
                                ?>
                            </select>
                        </div>

                        <div class="full_width">
                            <label class="field_title">Price</label>
                            <input  type="number" required="" name="price">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Discount</label>
                            <input  type="number" required="" name="discount">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Delivery Fee</label>
                            <input  type="number" required="" name="delivery_fee">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Coupon Code</label>
                            <select name="coupon_id" class="form-control" required="">
                                <option value="">Select Coupon</option>
                                <?php 
                                    foreach($json_data_showCoupons as $row):
                                ?>
                                    <option value="<?php echo $row['Coupon']['id'];?>" ><?php echo ucwords($row['Coupon']['coupon_code']); ?></option>
                                <?php 
                                    endforeach;
                                ?>
                            </select>
                        </div>

                        <div class="full_width">
                            <label class="field_title">Cod</label>
                            <input  type="text" required="" name="cod">
                        </div>

                        <div class="full_width">
                            <label class="field_title">Total</label>
                            <input  type="number" required="" name="total">
                        </div>

                        <div class="full_width">
                            <button class="com-button com-submit-button com-button--large " type="submit" style="width: 100%;" align="center">
                            Submit
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    <?php
    }
    else
    if (@$_GET['q'] == "showDocuments")
    {
        $user_id=$_GET['id'];
        
        $url=$baseurl . 'showUsers'; 
        $data =array(
            'user_id' => $user_id,
        );
        
            
        $json_data=@curl_request($data,$url);
        
        
        ?>
            <div class="main-container dataTables_wrapper" id="table_view_wrapper">
                <div style="height:auto;">
                    <div class="popup-tile">
                        <h2>Rider Documents</h2>
                    </div>
                    
                    <div style="overflow:scroll;">
                        
                        <div style="height:300px; overflow:scroll;">
                        
                        <table id="table_view" class="display" style="width:100%; margin-top: 30px;">
        
                            <thead>
                                <tr>
                                    <th class="user-th">ID</th>
                                    <th class="user-th">Document Type</th>
                                    <th class="user-th">Attachment</th>
                                    <th class="user-th">Status</th>
                                    <th class="user-th">Created</th>
                                    <th class="user-th">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <?php 
                                    if(is_array($json_data['msg']['UserDocument']) || is_object($json_data['msg']['UserDocument']))
                                    {
                                        foreach ($json_data['msg']['UserDocument'] as $singleRow): 
                                            ?>
                                                <tr class="circleUser-td">
                                                    <td><?php echo $singleRow['id']; ?></td>
                                                    <td><?php echo $singleRow['type']; ?></td>
                                                    <td><a href="<?php echo imagebaseurl.$singleRow['attachment']; ?>" target="_blank">View Attachment</a></td>
                                                    <td>
                                                        <?php 
                                                        
                                                            if($singleRow['status']==0)
                                                            {
                                                                echo "Pending";
                                                            }
                                                            else
                                                            if($singleRow['status']==1)
                                                            {
                                                                echo "Accepted";
                                                            }
                                                            else
                                                            if($singleRow['status']==2)
                                                            {
                                                                ?>
                                                                    <span style="color:red;">Rejected</span>
                                                                <?php
                                                            }
                                                        ?>
                                                    </td>
                                                    <td><?php echo $singleRow['created']; ?></td>
                                                    <td>
                                                        <div class="more">
                                                            <button id="more-btn" class="more-btn">
                                                                <span class="more-dot"></span>
                                                                <span class="more-dot"></span>
                                                                <span class="more-dot"></span>
                                                            </button>
                                                            <div class="more-menu">
                                                                <div class="more-menu-caret">
                                                                    <div class="more-menu-caret-outer"></div>
                                                                    <div class="more-menu-caret-inner"></div>
                                                                </div>
                                                                <ul class="more-menu-items" tabindex="-1" role="menu" aria-labelledby="more-btn" aria-hidden="true">
                                                                    <a href="process.php?action=changeDocumentStatus&id=<?php echo $singleRow['id']; ?>&status=1&user_id=<?php echo $singleRow['user_id']; ?>">
                                                                        <li class="more-menu-item" role="presentation">
                                                                            <button type="button" class="more-menu-btn" role="menuitem">Accept</button>
                                                                        </li>
                                                                    </a>
                                                                    <a href="process.php?action=changeDocumentStatus&id=<?php echo $singleRow['id']; ?>&status=2&user_id=<?php echo $singleRow['user_id']; ?>">
                                                                        <li class="more-menu-item" role="presentation">
                                                                            <button type="button" class="more-menu-btn" role="menuitem">Reject</button>
                                                                        </li>
                                                                    </a>
                                                                    
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            <?php
                                        endforeach; 
                                    }
                                ?>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th class="user-th">ID</th>
                                    <th class="user-th">Document Type</th>
                                    <th class="user-th">Attachment</th>
                                    <th class="user-th">Status</th>
                                    <th class="user-th">Created</th>
                                    <th class="user-th">Action</th>
                                </tr>
                            </tfoot>
        
                        </table>
        
                        
                    </div>
                    
                </div>
            </div>
        <?php
    }
    
    
}
?>