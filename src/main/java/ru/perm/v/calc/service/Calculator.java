package ru.perm.v.calc.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Калькулятор
 */
public class Calculator {

	// Ежемесячный аннуитетный процент
	private final BigDecimal percentOnMonth;
	// Начало кредита
	private LocalDate startDate;
	// Процент кредита
	private BigDecimal percent;
	// Период кредитования в месяцах
	private Integer months;
	// Сумма кредита
	private BigDecimal sumCredit;
	// Сумма к оплате
	private BigDecimal sumPay;
	// Ежемесячные платежи
	private Payment[] payments;
	// Платеж в месяц
	private final BigDecimal payOnMonth;

	public Calculator(LocalDate startDate, BigDecimal percent, Integer months,
			BigDecimal sumCredit) {
		this.startDate = startDate;
		this.percent = Formatter.format(percent);
		this.months = months;
		this.sumCredit = Formatter.format(sumCredit);
		percentOnMonth = percent.divide(new BigDecimal("12.0000"),
				MathContext.DECIMAL32);
		payOnMonth = calcPayMonth();
		this.sumPay = payOnMonth.multiply(
				BigDecimal.valueOf(months));
		calc();
	}


	public void calc() {
		generatePayments();
		BigDecimal currentLastDebt = sumCredit;
		BigDecimal payMonth = getPayMonth();
		for (int i = 0; i < months; i++) {
			payments[i].setSumLastDebtCredit(currentLastDebt);
			payments[i].setPayPercent(currentLastDebt.multiply(percentOnMonth));
			payments[i]
					.setPayDebt(payMonth.subtract(payments[i].getPayPercent()));
			currentLastDebt = currentLastDebt
					.subtract(payments[i].getPayDebt());
		}

	}

	protected void generatePayments() {
		payments = new Payment[months];
		LocalDate currentDate = startDate;
		for (int i = 0; i < months; i++) {
			// i+1, т.к. платежи со след.месяца
			Payment payment = new Payment();
			payment.setDate(currentDate.plusMonths(i + 1));
			payments[i] = payment;
		}
	}

	private BigDecimal calcPayMonth() {
		BigDecimal onePlusPpowN = BigDecimal.ONE.add(percentOnMonth)
				.pow(months);

		return Formatter.format(getSumCredit()
				.multiply(
						percentOnMonth
								.add(percentOnMonth
										.divide(
												onePlusPpowN.subtract(
														BigDecimal.ONE),
												MathContext.DECIMAL32
										)
								)
				)
		);
	}

	public BigDecimal getPayMonth() {
		return Formatter.format(payOnMonth);
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public Integer getMonths() {
		return months;
	}

	public BigDecimal getSumCredit() {
		return sumCredit;
	}

	public BigDecimal getSumPay() {
		return sumPay;
	}

	public Payment[] getPayments() {
		return payments;
	}

	@Override
	public String toString() {
		return "Calculator{" +
				"percentOnMonth=" + percentOnMonth +
				", startDate=" + startDate +
				", percent=" + percent +
				", months=" + months +
				", sumCredit=" + sumCredit +
				", sumPay=" + sumPay +
				", payments=" + Arrays.toString(payments) +
				", payOnMonth=" + payOnMonth +
				'}';
	}
}
