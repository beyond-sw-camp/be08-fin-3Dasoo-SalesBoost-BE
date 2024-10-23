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

        Product product = productRepository.getByName(request.getProdName());
        User user = userRepository.getUserByName(request.getUserName());

        if (user == null) {
            throw new BaseException(USER_NOT_EXIST);
        }

        if (product == null) {
            throw new BaseException(Product_NOT_EXIST);
        }

        List<TargetSale> targetSales = targetSaleRepository.findByUserAndProductAndYear(user, product, request.getYear());

        if (targetSales.isEmpty()) {
            if (request.getSum() != 0) {
                int sum = request.getSum() - (request.getSum() % 12);
                for (int i = 0; i < 12; i++) {

                    int monthTarget = sum / 12;

                    TargetSale targetSale = TargetSale.builder()
                            .user(user)
                            .product(product)
                            .monthTarget(monthTarget)
                            .month(i + 1)
                            .year(request.getYear())
                            .build();

                    targetSaleRepository.save(targetSale);
                }
            } else {
                for (int i = 0; i < 12; i++) {
                    int monthTarget = request.getMonthTargets().get(i);

                    TargetSale targetSale = TargetSale.builder()
                            .user(user)
                            .product(product)
                            .monthTarget(monthTarget)
                            .month(i + 1)
                            .year(request.getYear())
                            .build();

                    targetSaleRepository.save(targetSale);
                }
            }
        } else {
            if (request.getSum() != 0) {
                int sum = request.getSum() - (request.getSum() % 12);
                for (int i = 0; i < 12; i++) {
                    int newMonthTarget = sum / 12;

                    if (targetSales.get(i).getMonthTarget() != newMonthTarget) {
                        targetSales.get(i).setMonthTarget(newMonthTarget);
                        targetSaleRepository.save(targetSales.get(i));
                    }
                }
            } else {
                for (int i = 0; i < 12; i++) {
                    int newMonthTarget = 0;
                    if (request.getMonthTargets() != null && request.getMonthTargets().size() > i) {
                        if(request.getMonthTargets().get(i) == null){
                            newMonthTarget = 0;
                        }else{
                            newMonthTarget = request.getMonthTargets().get(i);
                        }

                        if (targetSales.get(i).getMonthTarget() != newMonthTarget) {
                            targetSales.get(i).setMonthTarget(newMonthTarget);
                            targetSaleRepository.save(targetSales.get(i));
                        }
                    }
                }
            }
        }
    }


    @Override
    public List<TargetSaleResponseDto> getTargetSaleByUserName(String userName, int year) {

        User user = userRepository.getUserByName(userName);

        if(user == null){
            throw new BaseException(USER_NOT_EXIST);
        }

        List<TargetSale> targetSales = targetSaleRepository.findByUserAndYear(user, year);

        return targetSales.stream()
                .map(targetSale -> new TargetSaleResponseDto(targetSale))
                .collect(Collectors.toList());
    }
}
