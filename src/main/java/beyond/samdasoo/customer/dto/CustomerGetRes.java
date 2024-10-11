package beyond.samdasoo.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomerGetRes {

    private Long id;

    private String name;

    private String position;

    private String company;

    private String email;

    private String phone;

    private String tel;

    private String randomImage;

}
