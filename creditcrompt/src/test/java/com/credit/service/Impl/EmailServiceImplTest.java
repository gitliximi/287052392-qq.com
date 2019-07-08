package com.credit.service.Impl;

import com.credit.CreditcromptApplication;
import com.credit.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CreditcromptApplication.class )
public class EmailServiceImplTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void receiveEmail() {

        emailService.receiveEmail();
    }
}