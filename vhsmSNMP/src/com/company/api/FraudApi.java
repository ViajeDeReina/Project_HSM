package com.company.api;

import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class FraudApi extends Api{
    private final String base_oid = ".1.3.6.1.4.1.4096.2.2.9000.7";
    private final String[] returnValue = new String[]{"True", "False"};

    /**
     *
     * Fraud Detection이 켜져 있고, 분당 허용된 PIN 검증이나 시간당 허용된 PIN 검증이 초과했다면 리턴
     * */
    public String getFraudPinVerifyLimitsExceeded() throws java.io.IOException {
        String oid = base_oid + ".1.0";
        setPDU(oid);
        setTargetAddress(IP);

        Snmp snmp = new Snmp(new DefaultUdpTransportMapping());

        snmp.listen();
        ResponseEvent response = snmp.send(pdu, target);

        String TruthValue = "-1";
        PDU result = response.getResponse();

        if (result == null) {
            System.out.println("Error: There is some problems.");
        } else {
            TruthValue = result.get(0).getVariable().toString();
        }

        snmp.close();
        return returnValue[Integer.parseInt(TruthValue) - 1];
    }

    /**
     *
     * Fraud Detection이 켜져 있고, PIN Attack의 수가 허용 횟수를 넘었다면 리턴
     * */
    public String getFraudPinAttackLimitsExceeded() throws java.io.IOException {
        String oid = base_oid + ".2.0";
        setPDU(oid);
        setTargetAddress(IP);

        Snmp snmp = new Snmp(new DefaultUdpTransportMapping());

        snmp.listen();
        ResponseEvent response = snmp.send(pdu, target);

        String TruthValue = "-1";
        PDU result = response.getResponse();

        if (result == null) {
            System.out.println("Error: There is some problems.");
        } else {
            TruthValue = result.get(0).getVariable().toString();
        }

        snmp.close();
        return returnValue[Integer.parseInt(TruthValue) - 1];
    }

}
