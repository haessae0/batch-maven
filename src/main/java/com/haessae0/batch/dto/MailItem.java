package com.haessae0.batch.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailItem {
    private String mailAddr;
    private String mailTitle;
    private String htmlContent;
    private String mailId;

}
