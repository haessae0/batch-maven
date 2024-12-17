package com.haessae0.batch.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MailCompositeItem {
    private String mrctId;
    private List<MailItem> mails = new ArrayList<>();

}
