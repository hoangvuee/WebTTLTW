package Controller.User;

import Models.User.User;
import Models.User.UserDTO;
import Services.ServiceUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/users/*")
public class UserController extends HttpServlet {
    ServiceUser serviceUser = new ServiceUser();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        List<UserDTO> users;
        List<User> userList = serviceUser.getAllUsers();
        users = userList.stream()
                .map(v -> new UserDTO(
                    v.getId(), 
                    v.getEmail(), 
                    v.getUserName(), 
                    v.getPhoneNumber(), 
                    v.getIdRole(), 
                    v.isActive(), 
                    v.getCreateDate()
                ))
                .toList();
        
        objectMapper.writeValue(resp.getWriter(), users);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//
//        try {
//            UserDTO userDTO = objectMapper.readValue(req.getReader(), UserDTO.class);
//            User user = new User();
//            user.setEmail(userDTO.getEmail());
//            user.setUserName(userDTO.getUserName());
//            user.setPassword(userDTO.getPassword()); // Make sure to hash password
//            user.setIdRole(userDTO.getIdRole());
//            user.setActive(userDTO.isActive());
//            user.setCreateDate(new Date());
//
//            boolean success = serviceUser.addUser(user);
//            if (success) {
//                resp.setStatus(HttpServletResponse.SC_CREATED);
//                objectMapper.writeValue(resp.getWriter(), userDTO);
//            } else {
//                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                objectMapper.writeValue(resp.getWriter(), Map.of("error", "Could not create user"));
//            }
//        } catch (Exception e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            objectMapper.writeValue(resp.getWriter(), Map.of("error", e.getMessage()));
//        }
//    }
//
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            UserDTO userDTO = objectMapper.readValue(req.getReader(), UserDTO.class);
            System.out.println(userDTO.toString());
            User user = new User();
            user.setId(userDTO.getId());
            user.setEmail(userDTO.getEmail());
            user.setUserName(userDTO.getUserName());
            user.setIdRole(userDTO.getRole());
            user.setActive(userDTO.isActive());

            boolean success = serviceUser.updateBasicUserInfo(user);
            if (success) {
                resp.setStatus(HttpServletResponse.SC_OK);
                objectMapper.writeValue(resp.getWriter(), userDTO);
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                objectMapper.writeValue(resp.getWriter(), Map.of("error", "Could not update user"));
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getWriter(), Map.of("error", e.getMessage()));
        }
    }
//
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getWriter(), Map.of("error", "User ID is required"));
            return;
        }

        try {
            int userId = Integer.parseInt(pathInfo.substring(1));
            System.out.println(userId + "idussrer");
            boolean success = serviceUser.deleteUser(userId);
            if (success) {
                resp.setStatus(HttpServletResponse.SC_OK);
                objectMapper.writeValue(resp.getWriter(), Map.of("message", "User deleted successfully"));
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                objectMapper.writeValue(resp.getWriter(), Map.of("error", "Could not delete user"));
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(resp.getWriter(), Map.of("error", "Invalid user ID"));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getWriter(), Map.of("error", e.getMessage()));
        }
    }
}
