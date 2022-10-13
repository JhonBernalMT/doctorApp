package com.doctorapp.doctorapp.repository.crudrepository;

import com.doctorapp.doctorapp.model.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorCrudRepository extends CrudRepository<Doctor, Integer> {
}
