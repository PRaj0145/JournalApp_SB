package com.rootZero.journalApp.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data

@NoArgsConstructor
public class ConfigJournalAppEntity {


    private String key;

    private String value;
    private LocalDateTime date;


}
