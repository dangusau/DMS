<?php 
if(isset($_SESSION[PRE_FIX.'id']))
{  
    $url=$baseurl . 'showPackageSize'; 
    $data =array();
    
        
    $json_data=@curl_request($data,$url);
    
    $allusers = [];
    if ($json_data['code'] == 200) {
        $allusers = $json_data['msg'];
    }
?>
<div class="qr-content">
    <div class="qr-page-content">
        <div class="qr-page zeropadding">
            <div class="qr-content-area">
                <div class="qr-row">
                    <div class="qr-el">

                        <div class="page-title">
                            <h2>Package Size</h2>
                            <div class="head-area">
                            </div>
                        </div>
                        <div class="right" style="padding: 10px 0;">
                            <button onclick="addPackageSize()"
                                class="com-button com-submit-button com-button--large com-button--default">
                                <div class="com-submit-button__content"><span>Add Package Size</span></div>
                            </button>
                        </div>
                       <table id="table_view" class="display" style="width:100%">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Image</th>
                                    <th>Price</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            <?php 
                                if($json_data!="")
                                {
                                    foreach ($json_data['msg'] as $singleRow): 
                            ?>
                            <tr>
                                <td><?php echo ucwords($singleRow['PackageSize']['id']);?></td>
                                <td><?php echo $singleRow['PackageSize']['title'];?></td>
                                <td><?php echo $singleRow['PackageSize']['description'];?></td>
                                <?php 
                                
                                $Image = checkImageExist($imagebaseurl.$singleRow['PackageSize']['image']);?>
                                <td><img src="<?php echo $Image;?>" alt="" width="30px" height="30px"></td>
                                <td><?php echo $singleRow['PackageSize']['price'];?></td>
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

                                                <li class="more-menu-item" role="presentation" onclick="editPackageSize('<?php echo $singleRow['PackageSize']['id'];?>')">
                                                    <button type="button" class="more-menu-btn" role="menuitem">Edit</button>
                                                </li>

                                                <li class="more-menu-item" role="presentation" onclick="return ConfirmDelete()">
                                                    <a href="process.php?action=deletePackageSize&id=<?php echo $singleRow['PackageSize']['id'];?>"><button type="button" class="more-menu-btn" role="menuitem">Delete</button></a> 
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
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Image</th>
                                    <th>Price</th>
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