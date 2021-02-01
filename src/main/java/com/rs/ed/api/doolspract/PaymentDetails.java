package com.rs.ed.api.doolspract;

public class PaymentDetails {
	double totalPay;
	int discount;
	public double getTotalPay() {
		return totalPay;
	}

	public int getDiscount() {
		return discount;
	}

	public void setTotalPay(double totalPay) {
		this.totalPay=totalPay;
	}

	public void setOffer(int discount) {
		this.discount=discount;
	}
	
	
	

}
