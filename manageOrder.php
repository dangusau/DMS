<?php 
if(isset($_SESSION[PRE_FIX.'id'])) 
{  

if(isset($_GET['status']))
{
    if($_GET['status']=="pendingOrder")
    {
        $data =array(
            "status" => "0"
        );
    }
    else
    if($_GET['status']=="activeOrder")
    {
        $data =array(
            "status" => "1"
        );
    }
    else
    if($_GET['status']=="orderAssigned")
    {
        $data =array(
            "status" => "4"
        );
    }
    else
    if($_GET['status']=="completedOrder")
    {
        $data =array(
            "status" => "2"
        );
    }
    else
    if($_GET['status']=="cancelOrder")
    {
        $data =array(
            "status" => "3"
        );
    }
    
    
}

$url=$baseurl . 'showOrders'; 


$json_data=@curl_request($data,$url);


?>
<div class="qr-content">
    <div class="qr-page-content">
        <div class="qr-page zeropadding">
            <div class="qr-content-area">
                <div class="qr-row">
                    <div class="qr-el">

                        <div class="page-title">
                            <h2>All Orders</h2>
                            <div class="head-area">
                            </div>
                        </div>
                        <!--<div class="" style="padding: 10px 0; text-align:right;">-->
                        <!--    <button onclick="CreateOrder()" class="com-button com-submit-button com-button--large com-button--default">-->
                        <!--        <div class="com-submit-button__content"><span>Create Order</span></div>-->
                        <!--    </button>-->
                        <!--</div>-->
                        <div class="right" style="padding: 10px 0;">
                            <a href="dashboard.php?p=manageOrder&status=pendingOrder" style="color:white;">
                                <button class="btnActive com-submit-button com-button--large com-button--default <?php if($_GET['p']=='manageOrder' && $_GET['status']=='pendingOrder'){ echo 'com-button';} ?>" style="padding: 5px 15px;font-size: 12px;">
                                    <div class="com-submit-button__content"><span>Pending Orders</span></div>
                                </button>
                            </a>
                            
                            <a href="dashboard.php?p=manageOrder&status=activeOrder" style="color:white;">
                                <button class="btnActive com-submit-button com-button--large com-button--default <?php if($_GET['p']=='manageOrder' && $_GET['status']=='activeOrder'){ echo 'com-button';} ?>" style="padding: 5px 15px;font-size: 12px;">
                                    <div class="com-submit-button__content"><span>Active Orders</span></div>
                                </button>
                            </a>
                            
                            <a href="dashboard.php?p=manageOrder&status=orderAssigned" style="color:white;">
                                <button class="btnActive com-submit-button com-button--large com-button--default <?php if($_GET['p']=='manageOrder' && $_GET['status']=='orderAssigned'){ echo 'com-button';} ?>" style="padding: 5px 15px;font-size: 12px;">
                                    <div class="com-submit-button__content"><span>Order Assigned</span></div>
                                </button>
                            </a>

                            <a href="dashboard.php?p=manageOrder&status=completedOrder" style="color:white;">
                                <button class="btnActive com-submit-button com-button--large com-button--default <?php if($_GET['p']=='manageOrder' && $_GET['status']=='completedOrder'){ echo 'com-button';} ?>" style="padding: 5px 15px;font-size: 12px;">
                                    <div class="com-submit-button__content"><span>Completed Orders</span></div>
                                </button>
                            </a>
                                    
                            <a href="dashboard.php?p=manageOrder&status=cancelOrder" style="color:white;">
                                <button class="btnActive com-submit-button com-button--large com-button--default <?php if($_GET['p']=='manageOrder' && $_GET['status']=='cancelOrder'){ echo 'com-button';} ?>" style="padding: 5px 15px;font-size: 12px;">
                                    <div class="com-submit-button__content"><span>Cancel Orders</span></div>
                                </button>
                            </a>
                        </div>
                        
                        <table id="table_view" class="display" style="width:100%">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Sender</th>
                                    <th>Receiver</th>
                                    <th>Product Title</th>                 
                                    <th>Status</th>
                                    <th>Created</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            <?php 
                                if(is_array($json_data['msg']) || is_object($json_data['msg']))
                                {
                                    foreach ($json_data['msg'] as $singleRow): 
                                    ?>
                                        <tr>
                                            <td><?php echo $singleRow['Order']['id'] ?></td>
                                            <td style="width:150px; overflow:hidden;">
                                                <?php echo ucwords($singleRow['Order']['sender_name']); ?><br>
                                                <?php echo $singleRow['Order']['sender_phone'] ?>
                                            </td>
                                            
                                            <td style="width:150px; overflow:hidden;">
                                                <?php echo ucwords($singleRow['Order']['receiver_name']); ?><br>
                                                <?php echo $singleRow['Order']['receiver_phone'] ?>
                                            </td>
                                            
                                            <td><?php echo ucwords($singleRow['Order']['item_title']); ?></td>
                                            <td>
                                                <?php
                                                    if($singleRow['Order']['status']=="0")
                                                    {
                                                        echo "Pending";
                                                    }
                                                    else
                                                    if($singleRow['Order']['status']=="1")
                                                    {
                                                        
                                                        echo "Ready To Assign";
                                                    }
                                                    else
                                                    if($singleRow['Order']['status']=="2")
                                                    {
                                                        echo "Completed";
                                                    }
                                                    else
                                                    if($singleRow['Order']['status']=="3")
                                                    {
                                                        echo "Cancelled";
                                                    }
                                                    else
                                                    if($singleRow['Order']['status']=="4")
                                                    {
                                                        ?>
                                                            <span style="color:green;" title="<?php echo $singleRow['RiderOrder']['Rider']['first_name']." ".$singleRow['RiderOrder']['Rider']['last_name'];?> (<?php echo $singleRow['RiderOrder']['Rider']['id'];?>)">Assigned To <?php echo @$singleRow['RiderOrder']['Rider']['first_name'];?></span>
                                                        <?php
                                                    }
                                                ?>
                                            </td>
                                            <td><?php echo $singleRow['Order']['created']; ?></td>
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
                                                            
                                                            <li class="more-menu-item" role="presentation" onclick="productDetail('<?php echo $singleRow['Order']['id'] ?>');">
                                                                <button type="button" class="more-menu-btn" role="menuitem">Order Details</button>
                                                            </li>
                                                            
                                                            <?php
                                                                if($singleRow['Order']['status']=="0")
                                                                {
                                                                    ?>
                                                                        <li class="more-menu-item" role="presentation">
                                                                            <a href="process.php?action=acceptOrder&orderID=<?php echo $singleRow['Order']['id'] ?>">
                                                                                <button type="button" class="more-menu-btn" role="menuitem">Accept Order</button>
                                                                            </a>
                                                                        </li> 
                                                                    <?php                
                                                                }
                                                                else
                                                                if($singleRow['Order']['status']=="1")
                                                                {
                                                                    
                                                                    ?>
                                                                        <li class="more-menu-item" role="presentation">
                                                                            <a href="dashboard.php?p=assignRider&orderID=<?php echo $singleRow['Order']['id'] ?>">
                                                                                <button type="button" class="more-menu-btn" role="menuitem">Assign Order</button>
                                                                            </a>
                                                                        </li>
                                                                    <?php                
                                                                }
                                                            ?>
                                                            
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
                                    <th>ID</th>
                                    <th>Sender</th>
                                    <th>Receiver</th>
                                    <th>Product Title</th>                 
                                    <th>Status</th>
                                    <th>Created</th>
                                    <th>Action</th>
                                </tr>
                            </tfoot>
                        </table>
                        
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