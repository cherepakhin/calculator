package ru.perm.v.calc.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.perm.v.calc.dto.CalculatorDTO;
import ru.perm.v.calc.service.Calculator;

@Controller
@RequestMapping("")
public class CalcCtrl {

	private final static Logger LOG = LoggerFactory.getLogger(CalcCtrl.class);

	@Value("${default-percent}")
	BigDecimal percent;

	@GetMapping("/")
	public String start(Model model) {
		CalculatorDTO calculatorDTO = new CalculatorDTO();
		Calculator calculator = new Calculator(LocalDate.now(), percent, 1,
				BigDecimal.ZERO);
		model.addAttribute("calculator", calculator);
		model.addAttribute("calculatorDTO", calculatorDTO);
		return "calcpage";
	}

	@PostMapping("/")
	public String calc(
			@ModelAttribute("calculatorDTO") @Valid CalculatorDTO calculatorDTO,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("calculatorDTO", calculatorDTO);
			Calculator calculator = new Calculator(LocalDate.now(), percent, 1,
					BigDecimal.ZERO);
			model.addAttribute("calculator", calculator);
			return "calcpage";
		}
		LOG.info(calculatorDTO.toString());
		Calculator calculator = new Calculator(LocalDate.now(), percent,
				calculatorDTO.getMonths(), calculatorDTO.getCredit());
		model.addAttribute("calculatorDTO", calculatorDTO);
		model.addAttribute("calculator", calculator);
		return "calcpage";
	}
}
