package com.company.api;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;

import java.net.InetAddress;

public class Api {
    protected final int Port = 161;
    public static final String IP = "111.222.333.444";//This is not real IP address
    protected final PDU pdu = new PDU();
    protected final CommunityTarget target = new CommunityTarget();
    protected final UdpAddress targetAddress = new UdpAddress();
    protected final OctetString octetString = new OctetString("public");

    /**
     * oid 를 받아 PDU 객체에 세팅
     * 타입은 GET
     */
    public void setPDU(String oid) {
        pdu.clear();
        pdu.add(new VariableBinding(new OID(oid)));
        pdu.setType(PDU.GET);
    }

    /**
     *
     * HSM장비 ip와 port를 이용하여 targetAddress 세팅
     */
    public void setTargetAddress(String IP) throws java.io.IOException {
        targetAddress.setInetAddress(InetAddress.getByName(IP));
        targetAddress.setPort(Port);
        target.setAddress(targetAddress);
        target.setCommunity(octetString);
        target.setVersion(SnmpConstants.version2c);
    }
}
