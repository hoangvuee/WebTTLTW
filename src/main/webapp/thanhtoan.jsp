<%@ page import="Models.cart.Cart" %>
<%@ page import="Models.Shipping.Shipping" %>
<%@ page import="Services.ServiceShipping" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Shipping.Shippingdetail" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: airm2
  Date: 15/12/2024
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Thanhtoan</title>
  <link rel="stylesheet" href="css/styleThanhtoan.css">
  <link rel="stylesheet" href="css/shoppingcart.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">


  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

  <script src="https://esgoo.net/scripts/jquery.js"></script>


  <link rel="stylesheet" href="css/fontawesome.css">
  <link rel="stylesheet" href="css/templatemo-space-dynamic.css">
  <link rel="stylesheet" href="css/animated.css">
  <link rel="stylesheet" href="css/owl.css">
</head>
<style>
  .progress-header {
    background-color: #f8f8f8;
    padding: 20px 0;
    display: flex;
    justify-content: center;
  }

  /* Step Container */
  .step-container {
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 80%;
    max-width: 800px;
  }

  /* Step Item */
  .step {
    display: flex;
    flex-direction: column;
    align-items: center;
    flex: 1;
    position: relative;
  }

  .step-icon {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    background-color: #ddd;
    color: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
    font-weight: bold;
    font-size: 1rem;
    transition: all 0.3s ease;
  }

  .step-text {
    margin-top: 8px;
    font-size: 0.9rem;
    color: #888;
    text-transform: uppercase;
    font-weight: 600;
  }

  /* Line Between Steps */
  .step-line {
    height: 2px;
    background-color: #ddd;
    flex: 1;
  }

  /* Active Step */
  .step.active .step-icon {
    background-color: #ff7b00; /* Màu cam nổi bật */
  }

  .step.active .step-text {
    color: #ff7b00; /* Màu cam cho text */
  }
  body {
    background-color: #f8f9fa;
  }
  .order-summary-card, .payment-info-card {
    border: none;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    margin-top: 20px;
  }
  .order-summary-header, .payment-info-header {
    background-color: #6c5ce7;
    color: #fff;
    font-weight: bold;
    text-transform: uppercase;
    border-radius: 10px 10px 0 0;
    padding: 15px;
  }
  .product-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px dashed #ccc;
    padding: 10px 0;
  }
  .product-details img {
    width: 50px;
    height: 50px;
    margin-right: 15px;
  }
  .total-section {
    font-weight: bold;
    color: #2c3e50;
    font-size: 18px;
  }
  .container {
    max-width: 100%; /* Ví dụ: chỉnh max-width phù hợp với giao diện */
    margin: 0 0; /* Canh giữa */
    height: 200px;
  }


  .btn-payment {
    background-color: #ff7b00;
    color: white;
    width: 100%;
    font-size: 18px;
    padding: 12px 0;
    border: none;
    border-radius: 25px;
    transition: background-color 0.3s ease, transform 0.2s ease;
  }

  .btn-payment:hover {
    background-color: #e67e22;
    transform: scale(1.05);
  }
  .shipping-options {
    display: flex;
    flex-direction: column;
    gap: 10px; /* Khoảng cách giữa các ô */
  }

  .shipping-option {
    display: flex;
    align-items: center;
    background: #f9f9f9; /* Màu nền nhạt */
    border: 2px solid #ddd; /* Viền nhẹ */
    border-radius: 12px; /* Bo góc */
    padding: 12px 16px;
    cursor: pointer;
    transition: all 0.3s ease;
  }

  .shipping-option:hover {
    background: #ececec; /* Hiệu ứng hover */
    border-color: #8a2be2; /* Viền tím nhẹ */
  }

  .shipping-option input {
    margin-right: 10px;
    transform: scale(1.2);
    accent-color: #8a8a8a; /* Màu radio */
  }

  .shipping-text {
    font-size: 16px;
    font-weight: 500;
    color: #333;
  }
  .shipping-options {
    display: flex;
    flex-direction: column;
    gap: 12px;
  }

  .shipping-option {
    display: flex;
    align-items: center;
    background: #fff;
    border: 2px solid #ccc;
    border-radius: 16px;
    padding: 14px 18px;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
    box-shadow: 2px 4px 8px rgba(0, 0, 0, 0.1);
    position: relative;
  }
  select {
    max-height: 300px; /* Giới hạn chiều cao */
    overflow-y: auto;  /* Hiện thanh cuộn khi dài quá */
  }
  .shipping-option:hover {
    background: #f3e5ff; /* Màu nhạt tím */
    border-color: #616161;
  }

  .shipping-option input {
    display: none; /* Ẩn radio mặc định */
  }

  .shipping-content {
    display: flex;
    flex-direction: column;
    gap: 4px;
    font-size: 16px;
    font-weight: 500;
    color: #333;
  }

  .shipping-title {
    font-size: 17px;
    font-weight: bold;
  }

  .shipping-price {
    font-size: 14px;
    color: #666;
  }

  /* Khi được chọn, đổi màu viền và thêm hiệu ứng */
  .shipping-option input:checked + .shipping-content {
    color: #da4338;
    font-weight: bold;
  }

  /* Hiệu ứng bo tròn cho radio button */
  .shipping-option::before {
    content: "";
    position: absolute;
    left: 10px;
    width: 20px;
    height: 20px;
    border: 2px solid #7a7a7a;
    border-radius: 50%;
    background: #fff;
    transition: all 0.3s ease-in-out;
  }

  .shipping-option input:checked + .shipping-content::before {
    background: #6e6d6e;
  }
  /* Container cho ô nhập mã giảm giá */
  .shipping-discount {
    display: flex;
    align-items: center;
    margin-top: 15px;
    gap: 10px;
  }

  /* Ô nhập mã giảm giá */
  .shipping-discount input {
    flex: 1;
    padding: 10px;
    border: 2px solid #ccc;
    border-radius: 12px;
    font-size: 16px;
    outline: none;
    transition: 0.3s ease-in-out;
  }

  /* Khi focus vào ô nhập */
  .shipping-discount input:focus {
    border-color: #868686;
    box-shadow: 0 0 8px rgba(138, 43, 226, 0.3);
  }

  /* Nút Áp dụng */
  .shipping-discount button {
    padding: 10px 15px;
    background: linear-gradient(135deg, #f46868, #f75932);
    color: #fff;
    border: none;
    border-radius: 12px;
    font-size: 16px;
    cursor: pointer;
    transition: 0.3s ease-in-out;
  }

  /* Hiệu ứng khi hover vào nút */
  .shipping-discount button:hover {
    background: linear-gradient(135deg, #6a0dad, #4b0082);
  }
</style>

<body>
<%@include file="header.jsp"%>
<section style="margin-bottom: 400px">
  <header class="progress-header">
    <div class="step-container">
      <!-- Step 1 -->
      <div class="step">
        <div class="step-icon">1</div>
        <div class="step-text">GIỎ HÀNG</div>
      </div>
      <!-- Line -->
      <div class="step-line"></div>
      <!-- Step 2 -->
      <div class="step active">
        <div class="step-icon">2</div>
        <div class="step-text">THANH TOÁN</div>
      </div>
      <!-- Line -->
      <div class="step-line"></div>
      <!-- Step 3 -->
      <div class="step">
        <div class="step-icon">3</div>
        <div class="step-text">HOÀN TẤT</div>
      </div>
    </div>
  </header>
  <section class="content" style="height: 1100px">
    <article class="khuyenmai">
      <p class="pkhuyenmai">
        Bạn có ưu đãi? <b onclick="toggleBox()">Ấn vào đây để nhập mã</b>
      </p>
      <article class="inputkhuyenmai" id = "newBox">
        <article class="textinput">
          Nếu bạn có mã giảm giá, vui lòng điền vào bên dưới.

        </article>
        <form action="" class="form">
          <textarea name="" id="" class="inputvaluekhuyenmai" placeholder="Nhập mã vào đây"></textarea>
          <button type="submit" class="button"> Xác nhận
          </button>
        </form>
      </article>
      <section class="contentproduct">
        <article class="inforkhuyenmai">
          <article class="icon">
            <i class="fa-solid fa-circle-exclamation"></i>
          </article>
          <article class="inforchitiet">
            <p>Để nhận
              "35 NAXA"
              Quý khách hãy
              <a href="http://">Đăng nhập</a>
              vào tài khoản. Nếu chưa có, Quý khách hãy tick
              <a href="http://">Tạo tài khoản mới?</a>
              nhé!</p>
          </article>
        </article>
        <form action="CheckoutServlet" method="post">
          <section class="information">

            <article class="inforfreeship">
              <p class="p">Thêm <span style="color: rgb(157, 56, 27); font-size: 15px;"> 455.000₫</span> nữa bạn sẽ được FREESHIP</p>
              <article class="rowfreeship">
                <article class="rowcheck">
                </article>
              </article>
            </article>
            <!--Thong tin ca  nhan de thanh toan-->
            <div class=" py-8" style="margin-left: -20px" >
              <div class="row">
                <!-- Thông Tin Thanh Toán -->
                <div class="col-md-5">
                  <div class="card payment-info-card">
                    <div class="payment-info-header text-center" style="background-color: #ff7b00">
                      Thông Tin Thanh Toán
                    </div>
                    <div class="card-body">
                      <div class="mb-3">
                        <label for="fullName" class="form-label">Họ và Tên *</label>
                        <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Nhập họ và tên">
                      </div>
                      <div class="mb-3">
                        <label for="phoneNumber" class="form-label">Số Điện Thoại *</label>
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Nhập số điện thoại">
                      </div>
                      <div class="mb-3">
                        <label for="email" class="form-label">Địa chỉ Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email (tùy chọn)">
                      </div>
                      <div class="row">
                        <!-- Chọn tỉnh/thành phố -->
                        <div class="col-md-6 mb-3">
                          <label for="province" class="form-label">Tỉnh/Thành Phố *</label>
                          <select class="form-select" id="province" name="province">
                            <option value="">Chọn một tùy chọn...</option>
                          </select>
                        </div>

                        <!-- Chọn quận/huyện -->
                        <div class="col-md-6 mb-3">
                          <label for="district" class="form-label">Quận/Huyện *</label>
                          <select class="form-select" id="district" name="district">
                            <option value="">Chọn quận/huyện...</option>
                          </select>
                        </div>

                        <!-- Chọn phường/xã -->
                        <div class="col-md-6 mb-3">
                          <label for="ward" class="form-label">Phường/Xã *</label>
                          <select class="form-select" id="ward" name="ward">
                            <option value="">Chọn phường/xã...</option>
                          </select>
                        </div>
                      </div>

                      <!-- Ẩn các input để gửi giá trị tên (text) -->
                      <input type="hidden" id="province_name" name="province_name" value="">
                      <input type="hidden" id="district_name" name="district_name" value="">
                      <input type="hidden" id="ward_name" name="ward_name" value="">


                      <div class="mb-3">
                        <label for="address" class="form-label">Địa chỉ *</label>
                        <input type="text" class="form-control" id="address" name="address" placeholder="Tòa nhà, số nhà, tên đường">
                      </div>
                      <div class="form-check mb-3">
                        <input type="checkbox" class="form-check-input" id="differentAddress" name="differentAddress">
                        <label class="form-check-label" for="differentAddress">Giao hàng tới địa chỉ khác?</label>
                      </div>
                      <div class="mb-3">
                        <label for="notes" class="form-label">Ghi Chú Đơn Hàng</label>
                        <textarea class="form-control" id="notes" name="notes" rows="3" placeholder="Ghi chú về đơn hàng, ví dụ: thời gian hay địa điểm giao hàng"></textarea>
                      </div>
                      <p>Địa chỉ cửa hàng mua: Tp.Cần thơ</p>
                    </div>

                  </div>
                  <div id="cod-info" class="payment-inf">
                    <form id="shippingForm">
                    <h4 ><i class="fa-solid fa-truck"></i> <span>Chọn phương thức giao hàng</span></h4>
                    <div class="card-body p-4" id="shipping-options">

                      <c:forEach var="item" items="${sessionScope.totalship.items}">
                        <label class="shipping-option">
                          <input type="radio"
                                 name="shippingMethod"
                                 value="${item.id}"
                                 data-price="${item.price}"
                                 id="shipping_${item.id}"
                                 onchange="updateShippingCost(this)"
                                 required>

                          <div class="shipping-content">
                            <span class="shipping-title">${item.name}</span>
                            <span class="shipping-price">
                <strong>
                    <fmt:formatNumber value="${item.price}" type="number" groupingUsed="true" /> đ
                </strong>
            </span>
                          </div>
                        </label>
                        <input type="hidden" id="shippingPrice" name="shippingPrice" value="0">

                      </c:forEach>
                    </div>

                    <!-- Ô nhập mã giảm giá vận chuyển -->
                    <div class="shipping-discount">
                      <input type="text" id="shippingCoupon" placeholder="Nhập mã giảm giá vận chuyển">
                      <button id="applyShippingCoupon">Áp dụng</button>
                    </div>
                    </form>
                  </div>

                </div>

                <!-- Thông Tin Đơn Hàng -->
                <div class="col-md-5" style="width: 700px">
                  <div class="card order-summary-card">
                    <div class="order-summary-header text-center" style="background-color: #ff7b00;">
                      Thông Tin Đơn Hàng
                    </div>
                    <div class="card-body" style="overflow: scroll ;height: 500px;">
                      <!-- Sản phẩm -->
                      <%
                        Cart cart = (Cart) session.getAttribute("cr7");
                        if (cart == null) {
                          cart = new Cart();
                          session.setAttribute("cr7", cart);
                        }
                        System.out.println("Session Cart: " + cart);
                        System.out.println("Number of items in cart: " + cart.getItems().size());
                        Shipping shipping = (Shipping) session.getAttribute("totalship");
                        if(shipping == null){
                          shipping = new Shipping();
                          session.setAttribute("totalship",shipping);
                        }
                        System.out.println(shipping.getItems() + "adsfasdfsdf");


                      %>
                      <c:forEach var="item" items="${sessionScope.cr7.items}">
                        <div class="cart-item" style="height: 100px">
                          <img src="img/${item.img}" alt="Sản phẩm">
                          <div class="cart-info">
                            <h5>${item.name}</h5>
                            <p><span style="color: rgb(83, 7, 7);">ID:</span> ${item.id} | <span style="color: rgb(83, 7, 7);">Trọng lượng:</span>${item.weight}gr</p>

                            <!-- Hiển thị giá -->
                            <div class="price-container" style="color: rgb(240, 89, 89); ;"><b>Giá:</b>
                              <span class="original-price">${item.rawTotal} đ</span>
                              <span class="discount-badge">-${item.sale}%</span>
                              <span class="discount-price"><fmt:formatNumber value="${item.total}" type="number" groupingUsed="true" /> đ</span>
                            </div>
                          </div>

                          <!-- Input số lượng -->
                          <td class="text-center">
                            <div class="quantity-control">

                              <input type="text" id="quantity-${item.id}" class="form-control form-control-sm text-center mx-1" value="${item.quantity}" style="width: 50px;" readonly data-price="${item.price}">

                            </div>
                          </td>

                          <!-- Nút xóa -->
                          <td><a href="remove?productID=${item.id}&weight=${item.weight}"><button class="remove-btn ms-3"><i class="fas fa-trash-alt"></i></button></a></td>
                        </div>
                      </c:forEach>
                      <!-- Tổng cộng -->

                    </div>
                    <div class="total-section d-flex justify-content-between mt-4" style="height: 50px">
                      <span>Tổng cộng:</span>
                      <c:set var="sum" value="${sessionScope.cr7.totalPrice}" />

                      <span id="totalAmount" class="text-danger" style="font-size: 25px">
                      <fmt:formatNumber value="${sum}" type="number" groupingUsed="true" /> đ
                        </span>
                    </div>





                   <div class="accordion mt-4" id="paymentAccordion">


                    <div class="accordion-item border-0 rounded-3 mb-3 shadow-sm">
                      <h2 class="accordion-header" id="codHeader">
                        <input type="radio" name="paymentMethod" id="cod" class="me-2" onchange="handlePaymentSelection(this)" >
                        <label for="cod" class="accordion-button collapsed">
                          <i class="fa-solid fa-truck me-2"></i>COD - Thanh toán khi nhận hàng
                        </label>
                      </h2>
                      <div id="codCollapse" class="accordion-collapse collapse">
                        <div class="accordion-body">
                          <p>Thanh toán cho shipper khi nhận hàng.</p>
                        </div>
                      </div>
                    </div>

                    <!-- Chuyển khoản ngân hàng -->
                    <div class="accordion-item border-0 rounded-3 mb-3 shadow-sm">
                      <h2 class="accordion-header" id="bankHeader">
                        <input type="radio" name="paymentMethod" id="bank" class="me-2" onchange="handlePaymentSelection(this)" value="BANK-Chuyển khoản ngân hàng">
                        <label for="bank" class="accordion-button collapsed">
                          <i class="fa-solid fa-building-columns me-2"></i>Chuyển khoản ngân hàng
                        </label>
                      </h2>
                      <div id="bankCollapse" class="accordion-collapse collapse">
                        <div class="accordion-body">
                          <p>Vui lòng chuyển khoản tới thông tin sau:</p>
                          <div class="d-flex align-items-center mb-3">
                            <img src="img/no-image-news.jpg" alt="Ngân hàng VCB" class="me-3" style="width: 50px; height: 50px;">
                            <div>
                              <p class="mb-0">Số tài khoản: <strong>5555-032-463-9999</strong></p>
                              <p class="mb-0">Tên chủ tài khoản: <strong>Nguyễn Bùi Hoàng Vũ</strong></p>
                              <p class="mb-0">Ngân hàng: <strong>Vietcombank</strong></p>
                            </div>
                          </div>
                          <p>Ghi chú: Vui lòng ghi "Họ tên - Mã đơn hàng" khi chuyển khoản.</p>
                        </div>
                      </div>
                    </div>

                    <!-- Ví MoMo -->
                    <div class="accordion-item border-0 rounded-3 shadow-sm">
                      <h2 class="accordion-header" id="momoHeader">
                        <input type="radio" name="paymentMethod" id="momo" class="me-2" onchange="handlePaymentSelection(this)" value="MOMO-Thanh toán MOMO">
                        <label for="momo" class="accordion-button collapsed">
                          <i class="fa-solid fa-wallet me-2"></i>Ví MoMo
                        </label>
                      </h2>
                      <div id="momoCollapse" class="accordion-collapse collapse">
                        <div class="accordion-body">
                          <p>Quét mã QR hoặc gửi tiền tới số sau:</p>
                          <div class="d-flex align-items-center mb-3">
                            <img src="img/momo.png" alt="Ví MoMo" class="me-3" style="width: 50px; height: 50px;">
                            <div>
                              <p class="mb-0">Số điện thoại: <strong>0868032463</strong></p>
                              <p class="mb-0">Tên chủ tài khoản: <strong>Nguyễn Bùi Hoàng Vũ</strong></p>
                            </div>
                          </div>
                          <p>Lưu ý: Ghi rõ "Họ tên - Mã đơn hàng" khi thực hiện giao dịch.</p>
                        </div>
                      </div>
                    </div>
                  </div>



                  </div>


                  <button type="submit" class="btn-payment mt-4">Thanh Toán</button>

                </div>
              </div>
            </div>

          </section>
        </form>
      </section>
    </article>
  </section>
</section>
<div style=" background-color: #f3eee7;  height: 450px">
  <%@include file="footer.jsp"%>
</div>
<script>
  // Hàm tải tỉnh/thành phố từ API
  // Hàm tải tỉnh/thành phố từ API
  async function loadProvinces() {
    const response = await fetch('https://esgoo.net/api-tinhthanh/1/0.htm');
    const data = await response.json();
    const provinceSelect = document.getElementById('province');

    if (data.error === 0) {
      data.data.forEach(province => {
        let option = new Option(province.full_name, province.id);
        provinceSelect.add(option);
      });
    }
  }

  // Hàm tải quận/huyện theo tỉnh/thành phố
  async function loadDistricts(provinceID) {
    const districtSelect = document.getElementById('district');
    const wardSelect = document.getElementById('ward');

    // Reset quận/huyện và phường/xã
    districtSelect.innerHTML = '<option value="">Chọn quận/huyện...</option>';
    wardSelect.innerHTML = '<option value="">Chọn phường/xã...</option>';

    if (!provinceID) return;

    try {
      const url = 'https://esgoo.net/api-tinhthanh/2/' + provinceID + '.htm';
      const response = await fetch(url);
      const data = await response.json();

      if (data.error === 0 && data.data) {
        data.data.forEach(district => {
          let option = new Option(district.full_name, district.id);
          districtSelect.add(option);
        });
      }
    } catch (error) {
      console.error("Lỗi tải quận/huyện:", error);
    }
  }

  // Hàm tải phường/xã theo quận/huyện
  async function loadWards(districtID) {
    const wardSelect = document.getElementById('ward');
    wardSelect.innerHTML = '<option value="">Chọn phường/xã...</option>';

    if (!districtID) return;

    const url = 'https://esgoo.net/api-tinhthanh/3/' + districtID + '.htm';
    const response = await fetch(url);
    const data = await response.json();

    if (data.error === 0) {
      data.data.forEach(ward => {
        let option = new Option(ward.full_name, ward.id);
        wardSelect.add(option);
      });
    }
  }

  // Khi trang tải, tải tỉnh/thành phố
  loadProvinces();

  // Khi chọn tỉnh/thành phố
  document.getElementById('province').addEventListener('change', function () {
    const provinceID = this.value;
    const provinceName = this.options[this.selectedIndex].text; // Lấy tên tỉnh/thành phố
    document.getElementById('province_name').value = provinceName; // Cập nhật giá trị vào input ẩn
    loadDistricts(provinceID);
  });

  // Khi chọn quận/huyện
  document.getElementById('district').addEventListener('change', function () {
    const districtID = this.value;
    const districtName = this.options[this.selectedIndex].text; // Lấy tên quận/huyện
    document.getElementById('district_name').value = districtName; // Cập nhật giá trị vào input ẩn
    loadWards(districtID);
  });

  // Khi chọn phường/xã
  document.getElementById('ward').addEventListener('change', function () {
    const wardID = this.value;
    const wardName = this.options[this.selectedIndex].text; // Lấy tên phường/xã
    document.getElementById('ward_name').value = wardName; // Cập nhật giá trị vào input ẩn
  });

</script>


<script>


  function handlePaymentSelection(selectedRadio) {
    // Lấy tất cả các phần tử collapse
    const paymentCollapses = document.querySelectorAll('.accordion-collapse');

    // Ẩn tất cả các phần collapse
    paymentCollapses.forEach((collapse) => {
      collapse.classList.remove('show');
    });

    // Hiển thị phần tương ứng với radio được chọn
    const selectedId = selectedRadio.id;
    const collapseId = selectedId + 'Collapse'; // Tạo id của phần collapse tương ứng
    const targetCollapse = document.getElementById(collapseId);

    if (targetCollapse) {
      targetCollapse.classList.add('show');
    }

    selectedRadio.form.submit();


  }
  fetch('https://esgoo.net/api-tinhthanh/1/0.htm')
          .then(response => response.json())
          .then(data => console.log(data));



  let productTotal = parseFloat(<%= cart.getTotalPrice() %>); // Tổng tiền sản phẩm

  function updateShippingCost(radio) {
    // Lấy phí giao hàng từ giá trị radio button
    let shippingCost = parseFloat(radio.getAttribute("data-price")) || 0;

    // Cập nhật giá trị của trường ẩn
    document.getElementById("shippingPrice").value = shippingCost;

    // Tính tổng tiền
    let total = productTotal + shippingCost;

    // Hiển thị tổng tiền
    document.getElementById("totalAmount").textContent = new Intl.NumberFormat("vi-VN", {
      style: "currency",
      currency: "VND",
    }).format(total);
  }



</script>

</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/scripthanhtoan.js"></script>
</html>