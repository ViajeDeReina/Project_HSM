package com.company.api;


import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class UtilApi extends Api{
    private final String base_oid = ".1.3.6.1.4.1.4096.2.2.9000.2";

    /**
     *
     * 지난 1초동안 hsm 장비에 의해 처리된 명령어를 표시한다.
     * 0~100 사이의 값으로, 1초간 처리한 명령어의 수 / 최대로 처리 가능한 명령어 수 로 계산됨
     */
    public String getUtilLoad() throws java.io.IOException {
        String oid = base_oid + ".1.0";
        setPDU(oid);
        setTargetAddress(IP);

        Snmp snmp = new Snmp(new DefaultUdpTransportMapping());

        snmp.listen();
        ResponseEvent response = snmp.send(pdu, target);

        String gauge = "-1";
        PDU result = response.getResponse();

        if (result == null) {
            System.out.println("Error: There is some problems.");
        } else {
            gauge = result.get(0).getVariable().toString();
        }

        snmp.close();
        return gauge;
    }

    /**
     *
     * 지난 1초간 hsm 장비가 처리한 명령의 리스트를 반환
     */
    public String getUtilHostCmdVolume() throws java.io.IOException {
        String oid = base_oid + ".2.0";
        setPDU(oid);
        setTargetAddress(IP);

        Snmp snmp = new Snmp(new DefaultUdpTransportMapping());

        snmp.listen();
        ResponseEvent response = snmp.send(pdu, target);

        String str = "-1";
        PDU result = response.getResponse();

        if (result == null) {
            System.out.println("Error: There is some problems.");
        } else {
            str = result.get(0).getVariable().toString();
        }

        snmp.close();
        return str;
    }
}
