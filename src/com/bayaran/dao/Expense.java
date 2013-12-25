package com.bayaran.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

public class Expense {
	private BigInteger trxId;
	private Calendar trxDate;
	private BigDecimal amount;
	private String store;
	
	public BigInteger getTrxId() {
		return trxId;
	}
	public void setTrxId(BigInteger trxId) {
		this.trxId = trxId;
	}
	public Calendar getTrxDate() {
		return trxDate;
	}
	public void setTrxDate(Calendar trxDate) {
		this.trxDate = trxDate;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
}
