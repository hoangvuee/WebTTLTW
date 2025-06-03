package Controller;

import Dao.CommentDao;
import Dao.ProductDao;
import Models.Comment.Comment;
import Models.Comment.Comments;
import Models.ListUser.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "CommentController", urlPatterns = {
        "/comments",
        "/add-comment",
        "/delete-comment",
        "/all-comments"
})
public class CommentController extends HttpServlet {
    private CommentDao commentDao;
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        super.init();
        commentDao = new CommentDao();
        productDao = new ProductDao();
        
        // Create comments table if it doesn't exist
        commentDao.createCommentsTableIfNotExists();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        
        switch (action) {
            case "/comments":
                getProductComments(request, response);
                break;
            case "/all-comments":
                getAllComments(request, response);
                break;
            case "/delete-comment":
                deleteComment(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        
        switch (action) {
            case "/add-comment":
                addComment(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    private void getProductComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Comments comments = commentDao.getCommentsByProductId(productId);
            
            HttpSession session = request.getSession();
            session.setAttribute("comments", comments);
            
            // Redirect back to product detail page
            response.sendRedirect("product_detail?id=" + productId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
        }
    }

    private void getAllComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Comments comments = commentDao.getAllComments();
        
        HttpSession session = request.getSession();
        session.setAttribute("allComments", comments);
        
        // Forward to all comments page
        request.getRequestDispatcher("all_comments.jsp").forward(request, response);
    }

    private void addComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the current user from session
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("user");
            
            if (currentUser == null) {
                // User not logged in, redirect to login page
                response.sendRedirect("Account/login.jsp");
                return;
            }
            
            // Get parameters from request
            int productId = Integer.parseInt(request.getParameter("productId"));
            String content = request.getParameter("content");
            int rating = Integer.parseInt(request.getParameter("rating"));
            
            // Create a new comment
            Comment comment = new Comment();
            comment.setProductId(productId);
            comment.setUserId(currentUser.getId());
            comment.setUserName(currentUser.getFullName());
            comment.setContent(content);
            comment.setRating(rating);
            
            // Add the comment to the database
            boolean success = commentDao.addComment(comment);
            
            if (success) {
                // Get updated comments for the product
                Comments comments = commentDao.getCommentsByProductId(productId);
                session.setAttribute("comments", comments);
            }
            
            // Redirect back to product detail page
            response.sendRedirect("product_detail?id=" + productId);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
        }
    }

    private void deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the current user from session
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("user");
            
            if (currentUser == null) {
                // User not logged in, redirect to login page
                response.sendRedirect("Account/login.jsp");
                return;
            }
            
            int commentId = Integer.parseInt(request.getParameter("commentId"));
            int productId = Integer.parseInt(request.getParameter("productId"));
            
            // Delete the comment
            boolean success = commentDao.deleteComment(commentId);
            
            if (success) {
                // Get updated comments for the product
                Comments comments = commentDao.getCommentsByProductId(productId);
                session.setAttribute("comments", comments);
            }
            
            // Redirect back to product detail page or all comments page
            String redirectUrl = request.getParameter("redirect");
            if ("all".equals(redirectUrl)) {
                response.sendRedirect("all-comments");
            } else {
                response.sendRedirect("product_detail?id=" + productId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
        }
    }
}
