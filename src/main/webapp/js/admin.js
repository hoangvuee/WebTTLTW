$(document).ready(function () {
    $.ajax({
        url: '/WebFinall/getCategories',
        method: 'GET',
        success: function (response) {
            const $select = $('#productCategory');
            $select.empty(); // Xóa các option cũ (nếu có)

            // Thêm option mặc định
            $select.append('<option value="" disabled selected>Select category</option>');

            // Thêm các option từ dữ liệu trả về
            response.items.forEach(function (category) {
                $select.append(`<option value="${category.id}">${category.name}</option>`);
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
            const $supplierSelect = $('#productSupplier');
            $supplierSelect.empty(); // clear select

            // Thêm option mặc định
            $supplierSelect.append('<option value="" disabled selected>Select supplier</option>');

            // Duyệt danh sách supplier và thêm vào select
            response.items.forEach(function (supplier) {
                $supplierSelect.append(`<option value="${supplier.idSupplier}">${supplier.nameSupplier}</option>`);
            });
        },
        error: function () {
            alert("Không thể tải danh sách nhà cung cấp");
        }
    });
});
