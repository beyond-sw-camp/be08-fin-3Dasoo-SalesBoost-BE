package beyond.samdasoo.customer.dto;

import lombok.Getter;

import java.util.PriorityQueue;

@Getter
public class CustomerCreateReq {

    private String name;
    private String company;
    private String dept;
    private String position;
    private String email;
    private String phone;
    private String tel;
    private String grade;
    private boolean keyman;
}
