package com.lmc;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot04TestApplicationTests {

    @Test
    public void testOne() {
        System.out.println("测试1........");
        TestCase.assertEquals(1,1);
    }
    @Test
    public void testTwo() {
        System.out.println("测试2........");
        TestCase.assertEquals(2,2);
    }

    @Before
    public void testBefore() {
        System.out.println("Before");
    }

    @After
    public void testAfter() {
        System.out.println("After");
    }

}
