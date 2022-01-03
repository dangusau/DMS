<?php 
if(isset($_SESSION[PRE_FIX.'id']))
{       
    
        $url=$baseurl . 'showWithdrawRequest';
        $data =array();
        
        $json_data=@curl_request($data,$url);
        
        ?>

        <div class="qr-content">
            <div class="qr-page-content">
                <div class="qr-page zeropadding">
                    <div class="qr-content-area">
                        <div class="qr-row">
                            <div class="qr-el">

                                <div class="page-title">
                                    <h2>All Withdraw Request</h2>
                                    <div class="head-area">
                                    </div>
                                </div>
                                
                               

                                <table id="table_view" class="display" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Amount</th>
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
                                                        <td><?php echo $singleRow['WithdrawRequest']['id']; ?></td>
                                                        <td>
                                                           <?php echo $singleRow['User']['first_name'].' '.$singleRow['User']['last_name']; ?>
                                                        </td>
                                                        <td>
                                                            <?php echo DEFAULT_CURRENCY.$singleRow['WithdrawRequest']['amount']; ?>
                                                        </td>
                                                        <td>
                                                           <?php
                                                                if($singleRow['WithdrawRequest']['status']=="0")
                                                                {
                                                                    echo "Pending";
                                                                }
                                                                else
                                                                if($singleRow['WithdrawRequest']['status']=="1")
                                                                {
                                                                    echo "Accepted";
                                                                }
                                                                else
                                                                if($singleRow['WithdrawRequest']['status']=="2")
                                                                {
                                                                    echo "Rejected";
                                                                }
                                                           ?>
                                                        </td>
                                                        <td>
                                                           <?php echo $singleRow['WithdrawRequest']['created']; ?>
                                                        </td>
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
                                                                        <?php 
                                                                        
                                                                            if($singleRow['WithdrawRequest']['status'] == "0")
                                                                            {
                                                                                ?>
                                                                                    <ul class="more-menu-items" tabindex="-1" role="menu" aria-labelledby="more-btn" aria-hidden="true">
                                                                                        <li class="more-menu-item" role="presentation">
                                                                                            <a href="process.php?action=changeWithdrawRequestStatus&id=<?php echo $singleRow['WithdrawRequest']['id']; ?>&status=1">
                                                                                                <button type="button" class="more-menu-btn" role="menuitem">Approve</button>
                                                                                            </a>
                                                                                        </li>
                                                                                        <li class="more-menu-item" role="presentation">
                                                                                            <a href="process.php?action=changeWithdrawRequestStatus&id=<?php echo $singleRow['WithdrawRequest']['id']; ?>&status=2">
                                                                                                <button type="button" class="more-menu-btn" role="menuitem">Reject</button>
                                                                                            </a>
                                                                                        </li>
                                                                                    </ul>
                                                                                <?php 
                                                                            }
                                                                    
                                                                        ?>
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
                                            <th>Name</th>
                                            <th>Amount</th>
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