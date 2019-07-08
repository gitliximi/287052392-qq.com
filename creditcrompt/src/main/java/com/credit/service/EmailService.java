package com.credit.service;

import javax.mail.Message;
import java.util.List;

public interface EmailService {

    List<Message> receiveEmail();
}
