
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

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
    .alert-success {
        background-color: #d4edda;
        color: #155724;
        border: 1px solid #c3e6cb;
        padding: 10px 20px;
        margin: 20px auto;
        width: fit-content;
        border-radius: 5px;
        font-family: 'Poppins', sans-serif;
        text-align: center;
        font-size: 16px;
        animation: fadeIn 0.5s ease-in-out;
    }

    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(-10px); }
        to { opacity: 1; transform: translateY(0); }
    }
</style>

</head>

<body>
    <body>
    <%-- Thông báo đăng ký thành công --%>
    <%
        String success = request.getParameter("success");
        if ("true".equals(success)) {
    %>
        <div class="alert-success" id="success-alert">
            Đăng ký tài khoản thành công! Vui lòng đăng nhập.
        </div>
    <%
        }
    %>

    <header class="customer-header">
        <div class="customer-content">
            <nav class="customer-navbar">
 
                <img class="Logo" src="./img/L_Hotel-removebg-preview.png" alt="L_Hotel" />
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Services</a></li>
                    <li><a href="#">Contact</a></li>
                    <li><a href="#">Room</a></li>
                </ul>
                <div class="actions">
                    <a href="#" class="btn action-btn">Sign up</a>
                </div>
            </nav>
        </div>
        <div class="hero">        
            <div class="login-container">
                <div class="login-background">
                    <div class="login-title">
                        <span>Sign In</span>
                    </div>
                    <div class="login-form">
                        <form action="login" method="post" >
                            <div class="field username-field">
                                <input type="text" id="username" name="TaiKhoan" placeholder="Username">
                            </div>
                            <div class="field password-field">
                                <input type="password" id="password" name="MatKhau" placeholder="Password">
                            </div>
                            <div class="field button-field">
                                <div class="button-place">
                                <button class="button button-login" type="submit">LOGIN</button>
                                <a href="DangKy.jsp" class="button register">REGISTER</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <script>
    setTimeout(() => {
        const alertBox = document.getElementById('success-alert');
        if (alertBox) {
            alertBox.style.display = 'none';
        }
    }, 3000);
</script>

</body>

</html>