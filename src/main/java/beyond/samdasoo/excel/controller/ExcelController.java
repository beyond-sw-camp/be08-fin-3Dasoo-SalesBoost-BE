package beyond.samdasoo.excel.controller;

import beyond.samdasoo.excel.dto.ExcelDto;
import beyond.samdasoo.excel.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ExcelController {
    private final ExcelService excelService;

    @PostMapping("/export/excel")
    public ResponseEntity<byte[]> exportExcel(@RequestBody ExcelDto excelDto) {
        try {
            byte[] excelFile = excelService.generateExcelFile(excelDto);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + excelDto.getFileName());
            headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            return new ResponseEntity<>(excelFile, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
