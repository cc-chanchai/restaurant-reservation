package com.example.backend;

import com.example.backend.model.Queue;
import com.example.backend.model.User;
import com.example.backend.repositoory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

//	@Bean
//	ApplicationRunner init(UserRepository userRepository){
//		return args -> {
//			userRepository.save(new User("chanchai"));
//		};
//	}

	@Override
	public void run(String... args) throws Exception {

		User user = new User("chanchai");
		Queue queue = new Queue(new Timestamp(new Date().getTime()), true);
		user.setQueue(queue);
//		queue.setUser(user);
		userRepository.save(user);

		User user1 = new User("admin");
		Queue queue1 = new Queue(new Timestamp(new Date().getTime()), false);
		user1.setQueue(queue1);
//		queue1.setUser(user1);
		userRepository.save(user1);

		User user2 = new User("q3");
		Queue queue2 = new Queue(new Timestamp(new Date().getTime()), false);
		user2.setQueue(queue2);
//		queue1.setUser(user1);
		userRepository.save(user2);
	}
}
