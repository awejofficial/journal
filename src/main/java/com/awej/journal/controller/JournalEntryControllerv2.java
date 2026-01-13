package com.awej.journal.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.awej.journal.entity.JournalEntry;
import com.awej.journal.service.JournalEntryService;


@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {
    @Autowired
    private JournalEntryService journalEntryService;
    
    @GetMapping
    public List<JournalEntry>getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return true;
    }
    @GetMapping("id/{myId}")
    public JournalEntry getEntryById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }
    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }

   @PutMapping("id/{myId}")
public JournalEntry updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
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
    }
    
    return old;
}




}
