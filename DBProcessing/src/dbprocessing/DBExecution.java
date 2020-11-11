package dbprocessing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBExecution {
    public static void main(String[] args) {
        try {
            //DBConnector 객체를 불러온다.
            DBConnector dbconn = new DBConnector();

            //DBMethod 객체를 불러온다.
            DbMethods dbmet = new DbMethods();

            // ***** String Fixed VARIABLES *******
            String hsmtype = "PAYSHIELD";
            String hsmip = "192.168.30.59";
            String temperDate; // will be same as strDate when it is temper

            // **** variable initialization ****
            // 이 부분은 첫 실행을 위해 0으로 시작하기는 하지만, while 안에 들어가서는 안됨 ! =)
            double cpu_usage_sum = 0;
            int transac_sum = 0;


            while (true) {
                // ***** Date & Time {yyyy-mm-dd hh:mm:ss} ******
                Date basic_time = new Date(System.currentTimeMillis());
                SimpleDateFormat timeFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strDate = timeFormatted.format(basic_time);// USE THIS FOR DB !

                // **** Numerical VARIABLES ******
                int randstat = dbmet.randomstat(); //mode in DB SQL
                int temper = dbmet.randomtemper(randstat); // temper in DB SQL
                int tempcause = dbmet.temperCause(randstat, temper); // temper_cause in DB SQL

                // ***** STATUS NAME IN TEXT *********
                // BELOW STRING DATA WILL BE INSERTED INTO DB
                String statList [] = dbmet.insertStatName(randstat, temper, tempcause);
                String hsmstat = statList[0];
                String tempstat = statList[1];
                String causeText = statList[2];

                // PLUS OR MINUS
                double plusOrMinus = Math.random();

                // CPU 사용률 & 초당 거래량
                if ((randstat == 1) || (randstat ==4)) {
                    double cpu_usage_change = dbmet.cpustat(temper); // 변동폭
                    cpu_usage_change = dbmet.cpuLevel(cpu_usage_sum, cpu_usage_change);

                    if (plusOrMinus >=0.5) {
                        cpu_usage_change *= -1;
                        if (cpu_usage_sum > 80) {
                            cpu_usage_change *=5;
                        }
                        else if (cpu_usage_sum > 50) {
                            cpu_usage_change *=2;
                        }
                    }

                    cpu_usage_sum += cpu_usage_change;
                    if (cpu_usage_sum >= 100) {
                        cpu_usage_sum = 100;
                    }
                    else if (cpu_usage_sum < 0) {
                        cpu_usage_sum = 0;
                    }
                }
                else {
                    cpu_usage_sum = 0;
                }

                // 초당 명령 처리 건수
                if ((randstat == 1) || (randstat ==4)) {
                    int transac_change = dbmet.transacstat(); //거래량 변동폭
                    transac_change = dbmet.transacLevel(transac_sum, transac_change);

                    if (plusOrMinus > 0.5) {
                        transac_change *= -1;
                        if (transac_sum > 150) {
                            transac_change *=5;
                        }
                        else if (transac_sum > 100) {
                            transac_change *=3;
                        }
                        else if (transac_sum > 80) {
                            transac_change *=2;
                        }
                    }

                    transac_sum += transac_change;
                    if (transac_sum >= 500) {
                        transac_sum = 500;
                    }
                    else if (transac_sum < 0) {
                        transac_sum = 0;
                    }
                }
                else {
                    transac_sum = 0;
                }

                // TEMPER DATE 는 이상 없을 시 -, 있을 경우 해당 시스템 일자와 동일하게!
                if (temper !=1) {
                    temperDate = strDate;
                }
                else {
                    temperDate = "-";
                }

                // SQL COMMAND : INSERT DATE INTO TABLE =)
                String dbCommand = "insert into hsm.hsmlog (type, ip, mode, temper, temper_date, temper_cause, cpu_usage, sys_date, transac)" +
                        " values ('" + hsmtype + "','" + hsmip + "','" + hsmstat + "','" + tempstat + "','" + temperDate + "','" +
                        causeText + "','" + cpu_usage_sum + "','" + strDate + "','" + transac_sum + "')";

                dbconn.update(dbCommand);

                Thread.sleep(5000);
            }
        } catch(Exception e) {
            System.out.println("오류: " + e);
        }
    }
}