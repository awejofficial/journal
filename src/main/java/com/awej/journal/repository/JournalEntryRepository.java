package com.awej.journal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.awej.journal.entity.JournalEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {


}
