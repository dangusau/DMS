$(document).ready(function() {

    $(document).on("click",".more", function (e) {
        $(this).toggleClass("show-more-menu");
    });

    $('html').click(function(e) {
        if(!$(e.target).hasClass('more') )
        {

            $(".more").removeClass("show-more-menu");
        }
    })

});

jQuery(document).on("click", "#deleteComment", function(){
    var comment_id	=	jQuery(this).attr("data-commentid");
    $.ajax({url: "process.php?action=deleteComment&comment_id="+comment_id, success: function(result){
    
        if(result=="200")
        {
            $("."+comment_id+"_commentRow").hide();
        }
        }
    });
});

function ClosePopup() {

    document.getElementById("PopupParent").style.display = "none";
    document.getElementById('contentReceived').innerHTML = "";

}

function editUser(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=editUser&id="+id);
    xmlhttp.send();
}

function adduser(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=adduser");
    xmlhttp.send();
}

function addrider(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=addrider");
    xmlhttp.send();
}

function editRider(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=editRider&id="+id);
    xmlhttp.send();
}

function productDetail(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=productDetail&id="+id);
    xmlhttp.send();
}

function editCountry(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=editCountry&id="+id);
    xmlhttp.send();
}

function addCountry(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=addCountry&id="+id);
    xmlhttp.send();

}

function addAdminUser(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=addAdminUser");
    xmlhttp.send();
}

function addCoupon(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=addCoupon");
    xmlhttp.send();
}

function editCoupon(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=editCoupon&id="+id);
    xmlhttp.send();
}

function addVehicle(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=addVehicle");
    xmlhttp.send();
}

function editVehicle(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=editVehicle&id="+id);
    xmlhttp.send();
}

function changeAdminPassword(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=changeAdminPassword&id="+id);
    xmlhttp.send();
}

function changeUserPassword(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=changeUserPassword&id="+id);
    xmlhttp.send();
}

function changeRiderPassword(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=changeRiderPassword&id="+id);
    xmlhttp.send();
}

function editAdminUser(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=editAdminUser&id="+id);
    xmlhttp.send();
}

function ConfirmDelete()
{
  var x = confirm("Are you sure you want to delete?");
  if (x)
      return true;
  else
    return false;
}

function addGoodType()
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
    xmlhttp.open("GET", "ajex-events.php?q=addGoodType");
    xmlhttp.send();
}

function editGoodType(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=editGoodType&id="+id);
    xmlhttp.send();
}

function assignOrder()
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
    xmlhttp.open("GET", "ajex-events.php?q=assignOrder");
    xmlhttp.send();
}
function addPackageSize(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=addPackageSize");
    xmlhttp.send();
}
function editPackageSize(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=editPackageSize&id="+id);
    xmlhttp.send();
}


function changeStatus(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=changeStatus&id="+id);
    xmlhttp.send();
}

function showDocuments(id)
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
    xmlhttp.open("GET", "ajex-events.php?q=showDocuments&id="+id);
    xmlhttp.send();
}

function CreateOrder()
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
    xmlhttp.open("GET", "ajex-events.php?q=CreateOrder");
    xmlhttp.send();
}

//======================================== Passowrd Validation
 
var password = document.getElementById("new-password") , 
confirm_password = document.getElementById("confirme-password");

function validatePassword(){
    if(password.value != confirm_password.value) {
        confirm_password.setCustomValidity("Passwords Don't Match");
    } else {
        confirm_password.setCustomValidity('');
    }
}
password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;

//============================== Passowrd Validation End Function




  





