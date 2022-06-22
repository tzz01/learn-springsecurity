package login.config;

import login.po.Resource;
import login.po.Role;
import login.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class MySecurityMetaDataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    ResourceService resourceService;

    AntPathMatcher antPathMatcher =  new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUri = ((FilterInvocation)object).getRequest().getRequestURI();
        List<Resource> allResource = resourceService.getAllResource();
        for (Resource resource: allResource) {
            if (antPathMatcher.match(resource.getPattern(), requestUri)) {
                String[] roles = resource.getRoles().stream()
                        .map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(roles);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
