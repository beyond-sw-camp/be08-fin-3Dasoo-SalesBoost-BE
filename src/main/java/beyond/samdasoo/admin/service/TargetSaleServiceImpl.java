package beyond.samdasoo.admin.service;

import beyond.samdasoo.admin.dto.TargetSaleRequestDto;
import beyond.samdasoo.admin.dto.TargetSaleResponseDto;
import beyond.samdasoo.admin.entity.Product;
import beyond.samdasoo.admin.entity.TargetSale;
import beyond.samdasoo.admin.repository.ProductRepository;
import beyond.samdasoo.admin.repository.TargetSaleRepository;
import beyond.samdasoo.common.exception.BaseException;
import beyond.samdasoo.user.entity.User;
import beyond.samdasoo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static beyond.samdasoo.common.response.BaseResponseStatus.Product_NOT_EXIST;
import static beyond.samdasoo.common.response.BaseResponseStatus.USER_NOT_EXIST;


@Service
@RequiredArgsConstructor
public class TargetSaleServiceImpl implements TargetSaleService{
    @Autowired
    private final TargetSaleRepository targetSaleRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ProductRepository productRepository;


    @Override
    public void addTargetSale(TargetSaleRequestDto request) {

        Product product = productRepository.getByProdNo(request.getProductNo());
        User user = userRepository.getReferenceById(request.getUserId());

        if(!userRepository.existsById(request.getUserId())){
            throw new BaseException(USER_NOT_EXIST);
        }

        if(product == null){
            throw new BaseException(Product_NOT_EXIST);
        }

        List<TargetSale> targetSales = targetSaleRepository.findByUserAndProductAndYear(user, product, request.getYear());

        if(targetSales.isEmpty()){
            if(request.getSum() != 0){

                int sum = request.getSum() - (request.getSum() % 12);

                for(int i = 0; i < 12; i ++){
                    TargetSale targetSale = TargetSale.builder()
                            .user(user)
                            .product(product)
                            .monthTarget(sum/12)
                            .month(i+1)
                            .year(request.getYear())
                            .build();

                    targetSaleRepository.save(targetSale);

                }
            }else{
                for(int i = 0; i < 12; i++){
                    TargetSale targetSale = null;
                    if(i+1 == request.getMonth()){
                        targetSale = TargetSale.builder()
                                .user(user)
                                .product(product)
                                .monthTarget(request.getMonthTarget())
                                .month(i+1)
                                .year(request.getYear())
                                .build();
                    }else{
                        targetSale = TargetSale.builder()
                                .user(user)
                                .product(product)
                                .monthTarget(0)
                                .month(i+1)
                                .year(request.getYear())
                                .build();
                    }

                    targetSaleRepository.save(targetSale);
                }
            }
        }else{
            if(request.getSum() != 0){
                int sum = request.getSum() - (request.getSum() % 12);

                for(int i = 0; i < 12; i ++) {
                    targetSales.get(i).setMonthTarget(sum/12);

                    targetSaleRepository.save(targetSales.get(i));
                }
            }else{
                for(int i = 0; i < 12; i++){
                    if(targetSales.get(i).getMonth() == request.getMonth()){
                        targetSales.get(i).setMonthTarget(request.getMonthTarget());

                        targetSaleRepository.save(targetSales.get(i));
                    }
                }
            }
        }

    }

    @Override
    public List<TargetSaleResponseDto> getTargetSaleByUserId(Long userNo, int year) {
        User user = userRepository.getReferenceById(userNo);

        if(!userRepository.existsById(userNo)){
            throw new BaseException(USER_NOT_EXIST);
        }

        List<TargetSale> targetSales = targetSaleRepository.findByUserAndYear(user, year);

        return targetSales.stream()
                .map(targetSale -> new TargetSaleResponseDto(targetSale))
                .collect(Collectors.toList());
    }
}
