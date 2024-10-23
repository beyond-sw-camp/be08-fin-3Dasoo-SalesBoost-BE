package beyond.samdasoo.sales.repository;

import beyond.samdasoo.common.dto.SearchCond;
import beyond.samdasoo.contract.entity.QContract;
import beyond.samdasoo.customer.entity.QCustomer;
import beyond.samdasoo.estimate.entity.QEstimate;
import beyond.samdasoo.lead.Entity.QLead;
import beyond.samdasoo.proposal.entity.QProposal;
import beyond.samdasoo.sales.dto.SalesStatusDto;
import beyond.samdasoo.sales.entity.QSales;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SalesRepositoryImpl implements SalesRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public SalesStatusDto findSalesStatus(SearchCond searchCond) {
        QSales sales = QSales.sales;
        QContract contract = QContract.contract;
        QEstimate estimate = QEstimate.estimate;
        QProposal proposal = QProposal.proposal;
        QLead lead = QLead.lead;
        QCustomer customer = QCustomer.customer;

        BooleanBuilder builder = new BooleanBuilder();

        if (searchCond.getUserNo() != null && searchCond.getUserNo() > 0) {
            builder.and(customer.user.id.eq(searchCond.getUserNo()));
        }

        LocalDate searchDate = searchCond.getSearchDate();
        String year = String.valueOf(searchDate.getYear());
        String month = String.format("%02d", searchDate.getMonthValue());

        Integer monthResult = queryFactory.select(sales.price.sum())
                .from(sales)
                .join(sales.contract, contract)
                .join(contract.estimate, estimate)
                .join(estimate.proposal, proposal)
                .join(proposal.lead, lead)
                .join(lead.customer, customer)
                .where(builder
                        .and(sales.salesDate.year().eq(Integer.parseInt(year)))
                        .and(sales.salesDate.month().eq(Integer.parseInt(month)))
                )
                .fetchOne();

        int monthSales = Optional.ofNullable(monthResult).orElse(0);

        Integer yearResult = queryFactory
                .select(sales.price.sum())
                .from(sales)
                .join(sales.contract, contract)
                .join(contract.estimate, estimate)
                .join(estimate.proposal, proposal)
                .join(proposal.lead, lead)
                .join(lead.customer, customer)
                .where(builder
                        .and(sales.salesDate.year().eq(Integer.parseInt(year)))
                )
                .fetchOne();

        int yearSales = Optional.ofNullable(yearResult).orElse(0);

        return new SalesStatusDto(monthSales, yearSales);
    }
}
