package ru.perm.v.calc.service;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Ежемесячные данные
 */
public class Payment {
	private LocalDate date = LocalDate.now();
	// Задолженность по кредиту
	private BigDecimal sumLastDebtCredit = BigDecimal.ZERO;
	// Платеж по процентам
	private BigDecimal payPercent =BigDecimal.ZERO;
	// Платеж по основному долгу
	private BigDecimal payDebt =BigDecimal.ZERO;

	public Payment() {
	}

	public Payment(BigDecimal sumLastDebtCredit, BigDecimal payPercent,
			BigDecimal payDebt) {
		this.sumLastDebtCredit = sumLastDebtCredit;
		this.payPercent = payPercent;
		this.payDebt = payDebt;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getSumLastDebtCredit() {
		return Formatter.format(sumLastDebtCredit);
	}

	public void setSumLastDebtCredit(BigDecimal sumLastDebtCredit) {
		this.sumLastDebtCredit = sumLastDebtCredit;
	}

	public BigDecimal getPayPercent() {
		return Formatter.format(payPercent);
	}

	public void setPayPercent(BigDecimal payPercent) {
		this.payPercent = payPercent;
	}

	public BigDecimal getPayDebt() {
		return Formatter.format(payDebt);
	}

	public void setPayDebt(BigDecimal payDebt) {
		this.payDebt = payDebt;
	}

	@Override
	public String toString() {
		return "Payment{" +
				"date=" + date +
				", sumLastDebtCredit=" + Formatter.format(sumLastDebtCredit) +
				", payPercent=" + Formatter.format(payPercent) +
				", payDebt=" + Formatter.format(payDebt) +
				'}';
	}
}
