package com.guomai.dushuhui.main;


public class CSession {



	private static CSession sSession = null;

	public void setData(String account, int accountType, String uid)
	{
		this.account = account;
		this.accountType = accountType;
		this.uid = uid;
	}

	private String uid;
	private String account;
	private int accountType;


	/**资产余额*/
	private double surplusMoney;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	
	private CSession() {
	}

	public static CSession getInst() {
		if (sSession == null) {
			sSession = new CSession();
		}

		return sSession;
	}


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public double getSurplusMoney() {
		return surplusMoney;
	}

	public void setSurplusMoney(double surplusMoney) {
		this.surplusMoney = surplusMoney;
	}
}
