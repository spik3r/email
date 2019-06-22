package com.kaitait.email.domain;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Slf4j
public class UserConverter implements AttributeConverter<User, String> {
    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(final User user) {
        log.info("convertToEntityAttribute called User user: {}", user);
//
        StringBuilder sb = new StringBuilder();
//        if (user.getFirstName() != null && !user.getLastName()
//                .isEmpty()) {
//            sb.append(user.getId());
////            sb.append(SEPARATOR);
////            sb.append(user.getCreatedAt());
//            sb.append(SEPARATOR);
//            sb.append(user.getFirstName());
//            sb.append(SEPARATOR);
//            sb.append(user.getLastName());
//            sb.append(SEPARATOR);
//            sb.append(user.getEmail());
//            sb.append(SEPARATOR);
//            sb.append(user.getPassword());
//            sb.append(SEPARATOR);
//        }

        return sb.toString();
    }

    @Override
    public User convertToEntityAttribute(final String dbUser) {
        log.info("convertToEntityAttribute called String user: {}", dbUser);
        String[] pieces = dbUser.split(SEPARATOR);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
//                Locale.ENGLISH);
            return new User(pieces[0], pieces[1], pieces[2], pieces[3]);
//
//        try {
//            final Date date = dateFormat.parse(pieces[1]);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Invalid Date", e);
//        }

//        return new User(pieces[0], date, pieces[2], pieces[3], pieces[4], pieces[5]);
    }
}
