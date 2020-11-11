package dbprocessing;

import java.lang.Math;

public class DbMethods {
    public static String[] insertStatName(int randstat, int temper, int tempcause) {

        String[] statlist = {"Online", "Unavailable", "Offline", "Secure"}; // as MODE
        String[] templist = {"Ok", "Temper", "Unknown"}; // as TEMPER
        String[] causelist = {"Unavailable", "TempOutOfRange", "BatteryLow","EraseButtonPressed",
                "SecurityProcessorWatchdog","SecurityProcessorRestart", "PowerTooHigh",
                "MotionDetected","CaseTempered","TSPPModule","General"}; // as TEMPER_CAUSE

        String stat = statlist[randstat-1]; // MODE
        String temp = templist[temper-1]; // TEMPER
        String cause = causelist[tempcause-1]; // TEMPER_CAUSE

        String statText[] = {stat, temp, cause};

        return statText;
    }

    // CPU 사용률의 변동폭을 + - 10 범위 내에서 난수 추출하여 이전 값에 더하는 식으로.
    public static double cpustat(int temper) {
        double cpuUsageChange = Math.random();
        if (temper == 1) {
            cpuUsageChange = cpuUsageChange*11;
        }
        else {
            cpuUsageChange = cpuUsageChange*50;
        }
        return cpuUsageChange;
    }

    // 초당 거래량의 변동폭을 + - 20 범위 내에서 난수 추출하여 이전 값에 더하는 식으로.
    public static int transacstat() {
        int transacChange = (int)(Math.random() * 20);
        return transacChange;
    }

    // 여기부터는 HSM status online 여부, 정상 작동 여부를 int로 상태번호를 나오게 함.
    public static int randomstat() {
        double statRate = Math.random();
        int hsmConnStat;
        if (statRate <= 0.99) {
            hsmConnStat = 1;
        }
        else if ((statRate <=0.9975) && (statRate > 0.99)) {
            hsmConnStat = 4;
        }
        else if ((statRate <= 0.999) && (statRate > 0.9975)) {
            hsmConnStat = 2;
        }
        else {
            hsmConnStat = 3;
        }
        return hsmConnStat;
    }

    public static int randomtemper(int randstat) {
        int hsmTemper;
        if (randstat == 1) {
            double okOrTemp = Math.random();
            if (okOrTemp <= 0.995) {
                hsmTemper = 1;// ONLINE = OK
            }
            else {
                hsmTemper = 2;//ONLINE - but Temper;
            }
        }
        else if (randstat == 3) {
            hsmTemper = 3;// OFFLINE - UNKNOWN
        }
        else {
            hsmTemper = 2;// Unavailable or Secure - Temper
        }
        return hsmTemper;
    }

    // Based on CPU stat (SUM), the probability also differs.
    public static double cpuLevel(double cpuUsage, double cpuChange) {
        if (cpuUsage > 80) {
            cpuChange /=5;
        }
        else if (cpuUsage > 50) {
            cpuChange /=2;
        }
        return cpuChange;
    }

    //Based on Transac stat (SUM), the probability also differs.
    public static int transacLevel(int transacsum, int transacchange) {
        if (transacsum > 150) {
            transacchange /=5;
        }
        else if (transacsum > 100) {
            transacchange /=3;
        }
        else if (transacsum > 80) {
            transacchange /=2;
        }
        return (int)transacchange;
    }

    public static int temperCause(int hsmstat, int temper) {
        int unavailableTemper [] = {2, 6};
        int onlineTemper [] = {3, 4, 7, 9, 10, 11};
        int secureTemper [] = {5, 8};

        if (temper == 1) {
            return 1; // ONLINE - OK - UNAVAILABLE
        }
        else if (temper == 2) {
            if (hsmstat == 1) {
                int randomcause = onlineTemper[(int)(Math.random()*6)];
                return randomcause;
            }
            else if (hsmstat == 2) {
                int randomcause = unavailableTemper[(int)(Math.random()*2)];
                return randomcause;
            }
            else {
                int randomcause = secureTemper[(int)(Math.random()*2)];
                return randomcause;
            }
        }
        else {
            return 11; // OFFLINE - UNKNOWN - GENERAL (접속이 끊어지지 않게 주의하라는 메시지가 뜸.)
        }
    }
}
