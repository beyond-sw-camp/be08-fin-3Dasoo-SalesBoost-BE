package beyond.samdasoo.sales.service;

import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.common.response.BaseResponseStatus;
import beyond.samdasoo.contract.entity.Contract;
import beyond.samdasoo.contract.repository.ContractRepository;
import beyond.samdasoo.sales.dto.SalesRequestDto;
import beyond.samdasoo.sales.dto.SalesResponseDto;
import beyond.samdasoo.sales.entity.Sales;
import beyond.samdasoo.sales.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
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
    public SalesResponseDto getSales(Long salesNo) {
        return salesRepository.findById(salesNo)
                .map(SalesResponseDto::new)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.SALES_NOT_EXIST));
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
    public SalesResponseDto updateSales(Long salesNo, SalesRequestDto requestDto) {
        Sales sales = salesRepository.findById(salesNo)
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

    // 매출 삭제
    @Transactional
    public void deleteSales(@PathVariable("no") Long no) {
        if (!salesRepository.existsById(no)) {
            throw new BaseException(BaseResponseStatus.SALES_NOT_EXIST);
        }
        salesRepository.deleteById(no);
    }
}
