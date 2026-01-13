package com.awej.journal.controller;

import java.util.List;
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
        return null;
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntryService.saveEntry(myEntry);
        return true;
    }
    @GetMapping("id/{myId}")
    public JournalEntry getEntryById(@PathVariable Long myId){
        return null;
    }
    @DeleteMapping("id/{myId}")
    public JournalEntry deleteEntryById(@PathVariable Long myId){
        return null;
    }

  //  @PutMapping("id/{myId}")
    //public JournalEntry updateEntryById(@PathVariable Long myId){
      //  return journalEntries.put(id,myEntry);
//    }




}
