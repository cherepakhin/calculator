package ru.perm.v.calc.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Formatter {
	public static BigDecimal format(BigDecimal i) {
		return i.setScale(2, RoundingMode.HALF_UP);
	}

}
