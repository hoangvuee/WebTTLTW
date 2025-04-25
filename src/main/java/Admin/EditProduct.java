package Admin;

import DTO.ProductDTO;
import Services.ServiceProduct;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/editProduct")
public class EditProduct extends HttpServlet {
    private ServiceProduct serviceProduct = new ServiceProduct();

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
        // Lấy các tham số từ form (ví dụ: thông tin sản phẩm đã được sửa)
//        String id = req.getParameter("id");
//        String name = req.getParameter("name");
//        String weight = req.getParameter("weight");
//
//        // Cập nhật thông tin sản phẩm
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setId(id);
//        productDTO.setName(name);
//        productDTO.setWeight( Integer.parseInt(weight));

        // Giả sử ServiceProduct có một phương thức để cập nhật sản phẩm
        //serviceProduct.updateProduct(productDTO);

        // Trả về phản hồi nếu cần
        resp.getWriter().write("Product updated successfully");
    }
}
