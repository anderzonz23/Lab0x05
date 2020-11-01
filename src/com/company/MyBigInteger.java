package com.company;

public class MyBigInteger {

    String Value;

    public MyBigInteger(String DecInt){

        Value = DecInt;

    }

    String Value(){
        return Value;
    }

    String AbbreviatedValue(){
        if(Value.length() < 12)
            return Value();
        else{
            return Value().substring(0,5) + "..." + Value().substring(Value().length() - 5);
        }
    }

    MyBigInteger Plus(MyBigInteger x){
        MyBigInteger C = new MyBigInteger("");
        int dA, dB, dC, sum, Carry = 0;
        int k = this.Value.length();
        int h = x.Value.length();


        for(int i = 0; (i < h || i < k); i++){

            if(i >= k) {
                dB = x.Value.charAt(h - i - 1) - 48;
                sum = dB + Carry;
            }
            else if (i >= h) {
                dA = this.Value.charAt(k - i - 1) - 48;
                sum = dA + Carry;
            }
            else {
                dA = this.Value.charAt(k - i - 1) - 48;
                dB = x.Value.charAt(h - i - 1) - 48;
                sum = dA + dB + Carry;
            }
            if(sum >= 10){
                Carry = 1;
                dC = sum - 10;
            }
            else {
                Carry = 0;
                dC = sum;
            }
            StringBuilder sb = new StringBuilder();

            if(i+1 == h && i+1 == k && Carry > 0) {

                C.Value = sb.append((char)(Carry + 48)).append((char) (48 + dC)).append(C.Value).toString();
            }
            else
                C.Value = sb.append((char)(48+ dC)).append(C.Value).toString();

        }




        return C;
    }

    MyBigInteger Times(MyBigInteger x){
        MyBigInteger C = new MyBigInteger("");
        MyBigInteger P1 = new MyBigInteger("");
        int dA, dB, dC, product, Carry = 0;
        int k = this.Value.length();
        int h = x.Value.length();


        for( int j = 0; j < k; j++) {
            dA = this.Value.charAt(k - j - 1) - 48;


            StringBuilder sb1 = new StringBuilder();
            for(int y = 0; y < j; y++ )
                P1.Value = sb1.append("0").toString();

            for (int i = 0; (i < h); i++) {
                dB = x.Value.charAt(h - i - 1) - 48;

                product = dA * dB + Carry;
                if (product >= 10) {
                    Carry = product / 10;
                    product = product % 10;
                }
                else{
                    Carry = 0;
                }

                StringBuilder sb = new StringBuilder();

                if(i+1 == h && Carry > 0)
                    P1.Value = sb.append((char)(48+ Carry)).append((char)(48+ product)).append(P1.Value).toString();
                else
                    P1.Value = sb.append((char)(48+ product)).append(P1.Value).toString();
            }

            C = C.Plus(P1);
            P1.Value = "";
            Carry = 0;

        }
        return C;
    }
}
