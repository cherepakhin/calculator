package ru.perm.v.calc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;

class CalculatorTest {

	// Период в месяцах
	final Integer MONTHS = 12;
	final LocalDate START_DATE = LocalDate.of(2020, Month.FEBRUARY, 1);
	final BigDecimal PERCENT = new BigDecimal("0.20");
	final BigDecimal CREDIT = new BigDecimal("2000.00");

	@Test
	void generatePayments() {
		Calculator calculator = new Calculator(START_DATE,
				PERCENT,
				MONTHS,
				CREDIT);
		assertEquals(MONTHS, calculator.getPayments().length);
		assertEquals(LocalDate.of(2021, Month.FEBRUARY, 1),
				calculator.getPayments()[MONTHS - 1].getDate());
	}

	@Test
	void getPayMonth() {
		Calculator calculator = new Calculator(START_DATE,
				PERCENT,
				MONTHS,
				CREDIT);
		BigDecimal MONTH_PAY = new BigDecimal("185.27");
		assertEquals(MONTH_PAY, calculator.getPayMonth());
	}

	@Test
	void checkSumPayCredit() {
		Calculator calculator = new Calculator(START_DATE,
				PERCENT,
				MONTHS,
				CREDIT);
		assertEquals(new BigDecimal("2223.24"), calculator.getSumPay());
	}

	@Test
	void checkSumLastDebt() {
		Calculator calculator = new Calculator(START_DATE,
				PERCENT,
				MONTHS,
				CREDIT);
		// Оплата основного долга
		BigDecimal PAY_MONTH_CREDIT = new BigDecimal("151.94");
		assertEquals(MONTHS, calculator.getPayments().length);
		assertEquals(CREDIT.subtract(PAY_MONTH_CREDIT),
				calculator.getPayments()[1].getSumLastDebtCredit().setScale(2
						, RoundingMode.HALF_UP)
		);

		assertEquals(new BigDecimal("182.21"),
				calculator.getPayments()[MONTHS - 1].getSumLastDebtCredit().setScale(2
						, RoundingMode.HALF_UP)
		);
	}
}