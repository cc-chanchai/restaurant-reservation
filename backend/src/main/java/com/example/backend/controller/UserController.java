package com.example.backend.controller;

import com.example.backend.model.Queue;
import com.example.backend.model.User;
import com.example.backend.repositoory.QueueRepository;
import com.example.backend.repositoory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QueueRepository queueRepository;

    @GetMapping("/reservations")
    public ResponseEntity<List<User>> getAllReservation(){
        try{
            List<User> user = userRepository.findAll();
            return new ResponseEntity<List<User>>(user, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/current")
    public ResponseEntity<Long> getCurrentQueue(){
        try{
            List<Queue> current = queueRepository.findAllByIsDone(false);
            return new ResponseEntity<Long>(current.get(0).getId(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reservations")
    public ResponseEntity<User> createQueue(@RequestParam("name") String name){
        try{
            User user = new User(name);
            Queue queue = new Queue(new Timestamp(new Date().getTime()), false);
            user.setQueue(queue);
            userRepository.save(user);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity<Long> nextQueue(@PathVariable("id") Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
