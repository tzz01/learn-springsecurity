package login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import login.mapper.ResourceMapper;
import login.po.Resource;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    ResourceMapper resourceMapper;

    /**
     * 查询所有的resource
     * @return 所有的resource
     */
    public List<Resource> getAllResource() {
        return resourceMapper.getAllResource();
    }
}
