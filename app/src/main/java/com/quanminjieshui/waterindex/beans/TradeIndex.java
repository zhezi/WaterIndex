package com.xingyoucai.irm.jsl.model;

public class TradeIndex {
	private int trade_id;
	private int trade_uid;
	private String avatar;
	private String user_nickname;
	private String sold;
	private String sold_rate;
	private String total;
	private String pay_type_bank_card;
	private String pay_type_alipay;
	private String pay_type_wechat;
	private String pay_min;
	private int pay_timeout;

	public void setTrade_id(int trade_id) {
		this.trade_id = trade_id;
	}

	public int getTrade_id() {
		return trade_id;
	}

	public void setTrade_uid(int trade_uid) {
		this.trade_uid = trade_uid;
	}

	public int getTrade_uid() {
		return trade_uid;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setSold(String sold) {
		this.sold = sold;
	}

	public String getSold() {
		return sold;
	}

	public void setSold_rate(String sold_rate) {
		this.sold_rate = sold_rate;
	}

	public String getSold_rate() {
		return sold_rate;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTotal() {
		return total;
	}

	public void setPay_type_bank_card(String pay_type_bank_card) {
		this.pay_type_bank_card = pay_type_bank_card;
	}

	public String getPay_type_bank_card() {
		return pay_type_bank_card;
	}

	public void setPay_type_alipay(String pay_type_alipay) {
		this.pay_type_alipay = pay_type_alipay;
	}

	public String getPay_type_alipay() {
		return pay_type_alipay;
	}

	public void setPay_type_wechat(String pay_type_wechat) {
		this.pay_type_wechat = pay_type_wechat;
	}

	public String getPay_type_wechat() {
		return pay_type_wechat;
	}

	public void setPay_min(String pay_min) {
		this.pay_min = pay_min;
	}

	public String getPay_min() {
		return pay_min;
	}

	public void setPay_timeout(int pay_timeout) {
		this.pay_timeout = pay_timeout;
	}

	public int getPay_timeout() {
		return pay_timeout;
	}
}
