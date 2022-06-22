package login.config.loginhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, Object> result = new HashMap<>();
        result.put("status", 10000);
        result.put("message", "登陆成功");
        result.put("data", null);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(result);
        response.getWriter().write(s);
    }
}
