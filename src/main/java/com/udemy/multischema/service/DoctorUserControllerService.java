package com.udemy.multischema.service;

import com.udemy.multischema.mapper.DoctorUserRequestMapper;
import com.udemy.multischema.model.DoctorUser;
import com.udemy.multischema.model.Hospital;
import com.udemy.multischema.model.types.StaffStatusEnum;
import com.udemy.multischema.model.types.UserTypeEnum;
import com.udemy.multischema.utils.HospitalNotFoundException;
import com.udemy.multischema.utils.UserExistsException;
import com.udemy.multischema.vo.DoctorUserRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service(value = "staffControllerService")
public class DoctorUserControllerService {

    @Autowired
    DoctorUserRepository doctorUserRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    BCryptPasswordEncoder bcryptEncoder;
    @Autowired
    ApplicationEventPublisher eventPublisher;

    public DoctorUser createDoctorUserDetails(DoctorUserRequestVO doctorUserRequestVO) throws HospitalNotFoundException, UserExistsException {


        List<DoctorUser> doctorUsers = doctorUserRepository.findByUserName(doctorUserRequestVO.getUserName());
        if(doctorUsers!=null && !doctorUsers.isEmpty()){
            throw new UserExistsException("User exists already");
        }

        DoctorUser doctorUser = DoctorUserRequestMapper.INSTANCE.organizationStaffVOToModel(doctorUserRequestVO);
        doctorUser.setCreateDateTime(Instant.now());
        doctorUser.setUpdateDateTime(Instant.now());
        doctorUser.setUserTypeEnum(UserTypeEnum.DOCTOR);
        doctorUser.setPassword(bcryptEncoder.encode(doctorUserRequestVO.getPassword()));
        doctorUser.setStatus(StaffStatusEnum.AVAILABLE);
        doctorUser = doctorUserRepository.save(doctorUser);
        return (doctorUser);
    }


    public DoctorUser getDoctorUser(String staffId) {
        DoctorUser doctorUser = doctorUserRepository.findById(staffId).get();

        return (doctorUser);

    }


    public void deleteDoctorUser(String userId) {
        doctorUserRepository.deleteById(userId);
    }


    public List<DoctorUser> listUsersForOrganization() throws HospitalNotFoundException {

        List<DoctorUser> doctorUsers = new ArrayList<>();

        doctorUserRepository.findAll().forEach(a -> doctorUsers.add(a));


        return doctorUsers;

    }

}
