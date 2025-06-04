package Controller;

import Services.ServiceWishlist;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteWishlist")
public class DeleteWishlist extends HttpServlet {
    private ServiceWishlist serviceWishlist = new ServiceWishlist();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(true);
        Integer idUser = (Integer) session.getAttribute("idUser");

        if (idUser == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("{\"success\": false, \"message\": \"User not logged in\"}");
            return;
        }

        try {
            int idProduct = Integer.parseInt(req.getParameter("productId"));
            boolean success = serviceWishlist.deleteWishlist(idUser, idProduct);
            
            if (success) {
                resp.getWriter().write("{\"success\": true, \"message\": \"Product removed from wishlist\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"success\": false, \"message\": \"Failed to remove product\"}");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"success\": false, \"message\": \"Invalid product ID\"}");
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"success\": false, \"message\": \"Database error\"}");
        }
    }
} 