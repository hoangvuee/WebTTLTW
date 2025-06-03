<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tất Cả Bình Luận</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .comment-card {
            transition: all 0.3s ease;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        .comment-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
        }
        .rating {
            color: #FFD43B;
        }
        .comment-header {
            background-color: #B0501D;
            color: white;
            padding: 10px 20px;
            border-radius: 8px 8px 0 0;
        }
        .comment-body {
            padding: 20px;
        }
        .comment-footer {
            padding: 10px 20px;
            background-color: #f8f9fa;
            border-radius: 0 0 8px 8px;
        }
        .page-title {
            color: #B0501D;
            margin-bottom: 30px;
            font-weight: bold;
        }
        .comment-actions a {
            color: #B0501D;
            margin-right: 15px;
            text-decoration: none;
        }
        .comment-actions a:hover {
            text-decoration: underline;
        }
        .no-comments {
            text-align: center;
            padding: 50px;
            color: #6c757d;
        }
    </style>
</head>
<body>
    <%@include file="header.jsp"%>
    
    <div class="container mt-5 mb-5">
        <h1 class="page-title text-center">Tất Cả Bình Luận Của Người Dùng</h1>
        
        <div class="row">
            <div class="col-md-12">
                <c:if test="${empty sessionScope.allComments.items}">
                    <div class="no-comments">
                        <i class="fa-regular fa-comment-dots fa-4x mb-3"></i>
                        <h3>Chưa có bình luận nào</h3>
                        <p>Hãy là người đầu tiên đánh giá sản phẩm của chúng tôi!</p>
                    </div>
                </c:if>
                
                <c:forEach var="comment" items="${sessionScope.allComments.items}">
                    <div class="comment-card border">
                        <div class="comment-header d-flex justify-content-between align-items-center">
                            <div>
                                <i class="fa-solid fa-user-circle me-2"></i>
                                <span class="fw-bold">${comment.userName}</span>
                            </div>
                            <div class="rating">
                                <c:forEach begin="1" end="5" var="i">
                                    <c:choose>
                                        <c:when test="${i <= comment.rating}">
                                            <i class="fa-solid fa-star"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fa-regular fa-star"></i>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                        </div>
                        
                        <div class="comment-body">
                            <p class="mb-0">${comment.content}</p>
                        </div>
                        
                        <div class="comment-footer d-flex justify-content-between align-items-center">
                            <div>
                                <small class="text-muted">
                                    <i class="fa-regular fa-clock me-1"></i>
                                    <fmt:formatDate value="${comment.createdAt}" pattern="dd/MM/yyyy HH:mm" />
                                </small>
                            </div>
                            
                            <div class="comment-actions">
                                <a href="product_detail?id=${comment.productId}" class="text-primary">
                                    <i class="fa-solid fa-eye me-1"></i>Xem Sản Phẩm
                                </a>
                                
                                <c:if test="${sessionScope.user != null && (sessionScope.user.id == comment.userId || sessionScope.user.role == 'admin')}">
                                    <a href="delete-comment?commentId=${comment.id}&productId=${comment.productId}&redirect=all" 
                                       onclick="return confirm('Bạn có chắc chắn muốn xóa bình luận này?');" class="text-danger">
                                        <i class="fa-solid fa-trash me-1"></i>Xóa
                                    </a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    
    <%@include file="footer.jsp"%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
