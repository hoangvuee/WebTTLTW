package Services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
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
}
