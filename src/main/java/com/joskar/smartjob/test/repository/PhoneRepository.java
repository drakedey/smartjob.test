package com.joskar.smartjob.test.repository;

import com.joskar.smartjob.test.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    boolean existsByPhoneNumberAndCityCodeAndCountryCode(String phoneNumber, String cityCode, String countryCode);

    @Override
    <S extends Phone> List<S> saveAllAndFlush(Iterable<S> entities);
}
