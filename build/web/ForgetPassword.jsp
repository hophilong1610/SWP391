<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <title>Forget Password Form</title>
    </head>
    <body>
        <div id="logreg-forms">
            <form class="form-signin" action="ForgetPasswordControl" method="post">
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Change Password</h1>
                <p class="text-danger">${mess}</p>
                <input name="user"  type="text" id="inputEmail" class="form-control" placeholder="Username" required="" autofocus="">
                <input name="pass"  type="password" id="inputPassword" class="form-control" placeholder="Old Password" required="">
                <input name="pass1"  type="password"  id="inputPassword1" class="form-control inputPass" placeholder="New Password" required="">
                <input name="pass2"  type="password"  id="inputPassword2" class="form-control inputPass" placeholder="Re-Confirm Password" required="">
                <button class="btn btn-success btn-block" type="submit" id="btnChangePass"><i class="fas fa-sign-in-alt"></i> Change Password</button>
                <hr>
<!--                <a href="Login.jsp" class="btn btn-success" style = "background-color: #0066ff; margin-bottom: 10px; color: white">Login</a>    -->
                <a href="home" class="btn btn-success" style = "background-color: white; color: black">Back to Home</a>
            </form>
            <br>

        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <script>
            $("#btnChangePass").prop("disabled", true);
            $(document).ready(function () {
                $(".inputPass").keyup(function () {
                    if ($("#inputPassword1").val() !== $("#inputPassword2").val())
                    {
                        console.log($("#inputPassword2").text());
                        $("#btnChangePass").prop("disabled", true);
                    } else
                    {
                        $("#btnChangePass").prop("disabled", false);
                    }
                });
            });
        </script>

    </body>
</html>