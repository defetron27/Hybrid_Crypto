import java.util.ArrayList;
import java.util.HashSet;

public class Utils {
    // get ASCII of message
    public static ArrayList<Integer> getASCIIOfMsg(String msg) {
        ArrayList<Integer> asciis = new ArrayList<>();
        for (int i = 0; i < msg.length(); i++) {
            int c = msg.charAt(i);
            asciis.add(c);
        }
        return asciis;
    }

    // get message of ASCII
    public static String getMsgOfASCII(ArrayList<Integer> asciiList) {
        StringBuilder msg = new StringBuilder();

        for (int i : asciiList) {
            msg.append((char) i);
        }
        return msg.toString();
    }

    public static ArrayList<Integer> getOneRightShiftOfArr(ArrayList<Integer> dtList) {
        ArrayList<Integer> rightShiftList = new ArrayList<>();

        // right shift to 1
        for (int i : dtList) {
            rightShiftList.add(i << 1);
        }
        return rightShiftList;
    }

    public static ArrayList<Integer> getTwoRightShiftOfArr(ArrayList<Integer> asciiLst) {
        ArrayList<Integer> leftShiftList = new ArrayList<>();

        // left shift to 1
        for (int i : asciiLst) {
            leftShiftList.add(i >> 1);
        }
        return leftShiftList;
    }

    static int findComplement(int num) {
        int originalNum = num;

        long i = 1;
        while(i<=originalNum) {
            num ^= i;
            i <<= 1;
        }
        return num;
    }

    public static ArrayList<Integer> get1sComplementOneList(ArrayList<Integer> lst1) {
        ArrayList<Integer> complementList = new ArrayList<>();

        for (int i : lst1) {
            complementList.add(Math.abs(~i));
        }
        return complementList;
    }

    public static ArrayList<Integer> getReverse1sComplementOneList(ArrayList<Integer> lst1) {
        ArrayList<Integer> complementList = new ArrayList<>();

        for (int i : lst1) {
            complementList.add(findComplement(i));
        }
        return complementList;
    }

    public static ArrayList<Integer> get1sComplementTwoList(ArrayList<Integer> lst1, ArrayList<Integer> lst2) {
        ArrayList<Integer> complementList = new ArrayList<>();

        for (int i=0, j=0; i<lst1.size(); i++, j++) {
            complementList.add(lst1.get(i) | lst2.get(j));
        }
        return complementList;
    }

    // find prime numbers between 2 to num
    public static HashSet<Integer> findPrimes(int num) {
        HashSet<Integer> primes = new HashSet<>();
        primes.add(2);
        for (int i = 3; i < num + 1; i++) {
            HashSet<Integer> _p = new HashSet<>(primes);
            boolean flag = true;
            for (int j : _p) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                primes.add(i);
            }
        }
        return primes;
    }

    // find prime factors of num
    public static HashSet<Integer> findPrimeFactors(int num) {
        HashSet<Integer> primes = findPrimes(num);
        HashSet<Integer> f_primes = new HashSet<>();
        while (num > 1) {
            for (int i :
                    primes) {
                if (num % i == 0) {
                    f_primes.add(i);
                    num /= i;
                }
            }
        }
        return f_primes;
    }

    public static int getNextPrimeNumber(int num) {
        boolean flag = true;
        HashSet<Integer> primes = findPrimes(num);
        num += 1;
        while (flag) {
            int fCount = 0;
            for (int i : primes) {
                if (num % i != 0) {
                    fCount += 1;
                }
            }
            if (fCount >= primes.size()) {
                flag = false;
            } else {
                num += 1;
            }
        }
        return num;
    }

    public static int powerMod(int base, int ex, int mod) {
        int res = 1;
        base %= mod;
        while (ex > 0) {
            if (ex % 2 == 1) {
                res = (res * base) % mod;
            }
            ex = ex >> 1;
            base = (base * base) % mod;
        }
        return res;
    }
}
