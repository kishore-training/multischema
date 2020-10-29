package com.udemy.multischema.service;

import com.udemy.multischema.model.DoctorUser;
import com.udemy.multischema.model.Hospital;
import com.udemy.multischema.model.types.UserTypeEnum;
import com.udemy.multischema.tenant.TenantAwareService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;
import java.util.logging.Logger;

@Component
public class PopulateSuperAdminUserService implements InitializingBean {
    @Autowired
    BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    DoctorUserRepository doctorUserRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    public Logger logger = Logger.getLogger("PopulateAdminUserService");

    @Override
    public void afterPropertiesSet() throws Exception {

        Hospital hospital = new Hospital();
        hospital.setHospitalName("ADMIN_TENANT");
        hospital.setCountry("INDIA");
        hospital.setEmail("admin@gmail.com");
        hospital.setMobileNumber("98989898");

        TenantAwareService.setTenant(TenantAwareService.ADMIN_TENANT);
        hospital.setHospitalTenantId(TenantAwareService.ADMIN_TENANT);
        hospital.setCreateDateTime(Instant.now());
        hospitalRepository.save(hospital);

        DoctorUser user = new DoctorUser();
        user.setUserName("SUPER_ADMIN");

        user.setUserTypeEnum(UserTypeEnum.SUPER_ADMIN);
        user.setMobileNumber("9898989898");
        user.setPassword(bcryptEncoder.encode("Admin123"));
        user.setEmailId("super_admin@gmail.com");

        user.setSpecialization("ADMIN");
        user.setCreateDateTime(Instant.now());
        user.setUpdateDateTime(Instant.now());


        doctorUserRepository.save(user);
        logger.info("CREATED SUPER_ADMIN USER");


    }
}
