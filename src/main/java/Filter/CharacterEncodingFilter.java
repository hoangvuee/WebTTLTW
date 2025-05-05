package Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo bộ lọc
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Thiết lập encoding cho request và response
        httpRequest.setCharacterEncoding("UTF-8");
        httpResponse.setCharacterEncoding("UTF-8");
        
        // Thiết lập content type cho response
        String contentType = httpResponse.getContentType();
        if (contentType == null || !contentType.contains("charset")) {
            httpResponse.setContentType("text/html; charset=UTF-8");
        }

        // Thiết lập header để đảm bảo hiển thị tiếng Việt đúng
        httpResponse.setHeader("Content-Language", "vi-VN");
        httpResponse.setHeader("X-Content-Type-Options", "nosniff");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Dọn dẹp tài nguyên khi filter bị hủy
    }
}