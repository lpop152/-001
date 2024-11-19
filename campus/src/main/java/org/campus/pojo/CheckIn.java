package org.campus.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class CheckIn {
    @Id
    private String id;
    String sid;
    Date time;

}
