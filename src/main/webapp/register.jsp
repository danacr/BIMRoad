<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>BIM ROAD - Create an account</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #eee;
        }

        .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 15px;
        }

        .form-signin .checkbox {
            font-weight: normal;
            margin-left: 20px;
        }

        .form-signin .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
        }

        .form-signin .form-control:focus {
            z-index: 2;
        }

        .form-signin input {
            margin-bottom: 5px;

            margin-top: 5px;
        }

        .form-signin {
            max-width: 350px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .aadd {
            float: right;
        }

        .btn {
            margin-bottom: 10px;
        }

        #navLogo {
            width: 150px;
        }

        #header2 {
            margin: auto;
            width: 150px;
        }

        .form-signin {
            max-width: 377px;
        }
    </style>
</head>

<body>
<div class="container">
    <form method="post" name="form1" action="Register" class="form-signin" role="form">
        <%
            if (request.getParameter("error") != null) {
        %>
        <div class="alert alert-danger">
            <strong>Error!</strong> <%= request.getParameter("error") %>
        </div>
        <%
            }
        %>
        <div id="header2">
            <img id="navLogo" src="Logo.png">
        </div>
        <h2 class="form-signin-heading">Create account</h2>

        <input name="email" type="email" value="" class="form-control" placeholder="Email" required>
        <input name="password" type="password" class="form-control" placeholder="Password" required>
        <input name="password2" type="password" class="form-control" placeholder="Confirm password" required>
        <input name="name" type="text" value="" class="form-control" placeholder="Full Name" required>
        <input name="street" type="text" value="" class="form-control" placeholder="Street" required>
        <input name="postalcode" type="text" value="" class="form-control" placeholder="Postal code" required>
        <input name="city" type="text" value="" class="form-control" placeholder="City" required>
        <input name="country" type="text" value="" class="form-control" placeholder="Country" required>
        <br>
        <button name="submit" class="btn btn-lg btn-primary btn-block" type="submit">Create</button>
        Already have an account? Please <a href="login.jsp">login</a>.
    </form>
</div> <!-- /container -->
</body>
</html>


  

