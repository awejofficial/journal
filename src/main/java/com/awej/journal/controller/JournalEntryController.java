package com.awej.journal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import com.awej.journal.entity.JournalEntry;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long,JournalEntry> journalEntries = new HashMap();
    
    @GetMapping
    public List<JournalEntry>getAll(){
         return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }
    @GetMapping("id/{myId}")
    public JournalEntry getEntryById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }
    @DeleteMapping("id/{myId}")
    public JournalEntry deleteEntryById(@PathVariable Long myId){
        return journalEntries.remove(myId);
    }

  //  @PutMapping("id/{myId}")
    //public JournalEntry updateEntryById(@PathVariable Long myId){
      //  return journalEntries.put(id,myEntry);
//    }




}
