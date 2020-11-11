package com.company;

import com.company.api.*;
import com.company.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SnmpClient{

    public static void main(String[] args) {

    	HsmApi hsmApi = new HsmApi();
		UtilApi utilApi = new UtilApi();
		LmkApi lmkApi = new LmkApi();
		FraudApi fraudApi = new FraudApi();
		DBConnection con = new DBConnection();
		Connection conn = con.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int temp = 10;

    	while(true) {
    		try {
    			String System_time = hsmApi.getSystemDateAndTime();
    			String SerialNumber = hsmApi.getSystemSerialNum();

    			String state = hsmApi.getDeviceState();
				String temper = hsmApi.getDeviceTemper();
				String temperDate = hsmApi.getDeviceTemperDate();
				String temperCause = hsmApi.getDeviceLastTemperCause();

				String load = utilApi.getUtilLoad();		// 명령어 처리 수
				String hostCmdVolume = utilApi.getUtilHostCmdVolume();

				String lmkLoaded = lmkApi.getLmkNumLoaded();
				String oldLmkLoaded = lmkApi.getOldLmkNumLoaded();

				System.out.println(System_time);
				System.out.println(state);
				System.out.println(temper);
				System.out.println(temperDate);
				System.out.println(temperCause);
				System.out.println(load);

				/*
					DB connection
				 */
				String sql = "INSERT INTO hsmlog (`type`, `ip`, `mode`, `temper`, `temper_date`, `temper_cause`, `load`, `sys_date`) VALUES (?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1,"payshield");
				pstmt.setString(2, Api.IP);
				pstmt.setString(3, state);
				pstmt.setString(4, temper);
				pstmt.setString(5, temperDate);
				pstmt.setString(6, temperCause);
				pstmt.setString(7, String.valueOf(temp));
				pstmt.setString(8, System_time);

				int count = pstmt.executeUpdate();
				System.out.println(count);

				temp +=10;
				Thread.sleep(5000);

			} catch (Exception ex) {
    			System.out.println ("error : " + ex.getMessage());
    		}

    	}
    }
}