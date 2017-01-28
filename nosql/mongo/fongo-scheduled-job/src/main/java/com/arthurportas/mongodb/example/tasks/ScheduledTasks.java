package com.arthurportas.mongodb.example.tasks;

import com.arthurportas.mongodb.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by arthurportas on 24/01/2017.
 */
@Component
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private UserRepository userRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRateString = "${users.scheduled.maintenance}")//every five seconds we clean records older than current time -1 day
    public void deleteUserRecords() throws ParseException {

        Date currentDate = new Date();

        LOGGER.info("The time now is {}, deleting {} records", dateFormat.format(currentDate),
                userRepository.findByLastUpdatedLessThan(Date.from(Instant.now().minus(1, ChronoUnit.DAYS))).size());//this is an ugly count query :\

        userRepository.deleteByLastUpdatedLessThan(currentDate);
    }
}
