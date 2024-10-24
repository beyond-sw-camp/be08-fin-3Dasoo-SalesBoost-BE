package beyond.samdasoo.sales.service;

import beyond.samdasoo.common.dto.SearchCond;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponseStatus;
import beyond.samdasoo.contract.entity.Contract;
import beyond.samdasoo.contract.repository.ContractRepository;
import beyond.samdasoo.sales.dto.SalesPredictionDto;
import beyond.samdasoo.sales.dto.SalesRequestDto;
import beyond.samdasoo.sales.dto.SalesResponseDto;
import beyond.samdasoo.sales.dto.SalesStatusDto;
import beyond.samdasoo.sales.entity.Sales;
import beyond.samdasoo.sales.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SalesService {

    private final SalesRepository salesRepository;

    private final ContractRepository contractRepository;

    // 전체 매출 조회
    public List<SalesResponseDto> getAllSales() {
        return salesRepository.findAll()
                .stream()
                .map(SalesResponseDto::new)
                .collect(Collectors.toList());
    }

    // 단일 매출 조회
    public SalesResponseDto getSales(Long no) {
        return salesRepository.findById(no)
                .map(SalesResponseDto::new)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.SALES_NOT_EXIST));
    }

    // 해당 연도의 월별 매출 데이터를 조회하는 메서드
    public Map<String, Integer> getMonthlySalesData(String year) {
        Map<String, Integer> monthlySales = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            String yearMonth = String.format("%s-%02d", year, i);
            // 월별 매출 데이터를 조회하여 없으면 0으로 설정
            Integer salesCount = salesRepository.findSalesCountByMonth(yearMonth).orElse(0);
            monthlySales.put(yearMonth, salesCount);
        }
        return monthlySales;
    }

    // 매출 생성
    @Transactional
    public SalesResponseDto createSales(SalesRequestDto requestDto) {

        Contract contract = contractRepository.findById(requestDto.getContractNo())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.SALES_ALREADY_EXIST));

        Sales sales = Sales.builder()
                .salesCls(requestDto.getSalesCls())
                .salesDate(requestDto.getSalesDate())
                .taxCls(requestDto.getTaxCls())
                .surtaxYn(requestDto.getSurtaxYn())
                .productCount(requestDto.getProductCount())
                .supplyPrice(requestDto.getSupplyPrice())
                .tax(requestDto.getTax())
                .price(requestDto.getPrice())
                .busiType(requestDto.getBusiType())
                .busiTypeDetail(requestDto.getBusiTypeDetail())
                .contract(contract)
                .expArrivalDate(requestDto.getExpArrivalDate())
                .note(requestDto.getNote())
                .build();

        Sales savedSales = salesRepository.save(sales);
        return new SalesResponseDto(savedSales);
    }

    // 매출 업데이트
    @Transactional
    public SalesResponseDto updateSales(Long no, SalesRequestDto requestDto) {
        Sales sales = salesRepository.findById(no)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.SALES_NOT_EXIST));

        Contract contract = contractRepository.findById(requestDto.getContractNo())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.CONTRACT_NOT_EXIST));

        // No는 기존 값 사용
        sales = Sales.builder()
                .salesNo(sales.getSalesNo())
                .salesCls(requestDto.getSalesCls())
                .salesDate(requestDto.getSalesDate())
                .taxCls(requestDto.getTaxCls())
                .surtaxYn(requestDto.getSurtaxYn())
                .productCount(requestDto.getProductCount())
                .supplyPrice(requestDto.getSupplyPrice())
                .tax(requestDto.getTax())
                .price(requestDto.getPrice())
                .busiType(requestDto.getBusiType())
                .busiTypeDetail(requestDto.getBusiTypeDetail())
                .contract(contract)
                .expArrivalDate(requestDto.getExpArrivalDate())
                .note(requestDto.getNote())
                .build();

        Sales updatedSales = salesRepository.save(sales);
        return new SalesResponseDto(updatedSales);
    }

    // 삭제를 위한 no 찾기 메소드 생성
    private Sales findSalesId(Long no) {
        return salesRepository.findById(no)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.SALES_NOT_EXIST));
    }

    // 매출 삭제
    @Transactional
    public void deleteSales(@PathVariable("no") Long no) {
        salesRepository.delete(findSalesId(no));
    }

    public List<SalesPredictionDto> getYearsPredictedSales(List<SalesResponseDto> salesData) {
        RestTemplate restTemplate = new RestTemplate();
        // 파이썬 url 저장
        String pythonApiUrl = "http://localhost:5000/api/forecast/years";

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<List<SalesResponseDto>> entity = new HttpEntity<>(salesData, headers);

        // 매출 데이터를 파이썬 API로 전달하고 예측된 매출을 받음
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                pythonApiUrl,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        return convertPredictionResponseToDto(response.getBody(), "years_total_predictions");
    }

    public List<SalesPredictionDto> getMonthlyPredictedSales(List<SalesResponseDto> salesData) {
        RestTemplate restTemplate = new RestTemplate();
        String pythonApiUrl = "http://localhost:5000/api/forecast/month";

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<List<SalesResponseDto>> entity = new HttpEntity<>(salesData, headers);

        // 매출 데이터를 파이썬 API로 전달하고 예측된 매출을 받음
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                pythonApiUrl,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        return convertPredictionResponseToDto(response.getBody(), "month_total_predictions");
    }

    public List<SalesPredictionDto> getQuarterlyPredictedSales(List<SalesResponseDto> salesData) {
        RestTemplate restTemplate = new RestTemplate();
        String pythonApiUrl = "http://localhost:5000/api/forecast/quarter";

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<List<SalesResponseDto>> entity = new HttpEntity<>(salesData, headers);

        // 매출 데이터를 파이썬 API로 전달하고 예측된 매출을 받음
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                pythonApiUrl,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        return convertPredictionResponseToDto(response.getBody(), "quarter_total_predictions");
    }

    // 파이썬 API 응답을 SalesPredictionDto 리스트로 변환하는 메서드
    private List<SalesPredictionDto> convertPredictionResponseToDto(Map<String, Object> responseBody, String predictionKey) {
        List<SalesPredictionDto> predictions = new ArrayList<>();

        List<Double> predictedPrices = (List<Double>) responseBody.get(predictionKey);
        List<String> predictedTimes = (List<String>) responseBody.get("predicted_times");
        Double growthRate = (Double) responseBody.get("growthRate");

        for (int i = 0; i < predictedPrices.size(); i++) {
            SalesPredictionDto dto = new SalesPredictionDto();
            dto.setPredictedPrice(predictedPrices.get(i));
            dto.setPredictedTime(predictedTimes.get(i));
            dto.setPredictGrowRate(growthRate);
            predictions.add(dto);
        }
        return predictions;
    }



    @Transactional(readOnly = true)
    public SalesStatusDto getSalesStatus(SearchCond searchCond) {
        return salesRepository.findSalesStatus(searchCond);
    }
}
