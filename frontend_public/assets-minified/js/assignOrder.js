$( function() {
    $( "#datepicker" ).datepicker();
} );


$(document).on('click', '.datepicker', function(){
    $(this).timePicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: "YY-mm-dd"
    }).focus();
    $(this).removeClass('datepicker');
});


function openTab(tabName) {
  var i;
  var x = document.getElementsByClassName("tabContent");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  
  $(".tabBar button").removeClass("active");
  document.getElementById(tabName).style.display = "block";   
  $(".tabBar ."+tabName+" ").addClass("active");
}



function showOrders(filter) 
{
    
    document.getElementById("orders").innerHTML = "<div align='center' style='padding:50px 0 0 0;'>Loading...</div>";
    
    $(".orderTabs ul li").removeClass("active");
    
    if(filter=="pending")
    {
        $(".pendingOrders").addClass("active");
    }
    else
    if(filter=="active")
    {
        $(".assignedOrders").addClass("active");
    }
    else
    if(filter=="rejected")
    {
        $(".failedOrders").addClass("active");
    }
    else
    if(filter=="completed")
    {
        $(".completedOrders").addClass("active");
    }
    
    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            //alert(xmlhttp.responseText);
            document.getElementById('orders').innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET", "ajex-events.php?action=getOrders&filter="+filter);
    xmlhttp.send();
}


function showOrderDetails(id)
{
    document.getElementById("PopupParent").style.display = "block";
    document.getElementById("contentReceived").innerHTML = "loading...";

    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            // alert(xmlhttp.responseText);
            document.getElementById('contentReceived').innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET", "ajex-events.php?action=orderDetails&id="+id);
    xmlhttp.send();
}

function ClosePopup() 
{

    document.getElementById("PopupParent").style.display = "none";

}


 window.onload = function () {
  initialize();
//   generateMarkers(
//     ['malik pura', 31.45623, 73.12974],
//     ['Nishat ababd', 31.461088, 73.112302],
//     ['ameen town',31.4442286, 73.1175795]
    
//   );
};

var map;

// Cretes the map

 function initialize() 
 {


         var a1 = {
                   	b1: "<srtong><b>Rider One<p  style='margin:0px;padding:0px;display:inherit;color:#0c930b'>(Online)</p></b><br/> , <br/><b>Start Shift Place:</b> <br/><b>Phone:</b>  +9203111222733<br/><b>Order Count:</b> 7<br/><a href='https://www.google.com/maps/place/41.012125400000000000,28.911426700000000000'  style='margin-top:5px; color:#be2c2c; text-decoration:underline;'>View On Map</a><br><a class='btn btn-danger btn-block btn-sm' href='?p=assignRider&AssignToRider=ok&rider_user_id=1&assigner_user_id=16&order_id=1323' style='background:#be2c2c; margin-top:5px; color:white; text-decoration: none; width:75%; padding:5px 0px; font-size: 11px;' >Assign Order</a><br><br></srtong>",lat:41.012125400000000000,long:28.911426700000000000,countt:7 }; var a4 = {
                   	b640: "<srtong><b>Tester Rider<p  style='margin:0px;padding:0px;display:inherit;color:#0c930b'>(Online)</p></b><br/>Miami , United States<br/><b>Start Shift Place:</b> area<br/><b>Phone:</b>  +92369852147<br/><b>Order Count:</b> 6<br/><a href='https://www.google.com/maps/place/14.320594700000000000,99.999999999999999999'  style='margin-top:5px; color:#be2c2c; text-decoration:underline;'>View On Map</a><br><a class='btn btn-danger btn-block btn-sm' href='?p=assignRider&AssignToRider=ok&rider_user_id=640&assigner_user_id=16&order_id=1323' style='background:#be2c2c; margin-top:5px; color:white; text-decoration: none; width:75%; padding:5px 0px; font-size: 11px;' >Assign Order</a><br><br></srtong>",lat:14.320594700000000000,long:99.999999999999999999,countt:6 }; var a9 = {
                   	b971: "<srtong><b>Joaquin Villegas<p  style='margin:0px;padding:0px;display:inherit;color:#0c930b'>(Online)</p></b><br/> , <br/><b>Start Shift Place:</b> <br/><b>Phone:</b>  +92<br/><b>Order Count:</b> 0<br/><a href='https://www.google.com/maps/place/0.000000000000000000,0.000000000000000000'  style='margin-top:5px; color:#be2c2c; text-decoration:underline;'>View On Map</a><br><a class='btn btn-danger btn-block btn-sm' href='?p=assignRider&AssignToRider=ok&rider_user_id=971&assigner_user_id=16&order_id=1323' style='background:#be2c2c; margin-top:5px; color:white; text-decoration: none; width:75%; padding:5px 0px; font-size: 11px;' >Assign Order</a><br><br></srtong>",lat:0.000000000000000000,long:0.000000000000000000,countt:0 };         		          

    
    var locations = [
    
	    [a1.b1, a1.lat, a1.long, a1.countt,0],[a4.b640, a4.lat, a4.long, a4.countt,0],[a9.b971, a9.lat, a9.long, a9.countt,0],        							    
    ];



	var map = new google.maps.Map(document.getElementById('map'), {
	zoom: 5,

        center: new google.maps.LatLng(40.71277530,-74.00597280),
   		mapTypeId: google.maps.MapTypeId.TERRAIN   
   		
	});
    
    marker = new google.maps.Marker({
        position: new google.maps.LatLng(40.71277530,-74.00597280),
        map: map ,
        title:'Restaurent',
        icon: 'frontend_public/uploads/restaurent.png'
    });

	

    var infowindow = new google.maps.InfoWindow({});

    var marker, i;
    
    
    for (i = 0; i < locations.length; i++) 
    {

    	
    	if(locations[i][3]!="0")
        {
        	marker = new google.maps.Marker({
            position: new google.maps.LatLng(locations[i][1], locations[i][2]),
            map: map ,
            title:'Rider',
            icon: 'frontend_public/uploads/rider.png'
            });

            google.maps.event.addListener(marker, 'click', (function (marker, i) {
                return function () {
                    infowindow.setContent(locations[i][0]);
                    infowindow.open(map, marker);
                }
            })(marker, i));
        }
        else
        {
        	marker = new google.maps.Marker({
            position: new google.maps.LatLng(locations[i][1], locations[i][2]),
            map: map ,
            title:'Rider',
            icon: 'frontend_public/uploads/rider.png'
            });

            google.maps.event.addListener(marker, 'click', (function (marker, i) {
                return function () {
                    infowindow.setContent(locations[i][0]);
                    infowindow.open(map, marker);
                }
            })(marker, i));
        }	

        


    }



    marker = new google.maps.Marker({
        //position: new google.maps.LatLng(locations[i][1], locations[i][2]),
        position: new google.maps.LatLng(31.45584524,73.12990107),
        map: map ,
        title:'Customer Location',
        icon: 'frontend_public/uploads/home.png'
    });
    marker = new google.maps.Marker({
        //position: new google.maps.LatLng(locations[i][1], locations[i][2]),
        position: new google.maps.LatLng(40.71277530,-74.00597280),
        map: map ,
        title:'Hotel Location',
        icon: 'frontend_public/uploads/restaurent.png'
    });

}

