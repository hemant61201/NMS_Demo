<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AdminLTE 3 | Log in (v2)</title>

    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
    <!-- icheck bootstrap -->
    <link rel="stylesheet" href="plugins/icheck-bootstrap/icheck-bootstrap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/adminlte.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        *{
            margin:0;
            padding:0;
            overflow-x: hidden;
        }
    </style>
</head>
<body>
<div class="row justify-content-between px-3" style="position: relative;background-color: #e9ecef">
    <div class="">

    </div>
    <div class="m-3 row p-2" id="hemant-alert" style="background-color: #e9ecef;color: #e9ecef;width:15%;font-size: 18px;font-weight: 800">
        <div class="col-4" id="hemant-icon">
        </div>
        <div class="col-8" id="hemant-text">X</div>
    </div>

</div>
<div class="hold-transition login-page" style="top:-20px">
<div class="login-box">
    <!-- /.login-logo -->
    <div class="card card-outline card-primary">
        <div class="card-header text-center">
            <a href="https://www.motadata.com/" class="h1"><b>Admin</b>MOTADATA</a>
        </div>
        <div class="card-body">
            <p class="login-box-msg">Sign in to start your session</p>

            <div class="input-group mb-3">
                <input type="email" id="Email" class="form-control" placeholder="Email">
                <div class="input-group-append">
                    <div class="input-group-text">
                        <span class="fas fa-envelope"></span>
                    </div>
                </div>
            </div>
            <div class="input-group mb-3">
                <input type="password" id="Password" class="form-control" placeholder="Password">
                <div class="input-group-append">
                    <div class="input-group-text">
                        <span class="fas fa-lock"></span>
                    </div>
                </div>
            </div>
            <div class="row">
                <!-- /.col -->
                <div class="col-4">
                    <button type="submit" class="btn btn-primary btn-block" onclick=verifyLogin()>Sign In</button>
                </div>
                <!-- /.col -->
            </div>
            <script>
                 function verifyLogin() {
                    var email = document.getElementById("Email").value;
                    var password = document.getElementById("Password").value;
                    var alertBox = document.getElementById("hemant-alert");
                    var alertText = document.getElementById("hemant-text");
                    var alertIcon = document.getElementById("hemant-icon");
                    // Verify login details here

                    if (email === "admin@motadata.com" && password === "admin") {
                        alertText.innerHTML="Login success";
                        alertIcon.innerHTML=" <i class='fa fa-check' style='font-size:18px;color:#fff'></i>";
                        alertBox.style.backgroundColor = "#00cc44";
                        alertBox.style.color="#fff";
                        setTimeout(async ()=>{
                            console.log('change color');
                            alertIcon.innerHTML="";
                            alertBox.style.backgroundColor="#e9ecef";
                            alertBox.style.color="#e9ecef";
                            window.location.href = "DashBoard.jsp"; // Redirect to dashboard
                        },1000);
                        // alert("login success");
                        //window.location.href = "DashBoard.jsp"; // Redirect to dashboard
                    } else {
                        alertText.innerHTML="Login failed";
                        alertIcon.innerHTML=" <i class='fa fa-close' style='font-size:18px;color:#fff'></i>";
                        alertBox.style.backgroundColor = "#f11";
                        alertBox.style.color="#fff";
                        setTimeout(async ()=>{
                            console.log('change color');
                            alertIcon.innerHTML="";
                            alertBox.style.backgroundColor="#e9ecef";
                            alertBox.style.color="#e9ecef";
                        },5000);
                        // alert("Invalid login details.");// Display error message
                        //window.location.href = "login.jsp";
                    }
                }
            </script>
        </div>
        <!-- /.card-body -->
    </div>
    <!-- /.card -->
</div>
<!-- /.login-box -->
</div>
<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
</body>
</html>

