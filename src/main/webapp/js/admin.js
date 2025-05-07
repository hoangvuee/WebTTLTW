$(document).ready(function () {
    // Gọi AJAX để lấy danh mục
    $.ajax({
        url: '/WebFinall/getCategories',
        method: 'GET',
        success: function (response) {
            // Xử lý dữ liệu danh mục
            const $selectCategory = $('#productCategory');
            $selectCategory.empty(); // Xóa các option cũ (nếu có)
            $selectCategory.append('<option value="" disabled selected>Select category</option>');

            response.items.forEach(function (category) {
                console.log(category + "haongvunha")
                $selectCategory.append(`<option value="${category.id}">${category.name}</option>`);
            });


        },
        error: function () {
            alert("Không thể tải danh sách danh mục.");
        }
    });

    // Gọi AJAX để lấy danh sách nhà cung cấp
    $.ajax({
        url: '/WebFinall/getSuppliers',
        method: 'GET',
        success: function (response) {
            console.log("cai lin me may")
            const $supplierSelect = $('#productSupplier');
            $supplierSelect.empty(); // Xóa các option cũ (nếu có)
            $supplierSelect.append('<option value="" disabled selected>Select supplier</option>');

            response.items.forEach(function (supplier) {
                console.log(supplier.nameSupplier)
                $supplierSelect.append(`<option value="${supplier.idSupplier}">${supplier.nameSupplier}</option>`);
            });
        },
        error: function () {
            alert("Không thể tải danh sách nhà cung cấp.");
        }
    });
});
// Hàm để load categories vào <select>
$(document).ready(function () {
    $.ajax({
        url: '/WebFinall/getCategories',
        method: 'GET',
        success: function (response) {
            console.log(response);  // Kiểm tra cấu trúc của response

            // Populate the productCategory select
            const $editSelectCategory = $('#editProductCategory');
            $editSelectCategory.empty(); // Clear previous options
            $editSelectCategory.append('<option value="" disabled selected>Select category</option>');

            response.items.forEach(function (category) {
                console.log(category);  // Log each category
                $editSelectCategory.append(`<option value="${category.id}">${category.name}</option>`);
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
                    $supplierSelect.append(`<option value="${supplier.idSupplier}">${supplier.nameSupplier}</option>`);
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
