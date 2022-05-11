package by.kovalenko.controller;

import by.kovalenko.dto.PaymentDto;
import by.kovalenko.dto.PaymentSearchAttributes;
import by.kovalenko.dto.UserDto;
import by.kovalenko.service.PaymentService;
import by.kovalenko.spetification.PaymentSearchSpecification;
import by.kovalenko.util.PaymentTransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final static String EMPTY_PLACEHOLDER = "";
    private final static String PAYMENT_TYPE_PLACEHOLDER = "Payment type";

    private final PaymentService paymentService;

    @GetMapping(path = "/userPayments")
    public String userPayments(
            @SessionAttribute(name = "user", required = false) UserDto userDto,
            @RequestParam(name = "paymentTransactionType", required = false) String paymentTransactionType,
            @RequestParam(name = "fromDateString", required = false) String fromDateString,
            @RequestParam(name = "toDateString", required = false) String toDateString,
            @RequestParam(name = "minimum", required = false) Double minimum,
            @RequestParam(name = "maximum", required = false) Double maximum,
            Pageable pageable,
            Model model
    ) {

        if (EMPTY_PLACEHOLDER.equals(paymentTransactionType) || PAYMENT_TYPE_PLACEHOLDER.equals(paymentTransactionType)) {
            paymentTransactionType = null;
        }
        Date fromDate = parseStringToDate(fromDateString);
        Date toDate = parseStringToDate(toDateString);
        PaymentSearchSpecification paymentSpecification = paymentService.getPaymentSpecification(
                new PaymentSearchAttributes(userDto.getUsername(), paymentTransactionType, fromDate, toDate, minimum, maximum));
        Page<PaymentDto> paymentDtoPage = paymentService.findAll(paymentSpecification, pageable);

        model.addAttribute("paymentDtoPage", paymentDtoPage.getContent());
        model.addAttribute("page", pageable.getPageNumber() + 1);
        model.addAttribute("pageCount", paymentDtoPage.getTotalPages());
        model.addAttribute("paymentTransactionType", paymentTransactionType);
        model.addAttribute("fromDateString", fromDateString);
        model.addAttribute("toDateString", toDateString);
        model.addAttribute("minimum", minimum);
        model.addAttribute("maximum", maximum);
        model.addAttribute("transactionType1", PaymentTransactionType.BUY_IN.name());
        model.addAttribute("transactionType2", PaymentTransactionType.REPLENISHMENT.name());
        model.addAttribute("transactionType3", PaymentTransactionType.CANCELLATION.name());
        model.addAttribute("transactionType4", PaymentTransactionType.PAYED_CHARITY.name());
        model.addAttribute("transactionType5", PaymentTransactionType.PAYED_WINNERS.name());
        return "/userPayments";
    }

    private Date parseStringToDate(String string) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            if (string != null) {
                return formatter.parse(string + " 00:00");
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
