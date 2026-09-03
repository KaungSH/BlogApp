package ai.blogapp.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import ai.blogapp.model.Role;
import ai.blogapp.model.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@Component
public class AdminInterceptor  implements HandlerInterceptor {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("loggedInUser") == null) {

            response.sendRedirect("/login");
            return false;
        }
        
        UserModel user = (UserModel) session.getAttribute("loggedInUser");
        if (!(user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.SUPERADMIN))) {
        	throw new ResponseStatusException(HttpStatus.FORBIDDEN, "NOT ENOUGH PERMISSION!!!");
        }

        return true;
    }
}
