package com.udemy.multischema.mapper;

import com.udemy.multischema.model.DoctorUser;
import com.udemy.multischema.vo.DoctorUserRequestVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorUserRequestMapper {

    DoctorUserRequestMapper INSTANCE = Mappers.getMapper(DoctorUserRequestMapper.class);


    DoctorUser organizationStaffVOToModel(DoctorUserRequestVO vo);

    DoctorUserRequestVO organizationStaffModelToVO(DoctorUser doctorUser);
}
