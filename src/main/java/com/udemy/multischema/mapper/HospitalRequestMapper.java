package com.udemy.multischema.mapper;

import com.udemy.multischema.model.DoctorUser;
import com.udemy.multischema.model.Hospital;
import com.udemy.multischema.vo.HospitalRequestVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HospitalRequestMapper {

    HospitalRequestMapper INSTANCE = Mappers.getMapper(HospitalRequestMapper.class);


    Hospital hospitalVOToModel(HospitalRequestVO vo);

    HospitalRequestVO hospitalModelToVO(Hospital appointment);

    DoctorUser hospitalVOToDoctorUser(HospitalRequestVO vo);
}
