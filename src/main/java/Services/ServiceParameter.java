package Services;

import jakarta.servlet.http.HttpServletRequest;

public class ServiceParameter {
    public static String getParameter(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        return value != null ? value : "";
    }

}
