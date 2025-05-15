package com.example.service;


import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<Message> createMessage(Message message){
        if(message.getMessageText() == null || message.getMessageText().isBlank() || message.getMessageText().length() >255 || !accountRepository.existsById(message.getPostedBy())){
            return ResponseEntity.badRequest().build();
        }
        Message saved = messageRepository.save(message);
        return ResponseEntity.ok(saved);
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }
    public ResponseEntity<Message> getMessageById(Integer id){
        return messageRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.ok(null));
    }
    public ResponseEntity<Integer> deleteMessage(Integer id){
        if(messageRepository.existsById(id)){
            messageRepository.deleteById(id);
            return ResponseEntity.ok(1);
        }
        return ResponseEntity.ok().build();
    }
    public ResponseEntity<Integer> updateMessage(Integer id, String newText){
        Optional<Message> optional = messageRepository.findById(id);
        if(optional.isPresent() && newText != null && !newText.isBlank() && newText.length() <=255){
            Message msg = optional.get();
            msg.setMessageText(newText);
            messageRepository.save(msg);
            return ResponseEntity.ok(1);
        }
        return ResponseEntity.badRequest().build();
    }
    public List<Message> getMessagesByUser(Integer accountId){
        return messageRepository.findByPostedBy(accountId);
    }
}
