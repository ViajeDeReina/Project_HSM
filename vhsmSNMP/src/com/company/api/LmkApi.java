package com.company.api;

import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class LmkApi extends Api{
    private final String base_oid = ".1.3.6.1.4.1.4096.2.2.9000.4";

    /**
     *
     * hsm장비에 load된 lmk의 수를 반환
     * */
    public String getLmkNumLoaded() throws java.io.IOException {
        String oid = base_oid + ".1.0";
        setPDU(oid);
        setTargetAddress(IP);

        Snmp snmp = new Snmp(new DefaultUdpTransportMapping());

        snmp.listen();
        ResponseEvent response = snmp.send(pdu, target);

        String num = "-1";
        PDU result = response.getResponse();

        if (result == null) {
            System.out.println("Error: There is some problems.");
        } else {
            num = result.get(0).getVariable().toString();
        }

        snmp.close();
        return num;
    }

    /**
     *
     * hsm장비에 load된 Old lmk의 수를 반환
     * */
    public String getOldLmkNumLoaded() throws java.io.IOException {
        String oid = base_oid + ".3.0";
        setPDU(oid);
        setTargetAddress(IP);

        Snmp snmp = new Snmp(new DefaultUdpTransportMapping());

        snmp.listen();
        ResponseEvent response = snmp.send(pdu, target);

        String oldLmkNum = "-1";
        PDU result = response.getResponse();

        if (result == null) {
            System.out.println("Error: There is some problems.");
        } else {
            oldLmkNum = result.get(0).getVariable().toString();
        }

        snmp.close();
        return oldLmkNum;
    }

}
