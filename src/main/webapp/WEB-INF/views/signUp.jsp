<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
    .container-size{
        width:400px;
        margin-top: 10vh;
    }
    </style>

</head>
<body>
<div class="container container-size">
    <h3> Sign Up Page </h3>
    <form action="/signup" method="post">
    <div class="form-group">
        <label>Name :</label>
        <input class="form-control" type="text" name="name" />
    </div>
    <div class="form-group">
        <label>User Name :</label>
        <input class="form-control" type="text" name="username" />
    </div>
    <div class="form-group">
        <label>Email :</label>
        <input class="form-control" type="email" name="email" />
    </div>
    <div class="form-group">
        <label>Password :</label>
        <input class="form-control" type="password" name="password" />
    </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>