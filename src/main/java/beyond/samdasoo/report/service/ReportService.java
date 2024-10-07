package beyond.samdasoo.report.service;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponseStatus;
import beyond.samdasoo.estimate.entity.EstProduct;
import beyond.samdasoo.estimate.entity.Estimate;
import beyond.samdasoo.estimate.repository.EstProductRepository;
import beyond.samdasoo.estimate.repository.EstimateRepository;
import beyond.samdasoo.report.dto.PdfEstimateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    private final EstimateRepository estimateRepository;
    private final EstProductRepository estProductRepository;

    public byte[] generateEstReport(String templateName, PdfEstimateDto dto) {
        try {
            Estimate estimate = estimateRepository.findById(dto.getEstimateNo())
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.ESTIMATE_NOT_EXIST));
            List<EstProduct> estProducts = estProductRepository.findByEstimate(estimate);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("estimateName", estimate.getName());
            parameters.put("estimateDate", Date.from(estimate.getEstDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            // 아래 부분은 lead, cust 연결되면 가져다 쓰기
            parameters.put("company", "3DASOO");
            parameters.put("companyAddress", "서울 동작구 보라매로 87 빌딩");
            parameters.put("customerName", "고객명");
            parameters.put("customerPosition", "고객직책");
            parameters.put("employeeName", "담당사원명");
            parameters.put("totalPrice", estimate.getTotalPrice());

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(estProducts);

            ClassPathResource reportResource = new ClassPathResource("reports/" + templateName + ".jrxml");
            InputStream inputStream = reportResource.getInputStream();

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (IOException e) {
            log.error(e.getMessage());
            throw new BaseException(BaseResponseStatus.IO_ERROR);
        } catch (JRException e) {
            log.error(e.getMessage());
            throw new BaseException(BaseResponseStatus.JRE_ERROR);
        }
    }
}
