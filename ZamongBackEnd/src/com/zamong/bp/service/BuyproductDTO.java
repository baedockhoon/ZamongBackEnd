package com.zamong.bp.service;

import java.util.Date;

/*
 * 
 *  BP_NO                                     NOT NULL NUMBER
 BP_REGIDATE                               NOT NULL DATE
 PD_NO                                     NOT NULL NUMBER
 ME_NO                                     NOT NULL NUMBER
 BP_PRICE                                  NOT NULL NVARCHAR2(10)
 BP_BUYWAY                                 NOT NULL NVARCHAR2(10)
 * 
 */
public class BuyproductDTO {

	private String bp_no;
	private java.util.Date bp_regidate;
	private String pd_no;
	private String me_no;
	private String bp_price;
	private String bp_buyway;
	public String getBp_no() {
		return bp_no;
	}
	public void setBp_no(String bp_no) {
		this.bp_no = bp_no;
	}
	public java.util.Date getBp_regidate() {
		return bp_regidate;
	}
	public void setBp_regidate(java.util.Date bp_regidate) {
		this.bp_regidate = bp_regidate;
	}
	public String getPd_no() {
		return pd_no;
	}
	public void setPd_no(String pd_no) {
		this.pd_no = pd_no;
	}
	public String getMe_no() {
		return me_no;
	}
	public void setMe_no(String me_no) {
		this.me_no = me_no;
	}
	public String getBp_price() {
		return bp_price;
	}
	public void setBp_price(String bp_price) {
		this.bp_price = bp_price;
	}
	public String getBp_buyway() {
		return bp_buyway;
	}
	public void setBp_buyway(String bp_buyway) {
		this.bp_buyway = bp_buyway;
	}
	public BuyproductDTO(String bp_no, Date bp_regidate, String pd_no, String me_no, String bp_price,
			String bp_buyway) {
		this.bp_no = bp_no;
		this.bp_regidate = bp_regidate;
		this.pd_no = pd_no;
		this.me_no = me_no;
		this.bp_price = bp_price;
		this.bp_buyway = bp_buyway;
	}
	public BuyproductDTO() {}
	
	
}
