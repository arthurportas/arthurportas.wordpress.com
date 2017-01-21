package com.arthurportas.mongodb.example.repository;

import com.arthurportas.mongodb.example.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by arthurportas on 21/01/2017.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
