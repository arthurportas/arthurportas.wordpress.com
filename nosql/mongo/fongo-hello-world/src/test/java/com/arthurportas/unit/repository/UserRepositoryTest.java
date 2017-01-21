package com.arthurportas.unit.repository;

import com.arthurportas.mongodb.example.repository.UserRepository;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.InMemoryMongoDb;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
