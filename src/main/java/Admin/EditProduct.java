package Admin;

import Services.ServiceProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        value = "/admin/editProduct"
)
public class EditProduct extends HttpServlet {
    ServiceProduct serviceProduct = new ServiceProduct();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productId = Integer.parseInt(req.getParameter("productId"));
        String productName = req.getParameter("productName");
        double productPrice = Double.parseDouble(req.getParameter("productPrice"));
        int productQuantity = Integer.parseInt(req.getParameter("productQuantity"));
        double productWeight = Double.parseDouble(req.getParameter("productWeight"));
        String productDescription = req.getParameter("productDescription");
        int idSupplier = Integer.parseInt(req.getParameter("productSupplier"));
        int idCategory = Integer.parseInt(req.getParameter("productCategory"));
        int  productStatus = Integer.parseInt(req.getParameter("productStatus"));
        System.out.println("productId: " + productId);
        System.out.println("productName: " + productName);
        System.out.println("productPrice: " + productPrice);
        System.out.println("productQuantity: " + productQuantity);
        System.out.println("productWeight: " + productWeight);
        System.out.println("productDescription: " + productDescription);
        System.out.println("productSupplier: " + idSupplier);
        System.out.println("productCategory: " + idCategory);
        System.out.println("productStatus: " + productStatus);


       // serviceProduct.updateProduct(productId,productPrice,productQuantity,productDescription,productWeight, Boolean.parseBoolean(productStatus));
        try {
            serviceProduct.updateProductAndVariant(productId,productWeight,productPrice,productQuantity,productDescription,idCategory,idSupplier,productStatus);
            resp.sendRedirect("admin/getAllProduct");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
