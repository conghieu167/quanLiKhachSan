<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">


        <link rel="stylesheet" href="./css/reset.css">
        <link rel="stylesheet" href="./css/CustomerStyle.css">
        <style>
            body {
                background: url("img/ThemeLogin.jpg") no-repeat center center fixed;
                background-size: cover;
                font-family: 'Quicksand', sans-serif;
                margin: 0;
                backdrop-filter: blur(4px);
            }
        </style>
    </head>

    <body>

        <div class="hero">
            <div class="register-background">
                <div class="register-title">
                    <span>Create Account</span>
                </div>
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null) {
                %>
                <div style="color: red;">
                    <p>"Tài khoản đã tồn tại!"</p>    
                </div>
                <%
                    }
                %>
                <div class="register-form">
                    <form method="post" action="signup">
                        <div class="field">
                            <input type="text" name="TenKH" placeholder="Họ và tên" required 
                                   maxlength="50" 
                                   title="Tên không được quá 50 ký tự." />
                        </div>
                        <div class="field">
                            <input type="text" name="TaiKhoan" placeholder="Tên tài khoản" required 
                                   maxlength="20" 
                                   title="Tên tài khoản không quá 20 ký tự." />
                        </div>
                        <div class="field">
                            <input type="email" name="Email" placeholder="Email" required 
                                   maxlength="50" 
                                   title="Email không quá 50 ký tự." />
                        </div>
                        <div class="field">
                            <input type="text" name="phone-number" placeholder="Số điện thoại" required
                                   pattern="^\d{9,10}$" 
                                   title="Số điện thoại phải có 9 hoặc 10 chữ số." />
                        </div>
                        <div class="field">
                            <input type="text" name="SoCCCD" placeholder="Số CCCD" required 
                                   pattern="^\d{12}$" 
                                   title="CCCD phải có đúng 12 chữ số." />
                        </div>
                        <div class="field">
                            <input type="password" name="MatKhau" placeholder="Mật khẩu" required 
                                   maxlength="20" 
                                   title="Mật khẩu không quá 20 ký tự." />
                        </div>
                        <div class="field">
                            <button class="button-register" type="submit">Sign Up</button>
                            <div style="text-align: center; margin-top: 15px;">
                                <span style="color: white;">Already have an account? </span>
                                <a href="login.jsp"
                                   style="color: #fff; font-weight: bold; text-decoration: underline;">Login here</a>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </body>

</html>