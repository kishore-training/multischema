package com.udemy.multischema.security;

import com.udemy.multischema.model.Hospital;
import com.udemy.multischema.service.HospitalRepository;
import com.udemy.multischema.tenant.TenantAwareService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class SimpleAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    HospitalRepository hospitalRepository;


    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException {

        String hospitalNameInput = request.getParameter("hospital");
        String hospitalTenantId = "NONE";

        if (hospitalNameInput != null) {
            WebApplicationContext webApplicationContext =
                    WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
            //reference to bean from app context
            TenantAwareService.setTenant(TenantAwareService.ADMIN_TENANT);
            hospitalRepository = webApplicationContext.getBean(HospitalRepository.class);
            List<Hospital> hospitalList = hospitalRepository.findByHospitalName(hospitalNameInput);
            Hospital hospitalEntity = null;

            if(hospitalList != null && !hospitalList.isEmpty()){
                hospitalEntity = hospitalList.get(0);
                hospitalTenantId = hospitalEntity.getHospitalTenantId();

            }
            Cookie cookie = new Cookie(TenantAwareService.TENANT_COOKIE_NAME,hospitalTenantId);
            cookie.setValue(hospitalTenantId);
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
        }else{
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie:cookies){
                if(cookie.getName().equals(TenantAwareService.TENANT_COOKIE_NAME)){
                    hospitalTenantId = cookie.getValue();
                }
            }
        }
        TenantAwareService.setTenant(hospitalTenantId);
        UsernamePasswordAuthenticationToken authRequest
                = getAuthRequest(request);
        setDetails(request, authRequest);

        return this.getAuthenticationManager()
                .authenticate(authRequest);
    }

    private UsernamePasswordAuthenticationToken getAuthRequest(
            HttpServletRequest request) {

        String username = obtainUsername(request);
        String password = obtainPassword(request);


        return new UsernamePasswordAuthenticationToken(
                username, password);
    }
}
