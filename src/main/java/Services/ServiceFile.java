package Services;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceFile {
    public static List<String> saveImages(HttpServletRequest request, String uploadFolder, String partName, HttpServlet servletContext) throws IOException, ServletException, ServletException, IOException {
        List<String> imagePaths = new ArrayList<>();

        // Đường dẫn upload
        String uploadPath = servletContext.getServletContext().getRealPath("") + File.separator + uploadFolder;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        // Lấy file từ request
        Collection<Part> fileParts = request.getParts().stream()
                .filter(part -> partName.equals(part.getName()) && part.getSize() > 0)
                .collect(Collectors.toList());

        for (Part filePart : fileParts) {
            String fileName = getFileName(filePart);
            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);
            imagePaths.add(fileName);
        }

        return imagePaths;
    }

    public static String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "file_" + System.currentTimeMillis();
    }
    public static String saveFileToImgFolder(Part filePart, ServletContext context) throws IOException {
        // Lấy tên file
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Xác định đường dẫn lưu trong thư mục "img"
        String uploadPath = context.getRealPath("/") + "img";

        // Tạo thư mục nếu chưa tồn tại
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        // Đường dẫn đầy đủ để lưu file
        String filePath = uploadPath + File.separator + fileName;

        // Ghi file vào hệ thống
        filePart.write(filePath);

        return filePath;
    }

}
