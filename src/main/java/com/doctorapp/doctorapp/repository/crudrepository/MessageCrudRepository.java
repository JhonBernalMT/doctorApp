package com.doctorapp.doctorapp.repository.crudrepository;

import com.doctorapp.doctorapp.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageCrudRepository extends CrudRepository<Message, Integer> {
}
