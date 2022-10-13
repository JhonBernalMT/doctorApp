package com.doctorapp.doctorapp.service;

import com.doctorapp.doctorapp.model.Doctor;
import com.doctorapp.doctorapp.repository.DoctorRepository;
import com.fasterxml.jackson.databind.DatabindContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAll(){
        return (List<Doctor>) doctorRepository.getAll();
    }

    public Optional<Doctor> getById(Integer idDoctor){
        return doctorRepository.getById(idDoctor);
    }

    public Doctor save(Doctor doctor){
        if (doctor.getIdDoctor()==null){
            return doctorRepository.save(doctor);
        } else {
            Optional<Doctor> optionalDoctor=doctorRepository.getById(doctor.getIdDoctor());
            if(optionalDoctor.isEmpty()){
                return doctorRepository.save(doctor);
            }else {
                return doctor;
            }
        }
    }

    public Doctor update(Doctor doctor){
        if (doctor.getIdDoctor()!=null){
            Optional<Doctor> optionalDoctor=doctorRepository.getById(doctor.getIdDoctor());
            if (!optionalDoctor.isEmpty()){
                if (doctor.getName()!=null){
                    optionalDoctor.get().setName(doctor.getName());
                }
                if (doctor.getDepartment()!=null){
                    optionalDoctor.get().setDepartment(doctor.getDepartment());;
                }
                if (doctor.getYear()!=null){
                    optionalDoctor.get().setYear(doctor.getYear());
                }
                if (doctor.getDescription()!=null){
                    optionalDoctor.get().setDescription(doctor.getDescription());
                }
                doctorRepository.save(optionalDoctor.get());
                return optionalDoctor.get();
            }else {
                return doctor;
            }
        }else {
            return doctor;
        }
    }

    public boolean delete(Integer idDoctor){
        Boolean aBoolean=getById(idDoctor).map(doctor -> {
            doctorRepository.delete(doctor);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
