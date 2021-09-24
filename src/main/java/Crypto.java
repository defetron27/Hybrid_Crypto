import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Crypto {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter message to encrypt : ");
        String msg = scanner.nextLine();

        ArrayList<Integer> msgASCIIs = Utils.getASCIIOfMsg(msg);

        System.out.println("ASCIIs : " + msgASCIIs);

        ArrayList<Integer> rightShiftedList = Utils.getOneRightShiftOfArr(msgASCIIs);

        System.out.println("rightShiftedList : " + rightShiftedList);

        ArrayList<Integer> onesComplementedTwo = Utils.get1sComplementTwoList(msgASCIIs, rightShiftedList);

        System.out.println("onesComplementedTwo : " + onesComplementedTwo);

        ArrayList<Integer> onesComplementedOne = Utils.get1sComplementOneList(onesComplementedTwo);

        System.out.println("onesComplementedOne : " + onesComplementedOne);

        double p = 23, q = 17;

        // find m
        double _n = p * q;

        // find v
        double _l = (p - 1) * (q - 1);

        int n = Math.toIntExact(Math.round(_n));
        int l = Math.toIntExact(Math.round(_l));

        // c_primes - common primes
        HashSet<Integer> cPrimes = Utils.findPrimes(l);
        HashSet<Integer> nPrimes = Utils.findPrimeFactors(n);
        HashSet<Integer> lPrimes = Utils.findPrimeFactors(l);

        // key generation

        // find co primes of m and v between 1 and v
        cPrimes.removeAll(nPrimes);
        cPrimes.removeAll(lPrimes);

        ArrayList<Integer> _cLst = new ArrayList<>(cPrimes);

        Collections.sort(_cLst);

        System.out.print("Possible Encryption Keys : ");
        for (int i=0; i<6; i++) {
            System.out.print(_cLst.get(i) + " ");
        }
        System.out.println();

        // public key
        int e = _cLst.get(0);

        // private key
        int npn = Utils.getNextPrimeNumber(e);

        double x = (l * npn) / (double)e;
        int d = (int)Math.round(x);

        System.out.println("n = " + n + ", l = " + l + ", e = " + e + ", d = " + d);

        // asymmetric encryption
        ArrayList<Integer> encryptedList = CustomCipher.asymmetricEncryption(onesComplementedOne, e, n);
        System.out.println("encryptedList : " + encryptedList);
        /*System.out.print("Encrypted Cipher Text Of Asymmetric : ");
        for (int i : encryptedList) {
            System.out.print(i);
        }
        System.out.println();*/

        // asymmetric decryption
        ArrayList<Integer> decryptedList = CustomCipher.asymmetricDecryption(encryptedList, d, n);
        System.out.println("decryptedList : " + decryptedList);
        /*System.out.print("Decrypted Message Of Asymmetric : ");
        for (int i : decryptedList) {
            System.out.print(i);
        }
        System.out.println();*/

        // leftShifted List
        ArrayList<Integer> leftShiftedList = Utils.getTwoRightShiftOfArr(decryptedList);

        System.out.println("leftShiftedList : " + leftShiftedList);

        ArrayList<Integer> reverseOnesComplementedTwo = Utils.get1sComplementTwoList(decryptedList, leftShiftedList);

        System.out.println("reverseOnesComplementedTwo : " + reverseOnesComplementedTwo);

        ArrayList<Integer> reverseOnesComplementedOne = Utils.getReverse1sComplementOneList(reverseOnesComplementedTwo);

        System.out.println("reverseOnesComplementedOne : " + reverseOnesComplementedOne);

        // Symmetric Decryption
        String dTMsg = Utils.getMsgOfASCII(reverseOnesComplementedOne);
        System.out.print("Decrypted Message Of Symmetric : " + dTMsg);
    }
}

