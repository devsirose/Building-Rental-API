package com.example.application;

import com.example.application.model.entity.User;
import com.example.application.repository.UserRepos;
import com.example.application.repository.UserRoleRepos;
import com.example.application.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private UserRepos userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleRepos userRoleRepos;
	@Test
	@Transactional
	@Rollback(value = false)
	void saveUserWithRoles() {
		// Create a new user
//		User user = User.builder()
//				.userName("sirose12345678")
//				.password("123456789")
//				.email("ntn2172k4@gmail.com")
//				.fullName("Nguyễn Nghĩa")
//				.phone("0796784916")
//				.status(1)
//				.build();
//
//		// Create a new role
//		Role role = Role.builder()
//				.code("owner")
//				.name("Chủ sở hữu")
//				.build();
//		UserRole userRole = UserRole.builder()
//				.createdDate(Timestamp.valueOf(LocalDateTime.now()))
//				.user((User)(userService.findById(5l)).get())
//				.role(roleService.findById(2l))
//				.build();
//		userRoleRepos.save(userRole);
//
		User u = userService.findById(5l).get();
	}
}
