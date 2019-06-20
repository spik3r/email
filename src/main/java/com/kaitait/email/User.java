package com.kaitait.email;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.util.Date;

//import org.hibernate.annotations.GenericGenerator;
//import org.springframework.data.annotation.CreatedDate;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.util.Date;
//
//@Entity
//@Table(name = "users")
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class User {

//    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
//    @CreatedDate
    private Date getCreateAt = new Date();
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String email;
    @NonNull private String password;
}

