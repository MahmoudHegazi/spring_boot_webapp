package com.project.javausers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class JavausersApplicationTests {

	@Test
	void contextLoads() {

			int a = 10;
			int b = 0;
			assertThrows(ArithmeticException.class, ()->{
				int c = a / b;
			});
	}

}
