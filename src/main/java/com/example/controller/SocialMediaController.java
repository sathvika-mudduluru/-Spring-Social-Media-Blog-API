package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account){
        return accountService.register(account);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginUser(@RequestBody Account account){
        return accountService.login(account);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message){
        return messageService.createMessage(message);
    }

    @GetMapping("/messages")
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer id){
        return messageService.getMessageById(id);
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable Integer id){
        return messageService.deleteMessage(id);
    }

    @PatchMapping("/messages/{id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable Integer id, @RequestBody Message updatedMessage){
        return messageService.updateMessage(id, updatedMessage.getMessageText());
    }

    @GetMapping("/accounts/{accountId}/messages")
    public List<Message> getMessagesByUser(@PathVariable Integer accountId){
        return messageService.getMessagesByUser(accountId);
    }
}
