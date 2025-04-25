package Dao;

import DTO.DescriptionDTO;
import DTO.ProductDTO;
import DTO.PromotionDTO;
import DTO.SaleDTO;
import Models.ManageProduct.ListProductManage;
import Models.ManageProduct.Product;
import Models.Product.ListProduct;
import Models.Products.Products;
import Models.TopProductBuy.TopProduct;
import Models.cart.CartProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDao {
    ConnDB dao = new ConnDB();
    private CategoryDao categoryDao = new CategoryDao();
    private SaleDao saleDao = new SaleDao();
    private InventoryDao inventoryDao = new InventoryDao();
    private DescriptionDao descriptionDao = new DescriptionDao();
    public List<CartProduct> getProductList(double weight) throws SQLException {
        List<CartProduct> productList = new ArrayList<>(); // Khởi tạo danh sách sản phẩm

        // Câu SQL với tham số "?"
        PreparedStatement ps = dao.conn.prepareStatement(
                "SELECT " +
                        "    p.id AS productID, " +
                        "    p.productName, " +
                        "    i.imageData AS productImage, " +
                        "    pv.weight AS productWeight, " +
                        "    pv.quantity AS productQuantity, " +
                        "    pv.price AS productPrice, " +
                        "    (pv.price * pv.quantity) AS totalPrice, " +  // Dấu phẩy thiếu
                        "    s.salePercent " +                          // Dòng này tiếp nối
                        "FROM " +
                        "    products p " +
                        "JOIN " +
                        "    product_variants pv ON p.id = pv.idProduct " +
                        "LEFT JOIN " +
                        "    Images i ON p.id = i.idProduct " +
                        "INNER JOIN " +
                        "    sales s ON s.idVariant = pv.id " +
                        "WHERE " +
                        "    pv.weight = ?;"
        );


// Truyền giá trị "weight" vào tham số ?
        ps.setDouble(1, weight);

// Thực thi câu lệnh SQL
        ResultSet rs = ps.executeQuery();

// Tạo danh sách Product để lưu kết quả


// Xử lý kết quả trả về từ ResultSet
        while (rs.next()) {
            // Lấy các giá trị từ ResultSet
            String productID = rs.getString("productID"); // id của sản phẩm
            String productName = rs.getString("productName"); // tên sản phẩm
            String productImage = rs.getString("productImage"); // ảnh sản phẩm
            int productWeight = rs.getInt("productWeight"); // trọng lượng sản phẩm
            int productQuantity = rs.getInt("productQuantity"); // số lượng sản phẩm
            double productPrice = rs.getDouble("productPrice"); // giá sản phẩm
            double totalPrice = rs.getDouble("totalPrice"); // tổng tiền sản phẩm (giá * số lượng)
            double salePercent = rs.getDouble("salePercent");

            // Tạo đối tượng Product và thêm vào danh sách
            CartProduct product   =   new CartProduct(productID,productName,productPrice,1,productImage,productWeight,totalPrice,salePercent);

            productList.add(product);
        }




        return productList;
    }
    public CartProduct getById(String id, int weight) throws SQLException {
        List<CartProduct> result = getProductList(weight);

        for (CartProduct pro:
                result) {

            if(pro.getId().equals(id) &&  pro.weight == weight  ){

                return pro;
            }
        }
        System.out.println("San pham khong ton tai");
        return null;
    }
    public int getProductVariantCountByIdAndWeight(int productId, float weight) {
        String query = "SELECT SUM(quantity) FROM product_variants WHERE idProduct = ? AND weight = ?";
        try (PreparedStatement statement = dao.conn.prepareStatement(query)) {
            statement.setInt(1, productId);
            statement.setFloat(2, weight);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1); // trả về số lượng bản ghi phù hợp
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // trả về 0 nếu không tìm thấy bản ghi nào
    }
    public int getTotalProducts() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM products";

        PreparedStatement stmt = dao.conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        }

        return 0;
    }
    public ListProduct getListProductByPage(int page, int itemsPerPage) throws SQLException {
        ListProduct list = new ListProduct();
        String query = """
                SELECT
                    p.id AS ProductID,
                    p.productName,
                    MIN(pv.price) AS MinPrice,
                    MAX(pv.price) AS MaxPrice,
                    img1.imageData AS Image1,
                    img2.imageData AS Image2
                FROM
                    products p
                LEFT JOIN product_variants pv ON p.id = pv.idProduct
                LEFT JOIN (
                    SELECT i1.idProduct, i1.imageData
                    FROM Images i1
                    WHERE (
                        SELECT COUNT(*) 
                        FROM Images i2 
                        WHERE i2.idProduct = i1.idProduct AND i2.id <= i1.id
                    ) = 1
                ) img1 ON p.id = img1.idProduct
                LEFT JOIN (
                    SELECT i1.idProduct, i1.imageData
                    FROM Images i1
                    WHERE (
                        SELECT COUNT(*) 
                        FROM Images i2 
                        WHERE i2.idProduct = i1.idProduct AND i2.id <= i1.id
                    ) = 2
                ) img2 ON p.id = img2.idProduct
                GROUP BY
                    p.id, p.productName, img1.imageData, img2.imageData
                LIMIT ? OFFSET ?;
            """;
        int offset = (page - 1) * itemsPerPage;

        try (PreparedStatement stmt = dao.conn.prepareStatement(query)) {
            stmt.setInt(1, itemsPerPage);
            stmt.setInt(2, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("ProductID");
                    String productName = rs.getString("productName");
                    int minPrice = rs.getInt("MinPrice");
                    int maxPrice = rs.getInt("MaxPrice");
                    String image1 = rs.getString("Image1");
                    String image2 = rs.getString("Image2");

                    list.addProduct(productId, productName, image1, image2, minPrice, maxPrice);
                }
            }
        }
        return list;
    }
    public Products getProductDetail(String idProduct) throws SQLException {
        Products pro = new Products();
        String sql = "SELECT "
                + "p.productName, "
                + "p.idCategory, "

                + "MIN(pv.price) AS minPrice, "
                + "MAX(pv.price) AS maxPrice, "
                + "pv.productDescription, "
                + "MAX(CASE WHEN i.row_num = 1 THEN i.imageData END) AS image1, "
                + "MAX(CASE WHEN i.row_num = 2 THEN i.imageData END) AS image2, "
                + "MAX(CASE WHEN i.row_num = 3 THEN i.imageData END) AS image3, "
                + "MAX(CASE WHEN i.row_num = 4 THEN i.imageData END) AS image4 "
                + "FROM products p "
                + "JOIN product_variants pv ON p.id = pv.idProduct "
                + "LEFT JOIN ( "
                + "    SELECT i.idProduct, i.imageData, ROW_NUMBER() OVER (PARTITION BY i.idProduct ORDER BY i.id) AS row_num "
                + "    FROM Images i "
                + ") i ON p.id = i.idProduct AND i.row_num <= 4 "
                + "WHERE p.id = ? "  // Tham số điều kiện p.id
                + "GROUP BY p.id, p.productName, pv.productDescription;";
        PreparedStatement statement = dao.conn.prepareStatement(sql);
        statement.setString(1, idProduct);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {

            String productName = resultSet.getString("productName");
            int idCate = resultSet.getInt("idCategory");
            double minPrice = resultSet.getDouble("minPrice");
            double maxPrice = resultSet.getDouble("maxPrice");
            String productDescription = resultSet.getString("productDescription");
            String image1 = resultSet.getString("image1");
            String image2 = resultSet.getString("image2");
            String image3 = resultSet.getString("image3");
            String image4 = resultSet.getString("image4");
            System.out.println(idProduct);
            // In kết quả ra console (hoặc có thể xử lý theo nhu cầu)
            System.out.println("Product Name: " + productName);
            System.out.println("Cate " + idCate);
            System.out.println("Min Price: " + minPrice);
            System.out.println("Max Price: " + maxPrice);
            System.out.println("Description: " + productDescription);
            System.out.println("Image 1: " + image1);
            System.out.println("Image 2: " + image2);
            System.out.println("Image 3: " + image3);
            System.out.println("Image 4: " + image4);
            System.out.println("-----------------------------");
            pro.addProduct(Integer.parseInt(idProduct),idCate,productName,minPrice,maxPrice,image1,image2,image3,image4,productDescription);

        }
        return pro;
    }
    public ListProduct getListProduct() throws SQLException {
        ListProduct list = new ListProduct();
        String query = """
            SELECT
                p.id AS ProductID,
                p.productName,
                MIN(pv.price) AS MinPrice,
                MAX(pv.price) AS MaxPrice,
                img1.imageData AS Image1,
                img2.imageData AS Image2
            FROM
                products p
            LEFT JOIN product_variants pv ON p.id = pv.idProduct
            LEFT JOIN (
                SELECT i1.idProduct, i1.imageData
                FROM Images i1
                WHERE (
                    SELECT COUNT(*) 
                    FROM Images i2 
                    WHERE i2.idProduct = i1.idProduct AND i2.id <= i1.id
                ) = 1
            ) img1 ON p.id = img1.idProduct
            LEFT JOIN (
                SELECT i1.idProduct, i1.imageData
                FROM Images i1
                WHERE (
                    SELECT COUNT(*) 
                    FROM Images i2 
                    WHERE i2.idProduct = i1.idProduct AND i2.id <= i1.id
                ) = 2
            ) img2 ON p.id = img2.idProduct
            GROUP BY
                p.id, p.productName, img1.imageData, img2.imageData;
        """;
        PreparedStatement preparedStatement = dao.conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Xử lý kết quả truy vấn
        System.out.println("ProductID | ProductName | MinPrice | MaxPrice | Image1 | Image2");
        while (resultSet.next()) {
            int productId = resultSet.getInt("ProductID");
            String productName = resultSet.getString("productName");
            int minPrice = resultSet.getInt("MinPrice");
            int maxPrice = resultSet.getInt("MaxPrice");
            String image1 = resultSet.getString("Image1"); // Lấy ảnh dưới dạng BLOB
            String image2 = resultSet.getString("Image2"); // Lấy ảnh dưới dạng BLOB

            // Hiển thị thông tin
            System.out.printf("%d | %s | %d | %d | %s | %s%n",
                    productId,
                    productName,
                    minPrice,
                    maxPrice,
                    image1 != null ? image1 : "NULL",
                    image2 != null ? image2 : "NULL"
            );
            list.addProduct(productId,productName,image1,image2,minPrice,maxPrice);
        }
        return list;

    }
    public ListProduct getFilteredProducts(String filterType, int page, int itemsPerPage) throws SQLException {
        ListProduct list = new ListProduct();
        String query = """
        SELECT
            p.id AS ProductID,
            p.productName,
            MIN(pv.price) AS MinPrice,
            MAX(pv.price) AS MaxPrice,
            img1.imageData AS Image1,
            img2.imageData AS Image2,
            MAX(pv.importDate) AS NewestDate,
            AVG(r.ratingRank) AS AvgRating,
            MAX(s.salePercent) AS MaxSalePercent
        FROM
            products p
        LEFT JOIN product_variants pv ON p.id = pv.idProduct
        LEFT JOIN (
            SELECT i1.idProduct, i1.imageData
            FROM Images i1
            WHERE (
                SELECT COUNT(*)\s
                FROM Images i2\s
                WHERE i2.idProduct = i1.idProduct AND i2.id <= i1.id
            ) = 1
        ) img1 ON p.id = img1.idProduct
        LEFT JOIN (
            SELECT i1.idProduct, i1.imageData
            FROM Images i1
            WHERE (
                SELECT COUNT(*)\s
                FROM Images i2\s
                WHERE i2.idProduct = i1.idProduct AND i2.id <= i1.id
            ) = 2
        ) img2 ON p.id = img2.idProduct
        LEFT JOIN ratings r ON p.id = r.idProduct
        LEFT JOIN sales s ON s.idVariant = pv.id
        GROUP BY
            p.id, p.productName, img1.imageData, img2.imageData
    """;

        // Thêm điều kiện ORDER BY theo filterType
        switch (filterType) {
            case "priceAsc":
                query += " ORDER BY MIN(pv.price) ASC";
                break;
            case "priceDesc":
                query += " ORDER BY MAX(pv.price) DESC";
                break;
            case "newest":
                query += " ORDER BY MAX(pv.importDate) DESC";
                break;
            case "rating":
                query += " ORDER BY AVG(r.ratingRank) DESC";
                break;
            case "sale":
                query += " ORDER BY MAX(s.salePercent) DESC";
                break;
            default:
                query += " ORDER BY p.id ASC"; // Mặc định sắp xếp theo ID
                break;
        }

        // Thêm phân trang
        query += " LIMIT ? OFFSET ?";

        try (PreparedStatement stmt = dao.conn.prepareStatement(query)){
            stmt.setInt(1, itemsPerPage); // Giới hạn số sản phẩm trên mỗi trang
            stmt.setInt(2, (page - 1) * itemsPerPage); // Tính toán OFFSET dựa trên số trang
            try(ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    int productId = resultSet.getInt("ProductID");
                    String productName = resultSet.getString("productName");
                    int minPrice = resultSet.getInt("MinPrice");
                    int maxPrice = resultSet.getInt("MaxPrice");
                    String image1 = resultSet.getString("Image1");
                    String image2 = resultSet.getString("Image2");

                    list.addProduct(productId, productName, image1, image2, minPrice, maxPrice);
                }
            }
        }
        return list;
    }
    public int getCategoryProductCounts(String categoryName) throws SQLException {
        // tính tổng số lượng sản phẩm theo loại
        String query = """
        SELECT COUNT(DISTINCT p.id) AS totalQuantity
                                        FROM products p
                                        JOIN categories c ON p.idCategory = c.id
                                        WHERE c.name = ?
    """;

        try (PreparedStatement stmt = dao.conn.prepareStatement(query)) {
            // tên loại
            stmt.setString(1, categoryName);

            // Thực thi truy vấn
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("totalQuantity");
                }
            }
        }
        return 0;
    }
    public String checkState(int isActive){
        return  isActive == 1 ? "Còn hàng" : isActive == 2 ? "Tạm ngưng" : "Hết hàng";
    }

    public ListProductManage getAllProducts() throws SQLException {
        ListProductManage products = new ListProductManage();
        String query = """
                SELECT 
                    p.id AS ID,
                    p.productName AS 'Tên sản phẩm',
                    pv.price AS 'Giá',
                    pv.weight AS 'Quy cách',
                    pv.quantity AS 'Số lượng',
                    img.imageData AS 'Link ảnh',
                    pv.productDescription AS 'Mô tả',
                    s.supplierName AS 'Nhà cung cấp',
                    c.name AS 'Phân loại',
                    pv.isActive
                FROM 
                    products p
                LEFT JOIN product_variants pv ON p.id = pv.idProduct
                LEFT JOIN Images img ON p.id = img.idProduct
                LEFT JOIN suppliers s ON p.idSupplier = s.id
                LEFT JOIN categories c ON p.idCategory = c.id 
                GROUP BY 
                    pv.id;
                """;


             PreparedStatement statement = dao.conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Tên sản phẩm");
                int price = resultSet.getInt("Giá");
                float weight = resultSet.getFloat("Quy cách");
                int quantity = resultSet.getInt("Số lượng");
                String image = resultSet.getString("Link ảnh");
                String productDescription = resultSet.getString("Mô tả");
                String supplierName = resultSet.getString("Nhà cung cấp");
                String category = resultSet.getString("Phân loại");
                int isActive = resultSet.getInt("isActive");
String temp = checkState(isActive)  ;
                System.out.println(temp);

                products.addProduct(new Product(id,name,price,weight,quantity,image,productDescription,supplierName,category,isActive,temp));
            }


        return products;
    }
    public boolean updateProduct(int idProduct, double price, int quantity, String productDescription, double weight,
                                 boolean isActive) {
        String sql = "UPDATE product_variants " +
                "SET price = ?, quantity = ?, productDescription = ?, weight = ?, isActive = ? " +
                "WHERE idProduct = ? AND weight = ?";

        try (PreparedStatement preparedStatement = dao.conn.prepareStatement(sql)) {
            // Gán giá trị cho các tham số trong câu truy vấn
            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, quantity);
            preparedStatement.setString(3, productDescription);
            preparedStatement.setDouble(4, weight);
            preparedStatement.setBoolean(5, isActive);
            preparedStatement.setInt(6, idProduct);
            preparedStatement.setDouble(7, weight);

            // Thực thi câu lệnh cập nhật
            int rowsAffected = preparedStatement.executeUpdate();

            // Nếu có ít nhất một dòng bị ảnh hưởng, trả về true
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Xử lý lỗi nếu có (in ra thông báo lỗi)
            e.printStackTrace();
            return false;
        }
    }
    public void deleteProductVariant(int idProduct, float weight) throws SQLException {
        // Câu truy vấn SQL để xóa bản ghi trong bảng sales liên quan đến product_variant
        String deleteSalesQuery = "DELETE FROM sales WHERE idVariant IN (SELECT id FROM product_variants WHERE idProduct = ? AND weight = ?)";

        // Câu truy vấn SQL để xóa bản ghi trong bảng product_variants
        String deleteProductVariantQuery = "DELETE FROM product_variants WHERE idProduct = ? AND weight = ?";


             PreparedStatement deleteSalesStmt = dao.conn.prepareStatement(deleteSalesQuery);
             PreparedStatement deleteProductVariantStmt = dao.conn.prepareStatement(deleteProductVariantQuery);

            // Thiết lập tham số cho câu truy vấn xóa sales
            deleteSalesStmt.setInt(1, idProduct);
            deleteSalesStmt.setFloat(2, weight);

            // Thiết lập tham số cho câu truy vấn xóa product_variant
            deleteProductVariantStmt.setInt(1, idProduct);
            deleteProductVariantStmt.setFloat(2, weight);

            // Thực thi câu truy vấn xóa trong bảng sales
            deleteSalesStmt.executeUpdate();

            // Thực thi câu truy vấn xóa trong bảng product_variants
            deleteProductVariantStmt.executeUpdate();

            System.out.println("Xóa thành công product_variant và các bản ghi liên quan trong sales.");
        }
    public ListProduct searchProductsByName(String proName) throws SQLException {
        ListProduct list = new ListProduct();
        String query = """
        SELECT
                p.id AS ProductID,
                p.productName,
                MIN(pv.price) AS MinPrice,
                MAX(pv.price) AS MaxPrice,
                img1.imageData AS Image1,
                img2.imageData AS Image2
            FROM
                products p
            LEFT JOIN product_variants pv ON p.id = pv.idProduct
            LEFT JOIN (
                SELECT i1.idProduct, i1.imageData
                FROM Images i1
                WHERE (
                    SELECT COUNT(*)
                    FROM Images i2
                    WHERE i2.idProduct = i1.idProduct AND i2.id <= i1.id
                ) = 1
            ) img1 ON p.id = img1.idProduct
            LEFT JOIN (
                SELECT i1.idProduct, i1.imageData
                FROM Images i1
                WHERE (
                    SELECT COUNT(*)
                    FROM Images i2
                    WHERE i2.idProduct = i1.idProduct AND i2.id <= i1.id
                ) = 2
            ) img2 ON p.id = img2.idProduct
            WHERE p.productName LIKE ?
            GROUP BY
                p.id, p.productName, img1.imageData, img2.imageData
            ORDER BY p.productName
    """;

        try (PreparedStatement stmt = dao.conn.prepareStatement(query)){
            stmt.setString(1, "%"+proName+"%");
            try(ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    int productId = resultSet.getInt("ProductID");
                    String productName = resultSet.getString("productName");
                    int minPrice = resultSet.getInt("MinPrice");
                    int maxPrice = resultSet.getInt("MaxPrice");
                    String image1 = resultSet.getString("Image1");
                    String image2 = resultSet.getString("Image2");

                    list.addProduct(productId, productName, image1, image2, minPrice, maxPrice);
                }
            }
        }
        return list;
    }
    public TopProduct getLatestProducts() {
        TopProduct items = new TopProduct();
        ConnDB dao = new ConnDB();
        String query = """
                SELECT 
                    p.id AS productId,
                    p.productName,
                    COALESCE(MIN(pv.price), 0) AS minPrice,
                    COALESCE(MAX(pv.price), 0) AS maxPrice,
                    (SELECT i.imageData 
                     FROM Images i 
                     WHERE i.idProduct = p.id 
                     LIMIT 1) AS image
                FROM 
                    products p
                LEFT JOIN 
                    product_variants pv ON p.id = pv.idProduct
                WHERE 
                    p.isActive = 1
                GROUP BY 
                    p.id, p.productName
                ORDER BY 
                    MAX(pv.importDate) DESC, p.id
                LIMIT 5;
                """;

        try (Connection conn = dao.getConn();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int productId = rs.getInt("productId");
                String name = rs.getString("productName");
                double minPrice = rs.getDouble("minPrice");
                double maxPrice = rs.getDouble("maxPrice");
                String image = rs.getString("image");
                items.addProduct(new Models.TopProductBuy.Product(productId,name, (int) minPrice, (int) maxPrice,image));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }
    public ListProduct getProductsByPriceRange(double minPrice, double maxPrice) throws SQLException {
        ListProduct list = new ListProduct();

        String query = """
        SELECT 
            p.id AS ProductID, 
            p.productName, 
            MIN(pv.price) AS MinPrice, 
            MAX(pv.price) AS MaxPrice, 
            img1.imageData AS Image1, 
            img2.imageData AS Image2 
        FROM 
            products p 
        JOIN 
            product_variants pv ON p.id = pv.idProduct 
        LEFT JOIN (
            SELECT i1.idProduct, i1.imageData
            FROM Images i1
            WHERE (
                SELECT COUNT(*) 
                FROM Images i2 
                WHERE i2.idProduct = i1.idProduct AND i2.id <= i1.id
            ) = 1
        ) img1 ON p.id = img1.idProduct
        LEFT JOIN (
            SELECT i1.idProduct, i1.imageData
            FROM Images i1
            WHERE (
                SELECT COUNT(*) 
                FROM Images i2 
                WHERE i2.idProduct = i1.idProduct AND i2.id <= i1.id
            ) = 2
        ) img2 ON p.id = img2.idProduct
        WHERE 
            pv.price BETWEEN ? AND ? 
        GROUP BY 
            p.id, p.productName, img1.imageData, img2.imageData;
    """;

        try (PreparedStatement stmt = dao.conn.prepareStatement(query)) {
            stmt.setDouble(1, minPrice); // Tham số giá thấp nhất
            stmt.setDouble(2, maxPrice); // Tham số giá cao nhất

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("ProductID");
                    String productName = rs.getString("productName");
                    int minProductPrice = rs.getInt("MinPrice");
                    int maxProductPrice = rs.getInt("MaxPrice");
                    String image1 = rs.getString("Image1");
                    String image2 = rs.getString("Image2");

                    // Thêm sản phẩm vào danh sách
                    list.addProduct(productId, productName, image1, image2, minProductPrice, maxProductPrice);
                }
            }
        }

        return list;
    }
    public void updateProductAndVariant(int idProduct, double weight, double price, int quantity,
                                        String productDescription, int idCategory, int idSupplier, int isActive) throws SQLException {
        ConnDB dao = new ConnDB();
        String updateVariantQuery = "UPDATE product_variants " +
                "SET weight = ?, price = ?, quantity = ?, productDescription = ?, importDate = NOW(), isActive = ? " +
                "WHERE idProduct = ? AND weight = ?";

        String updateProductQuery = "UPDATE products " +
                "SET idCategory = ?, idSupplier = ? " +
                "WHERE id = ?";

        try (Connection connection = dao.getConn()) {
            connection.setAutoCommit(false); // Bắt đầu giao dịch

            try (PreparedStatement ps1 = connection.prepareStatement(updateVariantQuery);
                 PreparedStatement ps2 = connection.prepareStatement(updateProductQuery)) {

                // Cập nhật `product_variants`
                ps1.setDouble(1, weight);
                ps1.setDouble(2, price);
                ps1.setInt(3, quantity);
                ps1.setString(4, productDescription);
                ps1.setInt(5, isActive); // Thêm isActive
                ps1.setInt(6, idProduct);
                ps1.setDouble(7, weight);
                ps1.executeUpdate();

                // Cập nhật `products`
                ps2.setInt(1, idCategory);
                ps2.setInt(2, idSupplier);
                ps2.setInt(3, idProduct);
                ps2.executeUpdate();

                connection.commit(); // Xác nhận giao dịch
            } catch (SQLException e) {
                connection.rollback(); // Khôi phục nếu có lỗi
                throw e;
            }
        }
    }
    public void getAllProductIds(List<ProductDTO> variants) {
        ConnDB dao = new ConnDB();
        String query = "SELECT id, productName, image FROM products";

        // ✅ Tạo cache để dùng lại trong vòng lặp
        Map<Integer, String> categoryCache = new HashMap<>();


        try (Connection conn = dao.getConn();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("productName");
                String url = rs.getString("image");

                getVariantsByProductId(id, variants, name, url, categoryCache);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProductDTO> getVariantsByProductId(
            int idProduct,
            List<ProductDTO> variants,
            String name,
            String image,
            Map<Integer, String> categoryCache) {

        ConnDB dao = new ConnDB();
        String sql = "SELECT * FROM product_variants WHERE idProduct = ?";

        try (Connection conn = dao.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduct);

            // 🔍 Cache category name
            String cate = categoryCache.computeIfAbsent(idProduct, k -> {
                try {
                    return categoryDao.getNameCategoriesByIdProduct(String.valueOf(k));
                } catch (SQLException e) {
                    e.printStackTrace();
                    return "Unknown";
                }
            });

            Map<String, Double> saleMap = saleDao.getSalesByProductId(idProduct);
            Map<String, Integer> soldMap = inventoryDao.getAllSoldQuantities();

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ProductDTO variant = new ProductDTO();
                    String idVariant = rs.getString("id");

                    variant.setId(idVariant);
                    variant.setSale(saleMap.getOrDefault(idVariant, 0.0)); // Set sale
                    variant.setImage(image);
                    variant.setCategory(cate);
                    variant.setName(name);
                    variant.setIdProduct(rs.getString("idProduct"));
                    variant.setWeight((int) rs.getFloat("weight"));
                    String key = rs.getInt("idProduct") + "_" + (int) rs.getFloat("weight");
                    int sold = soldMap.getOrDefault(key, 0);
                    int imported = rs.getInt("quantity");
                    variant.setInventory(imported - sold);
                    variant.setPrice(rs.getInt("price"));
                    variant.setIsActive(rs.getString("isActive"));
                    variant.setImportDate(rs.getString("importDate"));
                    variant.setQuantity(rs.getInt("quantity"));

                    variants.add(variant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return variants;
    }

    public ProductDTO getProductByIDandWeight(String idProduct, float weight) {
        ProductDTO productDTO = new ProductDTO();
        String sql = "SELECT \n" +
                "    p.id AS id,\n" +
                "    p.productName AS name,\n" +
                "    p.image,\n" +
                "    c.name AS category,\n" +
                "    pv.price,\n" +
                "    pv.weight,\n" +
                "    pv.quantity AS inventory,\n" +
                "    IF(pv.isActive = 1, 'Active', 'Inactive') AS status,\n" +
                "    pv.quantity,\n" +
                "    IF(pv.isActive = 1, 'Active', 'Inactive') AS isActive,\n" +
                "    pv.id AS idVariant,\n" +
                "    pv.importDate\n" + // Add importDate here
                "FROM \n" +
                "    products p\n" +
                "JOIN \n" +
                "    categories c ON p.idCategory = c.id\n" +
                "JOIN \n" +
                "    product_variants pv ON p.id = pv.idProduct\n" +
                "WHERE \n" +
                "    pv.idProduct = ?  \n" +
                "    AND pv.weight = ?;\n";

        ConnDB dao = new ConnDB();
        try (Connection conn = dao.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idProduct);
            stmt.setDouble(2, weight);
            Map<String, Integer> soldMap = inventoryDao.getAllSoldQuantities();

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Set information for ProductDTO
                    productDTO.setId(rs.getString("idVariant"));
                    productDTO.setName(rs.getString("name"));
                    productDTO.setImage(rs.getString("image"));
                    productDTO.setCategory(rs.getString("category"));
                    productDTO.setPrice(rs.getDouble("price"));
                    productDTO.setWeight(rs.getInt("weight"));
                    productDTO.setStatus(rs.getString("status"));
                    productDTO.setQuantity(rs.getInt("quantity"));
                    productDTO.setIsActive(rs.getString("isActive"));
                    productDTO.setIdProduct(idProduct);
                    productDTO.setImportDate(String.valueOf(rs.getTimestamp("importDate")));
                    String key = idProduct + "_" + (int) rs.getFloat("weight");
                    int sold = soldMap.getOrDefault(key, 0);
                    int imported = rs.getInt("quantity");
                    productDTO.setInventory(imported - sold);
                  SaleDTO sale = saleDao.getFullSaleByIdVariant(rs.getString("idVariant"));
                  List<DescriptionDTO> descriptionDTOS = descriptionDao.getAllDescriptionByIdProduct(idProduct);
                  List<PromotionDTO> promotionDTOS = saleDao.getFullPromotionByIdVariant(rs.getString("idVariant"));
                    productDTO.setSaleDTO(sale);
                    productDTO.setPromotionDTOS(promotionDTOS);
                    productDTO.setDescriptionDTOS(descriptionDTOS);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productDTO;
    }




    public static void main(String[] args) throws SQLException {
        ProductDao s = new ProductDao();
        //  System.out.println(s.getCategoryProductCounts("Nấm Khô"));
        //  System.out.println(s.getAllProducts().getItems());
        // s.deleteProductVariant(64,200);
        //  System.out.println(s.getProductDetail(String.valueOf(60)));
//        System.out.println(s.getAllProducts().getItems());
//        List<ProductDTO> variants = new ArrayList<>();
//        s.getAllProductIds(variants);
//    }
        System.out.println(s.getProductByIDandWeight(String.valueOf(44),500));
    }
}
