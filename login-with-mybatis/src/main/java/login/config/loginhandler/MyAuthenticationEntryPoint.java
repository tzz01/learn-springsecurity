package login.config.loginhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, Object> result = new HashMap<>();
        result.put("status", 505);
        result.put("message", "尚未登录，请先登录");
        result.put("data", null);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(result);
        response.getWriter().write(s);
    }
}
