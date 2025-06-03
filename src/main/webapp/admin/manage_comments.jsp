<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Bình Luận</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .comment-table th, .comment-table td {
            vertical-align: middle;
        }
        .rating-stars {
            color: #FFD43B;
        }
        .admin-header {
            background-color: #B0501D;
            color: white;
            padding: 15px;
            margin-bottom: 20px;
        }
        .table-responsive {
            overflow-x: auto;
        }
        .comment-content {
            max-width: 300px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .comment-content:hover {
            white-space: normal;
            overflow: visible;
        }
        .search-box {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="admin-header">
        <div class="container">
            <div class="d-flex justify-content-between align-items-center">
                <h2 class="mb-0"><i class="fa-solid fa-comments me-2"></i> Quản Lý Bình Luận</h2>
                <a href="${pageContext.request.contextPath}/admin/index.jsp" class="btn btn-light">
                    <i class="fa-solid fa-arrow-left me-1"></i> Quay Lại Dashboard
                </a>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header bg-light">
                        <div class="d-flex justify-content-between align-items-center">
                            <h5 class="mb-0">Danh Sách Bình Luận</h5>
                            <a href="${pageContext.request.contextPath}/all-comments" class="btn btn-primary">
                                <i class="fa-solid fa-sync me-1"></i> Làm Mới
                            </a>
                        </div>
                    </div>
                    
                    <div class="card-body">
                        <div class="search-box">
                            <input type="text" id="commentSearch" class="form-control" placeholder="Tìm kiếm bình luận...">
                        </div>
                        
                        <div class="table-responsive">
                            <table class="table table-striped table-hover comment-table">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Người Dùng</th>
                                        <th scope="col">Sản Phẩm</th>
                                        <th scope="col">Nội Dung</th>
                                        <th scope="col">Đánh Giá</th>
                                        <th scope="col">Ngày Tạo</th>
                                        <th scope="col">Thao Tác</th>
                                    </tr>
                                </thead>
                                <tbody id="commentTableBody">
                                    <c:if test="${empty sessionScope.allComments.items}">
                                        <tr>
                                            <td colspan="7" class="text-center py-4">
                                                <i class="fa-regular fa-comment-dots fa-3x mb-3 text-secondary"></i>
                                                <p class="mb-0">Chưa có bình luận nào</p>
                                            </td>
                                        </tr>
                                    </c:if>
                                    
                                    <c:forEach var="comment" items="${sessionScope.allComments.items}">
                                        <tr>
                                            <td>${comment.id}</td>
                                            <td>${comment.userName}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/product_detail?id=${comment.productId}" target="_blank">
                                                    Sản phẩm #${comment.productId}
                                                </a>
                                            </td>
                                            <td class="comment-content">${comment.content}</td>
                                            <td class="rating-stars">
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
                                            </td>
                                            <td><fmt:formatDate value="${comment.createdAt}" pattern="dd/MM/yyyy HH:mm" /></td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/delete-comment?commentId=${comment.id}&productId=${comment.productId}&redirect=all" 
                                                   class="btn btn-sm btn-danger" 
                                                   onclick="return confirm('Bạn có chắc chắn muốn xóa bình luận này?');">
                                                    <i class="fa-solid fa-trash"></i> Xóa
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script>
        // Simple search functionality
        document.getElementById('commentSearch').addEventListener('keyup', function() {
            const searchValue = this.value.toLowerCase();
            const tableRows = document.getElementById('commentTableBody').getElementsByTagName('tr');
            
            for (let i = 0; i < tableRows.length; i++) {
                const rowText = tableRows[i].textContent.toLowerCase();
                if (rowText.includes(searchValue)) {
                    tableRows[i].style.display = '';
                } else {
                    tableRows[i].style.display = 'none';
                }
            }
        });
    </script>
</body>
</html>
