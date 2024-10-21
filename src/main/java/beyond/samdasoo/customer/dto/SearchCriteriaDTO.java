package beyond.samdasoo.customer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SearchCriteriaDTO {
    private String selectedItem; // 검색 조건
    private String searchQuery; // 검색어
    private String personInCharge; // 담당자
    private String selectedKey; // 키맨 여부
}
