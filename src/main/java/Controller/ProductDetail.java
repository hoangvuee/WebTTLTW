package Controller;

import Dao.CommentDao;
import Models.Comment.Comments;
import Models.Products.Products;
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
        value = "/product_detail"
)
public class ProductDetail extends HttpServlet {
    Products pro = new Products();
    ServiceProduct serviceProduct = new ServiceProduct();
    CommentDao commentDao = new CommentDao();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            pro = serviceProduct.getProductDetail(id);
            
            // Load comments for this product
            if (id != null && !id.isEmpty()) {
                int productId = Integer.parseInt(id);
                Comments comments = commentDao.getCommentsByProductId(productId);
                HttpSession session = req.getSession(true);
                session.setAttribute("comments", comments);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("product_detail", pro);
        session.setAttribute("idCate", pro.getItems().get(0).getIdCategory());
        resp.sendRedirect("getProductSimilar");
    }
}
