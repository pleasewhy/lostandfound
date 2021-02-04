package team.cfc.lostandfound.bo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.cfc.lostandfound.model.WxUser;

import java.util.ArrayList;
import java.util.Collection;

public class WxUserDetails implements UserDetails {

    private WxUser wxUser;
    private GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("wxUser");

    public WxUserDetails(WxUser wxUser){
        this.wxUser = wxUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> arrayList = new ArrayList();
        arrayList.add(grantedAuthority);
        return arrayList;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return wxUser.getOpenId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
