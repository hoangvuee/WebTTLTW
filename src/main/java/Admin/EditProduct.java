package Admin;

import DTO.ProductDTO;
import Models.Description.Description;
import Models.Sale.Sale;
import Models.User.User;
import Models.inforTransaction.Product;
import Sercurity.JwtUtil;
import Services.*;
import Utils.LogActions;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/admin/editProduct")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100    // 100 MB
)

public class EditProduct extends HttpServlet {
    private static final Gson gson = new Gson();
    private final ServiceAddProduct productService = new ServiceAddProduct();
    private ServiceSale serviceSale = new ServiceSale();
    private ServiceDescription serviceDescription = new ServiceDescription();
    private ServiceImage serviceImage  = new ServiceImage();

    private ServiceProduct serviceProduct = new ServiceProduct();
    ServiceRole serviceRole = new ServiceRole();
    ServiceUser serviceUser = new ServiceUser();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy id và weight từ request
        String id = req.getParameter("id");
        String weight = req.getParameter("weight");
        ProductDTO productDTO = new ProductDTO();

        // Gọi service để lấy thông tin sản phẩm dựa trên id và weight
        productDTO = serviceProduct.getProductByIDandWeight(id, Float.parseFloat(weight));

        // Chuyển dữ liệu sản phẩm thành JSON để trả về cho client
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(productDTO);

        // Cấu hình response trả về cho client
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");
        HttpSession session = req.getSession();
        String ipAddress = req.getRemoteAddr();
        String userAgent = req.getHeader("User-Agent");

        try {
            // Lấy các tham số từ form
            int id = Integer.parseInt(req.getParameter("id"));
            int productId = Integer.parseInt(req.getParameter("productId"));
            int weight = Integer.parseInt(req.getParameter("weight"));
            String name = req.getParameter("name");
            String sku = req.getParameter("sku");
            String barcode = req.getParameter("barcode");
            String active  = req.getParameter("active");
            int stock = Integer.parseInt(req.getParameter("stock"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int category = Integer.parseInt(req.getParameter("category"));
            int supplier = Integer.parseInt(req.getParameter("supplier"));
            String spec = req.getParameter("spec");
            int price = Integer.parseInt(req.getParameter("price"));
            int comparePrice = Integer.parseInt(req.getParameter("comparePrice"));
            String inventoryPolicy = req.getParameter("inventoryPolicy");
            String salePercentStr = req.getParameter("salePercent");
            float discountPrice = Float.parseFloat(req.getParameter("discountPrice"));
            String discountStart = req.getParameter("discountStart");
            String discountEnd = req.getParameter("discountEnd");

            String promotions = req.getParameter("promotions");
            Part filePart = req.getPart("image");

            System.out.println("id: " + id);
            System.out.println("productId: " + productId);
            System.out.println("weight: " + weight);
            System.out.println("name: " + name);
            System.out.println("sku: " + sku);
            System.out.println("barcode: " + barcode);
            System.out.println("stock: " + stock);
            System.out.println("quantity: " + quantity);
            System.out.println("category: " + category);
            System.out.println("supplier: " + supplier);
            System.out.println("spec: " + spec);
            System.out.println("price: " + price);
            System.out.println("comparePrice: " + comparePrice);
            System.out.println("inventoryPolicy: " + inventoryPolicy);
            System.out.println("salePercentStr: " + salePercentStr);
            System.out.println("discountPrice: " + discountPrice);
            System.out.println("discountStart: " + discountStart);
            System.out.println("discountEnd: " + discountEnd);

            System.out.println("promotions: " + promotions);

            String descriptionsJson = ServiceParameter.getParameter(req, "descriptions");
            Description[] descriptions = gson.fromJson(descriptionsJson, Description[].class);
            if (descriptions != null) {
                serviceDescription.addProductDescriptions(productId,descriptions);
            }
            String salesJson = ServiceParameter.getParameter(req, "promotions");
            Sale[] sales = gson.fromJson(salesJson, Sale[].class);
            if (sales != null) {
                serviceSale.insertSales(id,sales);
            }
            System.out.println(active);
            boolean activeTemp = active.equals("true");

//update Product
            if (filePart == null || filePart.getSubmittedFileName() == null || filePart.getSubmittedFileName().isEmpty()) {
                serviceProduct.updateProduct(productId, name, category, supplier);
            } else {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                serviceProduct.updateProduct(productId, name, category, supplier, fileName);
                ServiceFile.saveFileToImgFolder(filePart,getServletContext());

            }
            User user = (User) session.getAttribute("userInfor");
            ServiceRole serviceRole = new ServiceRole();
            LogService.logUserActivity(
                    user.getUserName(),
                    serviceRole.getRoleNameById(user.getIdRole()),
                    LogActions.EDIT_PRODUCT,
                    "Added product: " + name + " (ID: " + productId + ")",
                    ipAddress,
                    userAgent );

            // Update Variant
            serviceProduct.updateProductDetail(id,productId,weight,price,activeTemp,quantity);
            resp.getWriter().write("Product updated successfully");

    } catch (Exception e) {
        e.printStackTrace();
            int productId = Integer.parseInt(req.getParameter("productId"));
            User user = (User) session.getAttribute("userInfor");
            ServiceRole serviceRole = new ServiceRole();
            LogService.logUserActivity(
                    user.getUserName(),
                    serviceRole.getRoleNameById(user.getIdRole()),
                    LogActions.EDIT_PRODUCT_FAILED,
                    "Edit Product id"+productId+" Failed",
                    ipAddress,
                    userAgent );
        resp.getWriter().write("Failed to update product: " + e.getMessage());
    }
}
}
