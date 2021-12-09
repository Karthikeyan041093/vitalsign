package com.example.i2i.VitalSignManagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.i2i.VitalSignManagement.entity.VitalSign;

@Repository
public interface VitalSignRepository
		extends PagingAndSortingRepository<VitalSign, Integer>, CrudRepository<VitalSign, Integer> {

}
