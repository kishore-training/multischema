package com.udemy.multischema.service;

import com.udemy.multischema.model.Hospital;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableMongoRepositories
public interface HospitalRepository extends PagingAndSortingRepository<Hospital, String> {

    List<Hospital> findByEmail(String email);

    List<Hospital> findByHospitalName(String hospitalName);

    List<Hospital> findByHospitalTenantId(String hospitalTenantId);

}
