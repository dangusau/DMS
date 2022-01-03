<?php 
if(isset($_SESSION[PRE_FIX.'id']))
{  
    $id = $_GET['id'];
    $url=$baseurl . 'showUserOrders'; 

?>
<div class="qr-content">
    <div class="qr-page-content">
        <div class="qr-page zeropadding">
            <div class="qr-content-area">
                <div class="qr-row">
                    <div class="qr-el">

                        <div class="page-title">
                            <h2>Order History</h2>
                            <div class="head-area">
                            </div>
                        </div>
                        <div class="right" style="padding: 10px 0;">

                            <a href="dashboard.php?p=orderHistory&q=activeOrder&id=<?php echo $id;?>" style="color:white;">
                                <button class="btnActive com-submit-button com-button--large com-button--default <?php if($_GET['p']=='orderHistory' && $_GET['q']=='activeOrder'){ echo 'com-button';} ?>" style="padding: 5px 15px;font-size: 12px;">
                                    <div class="com-submit-button__content"><span>Active Orders</span></div>
                                </button>
                            </a>

                            <a href="dashboard.php?p=orderHistory&q=completeOrder&id=<?php echo $id;?>" style="color:white;">
                                <button class="btnActive com-submit-button com-button--large com-button--default <?php if($_GET['p']=='orderHistory' && $_GET['q']=='completeOrder'){ echo 'com-button';} ?>" style="padding: 5px 15px;font-size: 12px;">
                                    <div class="com-submit-button__content"><span>Completed Orders</span></div>
                                </button>
                            </a>
                                    
                            <a href="dashboard.php?p=orderHistory&q=pendingOrder&id=<?php echo $id;?>" style="color:white;">
                                <button class="btnActive com-submit-button com-button--large com-button--default <?php if($_GET['p']=='orderHistory' && $_GET['q']=='pendingOrder'){ echo 'com-button';} ?>" style="padding: 5px 15px;font-size: 12px;">
                                    <div class="com-submit-button__content"><span>Pending Orders</span></div>
                                </button>
                            </a>
                            
                            <a href="dashboard.php?p=orderHistory&q=cancelOrder&id=<?php echo $id;?>" style="color:white;">
                                <button class="btnActive com-submit-button com-button--large com-button--default <?php if($_GET['p']=='orderHistory' && $_GET['q']=='cancelOrder'){ echo 'com-button';} ?>" style="padding: 5px 15px;font-size: 12px;">
                                    <div class="com-submit-button__content"><span>Cancel Orders</span></div>
                                </button>
                            </a>
                        </div>

                        <?php if($_GET['p']=='orderHistory' && $_GET['q']=='activeOrder'){
                            
                            $data =array(
                                'user_id' =>$id,
                                'status' => 1
                            );
                            
                                
                            $json_data=@curl_request($data,$url);
                            
                            $allusers = [];
                            if ($json_data['code'] == 200) {
                                $allusers = $json_data['msg'];
                            }

                            ?>
                            <table id="table_view" class="display" style="width:100%">
                                <thead>
                                    <tr>

                                        <th class="user-th">ID</th>

                                        <th class="user-th">Name</th>

                                        <th class="user-th">Product Name</th>

                                        <th class="user-th">Price</th>

                                        <th class="user-th">Delivery Type</th>

                                        <th class="user-th">Status</th>

                                        <th class="user-th">Created</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <?php 
                                        if($json_data!="")
                                        {
                                            foreach ($json_data['msg'] as $singleRow): 
                                    ?>
                                    <tr>

                                        <td><?php echo $singleRow['User']['id'];?></td>

                                        <td><?php echo $singleRow['User']['first_name'].$singleRow['User']['last_name'];?></td>

                                        <td>

                                            <?php echo $singleRow['Order']['item_title'];?>

                                        </td>

                                        <td>

                                            <?php echo $singleRow['Order']['price'];?>

                                        </td>

                                        <td>

                                            <?php echo $singleRow['DeliveryType']['title'];?>

                                        </td>
                                        <td>

                                        <?php
                                            if($singleRow['Order']['status'] =="1" )
                                            {
                                                echo "<span style='color:green;'>Active</span>";
                                            }
                                        ?>

                                        </td>

                                        <td>

                                            <?php echo $singleRow['User']['created'];?>

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

                                    <th class="user-th">Product Name</th>

                                    <th class="user-th">Price</th>

                                    <th class="user-th">Delivery Type</th>

                                    <th class="user-th">Status</th>

                                    <th class="user-th">Created</th>

                                </tr>
                                </tfoot>
                            </table>
                        <?php }?>

                        <?php if($_GET['p']=='orderHistory' && $_GET['q']=='completeOrder'){
                            
                            $data =array(
                                'user_id' =>$id,
                                'status' => 2
                            );
                            
                                
                            $json_data=@curl_request($data,$url);
                            
                            $allusers = [];
                            if ($json_data['code'] == 200) {
                                $allusers = $json_data['msg'];
                            }

                            ?>
                            <table id="table_view" class="display" style="width:100%">
                                <thead>
                                <tr>

                                    <th class="user-th">ID</th>

                                    <th class="user-th">Name</th>

                                    <th class="user-th">Product Name</th>

                                    <th class="user-th">Price</th>

                                    <th class="user-th">Delivery Type</th>

                                    <th class="user-th">Status</th>

                                    <th class="user-th">Created</th>

                                </tr>
                                </thead>
                                <tbody>
                                    <?php 
                                        if($json_data!="")
                                        {
                                            foreach ($json_data['msg'] as $singleRow): 
                                    ?>
                                    <tr>

                                        <td><?php echo $singleRow['User']['id'];?></td>

                                        <td><?php echo $singleRow['User']['first_name'].$singleRow['User']['last_name'];?></td>

                                        <td>

                                            <?php echo $singleRow['Order']['item_title'];?>

                                        </td>

                                        <td>

                                            <?php echo $singleRow['Order']['price'];?>

                                        </td>

                                        <td>

                                            <?php echo $singleRow['DeliveryType']['title'];?>

                                        </td>
                                        <td>

                                        <?php
                                            if($singleRow['Order']['status'] =="2" )
                                            {
                                                echo "<span style='color:#042904;'>Completed</span>";  
                                            }
                                        ?>

                                        </td>

                                        <td>

                                            <?php echo $singleRow['User']['created'];?>

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

                                    <th class="user-th">Product Name</th>

                                    <th class="user-th">Price</th>

                                    <th class="user-th">Delivery Type</th>

                                    <th class="user-th">Status</th>

                                    <th class="user-th">Created</th>

                                </tr>
                                </tfoot>
                            </table>
                        <?php }?>

                        <?php if($_GET['p']=='orderHistory' && $_GET['q']=='pendingOrder'){
                            $data =array(
                                'user_id' =>$id,
                                'status' => 0
                            );
                            
                                
                            $json_data=@curl_request($data,$url);
                            
                            $allusers = [];
                            if ($json_data['code'] == 200) {
                                $allusers = $json_data['msg'];
                            }

                            ?>
                            <table id="table_view" class="display" style="width:100%">
                                <thead>
                                <tr>

                                    <th class="user-th">ID</th>

                                    <th class="user-th">Name</th>

                                    <th class="user-th">Product Name</th>

                                    <th class="user-th">Price</th>

                                    <th class="user-th">Delivery Type</th>

                                    <th class="user-th">Status</th>

                                    <th class="user-th">Created</th>

                                </tr>
                                </thead>
                                <tbody>
                                    <?php 
                                        if($json_data!="")
                                        {
                                            foreach ($json_data['msg'] as $singleRow): 
                                    ?>
                                    <tr>

                                        <td><?php echo $singleRow['User']['id'];?></td>

                                        <td><?php echo $singleRow['User']['first_name'].$singleRow['User']['last_name'];?></td>

                                        <td>

                                            <?php echo $singleRow['Order']['item_title'];?>

                                        </td>

                                        <td>

                                            <?php echo $singleRow['Order']['price'];?>

                                        </td>

                                        <td>

                                            <?php echo $singleRow['DeliveryType']['title'];?>

                                        </td>
                                        <td>

                                        <?php
                                            if($singleRow['Order']['status'] =="0" )
                                            {
                                                echo "<span style='color:#bd2c14;'>Pending</span>";
                                            }
                                        ?>

                                        </td>

                                        <td>

                                            <?php echo $singleRow['User']['created'];?>

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

                                    <th class="user-th">Product Name</th>

                                    <th class="user-th">Price</th>

                                    <th class="user-th">Delivery Type</th>

                                    <th class="user-th">Status</th>

                                    <th class="user-th">Created</th>

                                </tr>
                                </tfoot>
                            </table>
                        <?php }?>

                        <?php if($_GET['p']=='orderHistory' && $_GET['q']=='cancelOrder'){
                            $data =array(
                                'user_id' =>$id,
                                'status' => 3
                            );
                            
                                
                            $json_data=@curl_request($data,$url);
                            
                            $allusers = [];
                            if ($json_data['code'] == 200) {
                                $allusers = $json_data['msg'];
                            }

                            ?>
                            <table id="table_view" class="display" style="width:100%">
                                <thead>
                                <tr>

                                    <th class="user-th">ID</th>

                                    <th class="user-th">Name</th>

                                    <th class="user-th">Product Name</th>

                                    <th class="user-th">Price</th>

                                    <th class="user-th">Delivery Type</th>

                                    <th class="user-th">Status</th>

                                    <th class="user-th">Created</th>

                                </tr>
                                </thead>
                                <tbody>
                                    <?php 
                                        if($json_data!="")
                                        {
                                            foreach ($json_data['msg'] as $singleRow): 
                                    ?>
                                    <tr>

                                        <td><?php echo $singleRow['User']['id'];?></td>

                                        <td><?php echo $singleRow['User']['first_name'].$singleRow['User']['last_name'];?></td>

                                        <td>

                                            <?php echo $singleRow['Order']['item_title'];?>

                                        </td>

                                        <td>

                                            <?php echo $singleRow['Order']['price'];?>

                                        </td>

                                        <td>

                                            <?php echo $singleRow['DeliveryType']['title'];?>

                                        </td>
                                        <td>

                                        <?php
                                            if($singleRow['Order']['status'] =="3" )
                                            {
                                                echo "<span style='color:orange;'>Cancelled</span>";  
                                            }
                                        ?>

                                        </td>

                                        <td>

                                            <?php echo $singleRow['User']['created'];?>

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

                                    <th class="user-th">Product Name</th>

                                    <th class="user-th">Price</th>

                                    <th class="user-th">Delivery Type</th>

                                    <th class="user-th">Status</th>

                                    <th class="user-th">Created</th>

                                </tr>
                                </tfoot>
                            </table>
                        <?php }?>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#table_view').DataTable({
                "pageLength": 15
            }
        );
    });
</script>
<?php
    } 
    else 
    {
        
        echo "<script>window.location='index.php'</script>";
        die;
        
    } 
?>