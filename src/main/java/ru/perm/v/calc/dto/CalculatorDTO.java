package ru.perm.v.calc.dto;

import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class CalculatorDTO {
	@Min(12)
	@Max(60)
	private Integer months=0;
	private BigDecimal credit = BigDecimal.ZERO;

	public CalculatorDTO() {
	}

	public CalculatorDTO(Integer months, BigDecimal credit) {
		this.months = months;
		this.credit = credit;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "CalculatorDTO{" +
				"months=" + months +
				", credit=" + credit +
				'}';
	}
}
