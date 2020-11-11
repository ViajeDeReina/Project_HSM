package com.company;

public class OctetStringConverter {
	
	public String convertDateAndTime(String octetString) {
		
		if(octetString.length() == 0) 
			return "None";
		

		String y1 = octetString.substring(0,2);
		String y2 = octetString.substring(3,5);
		String M = octetString.substring(6,8);
		String d = octetString.substring(9,11);
		String h = octetString.substring(12,14);
		String m = octetString.substring(15,17);
		String s = "00";
		
		if(octetString.length() == 20) 
			s = octetString.substring(18,20);			
		
		int year = Integer.parseInt(y1, 16) * 16 * 16 + Integer.parseInt(y2,16);
		String month = makeFormat(Integer.parseInt(M,16)); 
		String day = makeFormat(Integer.parseInt(d,16));
		String hour = makeFormat(Integer.parseInt(h,16)); 
		String minite = makeFormat(Integer.parseInt(m,16)); 
		String second = makeFormat(Integer.parseInt(s,16)); 

		return year + "-" + month + "-" + day + " " + hour + ":" + minite + ":" + second; 	
	}
	
	public String makeFormat(int num) {
		if(num/10 == 0) {
			return "0" + String.valueOf(num);
		}
		else {
			return String.valueOf(num);
		}
	}
}
