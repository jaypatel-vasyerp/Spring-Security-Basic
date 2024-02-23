
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
    body{
        display: flex;
        flex-direction: column;
        align-content: center;
        align-items: center;
    }
    .sizer{
        width:400px;
    }
    button{
        margin:10px:
    }
</style>
</head>
<body>

<h1>This is Admin Page !!</h1>
<br />

<input type="hidden" value=${Token} id="Token"/>
 <p id='name'></p>
 <p id='username'></p>
 <p id='email'></p>
 <br />

<div class="sizer">
<table id='records_table' class="table table-dark">
</table>
</div>

    <button class="btn btn-primary" id='BtnU'> user page </button>
    <button class="btn btn-primary" id='BtnUInf'> info </button>
    <button class="btn btn-primary" id='BtnAInf'>All user info </button>
<h2><a href="/logout"><button class="btn btn-primary">logout</button></a></h2>


<script>

$.ajaxSetup({
    beforeSend: function(xhr) {
        xhr.setRequestHeader("Authorization", "Bearer " + $('#Token').val());
    }
});

$('#BtnU').click(function(){
    $.ajax({
            type: "GET",
            url: "http://localhost:8080/userPage",
            success:function(response){
                      document.open();
                      document.title = "userPage";
                      document.write(response);
                      document.close();
                }
          });
})

$('#BtnUInf').click(function(){
    $.ajax({
            type: "GET",
            url: "http://localhost:8080/userPage/info",
            success:function(response){
                      $('#name').text("Name : "+response.name);
                      $('#username').text("User Name : "+response.username);
                      $('#email').text("Email : " + response.email);
                }
          });
})

$('#BtnAInf').click(function(){
    $.ajax({
            type: "GET",
            url: "http://localhost:8080/adminPage/info",
            success:function (response) {
                            var trHTML = '';
                            $.each(response, function (i, item) {
                                trHTML += '<tr><td>' + item.name + '</td><td>' + item.username + '</td><td>' + item.email + '</td></tr>';
                            });
                            $('#records_table').append(trHTML);
                        }
          });
})
</script>

</body>

</html>