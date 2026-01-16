package com.awej.journal.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.awej.journal.entity.JournalEntry;
import com.awej.journal.entity.User;
import com.awej.journal.service.JournalEntryService;
import com.awej.journal.service.UserService;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {
        @Autowired
        private JournalEntryService journalEntryService;

        @Autowired
        private UserService userService;
        
        @GetMapping("{userName}")
        public ResponseEntity<?>getAllJournalEntryOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && all.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @PostMapping("{userName}")
public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
    try {
        // This will now match the new method signature in the Service
        journalEntryService.saveEntry(myEntry, userName);
        return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
        @GetMapping("id/{myId}")
        public ResponseEntity<JournalEntry>getEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    
        @DeleteMapping("id/{myId}")
        public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId){
            journalEntryService.deleteById(myId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PutMapping("id/{myId}")
        public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
            JournalEntry old = journalEntryService.findById(myId).orElse(null);
    
        if (old != null) {
            // Use ! .equals("") to ensure we only update if the new value isn't empty
            if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
                old.setTitle(newEntry.getTitle());
            }
            if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
                old.setContent(newEntry.getContent());
            }
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}
