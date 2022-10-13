package com.doctorapp.doctorapp.repository.crudrepository;

import com.doctorapp.doctorapp.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
}
