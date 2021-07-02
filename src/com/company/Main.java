package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Random;

public class Main {
    public static long[] fibTable;
    public static MyBigInteger Zero = new MyBigInteger("0");
    public static MyBigInteger One = new MyBigInteger("1");

    public static void main(String[] args) {

        MyBigInteger B = new MyBigInteger("");
        MyBigInteger A = new MyBigInteger("");
        MyBigInteger C = new MyBigInteger("");
        MyBigInteger D = new MyBigInteger("");
        // System.out.println(C.AbbreviatedValue());

        long before,after,total,totalTotal,nothing,nothingTotal = 0;
        totalTotal=0;

        for(int i=1; i<=10000; i++) {
            before = getCpuTime();
            after = getCpuTime();
            nothingTotal += (after - before);
        }
        nothing = nothingTotal/10000;

        MyBigInteger y = new MyBigInteger("0");
        long x = 100;
        while(true){
            for(int i=1; i<=10; i++) {
                A.Value = generateRandomNDigitNum((int)x);
                B.Value = generateRandomNDigitNum((int)x);

                before = getCpuTime();
                //C = A.Times(B);
                //y = fibLoopBig(x);
                y = fibMatrixBig(x);
                after = getCpuTime();
                totalTotal += (after - before);

            }
            total = totalTotal/10 - nothing;
            System.out.println(x + " " + y.AbbreviatedValue() + " " + total);
            //System.out.println(total);
            //System.out.println(x + " " + A.AbbreviatedValue() + " " + B.AbbreviatedValue() + " " + total);
            totalTotal = 0;

            x=x+100;
        }

    }

    public static MyBigInteger fibLoopBig(long x){
        MyBigInteger A = new MyBigInteger("0");
        MyBigInteger B = new MyBigInteger("1");
        MyBigInteger next = new MyBigInteger("0");

        if(x<2) {
            StringBuilder sb = new StringBuilder();
            B.Value = sb.append((char)(48+ x)).append(B.Value).toString();
            return B;
        }
        for(int i=2; i<=x; i++){
            next = A.Plus(B);
            A = B;
            B = next;
        }

        return B;
    }

    public static MyBigInteger fibMatrixBig(long x){

        MyBigInteger M[][] = new MyBigInteger[][]{{One,One},{One,Zero}};
        if(x == 0)
            return Zero;
        powerMatrix(M,x-1);

        return M[0][0];
    }

    public static void powerMatrix(MyBigInteger N[][], long x){
        MyBigInteger P[][] = new MyBigInteger[][]{{One,One},{One,Zero}};

        for(int i=2; i<=x; i++){
            multiplyMatrix(N,P);
        }
    }

    public static void multiplyMatrix(MyBigInteger N[][], MyBigInteger P[][]){

        MyBigInteger A = N[0][0].Times(P[0][0]).Plus(N[0][1].Times(P[1][0]));
        MyBigInteger B = N[0][0].Times(P[0][1]).Plus(N[0][1].Times(P[1][1]));
        MyBigInteger C = N[1][0].Times(P[0][1]).Plus(N[1][1].Times(P[1][1]));
        MyBigInteger D = N[1][0].Times(P[0][1]).Plus(N[1][1].Times(P[1][1]));
           //calculate top left matrix index
           //calculate top right matrix index
           //calculate bottom left matrix index
           //calculate bottom right matrix index

        N[0][0] = A;
        N[0][1] = B;
        N[1][0] = C;
        N[1][1] = D;
    }

    public static String generateRandomNDigitNum(int N){
        //code adapted from https://www.baeldung.com/java-random-string
        int leftLimit = 48; // 0
        int rightLimit = 57; // 9
        int targetStringLength = N;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    // Get CPU time in nanoseconds since the program(thread) started.
    /** from: http://nadeausoftware.com/articles/2008/03/java_tip_how_get_cpu_and_user_time_benchmarking#TimingasinglethreadedtaskusingCPUsystemandusertime **/
    public static long getCpuTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
                bean.getCurrentThreadCpuTime( ) : 0L;
    }
}
