package login.config.loginhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=utf-8");
        Map<String, Object> result = new HashMap<>();
        result.put("status", 501);
        result.put("message", "当前用户已在另一处登录，请重新登录");
        result.put("data", null);
        String s = new ObjectMapper().writeValueAsString(result);
        response.getWriter().print(s);
        response.flushBuffer();
    }
}
