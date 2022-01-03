<div class="qr-sidebar">
    <div class="qr-sidebar-title-area">
        <div class="logo-area">
            <div class="qr-logo">
                <a href="#"> <img src="frontend_public/uploads/attachment/logo.png" alt=""> </a>
            </div>
        </div>
        <div class="burger-icon"> ☰</div>
    </div>
    <div class="not-mobile">
        <ul>
            
            <li>
                <a href="dashboard.php?p=users" class="<?php if (strpos($_SERVER['REQUEST_URI'], "users") !== false) { echo "router-link-active ";} ?>"> 
                <i aria-hidden="true" class="fa fa-users"></i>All Users
                </a>
            </li>
            

            <li>
                <a href="dashboard.php?p=rider" class="<?php if (strpos($_SERVER['REQUEST_URI'], "rider") !== false) { echo "router-link-active ";} ?>"> 
                <i aria-hidden="true" class="fa fa-biking"></i>Riders
                </a>
            </li>

            <li>
                <a href="dashboard.php?p=manageOrder&status=pendingOrder" class="<?php if (strpos($_SERVER['REQUEST_URI'], "manageOrder") !== false) { echo "router-link-active ";} ?>"> 
                <i aria-hidden="true" class="fas fa-tasks"></i>Manage Orders
                </a>
            </li>

            <!-- <li>
               <a href="dashboard.php?p=assignOrder&status=0" class="<?php if (strpos($_SERVER['REQUEST_URI'], "assignOrder") !== false) { echo "router-link-active ";} ?>">
                   <i class="fa fa-first-order" aria-hidden="true"></i>Manage Order
               </a>
            </li> -->

            <li>
                <a href="dashboard.php?p=couponCode" class="<?php if (strpos($_SERVER['REQUEST_URI'], "couponCode") !== false) { echo "router-link-active ";} ?>"> 
                <i class="fa fa-ticket" aria-hidden="true"></i>Coupon Code
                </a>
            </li>

            <li>
                <a href="dashboard.php?p=goodType" class="<?php if (strpos($_SERVER['REQUEST_URI'], "goodType") !== false) { echo "router-link-active ";} ?>"> 
                <i aria-hidden="true" class="fas fa-tasks"></i>Good Type
                </a>
            </li>
            <li>
                <a href="dashboard.php?p=package" class="<?php if (strpos($_SERVER['REQUEST_URI'], "package") !== false) { echo "router-link-active ";} ?>"> 
                    <i aria-hidden="true" class="fa fa-product-hunt"></i>Package Size
                </a>
            </li>

            <li>
                <a href="dashboard.php?p=vehicleType" class="<?php if (strpos($_SERVER['REQUEST_URI'], "vehicleType") !== false) { echo "router-link-active ";} ?>"> 
                <i aria-hidden="true" class="fa fa-car"></i>Vehicle Type
                </a>
            </li>

            <li>
                <a href="dashboard.php?p=country" class="<?php if (strpos($_SERVER['REQUEST_URI'], "country") !== false) { echo "router-link-active ";} ?>"> 
                    <i class="fas fa-flag" aria-hidden="true"></i>Country
                </a>
            </li>

            <li>
               <a href="dashboard.php?p=withdrawRequest" class="<?php if (strpos($_SERVER['REQUEST_URI'], "withdrawRequest") !== false) { echo "router-link-active ";} ?>">
                   <i aria-hidden="true" class="fas fa-hand-holding-usd"></i>
                   Withdraw Request
               </a>
            </li>
            
            <li>
                <a href="dashboard.php?p=adminUsers" class="<?php if (strpos($_SERVER['REQUEST_URI'], "adminUsers") !== false) { echo "router-link-active ";} ?>"> 
                <i aria-hidden="true" class="fa fa-users"></i>Admin User
                </a>
            </li>

            <li>
                <a href="dashboard.php?p=changePassword" class="<?php if (strpos($_SERVER['REQUEST_URI'], "changePassword") !== false) { echo "router-link-active ";} ?>"> 
                    <i aria-hidden="true" class="fa fa-unlock-alt"></i>
                    Change Password
                </a>
            </li>
            
            <li>
                <a href="dashboard.php?p=logout" class="<?php if (strpos($_SERVER['REQUEST_URI'], "logout") !== false) { echo "router-link-active ";} ?>"> 
                    <i aria-hidden="true" class="right-align fa fa-sign-out-alt"></i> Logout
                </a>
            </li>
            
        </ul>
        <div class='clear'></div>
    </div>
    <div class="mobile-only"></div>
</div>