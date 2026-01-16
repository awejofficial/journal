package com.awej.journal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.awej.journal.entity.JournalEntry;
import com.awej.journal.entity.User;
import com.awej.journal.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

@Transactional
public void saveEntry(JournalEntry journalEntry, String userName) {
    User user = userService.findByUserName(userName);
    journalEntry.setDate(LocalDateTime.now());
    JournalEntry saved = journalEntryRepository.save(journalEntry);
    user.getJournalEntries().add(saved);
    userService.saveEntry(user);
}
// Version 2: For updating existing entries (no user logic needed)
public void saveEntry(JournalEntry journalEntry) {
    journalEntryRepository.save(journalEntry);
}

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id, String userName) {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
    journalEntryRepository.deleteById(id);
}

}
