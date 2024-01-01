package com.example.buildingrental;

import com.example.buildingrental.entity.Role;
import com.example.buildingrental.entity.User;
import com.example.buildingrental.repository.RoleRepository;
import com.example.buildingrental.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest
class BuildingrentalApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void saveUser() {
		Role role = Role.builder()
				.code("STAFF_CSKH")
				.name("Nhan vien cham soc khach hang")
				.description("Nhan vien cham soc khach hang 24/24 cua cong ty")
				.build();
		User user = User.builder()
				.userName("devsirose")
				.password("N2g1h0i7@")
				.email("dev.sirose@gmail.com")
				.firstName("Sirose")
				.lastName("Nguyen")
				.phoneNumber("0796784916")
				.dayOfBirth(new Date(2004,07,21))
				.build();
		user.addRole(role);
		userRepository.save(user);
	}

	@Test
	public  void test(){
		Page<User> users = userRepository.findAll(Pageable.unpaged(JpaSort.unsafe("LENGTH(firstName)").ascending()));
		System.out.println(users);
	}
}
