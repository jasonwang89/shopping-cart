package com.shopping.cart.model;

public class ValidateVersion {
	private boolean isValid;
	private int version;

	public ValidateVersion() {
	}

	public ValidateVersion(boolean isValid, int version) {
		this.isValid = isValid;
		this.version = version;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean valid) {
		isValid = valid;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
