package beyond.samdasoo.excel.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@ToString
public class ExcelDto {
    private String fileName;
    private String sheetName;
    private List<Map<String, Object>> tableData;
}
