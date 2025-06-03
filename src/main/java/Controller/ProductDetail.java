package Controller;

import Dao.CommentDao;
import Dao.OrderDao;
import Models.Comment.Comments;
import Models.Feedback.Feedback;
import Models.ListUser.User;
import Models.Products.Products;
import Services.ServiceFeedback;
import Services.ServiceProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        value = "/product_detail"
)
public class ProductDetail extends HttpServlet {
    Products pro = new Products();
    ServiceProduct serviceProduct = new ServiceProduct();
    CommentDao commentDao = new CommentDao();
    OrderDao orderDao = new OrderDao();
    ServiceFeedback serviceFeedback = new ServiceFeedback();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id == null || id.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ");
            return;
        }

        try {
            // Lấy thông tin sản phẩm
            pro = serviceProduct.getProductDetail(id);
            if (pro == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy sản phẩm");
                return;
            }

            // Lấy danh sách feedbacks
            List<Feedback> feedbacks = serviceFeedback.getFeedbacksByProductId(Integer.parseInt(id));

            // Kiểm tra nếu feedbacks == null thì gán list rỗng
            if (feedbacks == null) {
                feedbacks = new ArrayList<>();
            }
            
            // Load comments for this product
            int productId = Integer.parseInt(id);
            Comments comments = commentDao.getCommentsByProductId(productId);
            
            // Check if user has purchased this product
            HttpSession session = req.getSession(true);
            User currentUser = (User) session.getAttribute("user");
            if (currentUser != null) {
                boolean hasPurchased = orderDao.hasUserPurchasedProduct(currentUser.getId(), productId);
                session.setAttribute("hasPurchasedProduct", hasPurchased);
            } else {
                session.setAttribute("hasPurchasedProduct", false);
            }

            // Lưu vào session
            session.setAttribute("product_detail", pro);
            session.setAttribute("feedbacks", feedbacks);
            session.setAttribute("comments", comments);

            // Kiểm tra xem product có items không để tránh lỗi NullPointerException
            if (pro.getItems() != null && !pro.getItems().isEmpty()) {
                session.setAttribute("idCate", pro.getItems().get(0).getIdCategory());
            }

            // Chuyển hướng đến trang sản phẩm tương tự
            resp.sendRedirect("getProductSimilar");

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xử lý dữ liệu");
        }
    }
}
