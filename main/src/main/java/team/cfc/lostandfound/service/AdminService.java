package team.cfc.lostandfound.service;

import org.springframework.security.core.userdetails.UserDetails;
import team.cfc.lostandfound.common.api.CommonResult;

import java.util.List;

public interface AdminService {
    UserDetails loadUserByUsername(String username);
    public CommonResult login(String username,String password);
}
