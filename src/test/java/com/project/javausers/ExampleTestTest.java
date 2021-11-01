package com.project.javausers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.project.javausers.mapper.MessageMapper;
import com.project.javausers.service.MessageListService;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ExampleTestTest  {
    private String testResult = "";
    private List<Integer> testList = new ArrayList<>();


    /* Before each test */
    @BeforeEach
    public void setTest(){
        this.testList =  new ArrayList<>();
        testList.add(1);
        testList.add(2);
        testList.add(3);
    }

    @Test
    public void testAddZero(){
        int a = 1;
        int b = 0;
        int c = a + b;
        testResult = String.valueOf(a);
        assertEquals(a, 1);
    }


    @Test
    public void testDivideByZero(){
        int a = 10;
        int b = 0;
        assertThrows(ArithmeticException.class, ()->{
            int c = a / b;
        });
    }


    @Test
    public void listContains(){
        assertTrue(testList.contains(1));
        testList.remove(testList.indexOf(1));
        assertFalse(testList.contains(1));
    }


    /* after Each Test */
    @AfterEach
    void tearDown() {
        System.out.println("Now We passed The test and it says that," + testResult);
        testResult = "";
        testList = null;
    }
}