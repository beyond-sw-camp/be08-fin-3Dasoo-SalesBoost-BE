package beyond.samdasoo.report.controller;

import beyond.samdasoo.report.dto.PdfEstimateDto;
import beyond.samdasoo.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
@Slf4j
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/estimate")
    public ResponseEntity<byte[]> generatePdf(@RequestBody PdfEstimateDto estimateDto) {
        try {
            byte[] pdfData = reportService.generateEstReport("estReport", estimateDto);

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "application/pdf");
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=estimate.pdf");

            return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
