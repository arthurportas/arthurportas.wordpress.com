package com.arthurportas.repository;

import com.arthurportas.mongodb.example.repository.UserRepository;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.InMemoryMongoDb;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static com.lordofthejars.nosqlunit.mongodb.InMemoryMongoDb.InMemoryMongoRuleBuilder.newInMemoryMongoDbRule;
import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by arthurportas on 21/01/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    private static Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);

    @ClassRule
    public static InMemoryMongoDb inMemoryMongoDb = newInMemoryMongoDbRule().build();

    @Rule
    public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("demo-test");

    /**
     * nosql-unit requirement
     */
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserRepository userRepository;

    @Test
    @UsingDataSet(locations = {"/five-users.json"}, loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    public void testCountAllUsers() {

        LOGGER.info("testCountAllUsers");

        long total = this.userRepository.findAll().size();

        assertThat(total).isEqualTo(5);
    }

    @Test
    @UsingDataSet(locations = {"/five-users.json"}, loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    public void testCountAllUsersByLastUpdated() throws ParseException {

        LOGGER.info("testCountAllUsersByLastUpdated");

        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse("2017-01-24T10:09:15.210");

        long total = this.userRepository.findByLastUpdated(date).size();

        assertThat(total).isEqualTo(1);
    }

    @Test
    @UsingDataSet(locations = {"/five-users.json"}, loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    public void testCountAllUsersNotUpdatedOnLast24Hours() throws ParseException, InterruptedException {

        LOGGER.info("testCountAllUsersUpdatedOnLast24Hours");

        Thread.sleep(10000);
        assertThat(this.userRepository.count()).isEqualTo(0);
    }
}
