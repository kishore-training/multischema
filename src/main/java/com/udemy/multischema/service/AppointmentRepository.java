package com.udemy.multischema.service;

import com.udemy.multischema.model.Appointment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@EnableMongoRepositories
public interface AppointmentRepository extends PagingAndSortingRepository<Appointment, String> {


    public  List<Appointment> findByAppointmentDateTimeBetweenAndDoctorId(Date from, Date to, String doctorId);

}
