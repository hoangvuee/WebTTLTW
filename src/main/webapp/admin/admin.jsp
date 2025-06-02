<%--
  Created by IntelliJ IDEA.
  User: airm2
  Date: 9/4/25
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
  <link rel="stylesheet" href="../css/admin.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>



</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-white px-3">
  <a class="navbar-brand d-flex align-items-center" href="#">
    <img src="../img/google-admin-logo-png_seeklogo-504247.png" alt="Logo" height="30">
    <span class="ms-2">Adminator</span>
  </a>

  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse justify-content-between" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link sidebar-toggle" href="#"><i class="fas fa-bars"></i></a>
      </li>
    </ul>

    <form class="d-flex">
      <input class="form-control me-2" type="search" placeholder="Tìm kiếm" aria-label="Search">
    </form>

    <ul class="navbar-nav align-items-center">
      <li class="nav-item position-relative">
        <a class="nav-link" href="#"><i class="fas fa-bell"></i> <span class="badge bg-danger rounded-pill">3</span></a>
      </li>
      <li class="nav-item position-relative mx-3">
        <a class="nav-link" href="#"><i class="fas fa-envelope"></i> <span class="badge bg-primary rounded-pill">5</span></a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" role="button" data-bs-toggle="dropdown">
          <img src="image/Logo-Zalo-Arc.png.webp" class="rounded-circle me-2" height="30" alt="User">
          <span>John Doe</span>
        </a>
        <ul class="dropdown-menu dropdown-menu-end">
          <li><a class="dropdown-item" href="#"><i class="fas fa-user me-2"></i> Profile</a></li>
          <li><a class="dropdown-item" href="#"><i class="fas fa-cog me-2"></i> Settings</a></li>
          <li><hr class="dropdown-divider"></li>
          <li><a class="dropdown-item" href="#"><i class="fas fa-sign-out-alt me-2"></i> Logout</a></li>
        </ul>
      </li>
    </ul>
  </div>
</nav>

<!-- Sidebar -->
<div class="sidebar">
  <ul class="sidebar-menu">
    <li class="menu-title">MAIN MENU</li>
    <li><a href="#" class="menu-item active" data-section="dashboard"><i class="fas fa-home"></i> Dashboard</a></li>

    <li class="menu-divider"></li>
    <li class="menu-title">COMMUNICATION</li>
    <li><a href="#" class="menu-item" data-section="email"><i class="fas fa-envelope"></i> Email</a></li>
    <li><a href="#" class="menu-item" data-section="chat"><i class="fas fa-comments"></i> Chat</a></li>

    <li class="menu-divider"></li>
    <li class="menu-title">ANALYTICS</li>
    <li><a href="#" class="menu-item" data-section="charts"><i class="fas fa-chart-bar"></i> Charts</a></li>
    <li><a href="#" class="menu-item" data-section="maps"><i class="fas fa-map"></i> Maps</a></li>

    <li class="menu-divider"></li>
    <li class="menu-title">PRODUCTS</li>
    <li><a href="#" class="menu-item" data-section="manage-products"><i class="fas fa-boxes"></i> Manage Products</a></li>
    <li><a href="#" class="menu-item" data-section="add-product"><i class="fas fa-plus-circle"></i> Add Product</a></li>

    <li class="menu-divider"></li>
    <li class="menu-title">SYSTEM</li>
    <li><a href="#" class="menu-item" data-section="settings"><i class="fas fa-cog"></i> Settings</a></li>
    <li><a href="#" class="menu-item" data-section="users"><i class="fas fa-users"></i> Users</a></li>
  </ul>
</div>

<!-- Main Content -->
<div class="main-content">
  <!-- Dashboard Section -->
  <div id="dashboard" class="content-section active">
    <h4 class="mb-4">Dashboard Overview</h4>
    <div class="row">
      <div class="col-md-3">
        <div class="dashboard-card">
          <div class="card-icon text-success"><i class="fas fa-chart-line"></i></div>
          <h5>Total Visits</h5>
          <p class="text-success">+10% <small>from last week</small></p>
        </div>
      </div>
      <div class="col-md-3">
        <div class="dashboard-card">
          <div class="card-icon text-danger"><i class="fas fa-eye"></i></div>
          <h5>Page Views</h5>
          <p class="text-danger">-7% <small>from last week</small></p>
        </div>
      </div>
      <div class="col-md-3">
        <div class="dashboard-card">
          <div class="card-icon text-primary"><i class="fas fa-users"></i></div>
          <h5>Unique Users</h5>
          <p class="text-primary">12,345 <small>this month</small></p>
        </div>
      </div>
      <div class="col-md-3">
        <div class="dashboard-card">
          <div class="card-icon text-warning"><i class="fas fa-dollar-sign"></i></div>
          <h5>Revenue</h5>
          <p class="text-warning">$34,567 <small>this month</small></p>
        </div>
      </div>
    </div>

    <div class="dashboard-card mt-4">
      <h5 class="mb-3">Recent Activity</h5>
      <div class="table-responsive">
        <table class="table table-hover">
          <thead>
          <tr>
            <th>User</th>
            <th>Action detail</th>
            <th>Time</th>
            <th>role</th>
          </tr>
          </thead>
          <tbody id="LogActivity">
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <!-- Email Section -->
  <div id="email" class="content-section">
    <h4 class="mb-4">Email Management</h4>
    <div class="dashboard-card">
      <div class="email-container">
        <div class="row">
          <!-- Sidebar danh sách email -->
          <div class="col-md-4">
            <div class="email-sidebar">
              <div class="d-flex justify-content-between align-items-center mb-3">
                <h5>Inbox</h5>
                <button class="btn btn-sm btn-primary"><i class="fas fa-plus"></i> Compose</button>
              </div>

              <div class="input-group mb-3">
                <span class="input-group-text"><i class="fas fa-search"></i></span>
                <input type="text" class="form-control" placeholder="Search emails...">
              </div>

              <div class="email-folders">
                <ul class="nav flex-column">
                  <li class="nav-item">
                    <a class="nav-link active" href="#"><i class="fas fa-inbox me-2"></i> Inbox <span class="badge bg-primary float-end">24</span></a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#"><i class="fas fa-star me-2"></i> Starred</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#"><i class="fas fa-paper-plane me-2"></i> Sent</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#"><i class="fas fa-trash me-2"></i> Trash</a>
                  </li>
                </ul>
              </div>

              <div class="email-list mt-3">
                <div class="email-item unread">
                  <div class="d-flex justify-content-between">
                    <h6>John Doe</h6>
                    <small class="text-muted">10:30 AM</small>
                  </div>
                  <p class="mb-1">Meeting tomorrow at 9AM</p>
                  <p class="text-muted small">Hi there, just a reminder about our meeting tomorrow...</p>
                </div>

                <div class="email-item">
                  <div class="d-flex justify-content-between">
                    <h6>Amazon</h6>
                    <small class="text-muted">Yesterday</small>
                  </div>
                  <p class="mb-1">Your order has been shipped</p>
                  <p class="text-muted small">Your recent order #12345 is on its way...</p>
                </div>

                <div class="email-item">
                  <div class="d-flex justify-content-between">
                    <h6>LinkedIn</h6>
                    <small class="text-muted">Mar 28</small>
                  </div>
                  <p class="mb-1">You have 5 new connections</p>
                  <p class="text-muted small">Your network is growing with 5 new connections...</p>
                </div>
              </div>
            </div>
          </div>

          <!-- Nội dung email -->
          <div class="col-md-8">
            <div class="email-content">
              <div class="email-header">
                <div class="d-flex justify-content-between align-items-center mb-3">
                  <h4>Meeting tomorrow at 9AM</h4>
                  <div>
                    <button class="btn btn-sm btn-outline-secondary"><i class="fas fa-reply"></i></button>
                    <button class="btn btn-sm btn-outline-secondary"><i class="fas fa-trash"></i></button>
                  </div>
                </div>

                <div class="d-flex align-items-center mb-4">
                  <img src="https://via.placeholder.com/40" class="rounded-circle me-3" alt="Avatar">
                  <div>
                    <h6 class="mb-0">John Doe</h6>
                    <small class="text-muted">to me, 10:30 AM</small>
                  </div>
                </div>

                <div class="email-body">
                  <p>Hi there,</p>
                  <p>Just a reminder about our meeting tomorrow at 9AM in the conference room. Please bring your project updates and any questions you might have.</p>
                  <p>Best regards,<br>John</p>
                </div>

                <div class="email-attachments mt-4">
                  <h6><i class="fas fa-paperclip me-2"></i> Attachments (2)</h6>
                  <div class="d-flex mt-2">
                    <div class="attachment me-2">
                      <i class="fas fa-file-pdf fa-2x text-danger"></i>
                      <small>Project_Update.pdf</small>
                    </div>
                    <div class="attachment">
                      <i class="fas fa-file-excel fa-2x text-success"></i>
                      <small>Budget.xlsx</small>
                    </div>
                  </div>
                </div>

                <div class="email-reply mt-4">
                  <textarea class="form-control mb-2" rows="4" placeholder="Write your reply here..."></textarea>
                  <div class="d-flex justify-content-between">
                    <div>
                      <button class="btn btn-sm btn-outline-secondary me-2"><i class="fas fa-paperclip"></i></button>
                      <button class="btn btn-sm btn-outline-secondary"><i class="fas fa-smile"></i></button>
                    </div>
                    <button class="btn btn-primary">Send</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Chat Section -->
  <div id="chat" class="content-section">
    <h4 class="mb-4">Chat Application</h4>
    <div class="dashboard-card p-0">
      <div class="chat-app-container">
        <!-- Sidebar danh sách cuộc trò chuyện -->
        <div class="chat-sidebar">
          <div class="chat-sidebar-header">
            <h5>Messages</h5>
            <button class="btn btn-sm btn-primary rounded-pill">
              <i class="fas fa-plus"></i> New Chat
            </button>
          </div>

          <div class="chat-search">
            <div class="input-group">
                            <span class="input-group-text bg-transparent border-end-0">
                                <i class="fas fa-search"></i>
                            </span>
              <input type="text" class="form-control border-start-0" placeholder="Search conversations...">
            </div>
          </div>

          <div class="chat-conversations">
            <div class="conversation active">
              <div class="conversation-avatar">
                <img src="https://randomuser.me/api/portraits/women/44.jpg" alt="User">
                <span class="online-status online"></span>
              </div>
              <div class="conversation-details">
                <div class="conversation-header">
                  <h6>Sarah Johnson</h6>
                  <span class="time">10:30 AM</span>
                </div>
                <p class="last-message">Hey, are we still meeting tomorrow?</p>
                <span class="unread-count">3</span>
              </div>
            </div>

            <div class="conversation">
              <div class="conversation-avatar">
                <img src="https://randomuser.me/api/portraits/men/32.jpg" alt="User">
                <span class="online-status away"></span>
              </div>
              <div class="conversation-details">
                <div class="conversation-header">
                  <h6>Michael Chen</h6>
                  <span class="time">Yesterday</span>
                </div>
                <p class="last-message">I've sent you the project files</p>
              </div>
            </div>

            <div class="conversation">
              <div class="conversation-avatar">
                <img src="https://randomuser.me/api/portraits/women/68.jpg" alt="User">
                <span class="online-status offline"></span>
              </div>
              <div class="conversation-details">
                <div class="conversation-header">
                  <h6>Design Team</h6>
                  <span class="time">Mar 28</span>
                </div>
                <p class="last-message">Emma: The new mockups are ready</p>
                <span class="unread-count">12</span>
              </div>
            </div>
          </div>
        </div>

        <!-- Phần chat chính -->
        <div class="chat-main">
          <div class="chat-header">
            <div class="chat-partner-info">
              <div class="avatar">
                <img src="https://randomuser.me/api/portraits/women/44.jpg" alt="User">
                <span class="online-status online"></span>
              </div>
              <div class="partner-details">
                <h5>Sarah Johnson</h5>
                <p class="status">Online</p>
              </div>
            </div>
            <div class="chat-actions">
              <button class="btn btn-icon">
                <i class="fas fa-phone"></i>
              </button>
              <button class="btn btn-icon">
                <i class="fas fa-video"></i>
              </button>
              <button class="btn btn-icon">
                <i class="fas fa-ellipsis-v"></i>
              </button>
            </div>
          </div>

          <div class="chat-messages">
            <div class="message-date">Today</div>

            <div class="message received">
              <div class="message-avatar">
                <img src="https://randomuser.me/api/portraits/women/44.jpg" alt="User">
              </div>
              <div class="message-content">
                <div class="message-text">Hey there! How are you doing?</div>
                <div class="message-time">10:30 AM</div>
              </div>
            </div>

            <div class="message sent">
              <div class="message-content">
                <div class="message-text">I'm good, thanks! Just preparing for our meeting tomorrow.</div>
                <div class="message-time">10:32 AM</div>
              </div>
            </div>

            <div class="message received">
              <div class="message-avatar">
                <img src="https://randomuser.me/api/portraits/women/44.jpg" alt="User">
              </div>
              <div class="message-content">
                <div class="message-text">Great! Are we still meeting at 9AM?</div>
                <div class="message-time">10:33 AM</div>
              </div>
            </div>

            <div class="message sent">
              <div class="message-content">
                <div class="message-text">Yes, that works for me. I'll bring the project updates.</div>
                <div class="message-time">10:35 AM</div>
              </div>
            </div>

            <div class="message received">
              <div class="message-avatar">
                <img src="https://randomuser.me/api/portraits/women/44.jpg" alt="User">
              </div>
              <div class="message-content">
                <div class="message-text">Perfect! I've attached the agenda for tomorrow.</div>
                <div class="message-time">10:36 AM</div>
                <div class="message-attachment">
                  <div class="attachment">
                    <i class="fas fa-file-pdf"></i>
                    <div class="attachment-info">
                      <div class="attachment-name">Meeting_Agenda.pdf</div>
                      <div class="attachment-size">2.4 MB</div>
                    </div>
                    <button class="btn-download">
                      <i class="fas fa-download"></i>
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="chat-input">
            <div class="input-tools">
              <button class="btn btn-icon">
                <i class="fas fa-paperclip"></i>
              </button>
              <button class="btn btn-icon">
                <i class="fas fa-smile"></i>
              </button>
            </div>
            <input type="text" placeholder="Type a message...">
            <button class="btn btn-send">
              <i class="fas fa-paper-plane"></i>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>


  <!-- Manage Products Section -->
  <div id="manage-products" class="content-section">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <div>
        <h4 class="mb-0">Product Management</h4>
        <p class="text-muted mb-0">Manage your product inventory efficiently</p>
      </div>
      <div>
        <button class="btn btn-outline-secondary me-2">
          <i class="fas fa-file-export me-2"></i>Export
        </button>
        <button class="btn btn-primary" onclick="showSection('add-product')">
          <i class="fas fa-plus-circle me-2"></i>Add Product
        </button>
      </div>
    </div>

    <div class="dashboard-card">
      <!-- Filter và Search Bar -->
      <div class="product-filter-bar">
        <div class="row g-3 align-items-center">
          <div class="col-md-3">
            <select class="form-select">
              <option selected>All Categories</option>
              <option>Electronics</option>
              <option>Fashion</option>
              <option>Home & Garden</option>
            </select>
          </div>
          <div class="col-md-3">
            <select class="form-select">
              <option selected>All Status</option>
              <option>Active</option>
              <option>Inactive</option>
              <option>Out of Stock</option>
            </select>
          </div>
          <div class="col-md-3">
            <div class="input-group">
              <input type="date" class="form-control">
              <span class="input-group-text">to</span>
              <input type="date" class="form-control">
            </div>
          </div>
          <div class="col-md-3">
            <div class="input-group">
              <input type="text" class="form-control" placeholder="Search products...">
              <button class="btn btn-outline-secondary" type="button">
                <i class="fas fa-search"></i>
              </button>
              <button class="btn btn-outline-secondary" type="button" data-bs-toggle="dropdown">
                <i class="fas fa-filter"></i>
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- Bảng sản phẩm -->
      <div class="table-responsive">
        <table class="table product-table">
          <thead>
          <tr>
            <th width="50">
              <input type="checkbox" class="form-check-input">
            </th>
            <th>Product</th>
            <th>Category</th>
            <th>Price</th>

            <th>Inventory</th>
            <th>Sales</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody id="product-table-body">

          </tbody>
        </table>
      </div>

      <!-- Pagination và Bulk Actions -->
      <div class="d-flex justify-content-between align-items-center mt-4">
        <div class="d-flex align-items-center">
          <select class="form-select form-select-sm me-2" style="width: 120px;">
            <option>Bulk Actions</option>
            <option>Edit</option>
            <option>Export</option>
            <option>Move to Trash</option>
          </select>
          <button class="btn btn-sm btn-outline-secondary">Apply</button>
        </div>

        <div class="d-flex align-items-center">
          <span class="me-3 text-muted small">1-10 of 128 products</span>
          <nav aria-label="Page navigation">
            <ul class="pagination pagination-sm mb-0">
              <li class="page-item disabled">
                <a class="page-link" href="#" aria-label="Previous">
                  <span aria-hidden="true">&laquo;</span>
                </a>
              </li>
              <li class="page-item active"><a class="page-link" href="#">1</a></li>
              <li class="page-item"><a class="page-link" href="#">2</a></li>
              <li class="page-item"><a class="page-link" href="#">3</a></li>
              <li class="page-item">
                <a class="page-link" href="#" aria-label="Next">
                  <span aria-hidden="true">&raquo;</span>
                </a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </div>
  </div>
  <!-- Add Product Section -->
  <div id="add-product" class="content-section">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h4>Add New Product</h4>
      <div>
        <button class="btn btn-outline-secondary me-2" id="backButton">
          <i class="fas fa-arrow-left me-2"></i>Back
        </button>
        <button class="btn btn-primary" form="productForm" type="submit">
          <i class="fas fa-save me-2"></i>Save Product
        </button>
      </div>
    </div>

    <div class="dashboard-card">
      <form id="productForm" class="product-form" enctype="multipart/form-data">
        <div class="row">
          <!-- Cột trái - Thông tin cơ bản -->
          <div class="col-lg-8">
            <div class="form-section">
              <h5 class="section-title">
                <i class="fas fa-info-circle me-2 text-primary"></i>Basic Information
              </h5>

              <div class="row">
                <div class="col-md-6">
                  <div class="form-group floating-label">
                    <input type="text" id="productID" name="id" class="form-control" placeholder="Auto-generated if empty">
                    <label for="productID">Product ID</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group floating-label">
                    <input type="text" id="productName" name="name" class="form-control" required>
                    <label for="productName">Product Name*</label>
                  </div>
                </div>
              </div>

              <div class="form-group floating-label">
                <textarea id="productDesc" name="description" class="form-control" rows="3" required></textarea>
                <label for="productDesc">Description*</label>
              </div>

              <div class="row">
                <div class="col-md-4">
                  <div class="form-group floating-label">
                    <input type="number" id="productPrice" name="price" class="form-control" required>
                    <label for="productPrice">Price*</label>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group floating-label">
                    <input type="number" id="productWeight" name="weight" class="form-control" required>
                    <label for="productWeight">Weight (g)*</label>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group floating-label">
                    <input type="number" id="productQuantity" name="quantity" class="form-control" required>
                    <label for="productQuantity">Quantity*</label>
                  </div>
                </div>
              </div>
            </div>

            <!-- Phần hình ảnh sản phẩm -->
            <div class="form-section mt-4">
              <h5 class="section-title">
                <i class="fas fa-images me-2 text-primary"></i>Product Images
              </h5>

              <div class="image-upload-container">
                <div class="dropzone" id="productImageDropzone">
                  <i class="fas fa-cloud-upload-alt fa-3x text-muted"></i>
                  <p>Drag & drop product images here</p>
                  <small class="text-muted">or click to browse (max 10 images)</small>
                  <input type="file" id="productImages" name="images" multiple accept="image/*" style="display: none;">
                </div>

                <div class="image-preview-container d-flex flex-wrap mt-3" id="imageUpdate">
                  <!-- Preview images will appear here -->
                </div>
              </div>
            </div>

            <!-- Phần khuyến mãi -->
            <div class="form-section mt-4">
              <h5 class="section-title">
                <i class="fas fa-tag me-2 text-primary"></i>Promotions
              </h5>

              <div id="saleContainer">
                <div class="sale-item row mb-3">
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="form-label">Discount %</label>
                      <input type="number" class="form-control" name="sale[0].salePersent" min="1" max="100">
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="form-label">Start Date</label>
                      <input type="datetime-local" class="form-control" name="sale[0].saleStartDate">
                    </div>
                  </div>
                  <div class="col-md-3">
                    <div class="form-group">
                      <label class="form-label">End Date</label>
                      <input type="datetime-local" class="form-control" name="sale[0].saleEndDate">
                    </div>
                  </div>
                  <div class="col-md-1 d-flex align-items-end">
                    <button type="button" class="btn btn-outline-danger remove-sale-btn">
                      <i class="fas fa-trash"></i>
                    </button>
                  </div>
                </div>
              </div>

              <button type="button" id="addSaleBtn" class="btn btn-outline-primary">
                <i class="fas fa-plus me-2"></i>Add Promotion
              </button>
            </div>
            <div class="form-section mt-4">
              <h5 class="section-title">
                <i class="fas fa-align-left me-2 text-primary"></i>Additional Descriptions
              </h5>

              <div id="descriptionContainer">
                <div class="description-item row mb-3">
                  <div class="col-md-5">
                    <div class="form-group">
                      <label class="form-label">Title</label>
                      <input type="text" class="form-control" name="descriptions[0].title" placeholder="Feature title">
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label class="form-label">Description</label>
                      <textarea class="form-control" name="descriptions[0].content" rows="2" placeholder="Detailed description"></textarea>
                    </div>
                  </div>
                  <div class="col-md-1 d-flex align-items-end">
                    <button type="button" class="btn btn-outline-danger remove-description-btn">
                      <i class="fas fa-trash"></i>
                    </button>
                  </div>
                </div>
              </div>

              <button type="button" id="addDescriptionBtn" class="btn btn-outline-primary">
                <i class="fas fa-plus me-2"></i>Add Description
              </button>
            </div>

          </div>


          <!-- Cột phải - Cài đặt bổ sung -->
          <div class="col-lg-4">
            <div class="form-section">
              <h5 class="section-title">
                <i class="fas fa-cog me-2 text-primary"></i>Settings
              </h5>

              <div class="form-group">
                <label class="form-label">Product Status</label>
                <div class="form-check form-switch">
                  <input class="form-check-input" type="checkbox" id="productStatus" name="isActive" checked>
                  <label class="form-check-label" for="productStatus">Active</label>
                </div>
              </div>

              <div class="form-group">
                <label class="form-label">Category*</label>
                <select class="form-select" id="productCategory" name="idCategory" required>
                  <!-- Option sẽ được load bằng Ajax -->
                </select>
              </div>


              <div id="categoryResult"></div>

              <div class="form-group">
                <label class="form-label">Supplier*</label>
                <select class="form-select" id="productSupplier" name="idSupplier" required>
                  <!-- Option sẽ được load bằng Ajax -->
                </select>
              </div>

              <div class="form-group floating-label">
                <input type="text" id="productImageURL" name="imageURL" class="form-control">
                <label for="productImageURL">Main Image URL</label>
              </div>
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>


  <!-- Other sections would follow the same pattern -->
</div>
<!-- Modal Edit Product -->
<div class="modal fade" id="editProductModal" tabindex="-1">
  <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content border-0 shadow-lg">
      <!-- Modal Header -->
      <div class="modal-header bg-gradient-primary p-4">
        <div>
          <h5 class="modal-title text-white">
            <i class="fas fa-edit me-2"></i>Edit Product
          </h5>
          <p class="text-white-50 mb-0 small">Update product details and inventory</p>
        </div>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>

      <!-- Modal Body -->
      <div class="modal-body p-0">
        <form id="editProductForm" class="needs-validation" novalidate>
          <div class="row g-0">
            <!-- Left Column - Main Content -->
            <div class="col-lg-8 p-4 border-end">
              <!-- Current Product Preview -->
              <div class="card mb-4 border-0 shadow-sm">
                <div class="card-body">
                  <div class="d-flex align-items-center mb-3">
                    <div class="bg-primary bg-opacity-10 p-2 rounded me-3">
                      <i class="fas fa-eye text-primary fs-5"></i>
                    </div>
                    <h6 class="mb-0">Current Product Preview</h6>
                  </div>

                  <div class="row">
                    <div class="col-md-4 mb-3">
                      <div class="current-image-container border rounded p-2 text-center">
                        <img src="../img/1735387762942_nam-bao-ngu-xam-tuoi-ngon-01.jpg" id="currentProductImage" class="img-fluid rounded" alt="Current Product Image">

                        <div class="mt-2 small text-muted">Main Image</div>
                      </div>
                    </div>
                    <div class="col-md-8">
                      <div class="product-preview-info">
                        <h5 id="currentProductName">Product Name</h5>
                        <div class="d-flex align-items-center mb-2">
                          <span class="text-primary fw-bold fs-4 me-2" id="currentProductPrice">$0.00</span>
                          <span class="text-decoration-line-through text-muted" id="currentProductComparePrice"></span>
                        </div>
                        <div class="mb-2">
                          <span class="badge bg-success" id="currentProductStatus">Active</span>
                          <span class="badge bg-secondary ms-2" id="currentProductSKU">SKU: N/A</span>
                        </div>
                        <div class="text-muted small mb-2" id="currentProductStock">In stock: 0</div>

                        <!-- New: Specification Preview -->
                        <div class="bg-light border rounded p-3 mt-3">
                          <h6 class="text-muted mb-2"><i class="fas fa-clipboard-list me-2 text-primary"></i>Specifications</h6>
                          <ul class="list-unstyled mb-0 small text-dark" id="currentProductSpecs">
                            <li>Category: Nấm Tươi</li>
                            <li>Weight: 500g</li>
                            <!-- Add dynamic lines here -->
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>


              <!-- Basic Information Section -->
              <section class="mb-5">
                <div class="d-flex align-items-center mb-4">
                  <div class="bg-primary bg-opacity-10 p-2 rounded me-3">
                    <i class="fas fa-info-circle text-primary fs-5"></i>
                  </div>
                  <h6 class="mb-0">Basic Information</h6>
                </div>

                <div class="row g-3">
                  <div class="col-md-6">
                    <label for="editProductName" class="form-label fw-bold">Product Name <span class="text-danger">*</span></label>
                    <div class="input-group">
                      <span class="input-group-text bg-light"><i class="fas fa-cube text-muted"></i></span>
                      <input type="text" class="form-control" id="editProductName" placeholder="e.g. Wireless Headphones" required>
                      <div class="invalid-feedback">Please provide a product name.</div>
                    </div>
                  </div>

                  <div class="col-md-6">
                    <label for="editProductSKU" class="form-label fw-bold">SKU Code</label>
                    <div class="input-group">
                      <span class="input-group-text bg-light"><i class="fas fa-barcode text-muted"></i></span>
                      <input type="text" class="form-control" id="editProductSKU1" placeholder="e.g. WH-2023">
                    </div>
                  </div>
                  <div class="col-md-6">
                    <label for="editProductSpec" class="form-label fw-bold">Specification <span class="text-danger">*</span></label>
                    <div class="input-group">
                      <span class="input-group-text bg-light"><i class="fas fa-ruler-combined text-muted"></i></span>
                      <input type="text" class="form-control" id="editProductSpec" placeholder="e.g. 200g bag" required>
                      <div class="invalid-feedback">Please enter product specification.</div>
                    </div>
                  </div>

                  <div class="col-md-6">
                    <label for="editProductStock" class="form-label fw-bold">Quantity in Stock <span class="text-danger">*</span></label>
                    <div class="input-group">
                      <span class="input-group-text bg-light"><i class="fas fa-boxes text-muted"></i></span>
                      <input type="number" class="form-control" id="editProductStock" placeholder="e.g. 50" min="0" required>
                      <div class="invalid-feedback">Please enter available quantity.</div>
                    </div>
                  </div>


                  <div class="col-12 mb-4">
                    <label class="form-label fw-bold">Descriptions</label>
                    <div id="descriptionList">
                      <!-- Các dòng mô tả sẽ nằm ở đây -->
                      <div class="row g-2 align-items-start mb-2 description-item">
                        <div class="col-md-4">
                          <input type="text" class="form-control" placeholder="Title (e.g. Usage)" value="Cách dùng">
                        </div>
                        <div class="col-md-7">
                          <textarea class="form-control" rows="2" placeholder="Content">Dùng pha với nước ấm, không quá 80°C.</textarea>
                        </div>
                        <div class="col-md-1 text-end">
                          <button type="button" class="btn btn-outline-danger btn-sm" onclick="this.closest('.description-item').remove()">
                            <i class="fas fa-trash"></i>
                          </button>
                        </div>
                      </div>
                      <div class="row g-2 align-items-start mb-2 description-item">
                        <div class="col-md-4">
                          <input type="text" class="form-control" placeholder="Title (e.g. Usage)" value="Cách dùng">
                        </div>
                        <div class="col-md-7">
                          <textarea class="form-control" rows="2" placeholder="Content">Dùng pha với nước ấm, không quá 80°C.</textarea>
                        </div>
                        <div class="col-md-1 text-end">
                          <button type="button" class="btn btn-outline-danger btn-sm" onclick="this.closest('.description-item').remove()">
                            <i class="fas fa-trash"></i>
                          </button>
                        </div>
                      </div>
                      <!-- Có thể lặp nhiều dòng như vậy -->
                    </div>
                    <div class="col-12 mb-2">
                      <button type="button" class="btn btn-primary btn-sm" onclick="addDescriptionItem()">
                        <i class="fas fa-plus"></i> AddDescription
                      </button>
                    </div>
                  </div>
                  <section class="mb-5">
                    <div class="d-flex align-items-center mb-4">
                      <div class="bg-danger bg-opacity-10 p-2 rounded me-3">
                        <i class="fas fa-percent text-danger fs-5"></i>
                      </div>
                      <h6 class="mb-0">Current Discount</h6>
                    </div>

                    <div class="row g-3">
                      <div class="col-md-6">
                        <label for="editDiscountPrice" class="form-label fw-bold">Sale Price</label>
                        <div class="input-group">
                          <span class="input-group-text bg-light">$</span>
                          <input type="number" class="form-control" id="editDiscountPrice" placeholder="Enter sale price..." min="0" step="0.01">
                        </div>
                        <small class="text-muted">If set, this price overrides the main price during the discount period.</small>
                      </div>

                      <div class="col-md-6">
                        <label for="editDiscountEnd" class="form-label fw-bold">Discount End Date</label>
                        <input type="date" class="form-control" id="editDiscountEnd">
                        <small class="text-muted">Discount ends at 11:59 PM of the selected date.</small>
                      </div>
                    </div>
                  </section>

                  <!-- Thêm trường Quy cách -->
                  <div class="col-12 mb-4">
                    <label class="form-label fw-bold">Sales Applied</label>
                    <div id="saleList">
                      <!-- Mỗi dòng sale sẽ nằm ở đây -->
                      <div class="row g-2 align-items-center mb-2 sale-item">
                        <div class="col-md-3">
                          <input type="number" class="form-control" placeholder="Sale Price" value="199.99" min="0" step="0.01">
                        </div>
                        <div class="col-md-3">
                          <input type="date" class="form-control" value="2025-04-01">
                        </div>
                        <div class="col-md-3">
                          <input type="date" class="form-control" value="2025-04-30">
                        </div>
                        <div class="col-md-2">
                          <button type="button" class="btn btn-outline-danger btn-sm" onclick="this.closest('.sale-item').remove()">
                            <i class="fas fa-trash"></i>
                          </button>
                        </div>
                      </div>
                      <!-- Có thể lặp nhiều dòng như vậy -->
                    </div>
                  </div>
                  <div class="col-12 mb-2">
                    <button type="button" class="btn btn-primary btn-sm" onclick="addSaleItem()">
                      <i class="fas fa-plus"></i> Add Sale
                    </button>
                  </div>
                </div>

              </section>

              <!-- Pricing Section -->
              <section class="mb-5">
                <div class="d-flex align-items-center mb-4">
                  <div class="bg-primary bg-opacity-10 p-2 rounded me-3">
                    <i class="fas fa-tag text-primary fs-5"></i>
                  </div>
                  <h6 class="mb-0">Pricing</h6>
                </div>

                <div class="row g-3">
                  <div class="col-md-4">
                    <label for="editProductPrice" class="form-label fw-bold">Price <span class="text-danger">*</span></label>
                    <div class="input-group">
                      <span class="input-group-text bg-light">$</span>
                      <input type="number" class="form-control" id="editProductPrice" placeholder="0.00" min="0" step="0.01" required>
                      <div class="invalid-feedback">Please set a valid price.</div>
                    </div>
                  </div>

                  <div class="col-md-4">
                    <label for="editProductComparePrice" class="form-label fw-bold">Compare Price</label>
                    <div class="input-group">
                      <span class="input-group-text bg-light">$</span>
                      <input type="number" class="form-control" id="editProductComparePrice" placeholder="0.00" min="0" step="0.01">
                    </div>
                    <small class="text-muted">Original price to show as discount</small>
                  </div>

                  <div class="col-md-4">
                    <label for="editProductCost" class="form-label fw-bold">Cost per item</label>
                    <div class="input-group">
                      <span class="input-group-text bg-light">$</span>
                      <input type="number" class="form-control" id="editProductCost" placeholder="0.00" min="0" step="0.01">
                    </div>
                    <small class="text-muted">For profit calculation</small>
                  </div>
                </div>
              </section>
              <!-- Discount Section -->


              <!-- Inventory Section -->
              <section class="mb-5">
                <div class="d-flex align-items-center mb-4">
                  <div class="bg-primary bg-opacity-10 p-2 rounded me-3">
                    <i class="fas fa-warehouse text-primary fs-5"></i>
                  </div>
                  <h6 class="mb-0">Inventory</h6>
                </div>

                <div class="row g-3">
                  <div class="col-md-6">
                    <label for="editProductSKU" class="form-label fw-bold">SKU</label>
                    <input type="text" class="form-control" id="editProductSKU" placeholder="Stock keeping unit">
                  </div>

                  <div class="col-md-6">
                    <label for="editProductQuantity" class="form-label fw-bold">Quantity <span class="text-danger">*</span></label>
                    <input type="number" class="form-control" id="editProductQuantity" placeholder="0" min="0" required>
                  </div>

                  <div class="col-md-6">
                    <label for="editProductBarcode" class="form-label fw-bold">Barcode</label>
                    <div class="input-group">
                      <span class="input-group-text bg-light"><i class="fas fa-qrcode text-muted"></i></span>
                      <input type="text" class="form-control" id="editProductBarcode" placeholder="ISBN, UPC, etc.">
                    </div>
                  </div>

                  <div class="col-md-6">
                    <label for="editProductInventoryPolicy" class="form-label fw-bold">Inventory Policy</label>
                    <select class="form-select" id="editProductInventoryPolicy">
                      <option value="continue">Continue selling when out of stock</option>
                      <option value="deny">Stop selling when out of stock</option>
                    </select>
                  </div>
                </div>
              </section>
              <!-- Specifications Details -->

            </div>

            <!-- Right Column - Sidebar -->
            <div class="col-lg-4 p-4">
              <!-- Status Card -->
              <div class="card mb-4 border-0 shadow-sm">
                <div class="card-body">
                  <div class="d-flex align-items-center mb-3">
                    <div class="bg-primary bg-opacity-10 p-2 rounded me-3">
                      <i class="fas fa-toggle-on text-primary fs-5"></i>
                    </div>
                    <h6 class="mb-0">Status</h6>
                  </div>

                  <div class="form-check form-switch mb-4">
                    <input class="form-check-input" type="checkbox" id="editProductStatus" checked>
                    <label class="form-check-label fw-bold" for="editProductStatus">Active Product</label>
                    <small class="text-muted d-block">Visible in storefront</small>
                  </div>

                  <div class="mb-3">
                    <label for="editProductURL" class="form-label fw-bold">Product URL</label>
                    <div class="input-group">
                      <span class="input-group-text bg-light">/product/</span>
                      <input type="text" class="form-control" id="editProductURL" placeholder="custom-url">
                    </div>
                  </div>
                </div>
              </div>

              <!-- Organization Card -->
              <div class="card mb-4 border-0 shadow-sm">
                <div class="card-body">
                  <div class="d-flex align-items-center mb-3">
                    <div class="bg-primary bg-opacity-10 p-2 rounded me-3">
                      <i class="fas fa-sitemap text-primary fs-5"></i>
                    </div>
                    <h6 class="mb-0">Organization</h6>
                  </div>

                  <div class="mb-3">
                    <label for="editProductCategory" class="form-label fw-bold">Category <span class="text-danger">*</span></label>
                    <select class="form-select" id="editProductCategory" required>
                      <option value="" disabled selected>Select category</option>
                    </select>
                  </div>

                  <div class="mb-3">
                    <label for="editProductSupplier" class="form-label fw-bold">Supplier</label>
                    <select class="form-select" id="editProductSupplier">
                      <option value="" selected>Select supplier</option>
                      <option value="1">Apple</option>
                      <option value="2">Samsung</option>
                      <option value="3">Nike</option>
                    </select>
                  </div>


                  <div class="mb-3">
                    <label for="editProductTags" class="form-label fw-bold">Tags</label>
                    <input type="text" class="form-control" id="editProductTags" placeholder="Add tags...">
                    <small class="text-muted">Separate with commas (e.g. wireless, audio, premium)</small>
                    <div class="tag-container mt-2 d-flex flex-wrap gap-2" id="tagContainer"></div>
                  </div>
                </div>
              </div>

              <!-- Image Upload Card -->
              <div class="form-section mt-4">
                <h5 class="section-title">
                  <i class="fas fa-images me-2 text-primary"></i>Product Images
                </h5>

                <div class="image-upload-container1">
                  <div class="dropzone" id="productImageDropzone1">
                    <label for="productImages1" style="cursor: pointer;">
                      <i class="fas fa-cloud-upload-alt fa-3x text-muted"></i>
                      <p>Drag & drop product images here</p>
                      <small class="text-muted">or click to browse (max 1 image)</small>
                    </label>
                    <input type="file" id="productImages1" name="images" multiple accept="image/*" style="display: none;">
                  </div>

                  <div class="image-preview-container d-flex flex-wrap mt-3" id="imageUpdate1">
                    <!-- Preview images will appear here -->
                  </div>
                </div>
              </div>


              <!-- Current Gallery Images -->
<%--              <div class="card mt-4 border-0 shadow-sm">--%>
<%--                <div class="card-body">--%>
<%--                  <div class="d-flex align-items-center mb-3">--%>
<%--                    <div class="bg-primary bg-opacity-10 p-2 rounded me-3">--%>
<%--                      <i class="fas fa-images text-primary fs-5"></i>--%>
<%--                    </div>--%>
<%--                    <h6 class="mb-0">Current Gallery</h6>--%>
<%--                  </div>--%>

<%--                  <div class="current-gallery-images d-flex flex-wrap gap-2">--%>
<%--                    <div class="position-relative">--%>
<%--                      <img src="https://via.placeholder.com/100" class="img-thumbnail" width="80" height="80">--%>
<%--                      <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">--%>
<%--                        <i class="fas fa-times"></i>--%>
<%--                      </span>--%>
<%--                    </div>--%>
<%--                    <div class="position-relative">--%>
<%--                      <img src="https://via.placeholder.com/100" class="img-thumbnail" width="80" height="80">--%>
<%--                      <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">--%>
<%--                        <i class="fas fa-times"></i>--%>
<%--                      </span>--%>
<%--                    </div>--%>
<%--                    <div class="position-relative">--%>
<%--                      <img src="https://via.placeholder.com/100" class="img-thumbnail" width="80" height="80">--%>
<%--                      <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">--%>
<%--                        <i class="fas fa-times"></i>--%>
<%--                      </span>--%>
<%--                    </div>--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--              </div>--%>
            </div>
          </div>
        </form>
      </div>

      <!-- Modal Footer -->
      <div class="modal-footer bg-light p-4 border-top">
        <button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">
          <i class="fas fa-times me-2"></i>Cancel
        </button>
        <button type="button" id="saveChangesBtn" class="btn btn-primary px-4">
          <i class="fas fa-save me-2"></i>Save Changes
        </button>
      </div>
    </div>
  </div>
</div>

<%--  //modal edit--%>


<script src="../js/admin.js"></script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
  $(document).ready(function() {
    // Toggle sidebar on mobile
    $('.sidebar-toggle').click(function(e) {
      e.preventDefault();
      $('.sidebar').toggleClass('active');
    });

    // Switch between sections
    $('.menu-item').click(function(e) {
      e.preventDefault();

      // Remove active class from all menu items
      $('.menu-item').removeClass('active');

      // Add active class to clicked menu item
      $(this).addClass('active');

      // Hide all sections
      $('.content-section').removeClass('active');

      // Show the selected section
      const sectionId = $(this).data('section');
      $('#' + sectionId).addClass('active');
    });
  });
  document.addEventListener('DOMContentLoaded', function() {
    // Xử lý thêm/xóa mô tả
    let descIndex = 1;
    document.getElementById('addDescriptionBtn').addEventListener('click', function() {
      const descContainer = document.getElementById('descriptionContainer');
      const newDescItem = document.createElement('div');
      newDescItem.className = 'description-item row mb-3';
      newDescItem.innerHTML = `
            <div class="col-md-5">
                <div class="form-group">
                    <label class="form-label">Title</label>
                    <input type="text" class="form-control" name="descriptions[${descIndex}].title" placeholder="Feature title">
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="form-label">Description</label>
                    <textarea class="form-control" name="descriptions[${descIndex}].content" rows="2" placeholder="Detailed description"></textarea>
                </div>
            </div>
            <div class="col-md-1 d-flex align-items-end">
                <button type="button" class="btn btn-outline-danger remove-description-btn">
                    <i class="fas fa-trash"></i>
                </button>
            </div>
        `;
      descContainer.appendChild(newDescItem);
      descIndex++;
    });

    // Xóa mô tả
    document.getElementById('descriptionContainer').addEventListener('click', function(e) {
      if (e.target.classList.contains('remove-description-btn') || e.target.closest('.remove-description-btn')) {
        const btn = e.target.classList.contains('remove-description-btn') ? e.target : e.target.closest('.remove-description-btn');
        btn.closest('.description-item').remove();
      }
    });

    // ... (phần xử lý khác giữ nguyên) ...
  });
  document.addEventListener('DOMContentLoaded', function() {
    // Lấy danh sách danh mục và nhà cung cấp từ API


    // Xử lý thêm/xóa khuyến mãi
    let saleIndex = 1;
    document.getElementById('addSaleBtn').addEventListener('click', function() {
      const saleContainer = document.getElementById('saleContainer');
      const newSaleItem = document.createElement('div');
      newSaleItem.className = 'sale-item row mb-3';
      newSaleItem.innerHTML = `
            <div class="col-md-4">
                <div class="form-group">
                    <label class="form-label">Discount %</label>
                    <input type="number" class="form-control" name="sale[${saleIndex}].salePersent" min="1" max="100">
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label class="form-label">Start Date</label>
                    <input type="datetime-local" class="form-control" name="sale[${saleIndex}].saleStartDate">
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <label class="form-label">End Date</label>
                    <input type="datetime-local" class="form-control" name="sale[${saleIndex}].saleEndDate">
                </div>
            </div>
            <div class="col-md-1 d-flex align-items-end">
                <button type="button" class="btn btn-outline-danger remove-sale-btn">
                    <i class="fas fa-trash"></i>
                </button>
            </div>
        `;
      saleContainer.appendChild(newSaleItem);
      saleIndex++;
    });


    // Xóa khuyến mãi
    document.getElementById('saleContainer').addEventListener('click', function(e) {
      if (e.target.classList.contains('remove-sale-btn') || e.target.closest('.remove-sale-btn')) {
        const btn = e.target.classList.contains('remove-sale-btn') ? e.target : e.target.closest('.remove-sale-btn');
        btn.closest('.sale-item').remove();
      }
    });

    // Xử lý upload ảnh
    const imageInput = document.getElementById('productImages');
    const dropzone = document.getElementById('productImageDropzone');
    const previewContainer = document.getElementById('imageUpdate');

    // Drag and drop functionality
    dropzone.addEventListener('click', () => imageInput.click());

    dropzone.addEventListener('dragover', (e) => {
      e.preventDefault();
      dropzone.classList.add('dragover');
    });

    dropzone.addEventListener('dragleave', () => {
      dropzone.classList.remove('dragover');
    });

    dropzone.addEventListener('drop', (e) => {
      e.preventDefault();
      dropzone.classList.remove('dragover');
      if (e.dataTransfer.files.length) {
        imageInput.files = e.dataTransfer.files;
        updateImagePreview();
      }
    });

    imageInput.addEventListener('change', updateImagePreview);


    function updateImagePreview() {
      previewContainer.innerHTML = '';
      const files = imageInput.files;

      for (let i = 0; i < files.length; i++) {
        const file = files[i];
        if (!file.type.match('image.*')) continue;

        const reader = new FileReader();
        reader.onload = function(e) {
          const imageWrapper = document.createElement('div');
          imageWrapper.className = 'image-preview-item';

          const img = document.createElement('img');
          img.src = e.target.result;
          img.className = 'img-thumbnail';

          const removeBtn = document.createElement('button');
          removeBtn.className = 'btn btn-sm btn-danger remove-image-btn';
          removeBtn.innerHTML = '<i class="fas fa-times"></i>';
          removeBtn.addEventListener('click', () => {
            imageWrapper.remove();
            // Update files list
            const dataTransfer = new DataTransfer();
            Array.from(imageInput.files).forEach((f, idx) => {
              if (idx !== i) dataTransfer.items.add(f);
            });
            imageInput.files = dataTransfer.files;
          });

          imageWrapper.appendChild(img);
          imageWrapper.appendChild(removeBtn);
          previewContainer.appendChild(imageWrapper);
        };
        reader.readAsDataURL(file);
      }
    }

    // Xử lý submit form
    document.getElementById('productForm').addEventListener('submit', function(e) {
      e.preventDefault();

      const formData = new FormData(this);
      const formObject = Object.fromEntries(formData.entries());
      console.log(formObject);

      // Thêm ngày tạo
      formData.append('createAt', new Date().toISOString());

      const sales = [];
      // Sửa lại cách query các phần tử sale
      document.querySelectorAll('.sale-item').forEach((item) => {
        // Sử dụng selector không phụ thuộc vào index cụ thể
        const salePersentInput = item.querySelector('[name*="salePersent"]');
        const saleStartDateInput = item.querySelector('[name*="saleStartDate"]');
        const saleEndDateInput = item.querySelector('[name*="saleEndDate"]');

        // Kiểm tra tồn tại trước khi truy cập value
        if (salePersentInput && saleStartDateInput && saleEndDateInput) {
          const salePersent = salePersentInput.value;
          const saleStartDate = saleStartDateInput.value;
          const saleEndDate = saleEndDateInput.value;

          if (salePersent && saleStartDate && saleEndDate) {
            sales.push({
              salePersent: parseFloat(salePersent),
              saleStartDate: saleStartDate,
              saleEndDate: saleEndDate
            });
          }
        }
      });

      // Thêm sales vào formData dưới dạng JSON
      if (sales.length > 0) {
        formData.append('sales', JSON.stringify(sales));
      }
      const descriptions = [];
      document.querySelectorAll('.description-item').forEach((item) => {
        // Sử dụng selector chung không phụ thuộc vào index
        const titleInput = item.querySelector('[name^="descriptions["][name$="].title"]');
        const contentInput = item.querySelector('[name^="descriptions["][name$="].content"]');

        if (titleInput && contentInput && titleInput.value && contentInput.value) {
          descriptions.push({
            title: titleInput.value,
            content: contentInput.value
          });
        }
      });

      // Thêm descriptions vào formData dưới dạng JSON
      formData.append('descriptions', JSON.stringify(descriptions));

      // Gửi dữ liệu lên server
      fetch('http://localhost:8080/WebFinall/admin/addProduct', {
        method: 'POST',
        body: formData
      })
              .then(async response => {
                // First check if the response is JSON
                const contentType = response.headers.get('content-type');
                if (contentType && contentType.includes('application/json')) {
                  return response.json();
                }

                // If not JSON, get the text response
                const text = await response.text();
                try {
                  // Try to parse it as JSON anyway (some servers return JSON with wrong headers)
                  return JSON.parse(text);
                } catch {
                  // If not JSON, throw the text error
                  throw new Error(text || 'Unknown error occurred');
                }
              })
              .then(data => {
                if (data.error) {
                  throw new Error(data.error);
                }
                alert(`Product ${data.id ? 'updated' : 'added'} successfully!`);
                if (!document.getElementById('productID').value && data.id) {
                  document.getElementById('productID').value = data.id;
                }
              })
              .catch(error => {
                console.error('Error:', error);
                alert('Error: ' + (error.message || 'Failed to save product'));
              });
    });

    // Back button
    document.getElementById('backButton').addEventListener('click', function() {
      window.history.back();
    });
  });

</script>
<script>
  $(document).ready(function () {
    console.log("Script is running...");

    $.ajax({
      url: '/WebFinall/allProduct',
      method: "GET",

      success: function (data) {
        data.forEach(product => {
          const status = getStatus(product.isActive);
          const imageUrl = product.image || "https://via.placeholder.com/60";
          const oldPrice = product.sale > 0 ? `<div class="text-decoration-line-through text-muted small">\${formatCurrency(product.price)}</div>` : "";
          const finalPrice = product.sale > 0
                  ? product.price * (1 - product.sale / 100)
                  : product.price;
          const progress = getInventoryProgress(product.inventory, product.quantity);
          const cate = product.category;
          const row = `
  <tr>
    <td><input type="checkbox" class="form-check-input"></td>
    <td>
      <div class="d-flex align-items-center">
        <div class="product-thumbnail me-3">
          <img src="img/\${imageUrl}" alt="\${product.name}" width="60">
        </div>
        <div>
          <h6 class="mb-0">\${product.name}</h6>
          <small class="text-muted">SKU: \${product.idProduct}</small><br>
          <small class="text-muted">Weight: \${product.weight} kg</small>
        </div>
      </div>
    </td>
    <td>\${cate}</td>
    <td>
      <span class="text-primary fw-bold">\${formatCurrency(finalPrice)}</span>
      \${oldPrice}
    </td>
    <td>
      <div class="inventory-status">
        <div class="progress" style="height: 6px;">
          <div class="progress-bar \${progress.color}" style="width: \${progress.percent}%"></div>
        </div>
        <small>\${product.inventory}/\${product.quantity} in stock</small>
      </div>
    </td>
    <td>
      <div class="sales-info">
        <span class="fw-bold">\${product.sale || 0} %</span>
        <span class="text-muted small"><i class="fas fa-chart-line"></i></span>
      </div>
    </td>
    <td>
      <span class="badge \${status.class}">\${status.text}</span>
    </td>
    <td>
      <div class="dropdown">
        <button class="btn btn-sm btn-icon" data-bs-toggle="dropdown">
          <i class="fas fa-ellipsis-v"></i>
        </button>
        <ul class="dropdown-menu dropdown-menu-end">
          <li><a class="dropdown-item" href="#"><i class="fas fa-eye me-2"></i>View</a></li>
<li>
<button type="button" class="dropdown-item edit-product-btn" data-id="\${product.idProduct}" data-weight="\${product.weight}">
    <i class="fas fa-edit me-1"></i> Edit
  </button>
</li>
       <li><a class="dropdown-item" href="#"><i class="fas fa-copy me-2"></i>Duplicate</a></li>
          <li><hr class="dropdown-divider"></li>
          <li><a class="dropdown-item text-danger" href="#"><i class="fas fa-trash me-2"></i>Delete</a></li>
        </ul>
      </div>
    </td>
  </tr>
`;

          $("#product-table-body").append(eval("`" + row + "`")); // Dùng eval để parse lại string có template
        });
      }
    });

    function formatCurrency(number) {
      return new Intl.NumberFormat("vi-VN", { style: "currency", currency: "VND" }).format(number);
    }

    function getStatus(statusCode) {
      switch (statusCode) {
        case "1":
          return { text: "Active", class: "badge bg-success bg-opacity-10 text-success" };
        case "2":
          return { text: "Low Stock", class: "badge bg-warning bg-opacity-10 text-warning" };
        case "3":
        default:
          return { text: "Out of Stock", class: "badge bg-danger bg-opacity-10 text-danger" };
      }
    }

    function getInventoryProgress(inventory, quantity) {
      const percent = quantity > 0 ? (inventory / quantity) * 100 : 0;
      let color = "bg-success";
      if (percent < 25) color = "bg-danger";
      else if (percent < 50) color = "bg-warning";
      return { percent: percent.toFixed(0), color };
    }
  });

</script>
<script>

  $(document).on('click', '.edit-product-btn', function() {
    // Lấy id và weight từ data attributes của nút
    const productId = $(this).data('id');
    const productWeight = $(this).data('weight');
    console.log(productId + "id");

    // Cập nhật modal với thông tin sản phẩm
    $('#currentProductSKU').val(productId);  // Hiển thị ID sản phẩm trong modal
    $('#productWeight').val(productWeight);  // Hiển thị weight sản phẩm trong modal

    // Mở modal
    $('#editProductModal').modal('show');

    // Gửi AJAX để lấy dữ liệu sản phẩm từ server
    $.ajax({
      url: '/WebFinall/admin/editProduct',  // Endpoint của bạn
      method: 'GET',
      data: {
        id: productId,
        weight: productWeight
      },
      success: function(response) {
        console.log('Product data received:', response);
        console.log(response.saleDTO.salePersent
        )
        const saleListContainer = $('#saleList');
        saleListContainer.empty();

        // Cập nhật các trường trong modal từ dữ liệu sản phẩm
        $('#editProductName').val(response.name);  // Tên sản phẩm
        $('#editProductSKU1').val(response.id);    // SKU (id sản phẩm)
        $('#editProductSpec').val(response.category);  // Mô tả sản phẩm (hoặc bạn có thể thêm một trường cho mô tả nếu cần)
        $('#editProductStock').val(response.inventory);  // Số lượng trong kho
        $('#currentProductName').text(response.name);  // Set the product name
        $('#currentProductPrice').text('VNĐ ' + response.price.toFixed(2));  // Set the price (formatted as a currency)
        $('#currentProductSKU').text('SKU: ' + response.id);  // Set the SKU (id)
        $('#currentProductStock').text('In stock: ' + response.inventory);  // Set the stock quantity
        const specsContainer = $('#currentProductSpecs');
        specsContainer.empty(); // Xoá nội dung cũ trước
        specsContainer.append(`<li>Category: \${response.category}</li>`);
        specsContainer.append(`<li>Weight: \${response.weight}g</li>`);
        specsContainer.append(`<li>Import Date: \${new Date(response.importDate).toLocaleDateString()}</li>`);
        $('#editProductQuantity').val(response.quantity); // Tổng nhập vào (có thể sửa)
        $('#editProductSKU').val("SP-" + response.idProduct); // Gợi ý tạo SKU từ ID
        $('#editProductBarcode').val("BC-" + response.idProduct); // Gợi ý tạo Barcode

// Tùy chính sách bạn đang dùng
        $('#editProductInventoryPolicy').val(response.inventory > 0 ? 'continue' : 'deny');
        // Update the image
        $('#currentProductImage').attr('src', 'img/' + response.image);  // Assuming the image URL is relative
        console.log(response.saleDTO)

        // Update product status (if the product is active or not)
        const statusBadge = response.isActive === 'Active' ? 'bg-success' : 'bg-danger';
        $('#currentProductStatus').text(response.isActive).removeClass('bg-success bg-danger').addClass(statusBadge);
        // Giá gốc
        const originalPrice = response.price;

// Kiểm tra có sale không
        if (response.saleDTO && response.saleDTO.salePersent > 0) {
          const discount = originalPrice * response.saleDTO.salePersent / 100;
          const salePrice = originalPrice - discount;

          $('#editProductPrice').val(salePrice.toFixed(2));
          $('#editProductComparePrice').val(originalPrice.toFixed(2));
        } else {
          // Không có khuyến mãi, cả hai giá giống nhau
          $('#editProductPrice').val(originalPrice.toFixed(2));
          $('#editProductComparePrice').val(originalPrice.toFixed(2));
        }

        if (response.descriptionDTOS && Array.isArray(response.descriptionDTOS)) {
          const descriptionListContainer = $('#descriptionList');
          descriptionListContainer.empty(); // Xóa các dòng cũ

          response.descriptionDTOS.forEach(desc => {
            const title = desc.title || '';
            const content = desc.content || '';

            const html = `
      <div class="row g-2 align-items-start mb-2 description-item">
        <div class="col-md-4">
          <input type="text" class="form-control" placeholder="Title (e.g. Usage)" value="\${title}">
        </div>
        <div class="col-md-7">
          <textarea class="form-control" rows="2" placeholder="Content">\${content}</textarea>
        </div>
        <div class="col-md-1 text-end">
          <button type="button" class="btn btn-outline-danger btn-sm" onclick="this.closest('.description-item').remove()">
            <i class="fas fa-trash"></i>
          </button>
        </div>
      </div>
    `;

            descriptionListContainer.append(html);
          });
        }



        if (response.saleDTO) {
          // Tính toán giá sau khi giảm
          const salePrice = response.price - (response.price * response.saleDTO.salePersent / 100);
          console.log(salePrice)
          $('#editDiscountPrice').val(salePrice.toFixed(2));  // Set discount price

          // Format và set ngày kết thúc giảm giá
          const saleEndDate = new Date(response.saleDTO.saleEndDate);
          const formattedEndDate = saleEndDate.toISOString().split('T')[0];
          $('#editDiscountEnd').val(formattedEndDate);

          // Format và set ngày bắt đầu giảm giá
          const saleStartDate = new Date(response.saleDTO.saleStartDate);
          const formattedStartDate = saleStartDate.toISOString().split('T')[0];
          $('#editDiscountStart').val(formattedStartDate);

          // Hiển thị phần trăm giảm giá
          $('#currentProductSalePercent').text(response.saleDTO.salePersent + '%');
        }
        if (Array.isArray(response.promotionDTOS) && response.promotionDTOS.length > 0) {

          const saleListContainer = $('#saleList');
          saleListContainer.empty();

          response.promotionDTOS.forEach(promo => {
            const startDate = new Date(promo.startDate).toISOString().split('T')[0];
            const endDate = new Date(promo.endDate).toISOString().split('T')[0];

            const promoItem = `
      <div class="row g-2 align-items-center mb-2 sale-item">
        <div class="col-md-3">
          <div class="input-group">
            <input type="number" class="form-control" placeholder="Sale %" value="\${promo.salePercent}" min="0" max="100" step="1">
            <span class="input-group-text">%</span>
          </div>
        </div>
        <div class="col-md-3">
          <input type="date" class="form-control" value="\${startDate}">
        </div>
        <div class="col-md-3">
          <input type="date" class="form-control" value="\${endDate}">
        </div>
        <div class="col-md-2">
          <button type="button" class="btn btn-outline-danger btn-sm" onclick="this.closest('.sale-item').remove()">
            <i class="fas fa-trash"></i>
          </button>
        </div>
      </div>
    `;
            saleListContainer.append(promoItem);
          });
        } else {
          $('#saleList').empty(); // Xoá hết nếu không có khuyến mãi
        }

      },
      error: function(xhr, status, error) {
        console.log('Error:', error);
      }
    });
  });



  // Khi click vào nút "Save changes", bạn có thể lấy thông tin đã chỉnh sửa và gửi lại tới server
  //   $('#saveChangesBtn').on('click', function() {
  //     const updatedProductId = $('#productId').val();
  //     const updatedWeight = $('#productWeight').val();
  //
  //     // Gửi dữ liệu đã chỉnh sửa đến server
  //     $.ajax({
  //       url: '/update-product',  // Thay thế URL bằng URL cập nhật sản phẩm của bạn
  //       method: 'POST',
  //       data: {
  //         id: updatedProductId,
  //         weight: updatedWeight
  //       },
  //       success: function(response) {
  //         console.log('Product updated successfully:', response);
  //         // Đóng modal sau khi lưu thay đổi
  //         $('#editProductModal').modal('hide');
  //       },
  //       error: function(xhr, status, error) {
  //         console.log('Error:', error);
  //       }
  //     });
  //   });
  $(document).ready(function () {
      $.ajax({
          url: '/WebFinall/getCategories',
          method: 'GET',
          success: function (response) {
              console.log(response + "cau truc");  // Kiểm tra cấu trúc của response

              // Populate the productCategory select
              const $editSelectCategory = $('#editProductCategory');
              $editSelectCategory.empty(); // Clear previous options
              $editSelectCategory.append('<option value="" disabled selected>Select category</option>');

              response.items.forEach(function (category) {
                  console.log(category);  // Log each category
                  $editSelectCategory.append(`<option value="\${category.id}">\${category.name}</option>`);
              });
          },
          error: function () {
              alert("Không thể tải danh sách danh mục.");
          }
      });
  });
  $(document).ready(function () {
    $.ajax({
      url: '/WebFinall/getSuppliers',
      method: 'GET',
      success: function (response) {
        console.log("Dữ liệu trả về: ", response);

        const $supplierSelect = $('#editProductSupplier');
        $supplierSelect.empty(); // Xóa các option cũ (nếu có)
        $supplierSelect.append('<option value="" selected>Select supplier</option>');

        if (Array.isArray(response.items)) {
          console.log("Dữ liệu nhà cung cấp: ", response.items);
          response.items.forEach(function (supplier) {
            console.log("Supplier: ", supplier);
            $supplierSelect.append(`<option value="\${supplier.idSupplier}">\${supplier.nameSupplier}</option>`);
          });
        } else {
          console.error("Dữ liệu không phải là mảng: ", response.items);
          alert("Có lỗi xảy ra khi tải danh sách nhà cung cấp.");
        }
      },
      error: function () {
        alert("Không thể tải danh sách nhà cung cấp.");
      }
    });
  });
    // Hàm để mở modal và điền thông tin vào form\
</script>
<script>
  function addDescriptionItem() {
    const container = document.getElementById('descriptionList');

    const newItem = document.createElement('div');
    newItem.className = 'row g-2 align-items-start mb-2 description-item';
    newItem.innerHTML = `
      <div class="col-md-4">
        <input type="text" class="form-control" placeholder="Title (e.g. Usage)">
      </div>
      <div class="col-md-7">
        <textarea class="form-control" rows="2" placeholder="Content"></textarea>
      </div>
      <div class="col-md-1 text-end">
        <button type="button" class="btn btn-outline-danger btn-sm" onclick="this.closest('.description-item').remove()">
          <i class="fas fa-trash"></i>
        </button>
      </div>
    `;
    container.appendChild(newItem);
  }
</script>
<script>
  function addSaleItem() {
    const container = document.getElementById('saleList');
    const newItem = document.createElement('div');
    newItem.className = 'row g-2 align-items-center mb-2 sale-item';
    newItem.innerHTML = `
      <div class="col-md-3">
        <div class="input-group">
            <input type="number" class="form-control" placeholder="Sale %"  min="0" max="100" step="1">
            <span class="input-group-text">%</span>
          </div>
      </div>
      <div class="col-md-3">
        <input type="date" class="form-control">
      </div>
      <div class="col-md-3">
        <input type="date" class="form-control">
      </div>
      <div class="col-md-2">
        <button type="button" class="btn btn-outline-danger btn-sm" onclick="this.closest('.sale-item').remove()">
          <i class="fas fa-trash"></i>
        </button>
      </div>
    `;
    container.appendChild(newItem);
  }
</script>
<script>
  $(document).ready(function () {
    const $input = $('#productImages1');
    const $previewContainer = $('#imageUpdate1');

    $input.on('change', function () {
      const files = this.files;
      $previewContainer.empty(); // Xóa ảnh cũ

      if (files.length > 10) {
        alert("Chỉ được chọn tối đa 1 ảnh!");
        $input.val(""); // Reset input
        return;
      }

      Array.from(files).forEach(file => {
        if (!file.type.startsWith("image/")) return;

        const reader = new FileReader();
        reader.onload = function (e) {
          const img = $('<img>').attr('src', e.target.result).css({
            width: '120px',
            height: '120px',
            objectFit: 'cover',
            margin: '5px',
            border: '1px solid #ccc',
            borderRadius: '8px'
          });
          $previewContainer.append(img);
        };
        reader.readAsDataURL(file);
      });
    });
  });
  function getEditProductFormData() {
    const descriptions = [];
    $('#descriptionList .description-item').each(function () {
      const title = $(this).find('input').val();
      const content = $(this).find('textarea').val();
      descriptions.push({ title, content });
    });

    const promotions = [];
    $('#saleList .sale-item').each(function () {
      const salePercent = $(this).find('input[type="number"]').val();
      const startDate = $(this).find('input[type="date"]').eq(0).val();
      const endDate = $(this).find('input[type="date"]').eq(1).val();
      promotions.push({
        salePercent: parseFloat(salePercent),
        startDate,
        endDate
      });
    });

    return {
      id: $('#editProductSKU1').val(), // ID thực tế của sản phẩm
      productId: $('#currentProductSKU').val(), // ID cập nhật (có thể giống id)
      active: $('#editProductStatus').prop('checked'),
      weight: $('#productWeight').val(),
      name: $('#editProductName').val(),
      sku: $('#editProductSKU').val(),
      barcode: $('#editProductBarcode').val(),
      stock: parseInt($('#editProductStock').val()),
      quantity: parseInt($('#editProductQuantity').val()),
      category: $('#editProductCategory').val(),
      supplier: $('#editProductSupplier').val(),
      spec: $('#editProductSpec').val(),
      price: parseFloat($('#editProductPrice').val()),
      comparePrice: parseFloat($('#editProductComparePrice').val()),
      inventoryPolicy: $('#editProductInventoryPolicy').val(),
      salePercent: $('#currentProductSalePercent').text().replace('%', '').trim(),
      discountPrice: $('#editDiscountPrice').val(),
      discountStart: $('#editDiscountStart').val(),
      discountEnd: $('#editDiscountEnd').val(),
      descriptions: descriptions,
      promotions: promotions
    };
  }

  $('#saveChangesBtn').on('click', function () {
    const formDataObj = getEditProductFormData();

    const formData = new FormData();

    // Gán các field vào FormData
    Object.keys(formDataObj).forEach(key => {
      if (key === 'descriptions' || key === 'promotions') {
        formData.append(key, JSON.stringify(formDataObj[key]));
      } else {
        formData.append(key, formDataObj[key]);
      }
    });

    // Gán ảnh vào FormData
    const fileInput = $('#productImages1')[0];
    if (fileInput.files.length > 0) {
      formData.append('image', fileInput.files[0]);
    }
    for (let pair of formData.entries()) {
      console.log(pair[0] + ':', pair[1]);
    }

    $.ajax({
      url: '/WebFinall/admin/editProduct',
      method: 'POST',
      data: formData,
      processData: false,
      contentType: false,
      success: function (res) {
        console.log("Cập nhật thành công", res);
        $('#editProductModal').modal('hide');
      },
      error: function (err) {
        console.error("Lỗi cập nhật", err);
      }
    });
  });
</script>
<script  src="${pageContext.request.contextPath}/js/admin.js"></script>
<script>
$(document).ready(function() {
    // Load logs when page loads
    loadLogs();

    // Refresh logs every 30 seconds
    setInterval(loadLogs, 30000);
});

function loadLogs() {
    $.ajax({
        url: '/WebFinall/admin/getRecentActivities',
        method: 'GET',
        success: function(data) {
            console.log("Received data:", data); // Debug log
            const tbody = $('#LogActivity');
            tbody.empty();

            if (Array.isArray(data)) {
                data.forEach(log => {
                    const date = new Date(log.createdAt);
                    const formattedDate = date.toLocaleString('vi-VN', {
                        year: 'numeric',
                        month: '2-digit',
                        day: '2-digit',
                        hour: '2-digit',
                        minute: '2-digit',
                        second: '2-digit'
                    });

                    const row = `
                        <tr>
                            <td>${'$'}{log.username ? log.username : 'N/A'}</td>
                            <td>${'$'}{log.details ? log.details : 'N/A'}</td>
                            <td>${'$'}{formattedDate}</td>
                            <td>
                                <span class="badge ${'$'}{log.roleName === 'Admin' ? 'bg-danger' : 'bg-secondary'}">
                                    ${'$'}{log.roleName ? log.roleName : 'N/A'}
                                </span>
                            </td>
                        </tr>
                    `;
                    tbody.append(row);
                });
            } else {
                console.error("Data is not an array:", data);
            }
        },
        error: function(xhr, status, error) {
            console.error('Error loading logs:', error);
            console.error('Status:', status);
            console.error('Response:', xhr.responseText);
        }
    });
}
</script>
</body>
</html>