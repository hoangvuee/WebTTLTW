package Admin;

import Models.AddProduct;
import Models.Description.Description;
import Models.ProductDescription;
import Models.Sale.Sale;
import Services.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/admin/addProduct")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100    // 100 MB
)
public class AddProductServlet extends HttpServlet {
    private static final Gson gson = new Gson();
    private final ServiceAddProduct productService = new ServiceAddProduct();
    private ServiceSale serviceSale = new ServiceSale();
    private ServiceDescription serviceDescription = new ServiceDescription();
    private ServiceImage serviceImage  = new ServiceImage();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // 1. Lấy các tham số cơ bản
            String id = ServiceParameter.getParameter(request, "id");
            System.out.println("id: " + id);

            String name = ServiceParameter.getParameter(request, "name");
            System.out.println("name: " + name);

            String description = ServiceParameter.getParameter(request, "description");
            System.out.println("description: " + description);

            double price = Double.parseDouble(ServiceParameter.getParameter(request, "price"));
            System.out.println("price: " + price);

            float weight = Float.parseFloat(ServiceParameter.getParameter(request, "weight"));
            System.out.println("weight: " + weight);

            String quantity = ServiceParameter.getParameter(request, "quantity");
            System.out.println("quantity: " + quantity);

            String isActive = request.getParameter("isActive");
            System.out.println("isActive: " + isActive);

            String idCategory = ServiceParameter.getParameter(request, "idCategory");
            System.out.println("idCategory: " + idCategory);

            String idSupplier = ServiceParameter.getParameter(request, "idSupplier");
            System.out.println("idSupplier: " + idSupplier);

            String imageURL = ServiceParameter.getParameter(request, "imageURL");
            System.out.println("imageURL: " + imageURL);
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm")
                    .create();
            int idProduct;
            if (id.equals("") || id.trim().isEmpty()) {
                idProduct = productService.addOrUpdateProduct(name, idCategory, idSupplier, isActive);
                System.out.println("==> Thêm mới sản phẩm, id: " + idProduct);
            } else {

                idProduct = Integer.parseInt(id);
                System.out.println("==> Thêm biến thể cho sản phẩm có sẵn, id: " + idProduct);
            }
            int variantId = productService.addProductVariant(idProduct, weight, price, description, Integer.parseInt(quantity));

            String descriptionsJson = ServiceParameter.getParameter(request, "descriptions");
            Description[] descriptions = gson.fromJson(descriptionsJson, Description[].class);
            if (descriptions != null) {
                serviceDescription.addProductDescriptions(idProduct,descriptions);
            }
            String salesJson = ServiceParameter.getParameter(request, "sales");
            Sale[] sales = gson.fromJson(salesJson, Sale[].class);
            if (sales != null) {
                    serviceSale.insertSales(variantId,sales);
            }

            List<String> imagePaths = ServiceFile.saveImages(request, "img", "images", this);
            serviceImage.insertProductImages(idProduct,imagePaths);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            out.print(gson.toJson(error));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }






}