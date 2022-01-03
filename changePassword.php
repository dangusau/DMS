<?php
if(isset($_SESSION[PRE_FIX.'id']))
{
?>

    <div class="qr-content">
        <div class="qr-page-content">
            <div class="qr-page toppaddzero">
                <div class="qr-content-area">
                    <div class="qr-row">
                        <div class="qr-el">
                            <div class="page-title">
                                <h2>Change Password</h2>
                                <div class="head-area">
                                </div>
                            </div>
                            <div class="panel margin-top-n">

                                <p style="color: #000;" id="error_message">
                                        <?php
                                        
                                        /*
                                        * sucess message custom
                                        *
                                        * */
                                        
                                        $error = [
                                            1 => "Invalid Password",
                                        ];
                                        
                                        $error_id = isset($_GET['error']);
                                        if ($error != 0 && in_array($error_id, $error)) {
                                            echo $error[$error_id];
                                        }
                                        ?>
                                </p>
                                <p style="color: #000;" id="sucess_message">
                                        <?php
                                        
                                        /*
                                        * sucess message custom
                                        *
                                        * */
                                        
                                        $sucess = [
                                            1 => "Password Updated",
                                        ];
                                        
                                        $sucess_id = isset($_GET['sucess']);
                                        if ($sucess != 0 && in_array($sucess_id, $sucess)) {
                                            echo $sucess[$sucess_id];
                                        }
                                        ?>
                                </p>

                                <form action="process.php?action=currentAdminChangePassword" method="post" class="inputs validatedForm">
                                    <input type="hidden" name="user_id" value="<?php echo $_SESSION[PRE_FIX.'id']; ?>">
                                    <input type="hidden" name="page_name" value="changePassword">
                                    <div class="inputs__item">
                                        <label for="previous-password" class="inputs__label">Previous password</label>
                                        <input type="password" name="password_previous" class="inputs__input" id="previous-password" value="" required="required"/>
                                    </div>
                                    <div class="inputs__item inputs__item--new">
                                        <label for="new-password" class="inputs__label">New password</label>
                                        <input type="password" class="inputs__input" id="new-password" name="new_password" value="" required="required"/>
                                    </div>
                                    <div class="inputs__item inputs__item--new">
                                        <label for="new-password" class="inputs__label">Confirm password</label>
                                        <input type="password" class="inputs__input" name="password" id="confirme-password" value="" required="required"/>
                                    </div>

                                    <button name="password_update"
                                            class="com-button com-submit-button com-button--large com-button--default">
                                        <div class="com-submit-button__content"><span>Change Password</span></div>
                                    </button>
                                </form>
                            </div>

                        </div> 
                    </div>
                </div>
            </div>
        </div>
<style>
    .highcharts-credits {
        display: none;
    }

    .place-card.default-card {
        display: none;
    }
</style>
    
<?php
}
else 
{
	
	@header("Location: index.php");
    echo "<script>window.location='index.php'</script>";
    die;
} 
?>