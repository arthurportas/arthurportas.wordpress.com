package com.arthurportas.mongodb.example.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by arthurportas on 21/01/2017.
 */
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class User {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private Date lastUpdated;
}
