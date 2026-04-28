package org.example;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    @Operation(summary = "Multiply a number by two")
    @GetMapping("/multiply")
    public int multiplyByTwo(
            @Parameter(description = "The number to be multiplied")
            @RequestParam int number) {
        return number * 2;
    }
}
