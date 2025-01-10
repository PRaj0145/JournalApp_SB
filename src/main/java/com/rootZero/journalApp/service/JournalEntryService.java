package com.rootZero.journalApp.service;

import com.rootZero.journalApp.entity.JournalEntry;

import com.rootZero.journalApp.entity.User;
import com.rootZero.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;


  private static final  Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUserName(userName);

            JournalEntry saved = journalEntryRepository.save(journalEntry);
               user.getJournalEntries().add(saved);

            userService.saveUser(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry" + e);
        }

    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

  public List<JournalEntry>getAll(){
      return journalEntryRepository.findAll();

  }

  public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
  }

  @Transactional
  public boolean deleteById(ObjectId id, String userName){
      boolean removed = false;
      try {
          User user = userService.findByUserName(userName);
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));

          if (removed) {
              userService.saveUser(user);
              journalEntryRepository.deleteById(id);
          }

      }catch (Exception e){
          System.out.println(e);
          throw new RuntimeException("An Error occurred while deleting the enrty" + e);
      }
      return removed;
  }

}

