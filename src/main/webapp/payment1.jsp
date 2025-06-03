<%@ page import="Models.OrderInfo" %><%--
  Created by IntelliJ IDEA.
  User: airm2
  Date: 2/6/25
  Time: 09:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Thanh Toán</title>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
  <style>
    body {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      min-height: 100vh;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    .payment-container {
      max-width: 600px;
      margin: 50px auto;
      background: white;
      border-radius: 20px;
      box-shadow: 0 20px 40px rgba(0,0,0,0.1);
      overflow: hidden;
    }

    .payment-header {
      background: linear-gradient(45deg, #1e3c72, #2a5298);
      color: white;
      padding: 30px;
      text-align: center;
    }

    .payment-header h2 {
      margin: 0;
      font-weight: 300;
      font-size: 2rem;
    }

    .order-summary {
      background: #f8f9fa;
      padding: 20px;
      border-bottom: 1px solid #dee2e6;
    }

    .payment-methods {
      padding: 30px;
    }

    .payment-option {
      border: 2px solid #e9ecef;
      border-radius: 15px;
      padding: 20px;
      margin-bottom: 15px;
      cursor: pointer;
      transition: all 0.3s ease;
      background: white;
    }

    .payment-option:hover {
      border-color: #007bff;
      transform: translateY(-2px);
      box-shadow: 0 5px 15px rgba(0,123,255,0.2);
    }

    .payment-option.selected {
      border-color: #007bff;
      background: #f8f9ff;
    }

    .payment-option input[type="radio"] {
      margin-right: 15px;
      transform: scale(1.2);
    }

    .payment-icon {
      width: 40px;
      height: 40px;
      margin-right: 15px;
      display: inline-flex;
      align-items: center;
      justify-content: center;
      border-radius: 10px;
      color: white;
      font-size: 1.2rem;
    }

    .cod-icon { background: #28a745; }
    .vnpay-icon { background: #0066cc; }
    .momo-icon { background: #d82d8b; }
    .bank-icon { background: #17a2b8; }

    .btn-pay {
      background: linear-gradient(45deg, #007bff, #0056b3);
      border: none;
      border-radius: 25px;
      padding: 15px 40px;
      font-size: 1.1rem;
      font-weight: 600;
      color: white;
      width: 100%;
      margin-top: 20px;
      transition: all 0.3s ease;
    }

    .btn-pay:hover {
      transform: translateY(-2px);
      box-shadow: 0 5px 15px rgba(0,123,255,0.3);
    }

    .vnpay-popup {
      display: none;
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background: rgba(0,0,0,0.7);
      z-index: 1000;
      backdrop-filter: blur(5px);
    }

    .popup-content {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      background: white;
      border-radius: 20px;
      padding: 40px;
      max-width: 400px;
      width: 90%;
      text-align: center;
      box-shadow: 0 20px 60px rgba(0,0,0,0.3);
    }

    .qr-code {
      width: 200px;
      height: 200px;
      background: #f8f9fa;
      border: 2px dashed #dee2e6;
      margin: 20px auto;
      display: flex;
      align-items: center;
      justify-content: center;
      border-radius: 10px;
      font-size: 3rem;
      color: #6c757d;
    }

    .close-popup {
      position: absolute;
      top: 15px;
      right: 20px;
      background: none;
      border: none;
      font-size: 1.5rem;
      color: #6c757d;
      cursor: pointer;
    }

    .amount-display {
      background: linear-gradient(45deg, #007bff, #0056b3);
      color: white;
      padding: 15px;
      border-radius: 10px;
      margin: 20px 0;
      font-size: 1.5rem;
      font-weight: bold;
    }

    .popup-timer {
      color: #dc3545;
      font-weight: 600;
      margin-top: 15px;
    }

    @media (max-width: 768px) {
      .payment-container {
        margin: 20px;
      }

      .payment-header h2 {
        font-size: 1.5rem;
      }

      .popup-content {
        padding: 30px 20px;
      }
    }
  </style>
</head>
<body>
<div class="payment-container">
  <div class="payment-header">
    <h2><i class="fas fa-credit-card me-3"></i>Thanh Toán</h2>
  </div>
  <%
    OrderInfo orderInfo = (OrderInfo) session.getAttribute("orderInfo");
    System.out.println(orderInfo.toString() + "ldhflshjfljkjsd");
    System.out.println(orderInfo.getAddress());
  %>
  <div class="order-summary">
    <div class="row">
      <div class="col-6">
        <strong>Tổng tiền hàng:</strong>
      </div>
      <div class="col-6 text-end">
        <span id="subtotal"><%= orderInfo.getTotalPrice() %> VNĐ</span>
      </div>
    </div>

    <hr>
    <div class="row">
      <div class="col-6">
        <h5><strong>Tổng thanh toán:</strong></h5>
      </div>
      <div class="col-6 text-end">
        <h5><strong id="total" style="color: #007bff;"><%= orderInfo.getTotalPrice() %> VNĐ</strong></h5>
      </div>
    </div>
  </div>

  <div class="payment-methods">
    <h5 class="mb-4">Chọn phương thức thanh toán:</h5>

    <div class="payment-option" onclick="selectPayment('cod')">
      <input type="radio" name="payment" id="cod" value="cod">
      <div class="payment-icon cod-icon">
        <i class="fas fa-truck"></i>
      </div>
      <div style="display: inline-block; vertical-align: middle;">
        <strong>Thanh toán khi nhận hàng (COD)</strong>
        <div class="text-muted small">Thanh toán bằng tiền mặt khi nhận hàng</div>
      </div>
    </div>

    <div class="payment-option" onclick="selectPayment('vnpay')">
      <input type="radio" name="payment" id="vnpay" value="vnpay">
      <div class="payment-icon vnpay-icon">
        <i class="fas fa-credit-card"></i>
      </div>
      <div style="display: inline-block; vertical-align: middle;">
        <strong>VNPay</strong>
        <div class="text-muted small">Thanh toán qua cổng VNPay</div>
      </div>
    </div>

    <div class="payment-option" onclick="selectPayment('momo')">
      <input type="radio" name="payment" id="momo" value="momo">
      <div class="payment-icon momo-icon">
        <i class="fas fa-mobile-alt"></i>
      </div>
      <div style="display: inline-block; vertical-align: middle;">
        <strong>Ví MoMo</strong>
        <div class="text-muted small">Thanh toán qua ví điện tử MoMo</div>
      </div>
    </div>

    <div class="payment-option" onclick="selectPayment('bank')">
      <input type="radio" name="payment" id="bank" value="bank">
      <div class="payment-icon bank-icon">
        <i class="fas fa-university"></i>
      </div>
      <div style="display: inline-block; vertical-align: middle;">
        <strong>Chuyển khoản ngân hàng</strong>
        <div class="text-muted small">Chuyển khoản trực tiếp qua ngân hàng</div>
      </div>
    </div>

    <button class="btn btn-pay" onclick="processPayment()">
      <i class="fas fa-lock me-2"></i>Thanh Toán Ngay
    </button>
  </div>
</div>

<!-- VNPay Popup -->
<div class="vnpay-popup" id="vnpayPopup">
  <div class="popup-content">
    <button class="close-popup" onclick="closeVNPayPopup()">&times;</button>
    <div style="text-align: center;">
      <div class="payment-icon vnpay-icon" style="margin: 0 auto 20px; width: 60px; height: 60px; font-size: 1.5rem;">
        <i class="fas fa-credit-card"></i>
      </div>
      <h4>Thanh toán VNPay</h4>

      <div class="amount-display">
        <div>Số tiền thanh toán</div>
        <div id="vnpayAmount"><%= orderInfo.getTotalPrice() %> VNĐ</div>
      </div>

      <div class="qr-code">
        <i class="fas fa-qrcode"></i>
      </div>

      <p class="text-muted">Quét mã QR bằng ứng dụng VNPay để thanh toán</p>

      <div class="popup-timer">
        <i class="fas fa-clock me-2"></i>
        Thời gian còn lại: <span id="countdown">05:00</span>
      </div>

      <div class="mt-3">
        <button class="btn btn-secondary me-2" onclick="closeVNPayPopup()">Hủy</button>
        <button class="btn btn-success" onclick="confirmPayment()">
          <i class="fas fa-check me-2"></i>Đã thanh toán
        </button>
      </div>
    </div>
  </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script>
  let selectedPayment = '';
  let countdownTimer;

  function selectPayment(method) {
    // Remove previous selection
    document.querySelectorAll('.payment-option').forEach(option => {
      option.classList.remove('selected');
    });

    // Add selection to current option
    event.currentTarget.classList.add('selected');

    // Check radio button
    document.getElementById(method).checked = true;
    selectedPayment = method;
  }

  function processPayment() {
    if (!selectedPayment) {
      alert('Vui lòng chọn phương thức thanh toán!');
      return;
    }

    if (selectedPayment === 'vnpay') {
      // Gọi API VNPay
      fetch('${pageContext.request.contextPath}/vnpay-payment?amount=' + <%= orderInfo.getTotalPrice() %>)
        .then(response => response.json())
        .then(data => {
          if (data.code === '00') {
            window.location.href = data.data;
          } else {
            alert('Có lỗi xảy ra khi tạo thanh toán VNPay: ' + data.message);
          }
        })
        .catch(error => {
          alert('Có lỗi xảy ra: ' + error);
        });
    } else {
      // Xử lý các phương thức thanh toán khác
      fetch('/WebFinall/create-order-cod', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ paymentMethod: selectedPayment })
      })
      .then(res => res.json())
      .then(data => {
        if (data.success) {
          alert('Đơn hàng của bạn đã được xác nhận!\nMã vận đơn GHN: ' + data.order_code);
          window.location.href = "hoanthanh.jsp";
        } else {
          alert('Tạo đơn hàng thất bại: ' + data.message);
        }
      })
      .catch(err => {
        alert('Lỗi khi tạo đơn hàng: ' + err.message);
      });
    }
  }


  function showVNPayPopup() {
    document.getElementById('vnpayPopup').style.display = 'block';
    document.body.style.overflow = 'hidden';
    startCountdown(300); // 5 minutes countdown
  }

  function closeVNPayPopup() {
    document.getElementById('vnpayPopup').style.display = 'none';
    document.body.style.overflow = 'auto';
    if (countdownTimer) {
      clearInterval(countdownTimer);
    }
  }

  function startCountdown(seconds) {
    let timeLeft = seconds;
    const countdownElement = document.getElementById('countdown');

    countdownTimer = setInterval(() => {
      const minutes = Math.floor(timeLeft / 60);
      const secs = timeLeft % 60;
      countdownElement.textContent =
              `${minutes.toString().padStart(2, '0')}:${secs.toString().padStart(2, '0')}`;

      if (timeLeft <= 0) {
        clearInterval(countdownTimer);
        alert('Phiên thanh toán đã hết hạn!');
        closeVNPayPopup();
      }
      timeLeft--;
    }, 1000);
  }

  function confirmPayment() {
    clearInterval(countdownTimer);
    closeVNPayPopup();

    // Simulate payment success
    setTimeout(() => {
      alert('Thanh toán thành công! Cảm ơn bạn đã mua hàng.');
    }, 500);
  }

  // Close popup when clicking outside
  document.getElementById('vnpayPopup').addEventListener('click', function(e) {
    if (e.target === this) {
      closeVNPayPopup();
    }
  });

  // Auto-select first payment method
  document.addEventListener('DOMContentLoaded', function() {
    selectPayment('cod');
    document.querySelector('.payment-option').click();
  });
</script>
</body>
</html>