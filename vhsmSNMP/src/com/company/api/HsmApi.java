package com.company.api;

import com.company.OctetStringConverter;
import org.snmp4j.*;
import org.snmp4j.event.*;
import org.snmp4j.transport.*;

public class HsmApi extends Api {

	private final OctetStringConverter converter = new OctetStringConverter();

	/**
	 *
	 * 현재 hsm 장비에 설정된 시간을 string으로 반환
	 * return YYYY/MM/DD hh:mm:ss
	 */
	public String getSystemDateAndTime() throws java.io.IOException {
	   final String OID = ".1.3.6.1.4.1.4096.2.2.9000.6.1.0";

	   setPDU(OID);
	   setTargetAddress(IP);
	   
       Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
       
       snmp.listen();
       ResponseEvent response = snmp.send(pdu, target);
       
       String dateAndTime = "-1";
       PDU result = response.getResponse();
       
       if (result == null) {
           System.out.println("Error: There is some problems.");
       } else {
    	   dateAndTime = converter.convertDateAndTime(result.get(0).getVariable().toString());
       }
       
       snmp.close();
       return dateAndTime;
	}

	/**
	 * hsm장비의 시리얼 넘버를 가지고 옴
	 *
	 */
	public String getSystemSerialNum() throws java.io.IOException {
		   final String OID = ".1.3.6.1.4.1.4096.2.2.9000.6.2.0";

		   setPDU(OID);
		   setTargetAddress(IP);
		   
	       Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
	       
	       snmp.listen();
	       ResponseEvent response = snmp.send(pdu, target);
	       
	       String serialNum = "-1";
	       PDU result = response.getResponse();
	       
	       if (result == null) {
	           System.out.println("Error: There is some problems.");
	       } else {
	    	   serialNum = result.get(0).getVariable().toString();
	       }
	       
	       snmp.close();
	       return serialNum;

	}

	/**
	 * hsm 장비의 현재 상태를 string 으로 반환
	 * 상태는 "Unavaliable","Online","Offline","Secure" 중 하나
	 *
	 */
	public String getDeviceState() throws java.io.IOException {
		   String OID = ".1.3.6.1.4.1.4096.2.2.9000.3.1.0";
		   String[] results = new String[]{"Unavaliable","Online","Offline","Secure"};
		   setPDU(OID);
		   setTargetAddress(IP);
		   
	       Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
	       
	       snmp.listen();
	       ResponseEvent response = snmp.send(pdu, target);
	       
	       String state = "-1";
	       PDU result = response.getResponse();
	       
	       if (result == null) {
	           System.out.println("Error: There is some problems.");
	       } else {
	    	   state = results[Integer.valueOf(result.get(0).getVariable().toString()) - 1];
	       }
	       
	       snmp.close();
	       return state;
	}

	/**
	 *
	 * hsm 장비에 현재 문제가 있는지 상태를 string으로 반환
	 * 상태는 "Unknown","Ok","Temper" 중 하나
	 */
	public String getDeviceTemper() throws java.io.IOException {
		   String OID = ".1.3.6.1.4.1.4096.2.2.9000.3.2.1.0";
		   String[] results = new String[]{"Unknown","Ok","Temper"};
		   setPDU(OID);
		   setTargetAddress(IP);
		   
	       Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
	       
	       snmp.listen();
	       ResponseEvent response = snmp.send(pdu, target);
	       
	       String state = "-1";
	       PDU result = response.getResponse();
	       
	       if (result == null) {
	           System.out.println("Error: There is some problems.");
	       } else {
	    	   state = results[Integer.valueOf(result.get(0).getVariable().toString()) - 1];
	       }
	       
	       snmp.close();
	       return state;
	}

	/**
	 *
	 * 가장 최근에 발생한 문제 언제 발생했는지 날짜를 반환
	 * 	return YYYY/MM/DD hh:mm:ss
	 */
	public String getDeviceTemperDate() throws java.io.IOException {
		   String OID = ".1.3.6.1.4.1.4096.2.2.9000.3.2.2.0";
		   setPDU(OID);
		   setTargetAddress(IP);
		   
	       Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
	       
	       snmp.listen();
	       ResponseEvent response = snmp.send(pdu, target);
	       
	       String dateAndTime = "-1";
	       PDU result = response.getResponse();
	       
	       if (result != null) {
	    	   dateAndTime = converter.convertDateAndTime(result.get(0).getVariable().toString());
	       }
	       else {
	           System.out.println("Error: There is some problems.");
	       }
	       
	       snmp.close();
	       return dateAndTime;
	}

	/**
	 *
	 * 가장 최근에 발생한 문제가 무엇이엇는지 string 으로 반환
	 * 1. "Unavaliable"
	 * 2. "TempOutOfRange"
	 * 3. "BatteryLow"
	 * 4. "EraseButtonPressed"
	 * 5. "SecurityProcessorWatchdog"
	 * 6. "SecurityProcessorRestart"
	 * 7. "PowerTooHigh"
	 * 8. "MotionDetected"
	 * 9. "CaseTempered"
	 * 10."TSPPModule"
	 * 11."General"
	 */
	public String getDeviceLastTemperCause() throws java.io.IOException {
		   String OID = ".1.3.6.1.4.1.4096.2.2.9000.3.2.3.0";
		   String[] results = new String[]{"Unavaliable","TempOutOfRange","BatteryLow","EraseButtonPressed",
				   "SecurityProcessorWatchdog", "SecurityProcessorRestart","PowerTooHigh","MotionDetected",
				   "CaseTempered","TSPPModule","General"};
		   setPDU(OID);
		   setTargetAddress(IP);
		   
	       Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
	       
	       snmp.listen();
	       ResponseEvent response = snmp.send(pdu, target);
	       
	       String state = "-1";
	       PDU result = response.getResponse();
	       
	       if (result == null) {
	           System.out.println("Error: There is some problems.");
	       } else {
	    	   state = results[Integer.valueOf(result.get(0).getVariable().toString()) - 1];
	       }
	       
	       snmp.close();
	       return state;
	}
}
