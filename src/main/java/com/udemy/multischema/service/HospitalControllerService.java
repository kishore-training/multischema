package com.udemy.multischema.service;

import com.udemy.multischema.mapper.HospitalRequestMapper;
import com.udemy.multischema.model.DoctorUser;
import com.udemy.multischema.model.Hospital;
import com.udemy.multischema.model.types.UserTypeEnum;
import com.udemy.multischema.tenant.TenantAwareService;
import com.udemy.multischema.utils.HospitalExistsException;
import com.udemy.multischema.utils.HospitalNotFoundException;
import com.udemy.multischema.vo.HospitalRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@Service(value = "organisationControllerService")
public class HospitalControllerService {

    public Logger logger = Logger.getLogger("HospitalControllerService");
    @Autowired
    HospitalRepository hospitalRepository;
    @Autowired
    DoctorUserRepository doctorUserRepository;

    @Autowired
    BCryptPasswordEncoder bcryptEncoder;


    public Hospital registerHospitalDetails(HospitalRequestVO requestVO) throws HospitalExistsException {
        String email = requestVO.getEmail();
        logger.info("Create Organization for email: " + email + " with logo: " + requestVO.getLogo());
        //logo is my actual check here for authentication

        DoctorUser tenantAdminUser = null;


        Hospital hospital = HospitalRequestMapper.INSTANCE.hospitalVOToModel(requestVO);
        //set a random number between 1 to 100000 as the tenant id of the hospital
        String tenantId = new Random().nextInt(100000) + "";
        hospital.setHospitalTenantId(tenantId);
        //Create the hospital and user in tenant based schema
        TenantAwareService.setTenant(tenantId);

        hospital.setCreateDateTime(Instant.now());
        hospital.setUpdateDateTime(Instant.now());


        hospital = hospitalRepository.save(hospital);

        //save tenant admin tenantAdminuser for the hospital in the tenant schema.
        tenantAdminUser = HospitalRequestMapper.INSTANCE.hospitalVOToDoctorUser(requestVO);
        tenantAdminUser.setUserTypeEnum(UserTypeEnum.HOSPITAL_TENANT_ADMIN);
        tenantAdminUser.setPassword(bcryptEncoder.encode(requestVO.getPassword()));
        tenantAdminUser.setEmailId(requestVO.getEmail());
        tenantAdminUser.setSpecialization("ADMIN");
        tenantAdminUser.setCreateDateTime(Instant.now());
        tenantAdminUser.setUpdateDateTime(Instant.now());

        tenantAdminUser = doctorUserRepository.save(tenantAdminUser);

        hospital = hospitalRepository.save(hospital);

        //Register the hospital in ADMIN_TENANT so SUPER_ADMIN can view the list of all hospitals
        TenantAwareService.setTenant(TenantAwareService.ADMIN_TENANT);
        hospital = hospitalRepository.save(hospital);

        return hospital;
    }

    public void deleteHospital(String hospitalName) throws HospitalNotFoundException {
        List<Hospital> hospitals = hospitalRepository.findByHospitalName(hospitalName);
        if (hospitals == null || hospitals.isEmpty()) {
            throw new HospitalNotFoundException("Specified hospital not found");
        }
        hospitalRepository.deleteById(hospitals.get(0).getId());
    }

    public Hospital getHospital(String organizationId) {
        Hospital hospital = hospitalRepository.findById(organizationId).get();
        return (hospital);
    }

    public List<Hospital> listHospitals() {
        List<Hospital> hospitals = new ArrayList<>();
        hospitalRepository.findAll().forEach(a ->
                hospitals.add(a));
        return hospitals;
    }

}
