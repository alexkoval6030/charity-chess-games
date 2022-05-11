package by.kovalenko.controller;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.RefereeSearchAttributes;
import by.kovalenko.dto.WalletDto;
import by.kovalenko.service.GameService;
import by.kovalenko.service.RefereeService;
import by.kovalenko.spetification.RefereeSearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@SessionAttributes("wallet")
public class RefereeController {
    private final RefereeService refereeService;
    private final GameService gameService;

    @GetMapping(path = "/gamesAvailableForProcessing")
    public String gamesAvailableForProcessing(
            @RequestParam(name = "creatorUsername", required = false) String creatorUsername,
            @RequestParam(name = "fromDateString", required = false) String fromDateString,
            @RequestParam(name = "toDateString", required = false) String toDateString,
            @RequestParam(name = "minimum", required = false) Double minimum,
            @RequestParam(name = "maximum", required = false) Double maximum,
            Pageable pageable,
            Model model
    ) {
        Date fromDate = parseStringToDate(fromDateString);
        Date toDate = parseStringToDate(toDateString);
        RefereeSearchSpecification specification = refereeService.getSpecification(
                new RefereeSearchAttributes(creatorUsername, fromDate, toDate, minimum, maximum));
        Page<GameDto> gameDtoPage = refereeService.findAll(specification, pageable);
        model.addAttribute("gameDtoPage", gameDtoPage.getContent());
        model.addAttribute("page", pageable.getPageNumber() + 1);
        model.addAttribute("pageCount", gameDtoPage.getTotalPages());
        model.addAttribute("creatorUsername", creatorUsername);
        model.addAttribute("fromDateString", fromDateString);
        model.addAttribute("toDateString", toDateString);
        model.addAttribute("minimum", minimum);
        model.addAttribute("maximum", maximum);
        return "/gamesAvailableForProcessing";
    }

    @GetMapping(path = "resultCreatorWin")
    public String resultCreatorWin(
            @SessionAttribute(name = "wallet") WalletDto walletDto,
            @RequestParam(name = "gameId") UUID gameId,
            @RequestParam(name = "result") Boolean result,
            Model model
    ) {
        gameService.processingResultCreatorWin(walletDto, gameId, result);
        model.addAttribute("page", 0);
        model.addAttribute("size", 5);
        return "redirect:/gamesAvailableForProcessing";
    }

    @GetMapping(path = "resultCreatorLose")
    public String resultCreatorLose(
            @SessionAttribute(name = "wallet") WalletDto walletDto,
            @RequestParam(name = "gameId") UUID gameId,
            @RequestParam(name = "result") Boolean result,
            Model model
    ) {
        gameService.processingResultCreatorLose(walletDto, gameId, result);
        model.addAttribute("page", 0);
        model.addAttribute("size", 5);
        return "redirect:/gamesAvailableForProcessing";
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
