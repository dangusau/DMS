<?php 
if(isset($_SESSION[PRE_FIX.'id']))
{  
    $url=$baseurl . 'All_Users'; 
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
                            <h2>Tax</h2>
                            <div class="head-area">
                            </div>
                        </div>
                       <table id="table_view" class="display" style="width:100%">
                            <thead>
                                <tr>
                                    <th>Facebook ID</th>
                                    <th>First Name</th>
                                    <th>Age</th>
                                    <th>Gender</th>
                                    <th>Purchase</th>
                                    <th>Like</th>
                                    <th>Dislike</th>
                                    <th>View Profile</th>
                                </tr>
                            </thead>
                            <tbody>
                            <?php 
                                if($json_data!="")
                                {
                                    foreach ($json_data['msg'] as $singleRow): 
                            ?>
                            <tr>
                                <td><?php echo $singleRow['fb_id'] ?></td>
                                <td style="width:150px; overflow:hidden;"><?php echo $singleRow['first_name'].' '. $singleRow['last_name']; ?></td>
                                <td style="width:150px; overflow:hidden;">
                                    <?php
                                        $birthDate = $singleRow['birthday'];
                                        
                                        if($birthDate == "" || $birthDate == "XX/XX/XXXX")
                                        {
                                            echo 'No Age Found';
                                        }
                                        else
                                        {
                                            $birthDate = $singleRow['birthday'];
                                            //explode the date to get month, day and year
                                            $word = "-";
                                            $word2 = "/";

                                            if(strpos($birthDate, $word) !== false){
                                                $birthDate = explode("-", $birthDate);
                                                $age = (date("md", date("U", mktime(0, 0, 0, $birthDate[0], $birthDate[1], $birthDate[2]))) > date("md") ? ((date("Y") - $birthDate[2]) - 1) : (date("Y") - $birthDate[2]));
                                            }elseif(strpos($birthDate, $word2) !== false){
                                                $birthDate = explode("/", $birthDate);
                                                $age = (date("md", date("U", mktime(0, 0, 0, $birthDate[0], $birthDate[1], $birthDate[2]))) > date("md") ? ((date("Y") - $birthDate[2]) - 1) : (date("Y") - $birthDate[2]));
                                            }else{
                                                $birthDate =  $birthDate;
                                                $age = $birthDate;
                                            }
                                            
                                            echo $age;
                                        }
                                    ?>
                                </td>
                                <td>
                                    <?php echo $singleRow['gender']; ?>
                                </td>
                                <td>
                                    <?php 
                                        if($singleRow['purchased']=="0")
                                        {
                                            echo "<i>No</i>";
                                        }
                                        else
                                        {
                                            echo "Yes";
                                        }
                                    ?>
                                </td>
                                <td>
                                    <span class="far fa-thumbs-up" style="font-size: 15px; color:green; margin-bottom:5px;"></span>
                                    <?php 
                                        echo $singleRow['like_count'];
                                    ?>
                                </td>
                                <td>
                                    <span class="far fa-thumbs-down" style="font-size: 15px; color:red; margin-bottom:5px;"></span>
                                    <?php 
                                        echo $singleRow['dislike_count'];
                                    ?>
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
                                            <ul class="more-menu-items" tabindex="-1" role="menu" aria-labelledby="more-btn" aria-hidden="true">
                                                <li class="more-menu-item" role="presentation" onclick="viewProfile('<?php echo $singleRow['fb_id']; ?>')">
                                                    <button type="button" class="more-menu-btn" role="menuitem">View Profile</button>
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
                                    <th>Facebook ID</th>
                                    <th>First Name</th>
                                    <th>Age</th>
                                    <th>Gender</th>
                                    <th>Purchase</th>
                                    <th>Like</th>
                                    <th>Dislike</th>
                                    <th>View Profile</th>
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