package com.bayaran.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Expense {
	private Long trxId;
	private Date trxDate;
	private BigDecimal amount;
	private String store;
	private String description;
	private String remark;
	private User payor;
	
	public Long getTrxId() {
		return trxId;
	}
	public void setTrxId(Long trxId) {
		this.trxId = trxId;
	}
	public Date getTrxDate() {
		return trxDate;
	}
	public void setTrxDate(Date trxDate) {
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public User getPayor() {
		return payor;
	}
	public void setPayor(User payor) {
		this.payor = payor;
	}
}
