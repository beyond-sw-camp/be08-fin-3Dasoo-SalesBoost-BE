package beyond.samdasoo.excel.service;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponseStatus;
import beyond.samdasoo.excel.dto.ExcelDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ExcelService {
    public byte[] generateExcelFile(ExcelDto excelDto) throws IOException {

        List<Map<String, Object>> tableData = excelDto.getTableData();
        String sheetName = excelDto.getSheetName();

        ByteArrayOutputStream outputStream = null;
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(sheetName);

            //log.info("tableData row cnt {}", tableData.size());

            int rowIndex = 0;
            for (Map<String, Object> rowData : tableData) {
                Row row = sheet.createRow(rowIndex++);
                int cellIndex = 0;
                for (Object value : rowData.values()) {
                    Cell cell = row.createCell(cellIndex++);
                    cell.setCellValue(value.toString());
                }
            }

            outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (IOException e) {
            log.error("엑셀 파일 생성 중에 오류가 발생했습니다.", e);
//            throw e;
            throw new BaseException(BaseResponseStatus.UNEXPECTED_ERROR);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("출력 스트림을 close 하는데 실패했습니다.", e);
                }
            }
        }
    }
}
