package viewModel;

import models.*;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
public class Controller {
    private final DepositHandler depositHandler = new DepositHandler();
    private final CreditHandler creditHandler = new CreditHandler();
    private final MathHandler mathHandler = new MathHandler();

    @PostMapping(value = "/constant", produces = MediaType.APPLICATION_JSON_VALUE)
    public MathResponse getConstant(@RequestBody MathRequest mathRequest) {
        return mathHandler.getConstant(mathRequest);
    }

    @GetMapping(value="/properties")
    public CalcProperties getProperties() {
        return new PropertiesHandler().getProperties();
    }

    @PostMapping(value = "/credit", produces = MediaType.APPLICATION_JSON_VALUE)
    public CreditResponse getCredit(@RequestBody CreditRequest request) {
        LoggerFactory.getLogger(Controller.class).info("controller request " + request);
        return creditHandler.getCredit(request);
    }

    @PostMapping(value = "/deposit", produces = MediaType.APPLICATION_JSON_VALUE)
    public DepositResponse getCredit(@RequestBody DepositRequest request) {
        return depositHandler.getDeposit(request);
    }

    @PostMapping(value = "/graph", produces = MediaType.APPLICATION_JSON_VALUE)
    public MathResponse getGraph(@RequestBody MathRequest mathRequest) {
        return mathHandler.getGraph(mathRequest);
    }

    @GetMapping(value = "/getHistory/{inputNum}")
    public String getHistory(@PathVariable String inputNum) throws IOException {
        return mathHandler.getHistory(inputNum);
    }

    @GetMapping(value = "/clearHistory")
    public void clearHistory() {
        mathHandler.clearHistory();
    }

    @GetMapping(value = "/exit")
    public void exitApp() {
        LoggerFactory.getLogger(Controller.class).info("[exitApp]");
        System.exit(0);
    }

}