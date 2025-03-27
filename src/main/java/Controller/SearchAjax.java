package Controller;

import Dao.ProductDao;
import Models.Product.ListProduct;
import Models.Product.Product;
import Services.ServiceProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/SearchAjax")
public class SearchAjax extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        
        String search = req.getParameter("productName");
        PrintWriter out = resp.getWriter();
        
        // Thêm debug log
        System.out.println("Search term received: " + search);
        
        if (search == null || search.trim().isEmpty()) {
            out.println("No search term provided");
            return;
        }

        ServiceProduct serviceProduct = new ServiceProduct();
        try {
            ListProduct listProduct = serviceProduct.searchProductsByName(search);
            
            // Debug số lượng kết quả
            System.out.println("Number of products found: " + 
                (listProduct != null && listProduct.getItems() != null ? listProduct.getItems().size() : 0));
            
            if (listProduct == null || listProduct.getItems() == null || listProduct.getItems().isEmpty()) {
                out.println("No products found");
                return;
            }

            for (Product o : listProduct.getItems()) {
                String html = "<div class=\"d-flex ps-5\" style=\"width: 100%;\">" +
                    "<ul class=\"nav d-flex flex-wrap gap-4\" style=\"width: 100%\">" +
                    "<li class=\"nav-item\" style=\"width: 30%; height: 500px;\">" +
                    "<div class=\"h-100 d-inline-block border rounded product-item shadow-sm\" style=\"height: auto; position: relative; overflow: hidden; background: #fff; border-radius: 10px;\">" +
                    
                    "<!-- Hình ảnh sản phẩm -->" +
                    "<div class=\"img_product position-relative\">" +
                    "<a href=\"product_detail?id=" + o.getId() + "\" style=\"text-decoration: none; color: inherit;\">" +
                    "<img src=\"" + o.getFileName1() + "\" class=\"default-img\" alt=\"Product Image\" style=\"width: 100%; height: 300px; border-bottom: 1px solid #eee;\">" +
                    "<img src=\"img/" + o.getFileName2() + "\" class=\"hover-img position-absolute\" alt=\"Hover Image\" style=\"width: 100%; height: auto; top: 0; left: 0; transition: opacity 0.3s ease;\">" +
                    "</a>" +

                    "<!-- Icons hiển thị khi hover -->" +
                    "<div class=\"product-icons position-absolute d-flex gap-3 justify-content-center align-items-center\" style=\"top: 50%; left: 50%; transform: translate(-50%, -50%); transition: opacity 0.5s;\">" +
                    
                    "<!-- Icon giỏ hàng -->" +
                    "<form id=\"cart-form-" + o.getId() + "\" action=\"product_deIcon\" method=\"GET\" style=\"display: none;\">" +
                    "<input type=\"hidden\" name=\"productID\" value=\"" + o.getId() + "\">" +
                    "<input type=\"hidden\" name=\"weight\" class=\"selected-weight\" value=\"\">" +
                    "</form>" +

                    "<a href=\"#\" class=\"fas fa-shopping-cart cart-icon\" " +
                    "style=\"font-size: 20px; background: rgba(0,0,0,0.6); color: white; padding: 10px; border-radius: 50%; text-decoration: none;\" " +
                    "onclick=\"submitCartForm('" + o.getId() + "')\">" +
                    "</a>" +

                    "<div id=\"notification1\" class=\"alert alert-success\" style=\"display: none; position: fixed; top: 20px; right: 20px; z-index: 1000;\">" +
                    "Sản phẩm đã được thêm vào giỏ hàng thành công!" +
                    "</div>" +

                    "<!-- Icon yêu thích -->" +
                    "<a href=\"#\" class=\"fas fa-heart heart-icon\" " +
                    "style=\"font-size: 20px; background: rgba(0,0,0,0.6); color: white; padding: 10px; border-radius: 50%; text-decoration: none;\" " +
                    "onclick=\"addToFavorites('" + o.getName() + "','', '" + o.getId() + "')\">" +
                    "</a>" +
                    "</div>" +
                    "</div>" +

                    "<!-- Thông tin sản phẩm -->" +
                    "<div class=\"p-3 text-center\">" +
                    "<div class=\"product_infor d-flex justify-content-center gap-2 mb-2\">" +
                    "<button data-id=\"" + o.getId() + "\" data-weight=\"200\" class=\"btn btn-sm btn-outline-secondary rounded-pill weight-btn\">200g</button>" +
                    "<button data-id=\"" + o.getId() + "\" data-weight=\"500\" class=\"btn btn-sm btn-outline-secondary rounded-pill weight-btn\">500g</button>" +
                    "<button data-id=\"" + o.getId() + "\" data-weight=\"1000\" class=\"btn btn-sm btn-outline-secondary rounded-pill weight-btn\">1kg</button>" +
                    "</div>" +
                    "<div class=\"product_name text-dark fw-bold mb-2\">" + o.getName() + "</div>" +
                    "<div class=\"price text-danger fw-bold\" style=\"font-size: 1.2rem;\">" + o.getPriceMin() + " đ - " + o.getPriceMax() + " đ</div>" +
                    "</div>" +
                    "</div>" +
                    "</li>" +
                    "</ul>" +
                    "</div>";
                out.println(html);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            out.println("Error searching for products");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("General Error: " + e.getMessage());
            out.println("Unexpected error occurred");
            e.printStackTrace();
        }
    }
}
