<%--
  Created by IntelliJ IDEA.
  User: airm2
  Date: 15/12/2024
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<style>
    body{
        background-color: rgb(242, 242, 242);
    }
    .login-container{
        min-height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .login-box{
        background-color: #fff;
        padding: 2rem;
        border-radius: 10px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }
    .illustration {
        max-width: 100%;
        height: auto;
    }

    .form-title {
        font-size: 1.25rem;
        font-weight: bold;
    }

    .terms {
        font-size: 0.875rem;
    }

    .social-buttons img {
        width: 40px;
        margin: 0 0.5rem;
        cursor: pointer;
    }

    .social-buttons img:hover {
        transform: scale(1.1);
        transition: transform 0.2s;
    }
    .g-recaptcha {
        display: block !important;
        visibility: visible !important;
    }
</style>
<body>


<%
    HttpSession session1 = request.getSession(false);
    Integer idUser = (Integer) session1.getAttribute("idUser");
    if(idUser!= null){
        response.sendRedirect("../setupData");
    }

%>
<div class="container login-container">
    <div class="row w-100">
        <div class="col-lg-6 d-none d-lg-block">
            <img src="../img/pngtree-colorful-mushrooms-sticker-in-cartoon-style-vector-illustration-isolated-on-white-png-image_11380161.png" alt="Illustration" class="illustration">
        </div>
        <div class="col-lg-6">
            <div class="login-box">
                <h2 class="form-title">Đăng nhập</h2>
        <form action="${pageContext.request.contextPath}/checkLogin" method="POST">
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Nhập địa chỉ email của bạn">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Mật khẩu: </label>
                <div class="input-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu">
                    <span class="input-group-text">
            <i class="fa-solid fa-eye" id="togglePassword" style="cursor: pointer;"></i></span>
                </div>

            </div>
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="remember" name="rememberMe">
                <label class="form-check-label" for="remember">Ghi nhớ đăng nhập</label>



            </div>
            <div class="mb-3" id="captcha-container">
                <%
                    Integer failedAttempts = (Integer) session.getAttribute("failedAttempts");
                    System.out.println(failedAttempts + "CAPCHA JSP");
                    if (failedAttempts != null && failedAttempts >= 3) {
                %>

                <div class="mb-3">
                    <div class="g-recaptcha" data-sitekey="6Lf30PwqAAAAADW2bDyLzaxykPZDCjVYb8d49LMS"></div>
                </div>
                <%
                    }
                %>
            </div>
            <button type="submit" class="btn btn-dark w-100">Đăng nhập</button>

            <%
                String errorMessage = (String) session.getAttribute("errorMessage");
                if (errorMessage != null) {
            %>
            <div class="alert alert-danger text-center" role="alert">
                <%= errorMessage %>
            </div>
            <%
                    session.removeAttribute("errorMessage");
                }
            %>
            <div class="text-center mt-3">
            <a href="register.jsp">Đăng ký</a>
        </div>
            <div class="text-center mt-3">
                <a href="forgotPassword.jsp" >Quên mật khẩu?</a>
            </div>

        </form>
                <div class="text-center mt-3">
                    <p>hoặc đăng nhập bằng</p>
                    <div class="social-buttons">
                        <img src="../img/vector-blue-social-media-logo_1080184-225.jpg.avif" alt="Facebook">
                        <a href="#" id="loginBtn">
                            <img src="../img/Google_Icons-09-512.webp" alt="Google">
                        </a>
                        <img src="../img/Logo-Zalo-Arc.png.webp" alt="Zalo">
                        <img src="../img/yahoo-512.webp" alt="Yahoo">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    const client_id = "673781377779-7p6kijl6vllmrrmc3jeocqia7d2jojjv.apps.googleusercontent.com";
    const redirect_uri = "http://localhost:8080/WebFinall/callback"; // Servlet xử lý đăng nhập Google
    const scope = encodeURIComponent("https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile");

    const auth_url = "https://accounts.google.com/o/oauth2/v2/auth" +
        "?scope=" + scope +
        "&response_type=token" +
        "&redirect_uri=" + encodeURIComponent(redirect_uri) +
        "&client_id=" + client_id;

    document.getElementById("loginBtn").addEventListener("click", () => {
        window.location.href = auth_url;
    });

    window.onload = function () {
        const params = new URLSearchParams(window.location.hash.substring(1));
        const access_token = params.get("access_token");

        if (access_token && !sessionStorage.getItem("token_sent")) {
            sessionStorage.setItem("token_sent", "true"); // Đánh dấu token đã gửi

            // Gửi token đến Servlet để xử lý
            fetch("http://localhost:8080/WebFinall/callback?access_token=" + access_token)
                .then(response => response.text())
                .then(data => {
                    console.log("Response từ server:", data);
                    if (data.includes("Login failed")) {
                        alert("Đăng nhập thất bại, vui lòng thử lại!");
                        sessionStorage.removeItem("token_sent"); // Cho phép gửi lại nếu lỗi
                    } else {
                        window.location.replace("../index.jsp?logout=true"); // Chuyển hướng sau khi login
                    }
                })
                .catch(error => {
                    console.error("Lỗi gửi token:", error);
                    alert("Lỗi khi đăng nhập, vui lòng thử lại!");
                    sessionStorage.removeItem("token_sent");
                });
        }
    };
    function togglePassword(inputId, iconId) {
        var passwordInput = document.getElementById(inputId);
        var icon = document.getElementById(iconId);
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
            icon.classList.remove("fa-eye");
            icon.classList.add("fa-eye-slash");
        } else {
            passwordInput.type = "password";
            icon.classList.remove("fa-eye-slash");
            icon.classList.add("fa-eye");
        }
    }

    // Gán sự kiện cho từng icon
    document.getElementById("togglePassword").addEventListener("click", function () {
        togglePassword("password", "togglePassword");
    });

</script>
<script>

</script>
<script>
    console.log("Loading reCAPTCHA...");
</script>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>

</html>
