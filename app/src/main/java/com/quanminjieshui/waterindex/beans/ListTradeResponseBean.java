package com.quanminjieshui.waterindex.beans;

import java.util.List;

public class ListTradeResponseBean {
	private TradeList trade_list;
	private List<PayInfoList> pay_info_list;
	private String total;

	public TradeList getTrade_list() {
		return trade_list;
	}

	public void setTrade_list(TradeList trade_list) {
		this.trade_list = trade_list;
	}

	public List<PayInfoList> getPay_info_list() {
		return pay_info_list;
	}

	public void setPay_info_list(List<PayInfoList> pay_info_list) {
		this.pay_info_list = pay_info_list;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public static class PayInfoList{

	}
}
