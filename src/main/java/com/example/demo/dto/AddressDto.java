package com.example.demo.dto;

	public class AddressDto {

	    private String city;
	    private String state;
	    private String country;
	    private String pincode;
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getPincode() {
			return pincode;
		}
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}
		public AddressDto() {
			super();
		}
		public AddressDto(String city, String state, String country, String pincode) {
			super();
			this.city = city;
			this.state = state;
			this.country = country;
			this.pincode = pincode;
		}

	   
	}


