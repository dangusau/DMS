<?php include("config.php") ?>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content="WiFi Admin Dashboard"/>
    <meta name="author" content=""/>
    <meta name="csrf-token" content="GtJPRtA43dGDwYBYLXszN4GFKL5m9qJR8DzyVuW8"/>
    <title>Godelivery Admin Panel</title>
    <link rel="shortcut icon" href="frontend_public/assets-minified/images/fav.png">
    <link href='https://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700" rel="stylesheet">
    <link rel="stylesheet" href="frontend_public/assets-minified/css/common.min.css?time=<?php echo time(); ?>">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic">
    <link rel="stylesheet" href="frontend_public/assets-minified/css/not_landing.min.css">
    <link rel="stylesheet" href="frontend_public/assets-minified/css/neon.min.css">
    <link rel="stylesheet" href="frontend_public/assets-minified/css/orderMap.css">
    <link rel="stylesheet" href="frontend_public/assets-minified/css/style.css?time=<?php echo time(); ?>">
    <script src="https://kit.fontawesome.com/ac9b11d13d.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=<?php echo GOOGLE_MAPS_API; ?>&libraries=places"></script>
    
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    
    <script type="text/javascript" language="javascript" src="frontend_public/assets-minified/js/jquery-3.3.1.js"></script>
    <script src="frontend_public/assets-minified/js/jquery-ui.js"></script>
    <!-- Global site tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-88404643-1"></script>
    <script>
      window.dataLayer = window.dataLayer || [];
      function gtag(){dataLayer.push(arguments);}
      gtag('js', new Date());
    
      gtag('config', 'UA-88404643-1');
    </script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );


        $(document).on('click', '.datepicker', function(){
            $(this).timePicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: "yy-mm-dd"
            }).focus();
            $(this).removeClass('datepicker');
        });

        
        
        $(document).ready(function () {
            $(".page_loading_status_bar div").animate({width: "100%"}, 2000);
    
            setTimeout(function () {
                $(".page_loading_status_bar").fadeOut(2000);
            }, 1000);
    
            $("#preloader").addClass('hide');
            /*Auth check condition end*/
        });


    </script>
 

</head>
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

<body class="page-body">
    <?php
        if(@$_GET['action']=="notAllowDemo")
        {
            ?>
                <div class="statusBar notAllowDemo">This is a demo account. Some of the options have been disabled for example updation. You will receive an error if you try to update something </div>
            <?php
        }
        else
        if(@$_GET['action']=="success")
        {
            ?>
                <div class="statusBar success">Operation has been successfully</div>
            <?php
        }
        else
        if(@$_GET['action']=="error")
        {
            if(isset($_SESSION[PRE_FIX.'error']))
            {
                ?>
                    <div class="statusBar error"><?php echo $_SESSION[PRE_FIX.'error']; ?></div>
                <?php
            }
            else
            {
                ?>
                    <div class="statusBar error">Something went wrong</div>
                <?php
            }
        }
    ?>
    <div class="progress page_loading_status_bar">
        <div class="progress-bar progress-bar-warning progress-bar-striped"></div>
    </div>
    <div id="preloader">
        <div id="status">&nbsp;</div>
    </div> 

