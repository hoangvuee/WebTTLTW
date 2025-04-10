<%--
  Created by IntelliJ IDEA.
  User: airm2
  Date: 9/4/25
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
  <link rel="stylesheet" href="css/admin.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-white px-3">
  <a class="navbar-brand d-flex align-items-center" href="#">
    <img src="img/google-admin-logo-png_seeklogo-504247.png" alt="Logo" height="30">
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
            <th>Action</th>
            <th>Time</th>
            <th>Status</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>John Doe</td>
            <td>Updated product</td>
            <td>10:30 AM</td>
            <td><span class="badge bg-success">Completed</span></td>
          </tr>
          <tr>
            <td>Jane Smith</td>
            <td>Created new order</td>
            <td>09:45 AM</td>
            <td><span class="badge bg-warning">Pending</span></td>
          </tr>
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
          <tbody>
          <tr>
            <td>
              <input type="checkbox" class="form-check-input">
            </td>
            <td>
              <div class="d-flex align-items-center">
                <div class="product-thumbnail me-3">
                  <img src="https://via.placeholder.com/60" alt="Wireless Headphones">
                </div>
                <div>
                  <h6 class="mb-0">AirPods Pro (2nd Gen)</h6>
                  <small class="text-muted">SKU: AP-2023</small>
                </div>
              </div>
            </td>
            <td>Electronics</td>
            <td>
              <span class="text-primary fw-bold">$249.00</span>
              <div class="text-decoration-line-through text-muted small">$279.00</div>
            </td>
            <td>
              <div class="inventory-status">
                <div class="progress" style="height: 6px;">
                  <div class="progress-bar bg-success" style="width: 75%"></div>
                </div>
                <small>45/60 in stock</small>
              </div>
            </td>
            <td>
              <div class="sales-info">
                <span class="fw-bold">128</span>
                <span class="text-success small"><i class="fas fa-arrow-up"></i> 12%</span>
              </div>
            </td>
            <td>
              <span class="badge bg-success bg-opacity-10 text-success">Active</span>
            </td>
            <td>
              <div class="dropdown">
                <button class="btn btn-sm btn-icon" data-bs-toggle="dropdown">
                  <i class="fas fa-ellipsis-v"></i>
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                  <li><a class="dropdown-item" href="#"><i class="fas fa-eye me-2"></i>View</a></li>
                  <li><a class="dropdown-item" href="#"><i class="fas fa-edit me-2"></i>Edit</a></li>
                  <li><a class="dropdown-item" href="#"><i class="fas fa-copy me-2"></i>Duplicate</a></li>
                  <li><hr class="dropdown-divider"></li>
                  <li><a class="dropdown-item text-danger" href="#"><i class="fas fa-trash me-2"></i>Delete</a></li>
                </ul>
              </div>
            </td>
          </tr>
          <tr>
            <td>
              <input type="checkbox" class="form-check-input">
            </td>
            <td>
              <div class="d-flex align-items-center">
                <div class="product-thumbnail me-3">
                  <img src="https://via.placeholder.com/60" alt="Smart Watch">
                </div>
                <div>
                  <h6 class="mb-0">Apple Watch Series 8</h6>
                  <small class="text-muted">SKU: AW-S8</small>
                </div>
              </div>
            </td>
            <td>Electronics</td>
            <td>
              <span class="text-primary fw-bold">$399.00</span>
            </td>
            <td>
              <div class="inventory-status">
                <div class="progress" style="height: 6px;">
                  <div class="progress-bar bg-warning" style="width: 25%"></div>
                </div>
                <small>5/20 in stock</small>
              </div>
            </td>
            <td>
              <div class="sales-info">
                <span class="fw-bold">86</span>
                <span class="text-success small"><i class="fas fa-arrow-up"></i> 8%</span>
              </div>
            </td>
            <td>
              <span class="badge bg-warning bg-opacity-10 text-warning">Low Stock</span>
            </td>
            <td>
              <div class="dropdown">
                <button class="btn btn-sm btn-icon" data-bs-toggle="dropdown">
                  <i class="fas fa-ellipsis-v"></i>
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                  <li><a class="dropdown-item" href="#"><i class="fas fa-eye me-2"></i>View</a></li>
                  <li><a class="dropdown-item" href="#"><i class="fas fa-edit me-2"></i>Edit</a></li>
                  <li><a class="dropdown-item" href="#"><i class="fas fa-copy me-2"></i>Duplicate</a></li>
                  <li><hr class="dropdown-divider"></li>
                  <li><a class="dropdown-item text-danger" href="#"><i class="fas fa-trash me-2"></i>Delete</a></li>
                </ul>
              </div>
            </td>
          </tr>
          <tr>
            <td>
              <input type="checkbox" class="form-check-input">
            </td>
            <td>
              <div class="d-flex align-items-center">
                <div class="product-thumbnail me-3">
                  <img src="https://via.placeholder.com/60" alt="Leather Wallet">
                </div>
                <div>
                  <h6 class="mb-0">Premium Leather Wallet</h6>
                  <small class="text-muted">SKU: LW-PRM</small>
                </div>
              </div>
            </td>
            <td>Fashion</td>
            <td>
              <span class="text-primary fw-bold">$59.99</span>
              <div class="text-decoration-line-through text-muted small">$79.99</div>
            </td>
            <td>
              <div class="inventory-status">
                <div class="progress" style="height: 6px;">
                  <div class="progress-bar bg-danger" style="width: 0%"></div>
                </div>
                <small>0/15 in stock</small>
              </div>
            </td>
            <td>
              <div class="sales-info">
                <span class="fw-bold">42</span>
                <span class="text-danger small"><i class="fas fa-arrow-down"></i> 5%</span>
              </div>
            </td>
            <td>
              <span class="badge bg-danger bg-opacity-10 text-danger">Out of Stock</span>
            </td>
            <td>
              <div class="dropdown">
                <button class="btn btn-sm btn-icon" data-bs-toggle="dropdown">
                  <i class="fas fa-ellipsis-v"></i>
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                  <li><a class="dropdown-item" href="#"><i class="fas fa-eye me-2"></i>View</a></li>
                  <li><a class="dropdown-item" href="#"><i class="fas fa-edit me-2"></i>Edit</a></li>
                  <li><a class="dropdown-item" href="#"><i class="fas fa-copy me-2"></i>Duplicate</a></li>
                  <li><hr class="dropdown-divider"></li>
                  <li><a class="dropdown-item text-danger" href="#"><i class="fas fa-trash me-2"></i>Delete</a></li>
                </ul>
              </div>
            </td>
          </tr>
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
                  <input type="file" id="productImages" name="images" multiple accept="image/*">
                </div>

                <div class="image-preview-container" id="imagePreviewContainer">
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
<script src="js/admin.js"></script>
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
    const previewContainer = document.getElementById('imagePreviewContainer');

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
      fetch('http://localhost:8080/WebFinall/addProduc', {
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

<script  src="js/admin.js"></script>
</body>
</html>