<html>
<head>
<meta charset="ISO-8859-1">
<title>User Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style>
    body{
        display: flex;
        flex-direction: column;
        align-content: center;
        align-items: center;
    }
</style>
</head>
<body>
<h1>This is User Page !!! </h1>
<br/>

<p id='name'></p>
<p id='username'></p>
<p id='email'></p>

<br />
<button class="btn btn-primary" id='BtnUInf'> info </button>

<h2><a href="/logout"><button class="btn btn-primary">logout</button></a></h2>
<input type="hidden" value=${Token} id="Token"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>

$.ajaxSetup({
    beforeSend: function(xhr) {
        xhr.setRequestHeader("Authorization", "Bearer " + $('#Token').val());
    }
});

$("#BtnA").click(function(){
$.ajax({
        type: "GET",
        url: "http://localhost:8080/adminPage",
        success : function(html){
                        $("html").html(html);
                    }
      });
});

$('#BtnUInf').click(function(){
    $.ajax({
            type: "GET",
            url: "http://localhost:8080/userPage/info",
            success:function(response){
                      $('#name').text("Name : "+response.name);
                      $('#username').text("User Name : "+response.username);
                      $('#email').text("Email : "+response.email);
                }
          });
})

</script>
</body>
</html>