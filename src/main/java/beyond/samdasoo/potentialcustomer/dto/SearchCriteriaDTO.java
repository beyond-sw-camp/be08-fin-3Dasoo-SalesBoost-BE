package beyond.samdasoo.potentialcustomer.dto;

import lombok.Getter;

@Getter
public class SearchCriteriaDTO {
    private String selectedItem; // 검색 조건
    private String searchQuery; // 검색어
    private String selectedContact;
}
