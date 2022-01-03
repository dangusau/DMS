<?php 
include("header.php"); 
if(isset($_SESSION[PRE_FIX.'id']))
{
?>
<div class="mainwrapper allusers">
    <div class="qr-layout">
		<?php require_once("rightsidebar.php"); ?> 
		<?php 
			if(isset($_GET['p']) ) 
			{ 
				if( $_GET['p'] == "users" ) 
				{ 
					include("users.php");
				}
				
				if( $_GET['p'] == "manageOrder" ) 
				{ 
					include("manageOrder.php");
				}

				if( $_GET['p'] == "country" ) 
				{ 
					include("country.php");
				}

				if( $_GET['p'] == "tax" ) 
				{ 
					include("tax.php");
				}

				if( $_GET['p'] == "currency" ) 
				{ 
					include("currency.php");
				}

				if( $_GET['p'] == "rider" ) 
				{ 
				include("rider.php");
				}

				if( $_GET['p'] == "adminUsers" ) 
				{ 
				include("adminUsers.php");
				}

				if( $_GET['p'] == "couponCode" ) 
				{ 
				include("couponCode.php");
				}

				if( $_GET['p'] == "orderHistory" ) 
				{ 
				include("orderHistory.php");
				}

				if( $_GET['p'] == "goodType" ) 
				{ 
				include("goodType.php");
				}
				
				if( $_GET['p'] == "deliveryType" ) 
				{ 
				include("deliveryType.php");
				}

				if( $_GET['p'] == "vehicleType" ) 
				{ 
				include("vehicleType.php");
				}

				if( $_GET['p'] == "changePassword" ) 
				{ 
				    include("changePassword.php");
				}

				if( $_GET['p'] == "assignOrder" ) 
				{ 
				    include("assignOrder.php");
				}
				if( $_GET['p'] == "package" ) 
				{ 
				    include("packageSize.php");
				}
				if( $_GET['p'] == "assignRider" ) 
				{ 
				    include("assignRider.php");
				}
				if( $_GET['p'] == "withdrawRequest" ) 
				{ 
				    include("withdrawRequest.php");
				}
			}
		?>
	</div>
</div>

<?php 
require_once("footer.php"); 
}
else
{
    echo "<script>window.location='index.php'</script>";
}


?>