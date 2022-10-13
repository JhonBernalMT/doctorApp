package com.doctorapp.doctorapp.service;

import com.doctorapp.doctorapp.model.Message;
import com.doctorapp.doctorapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    public Optional<Message> getById(Integer idMessage){
        return messageRepository.getById(idMessage);
    }

    public Message save(Message message){
        if (message.getIdMessage()==null){
            return messageRepository.save(message);
        }else {
            Optional<Message> optionalMessage=messageRepository.getById(message.getIdMessage());
            if ((optionalMessage.isEmpty())){
                return messageRepository.save(message);
            }else {
                return message;
            }
        }
    }

    public Message update(Message message){
        if (message.getIdMessage()!=null){
            Optional<Message> optionalMessage=messageRepository.getById(message.getIdMessage());
            if (!optionalMessage.isEmpty()){
                if (message.getMessageText()!=null){
                    optionalMessage.get().setMessageText(message.getMessageText());
                    optionalMessage.get().setClient(message.getClient());
                    optionalMessage.get().setDoctor(message.getDoctor());
                }
                messageRepository.save(optionalMessage.get());
                return optionalMessage.get();
            }else {
                return message;
            }
        }else {
            return message;
        }
    }

    public boolean delete(Integer idMessage){
        Boolean aBoolean=getById(idMessage).map(message -> {
            messageRepository.delete(message);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
