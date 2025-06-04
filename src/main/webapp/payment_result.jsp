<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kết quả thanh toán</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .result-container {
            background-color: white;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
            text-align: center;
        }

        .icon {
            font-size: 48px;
            margin-bottom: 20px;
        }

        .success {
            color: #28a745;
        }

        .error {
            color: #dc3545;
        }

        .message {
            font-size: 24px;
            margin-bottom: 20px;
            font-weight: bold;
        }

        .details {
            text-align: left;
            margin: 20px 0;
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 5px;
        }

        .detail-item {
            margin: 10px 0;
            display: flex;
            justify-content: space-between;
        }

        .detail-label {
            font-weight: bold;
            color: #666;
        }

        .buttons {
            margin-top: 30px;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            margin: 0 10px;
            font-weight: bold;
        }

        .btn-primary {
            background-color: #007bff;
            color: white;
        }

        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }

        .btn:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
    <div class="result-container">
        <% if ("success".equals(request.getAttribute("status"))) { %>
            <i class="fas fa-check-circle icon success"></i>
            <div class="message success">${message}</div>
            <div class="details">
                <div class="detail-item">
                    <span class="detail-label">Mã đơn hàng:</span>
                    <span>${orderId}</span>
                </div>
                <div class="detail-item">
                    <span class="detail-label">Số tiền:</span>
                    <span>${amount} VNĐ</span>
                </div>
                <div class="detail-item">
                    <span class="detail-label">Ngân hàng:</span>
                    <span>${bankCode}</span>
                </div>
                <div class="detail-item">
                    <span class="detail-label">Thời gian thanh toán:</span>
                    <span>${payDate}</span>
                </div>
            </div>
        <% } else { %>
            <i class="fas fa-times-circle icon error"></i>
            <div class="message error">${message}</div>
        <% } %>
        
        <div class="buttons">
            <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Về trang chủ</a>
            <% if ("success".equals(request.getAttribute("status"))) { %>
                <a href="${pageContext.request.contextPath}/orders" class="btn btn-secondary">Xem đơn hàng</a>
            <% } else { %>
                <a href="${pageContext.request.contextPath}/payment" class="btn btn-secondary">Thử lại</a>
            <% } %>
        </div>
    </div>
</body>
</html> 