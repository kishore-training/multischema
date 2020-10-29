package com.udemy.multischema.mapper;

import com.udemy.multischema.model.Appointment;
import com.udemy.multischema.vo.AppointmentRequestVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentRequestMapper {

    AppointmentRequestMapper INSTANCE = Mappers.getMapper(AppointmentRequestMapper.class);



    Appointment appointmentVOToModel(AppointmentRequestVO vo);


    AppointmentRequestVO appointmentModelToVO(Appointment appointment);
}
