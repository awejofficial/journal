package com.awej.journal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.awej.journal.entity.User;
import com.awej.journal.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {
        @Autowired
        private UserService userService;

        @GetMapping
        public List<User> getAllUsers(){
            return userService.getAll();
        }
        @PostMapping("/{userName}")
        public void createUser(@RequestBody User user, @PathVariable String userName){
    // Optional: user.setUserName(userName);
        userService.saveEntry(user);
        }

        @PutMapping("/{userName}")
        public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName) {
            User userInDb = userService.findByUserName(userName);
            if(userInDb != null){
                userInDb.setUserName(user.getUserName());
                userInDb.setPassword(user.getPassword());
                userService.saveEntry(userInDb);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // @DeleteMapping
        // public ResponseBody<?> deleteUser(@RequestBody User user) {
        //     userService.deleteById(user.getId());
            
        // }



































    //     @GetMapping
    //     public ResponseEntity<?>getAllUsers(){
    //     List<User> all = UserService.getAll();
    //     if(all != null && all.isEmpty()){
    //         return new ResponseEntity<>(HttpStatus.OK);
    //     }
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }

    //     @PostMapping
    //     public ResponseEntity<User> createEntry(@RequestBody User myEntry) {
    //         try{
    //             myEntry.setDate(LocalDateTime.now());
    //             User.saveEntry(myEntry);
    //             return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
    //         }catch (Exception e) {
    //             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    //         }
            
    //     }
    //     @GetMapping("id/{myId}")
    //     public ResponseEntity<User>getEntryById(@PathVariable ObjectId myId){
    //     Optional<User> journalEntry = UserService.findById(myId);
    //     if(journalEntry.isPresent()){
    //         return new ResponseEntity<>(User.get(),HttpStatus.OK);
    //     }
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    
    //     @DeleteMapping("id/{myId}")
    //     public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId){
    //         UserService.deleteById(myId);
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     }

    //     @PutMapping("id/{myId}")
    //     public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myId, @RequestBody User newEntry) {
    //         JournalEntry old = User.findById(myId).orElse(null);
    
    //     if (old != null) {
    //         // Use ! .equals("") to ensure we only update if the new value isn't empty
    //         if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
    //             old.setTitle(newEntry.getTitle());
    //         }
    //         if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
    //             old.setContent(newEntry.getContent());
    //         }
    //         UserService.saveEntry(old);
    //         return new ResponseEntity<>(HttpStatus.OK);
    //     }
    
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
// }
}
