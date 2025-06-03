<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page import="Models.Products.Products" %><%--
  Created by IntelliJ IDEA.
  User: airm2
  Date: 16/12/2024
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chi Tiết Sản Phẩm</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<style>
    .row-item:hover {
        background-color: #f5f5f5;
        transform: scale(1.02); /* Phóng to nhẹ */
        transition: all 0.3s ease-in-out; /* Hiệu ứng mượt */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Thêm bóng */
    }

    .row-item .badge {
        transition: background-color 0.3s ease-in-out;
    }

    /* Hiệu ứng badge khi di chuột */
    .row-item:hover .badge {
        background-color: #ff9800; /* Đổi màu */
    }
</style>
<body>
<%@include file="header.jsp"%>
<section class="content1">
    <div class="container">
        <div class="row">
            <div class="col-5 ps-5">
                <ul class="nav">
                    <li class="nav_item mt-4">
                        <div class="d-flex justify-content-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a class="text-secondary" href="#">Trang Chủ</a></li>
                                    <li class="breadcrumb-item"><a class="text-secondary" href="#">Sản Phẩm</a></li>
                                    <li class="breadcrumb-item" aria-current="page">Data</li>
                                </ol>
                            </nav>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <%
        Products pro = (Products) session.getAttribute("product_detail");
        if(pro == null){
            pro  = new Products();
            session.setAttribute("product_detail",pro);
        }
        System.out.println(pro.items.size());
    %>
        <c:forEach var="item" items="${sessionScope.product_detail.items}" >
        <div class="row">
            <div class="col-5 pe-0">
                <div class="d-flex justify-content-center">
                    <ul class="nav">
                        <li class="nav-item mt-3">
                            <div class="d-inline-block">
                                <div class="ms-4"><img src="img/${item.image1}" style="width: 450px" class="" alt="..."></div>
                                <div class="">
                                    <ul class="nav">
                                        <li class=""><a href=""><img src="img/${item.image1}" style="width: 120px" class="" alt="..."></a></li>

                                        <li class=""><a href=""><img src="img/${item.image2}" style="width: 120px" class="" alt="..."></a></li>
                                        <li class=""><a href=""><img src="img/${item.image3}" style="width: 120px" class="" alt="..."></a></li>
                                        <li class=""><a href=""><img src="img/${item.image4}" style="width: 120px" class="" alt="..."></a></li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="col-4 ps-0 chitiet">



                 <div class="text-danger fw-medium d-flex mt-1 fs-2" id="sp">
                         ${item.name}
                </div>
                <div class="mt-3">
                    <ul class="nav">
                        <li class="nav_item">5.0</li>
                        <li class=""><div class="ms-2"><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                        <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                        <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                        <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                        <li class=""><div class=""><i class="fa-solid fa-star" style="color: #FFD43B;"></i></div></li>
                        <li class="ms-2 text-secondary">(18 đánh giá)</li>
                        <li class="ms-2 text-secondary" style="--bs-text-opacity: .30;">|</li>
                        <li class="ms-2 text-secondary">Đã bán 375</li>
                    </ul>
                </div>
                <div class="mt-2">
                    <div class="d-flex fw-bold text-danger fs-4"><fmt:formatNumber value="${item.minPrice}" type="number" groupingUsed="true" /> đ - <fmt:formatNumber value="${item.maxPrice}" type="number" groupingUsed="true" /> đ</div>
                </div>
                <div class="border border-warning rounded-3 border-3 mt-3" style="border-color: rgb(193 101 44) !important">
                    <div class="border rounded" style="background-color: #B0501D; border-width: 6px">
                        <div class="text-light fw-bold ms-3 pt-2"  style="height:40px">THÔNG TIN SẢN PHẨM - <span id="masanpham">NX${item.id}</span> </div>
                    </div>
                    <div class="container">
                        <ul class="nav">
                            <li class="nav_item fw-bold mt-2" style="font-size: smaller">${item.name}: </li>
                            <li class="nav_item mt-2" style="font-size: smaller">${item.description}</li>
<%--                            <li class="nav_item" style="font-size: smaller">ngon ngọt với vị Umami tự nhiên như thịt, thanh đạm, tốt cho sức khỏe, chế biến được nhiều món ăn ngon. Là một trong các loại nấm có hoạt chất giúp cơ thể ngừa ung thư hiệu quả.</li>--%>
                        </ul>
                        <ul class="nav">
                            <li class="nav_item fw-bold mt-2" style="font-size: smaller">Giao hàng:</li>
                            <li class="nav_item mt-2" style="font-size: smaller">Nội thành của Thành phố HCM</li>
                        </ul>
                        <ul class="nav">
                            <li class="nav_item fw-bold mt-2" style="font-size: smaller">Xuất xứ: </li>
                            <li class="nav_item mt-2" style="font-size: smaller">Nông Trại Nấm Xanh Việt Nam</li>
                        </ul>
                        <ul class="nav">
                            <li class="nav_item fw-bold mt-2" style="font-size: smaller">NSX: </li>
                            <li class="nav_item mt-2" style="font-size: smaller">Từ farm về nấm mới mỗi ngày</li>
                        </ul>
                        <ul class="nav">
                            <li class="nav_item fw-bold mt-2" style="font-size: smaller">HSD: </li>
                            <li class="nav_item mt-2" style="font-size: smaller">5-7 ngày (từ ngày sản xuất)</li>
                        </ul>
                        <ul class="nav">
                            <li class="nav_item fw-bold mt-2" style="font-size: smaller">Khách lẻ đặt nhanh qua SĐT/Zalo:</li>
                            <li class="nav_item mt-2" style="font-size: smaller">027 637 5467</li>
                        </ul>
                        <ul class="nav">
                            <li class="nav_item fw-bold mt-2" style="font-size: smaller">Quý Doanh Nghiệp liên hệ SĐT/Zalo:</li>
                            <li class="nav_item mt-2" style="font-size: smaller">027 348 4589 (MR T)</li>
                        </ul>
                    </div>
                </div>
                <div class="border rounded mt-3 d-flex justify-content-center" style="background-color: #B0501D">
                    <div class="text-light fw-bold ms-3 pt-1"  style="height:30px">SẢN PHẨM GỢI Ý</div>
                </div>
                <form action="product_de?productID=&weight=&quantity=" method="get">
                    <!-- Thêm trường ẩn để lưu id sản phẩm -->
                    <input type="hidden" name="productID" value="${item.id}"> <!-- Thay 1 bằng id sản phẩm thực tế -->

                    <!-- Thêm trường ẩn để lưu quy cách (weight) -->
                    <input type="hidden" name="weight" id="selectedWeight" value="200"> <!-- Mặc định là 200gr -->
                    <input type="hidden" name="quantity" id="quantity" value="1">

                    <div class="container d-flex justify-content-center">
                        <div class="border rounded bg-secondary-subtle mt-2 border pt-1" style="width: 220px;height: 35px">
                            <a class="nav-link fw-bold d-flex justify-content-center" href="#">${item.name}</a>
                        </div>
                    </div>
                    <div class="border mt-3"></div>
                    <div class="border rounded mt-4 d-flex justify-content-start" style="background-color: #B0501D">
                        <div class="text-light fw-medium ms-3 pt-2" style="height:40px"><i class="fa-regular fa-gem"></i> Tích 39-165 NAXA khi mua sản phẩm này</div>
                    </div>
                    <div class="fw-medium mt-4">Quy Cách:
                        <input type="radio" class="btn-check" data-bs-toggle="collapse" data-bs-target="#collapseExample1" aria-controls="collapseExample1" name="btnradio" id="btnradio1" onclick="updateWeight('200')">
                        <label class="btn btn-outline-dark pt-0 ps-1 ms-3" style="height: 25px; width: 50px" for="btnradio1">200gr</label>
                        <input type="radio" class="btn-check" data-bs-toggle="collapse" data-bs-target="#collapseExample2" aria-controls="collapseExample2" name="btnradio" id="btnradio2" onclick="updateWeight('500')">
                        <label class="btn btn-outline-dark pt-0 ps-1 ms-2" style="height: 25px; width: 50px" for="btnradio2">500gr</label>
                        <input type="radio" class="btn-check" data-bs-toggle="collapse" data-bs-target="#collapseExample3" aria-controls="collapseExample3" name="btnradio" id="btnradio3" onclick="updateWeight('1000')">
                        <label class="btn btn-outline-dark pt-0 ps-1 ms-2" style="height: 25px; width: 50px" for="btnradio3">1kg</label>

                        <div class="collapse" id="collapseExample1">
                            <div class="mt-4 text-danger fw-bold"><fmt:formatNumber value="${item.minPrice}" type="number" groupingUsed="true" /> đ</div>
                        </div>
                        <div class="collapse" id="collapseExample2">
                            <div class="mt-4 text-danger fw-bold">65.000đ</div>
                        </div>
                        <div class="collapse" id="collapseExample3">
                            <div class="mt-4 text-danger fw-bold"><fmt:formatNumber value="${item.maxPrice}" type="number" groupingUsed="true" /> đ</div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-2 mt-4">
                            <div class="btn-group" role="group" aria-label="Basic outlined example">
                                <button type="button" class="btn btn-outline-secondary text-dark d-flex justify-content-center" style="width: 20px" onclick="updateQuantity(-1)">-</button>
                                <a class="btn btn-outline-secondary text-dark d-flex justify-content-center" id="displayQuantity" style="width: 20px">1</a>
                                <button type="button" class="btn btn-outline-secondary text-dark d-flex justify-content-center" style="width: 20px" onclick="updateQuantity(1)">+</button>
                            </div>
                        </div>
                        <div class="col-6 ms-3 border rounded mt-4" style="background-color: #B0501D; height: 40px;padding: 0;border-radius: 5px">
                            <button type="submit" class="text-light fw-medium d-flex pt-2 d-flex justify-content-center btn-add-to-cart" style="cursor: pointer;color: white;background-color: #B0501D; width: 100%;margin: 0 ;border: none">THÊM VÀO GIỎ HÀNG</button>
                        </div>
                        <div class="col-3 ms-1 border rounded mt-4" style="background-color: #B0501D; height: 40px">
                            <div class="text-light fw-medium pt-2 d-flex justify-content-center">
                                <a href="javascript:void(0);" onclick="submitFormNow()" style="text-decoration: none; color: white; cursor: pointer;">MUA NGAY</a>
                            </div>
                        </div>
                    </div>
                </form>
                <div>
                <ul class="nav mt-2">
                    <li class="nav-item mt-0">
                        <button type="button" class="btn"><i class="fa-regular fa-heart"></i></button>
                    </li>
                    <a href="addWishlist?productID=${item.id}" style="text-decoration: none;color: black"><li class="nav_item fw-bold mt-1">Thêm yêu thích</li></a>
                </ul>
            </div>
            </div>
                </c:forEach>


            <div class="col-3 pe-0">
                <div class="">
                    <ul class="nav">
                        <li class="nav_item mt-4 d-inline-block border border-danger-subtle border-3 rounded">
                            <div class="text-secondary fw-bold mt-1" style="font-size: smaller">NẤM đạt các kiểm nghiệm an toàn</div>
                            <div class="text-secondary fw-bold mt-1" style="font-size: smaller">Chứng nhận VSATTP Farm & CSSX</div>
                            <div class="text-secondary fw-bold mt-1" style="font-size: smaller">Hỗ trợ đổi/trả nhanh chóng 24h</div>
                            <div class="text-secondary fw-bold mt-1" style="font-size: smaller">Ưu tiên chất lượng NẤM Loại 1</div>
                            <div class="text-secondary fw-bold mt-1" style="font-size: smaller">Giao hàng TP.HCM & Toàn Quốc</div>
                            <div class="text-secondary fw-bold mt-1" style="font-size: smaller">Đa dạng hình thức thanh toán</div>
                        </li>
                    </ul>
                    <div class="d-flex mt-3 text-danger fw-bold fs-5">
                        MENU SẢN PHẨM
                    </div>
                    <div class="d-flex mt-2">
                        <div class="container">
                            <div class="vstack gap-3">
                                <div class="row" >
                                    <c:forEach var="item" items="${sessionScope.categories.items}">
                                        <!-- Tạo một hàng cho mỗi mục -->

                                        <ul class="row align-items-center row-item p-2 rounded" style="list-style: none;margin-bottom: 0">
                                            <!-- Cột bên trái hiển thị tên danh mục -->
                                            <div class="col text-secondary fw-medium">
                                                <a href="product_category?idCategory=${item.id}" style="text-decoration: none;color: black"><li style="cursor: pointer" >${item.name}</li></a>
                                            </div>
                                            <!-- Cột bên phải hiển thị giá trị tương ứng -->
                                            <div class="col-4 fs-6">
                                                <div class="badge border text-bg-light">${item.quantity}</div>
                                            </div>
                                        </ul>
                                    </c:forEach>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="border mt-4"></div>
                    <div class="fw-bold text-danger fs-5 mt-4">TÌM KIẾM SẢN PHẨM</div>
                    <div class="border border-1 rounded mt-3">
                        <ul class="nav">
                            <li class="w-75"><input type="text" class="form-control" placeholder="Tìm kiếm sản phẩm" aria-label="Tìm kiếm sản phẩm"></li>
                            <li class="w-25 d-flex justify-content-center"><button type="button" class="btn "><i class="fa-solid fa-magnifying-glass"></i></button></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section>
    <div class="row">
        <div class="border border-1 mt-3"></div>
    </div>
    <div class="container mt-4">
        <div class="row">
            <div class="col-md-12">
                <div class="border rounded mb-4" style="background-color: #B0501D">
                    <div class="text-light fw-bold ms-3 pt-2" style="height:40px">ĐÁNH GIÁ & BÌNH LUẬN</div>
                </div>
                
                <!-- Comment Form - Only show if user is logged in AND has purchased the product -->
                <c:if test="${sessionScope.user != null}">
                    <c:choose>
                        <c:when test="${sessionScope.hasPurchasedProduct == true}">
                            <div class="card mb-4">
                                <div class="card-header bg-light">
                                    <h5 class="mb-0">Viết đánh giá của bạn</h5>
                                </div>
                                <div class="card-body">
                                    <c:if test="${not empty sessionScope.commentError}">
                                        <div class="alert alert-warning alert-dismissible fade show" role="alert">
                                            ${sessionScope.commentError}
                                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                        </div>
                                        <% session.removeAttribute("commentError"); %>
                                    </c:if>
                                    <form action="add-comment" method="post">
                                        <input type="hidden" name="productId" value="${sessionScope.product_detail.items[0].id}">
                                        
                                        <div class="mb-3">
                                            <label for="rating" class="form-label">Đánh giá</label>
                                            <div class="rating-input">
                                                <div class="d-flex">
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="rating" id="rating1" value="1" required>
                                                        <label class="form-check-label" for="rating1">1 <i class="fa-solid fa-star text-warning"></i></label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="rating" id="rating2" value="2">
                                                        <label class="form-check-label" for="rating2">2 <i class="fa-solid fa-star text-warning"></i></label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="rating" id="rating3" value="3">
                                                        <label class="form-check-label" for="rating3">3 <i class="fa-solid fa-star text-warning"></i></label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="rating" id="rating4" value="4">
                                                        <label class="form-check-label" for="rating4">4 <i class="fa-solid fa-star text-warning"></i></label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="rating" id="rating5" value="5" checked>
                                                        <label class="form-check-label" for="rating5">5 <i class="fa-solid fa-star text-warning"></i></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <div class="mb-3">
                                            <label for="content" class="form-label">Nội dung bình luận</label>
                                            <textarea class="form-control" id="content" name="content" rows="3" required placeholder="Chia sẻ trải nghiệm của bạn về sản phẩm này..."></textarea>
                                        </div>
                                        
                                        <button type="submit" class="btn text-white" style="background-color: #B0501D;">Gửi bình luận</button>
                                    </form>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <!-- User is logged in but hasn't purchased the product -->
                            <div class="alert alert-warning mb-4">
                                <i class="fa-solid fa-exclamation-triangle me-2"></i>
                                <strong>Lưu ý:</strong> Bạn chỉ có thể bình luận sau khi mua sản phẩm này.
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                
                <c:if test="${sessionScope.user == null}">
                    <div class="alert alert-info mb-4">
                        <i class="fa-solid fa-circle-info me-2"></i>
                        Vui lòng <a href="Account/login.jsp" class="alert-link">đăng nhập</a> để viết bình luận.
                    </div>
                </c:if>
                
                <!-- Comments List -->
                <div class="comments-section">
                    <h5 class="mb-3">Tất cả bình luận (<span id="commentCount">${empty sessionScope.comments.items ? 0 : sessionScope.comments.items.size()}</span>)</h5>
                    
                    <c:if test="${empty sessionScope.comments.items}">
                        <div class="text-center py-5 border rounded bg-light">
                            <i class="fa-regular fa-comment-dots fa-3x mb-3 text-secondary"></i>
                            <h5 class="text-secondary">Chưa có bình luận nào</h5>
                            <p class="text-muted">Hãy là người đầu tiên đánh giá sản phẩm này!</p>
                        </div>
                    </c:if>
                    
                    <c:forEach var="comment" items="${sessionScope.comments.items}">
                        <div class="card mb-3 comment-card">
                            <div class="card-header d-flex justify-content-between align-items-center" style="background-color: #f8f9fa;">
                                <div>
                                    <i class="fa-solid fa-user-circle me-2"></i>
                                    <span class="fw-bold">${comment.userName}</span>
                                </div>
                                <div class="rating">
                                    <c:forEach begin="1" end="5" var="i">
                                        <c:choose>
                                            <c:when test="${i <= comment.rating}">
                                                <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="fa-regular fa-star" style="color: #FFD43B;"></i>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </div>
                            </div>
                            
                            <div class="card-body">
                                <p class="card-text">${comment.content}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <small class="text-muted">
                                        <i class="fa-regular fa-clock me-1"></i>
                                        <fmt:formatDate value="${comment.createdAt}" pattern="dd/MM/yyyy HH:mm" />
                                    </small>
                                    
                                    <c:if test="${sessionScope.user != null && (sessionScope.user.id == comment.userId || sessionScope.user.role == 'admin')}">
                                        <a href="delete-comment?commentId=${comment.id}&productId=${comment.productId}" 
                                           onclick="return confirm('Bạn có chắc chắn muốn xóa bình luận này?');" 
                                           class="btn btn-sm btn-outline-danger">
                                            <i class="fa-solid fa-trash me-1"></i>Xóa
                                        </a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    
                    <c:if test="${not empty sessionScope.comments.items && sessionScope.comments.items.size() > 5}">
                        <div class="text-center mt-3">
                            <a href="all-comments" class="btn btn-outline-secondary">
                                <i class="fa-solid fa-list me-1"></i>Xem tất cả bình luận
                            </a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>
<section>
    <div class="row">
        <div class="border border-1 mt-3"></div>
    </div>
    <div class="container">
        <div class="row ms-3">
            <div class="fw-bold text-danger fs-4 mt-4">Sản Phẩm Tương Tự</div>
        </div>
        <div class="row">
            <div class="col d-flex justify-content-center">
                <ul class="nav mt-4">
                    <c:forEach var="item" items="${sessionScope.productSimilar.items}">
                        <a href="product_detail?id=${item.id}">
                            <li class="nav-item ms-3" style="width: auto">
                                <div class="h-100 d-inline-block border border-2 rounded" style="height:100px">
                                    <div class="container">
                                        <div class="img_product">
                                            <img src="${pageContext.request.contextPath}/img/${item.fileName1}" style="width: 190px;" class="" alt="...">
                                        </div>
                                    </div>
                                    <div class="border rounded d-flex justify-content-start fs-6" style="background-color: #B0501D">
                                        <div class="text-light fw-light ms-3 pt-0"  style="width: 160px"><i class="fa-regular fa-gem"></i> Tích 39-165 NAXA khi mua sản phẩm này</div>
                                    </div>
                                    <div class="product_infor mt-3 d-flex justify-content-center">
                                        <button data-weight="200" class="btn">200g</button>
                                        <button data-weight="500" class="btn">500g</button>
                                        <button data-weight="1000" class="btn">1kg</button>
                                    </div>
                                    <div class="product_name mt-1 d-flex justify-content-center" style="color: black; ">${item.name}</div>
                                    <div class="mt-3 d-flex justify-content-center fw-bold text-danger" style="--bs-text-opacity: 0.8;"><fmt:formatNumber value="${item.priceMin}" type="number" groupingUsed="true" /> đ - <fmt:formatNumber value="${item.priceMax}" type="number" groupingUsed="true" /> đ</div>
                                </div>
                            </li>
                        </a>


                    </c:forEach>



                </ul>
            </div>
        </div>
    </div>
</section>
<section>
    <div class="row">
        <div class="border mt-3" style="background-color: #d9d6d6; height: 70px"></div>
    </div>
</section>
<%@include file="footer.jsp"%>
<script>
    // Cập nhật giá trị cho trường ẩn "weight" khi người dùng chọn quy cách
    function updateWeight(weight) {
        document.getElementById('selectedWeight').value = weight;
    }
    function updateQuantity(change) {
        const quantityField = document.getElementById('quantity');
        const displayQuantity = document.getElementById('displayQuantity');

        // Lấy giá trị hiện tại
        let currentQuantity = parseInt(quantityField.value);

        // Tính giá trị mới
        currentQuantity += change;

        // Đảm bảo số lượng không nhỏ hơn 1
        if (currentQuantity < 1) {
            currentQuantity = 1;
        }

        // Cập nhật giá trị mới
        quantityField.value = currentQuantity;
        displayQuantity.textContent = currentQuantity;
    }
    function submitFormNow() {
        // Lấy đối tượng form
        const form = document.querySelector('form');

        // Lấy giá trị của các tham số từ các yếu tố input (ví dụ từ form)
        const productID = document.querySelector('input[name="productID"]').value; // Lấy giá trị productID từ input
        const weight = document.querySelector('input[name="weight"]').value;       // Lấy giá trị weight từ input
        const quantity = document.querySelector('input[name="quantity"]').value;   // Lấy giá trị quantity từ input

        // Cập nhật action của form với các giá trị này
        form.action = `buyProduct?productID=${productID}&weight=${weight}&quantity=${quantity}`;

        // Gửi form
        form.submit();
    }
    function updateWeight(weight) {
        // Ẩn tất cả các phần tử collapse (bao gồm các giá cũ)
        document.querySelectorAll('.collapse').forEach(function(collapse) {
            collapse.classList.remove('show');
        });
updateWeight1(weight);
        // Kiểm tra quy cách và hiển thị giá tương ứng
        if (weight === '200') {
            document.getElementById('collapseExample1').classList.add('show');
        } else if (weight === '500') {
            document.getElementById('collapseExample2').classList.add('show');
        } else if (weight === '1000') {
            document.getElementById('collapseExample3').classList.add('show');
        }
    }
    function updateWeight1(weightValue) {
        // Cập nhật giá trị weight trong form
        document.getElementById('selectedWeight').value = weightValue;
    }
</script>
<script src="js/bootstrap.bundle.min.js"></script>

<script src="js/duavaogiohang.js "></script>
</body>
</html>