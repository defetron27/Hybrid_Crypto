import java.util.ArrayList;

public class CustomCipher {
    public static ArrayList<Integer> asymmetricEncryption(ArrayList<Integer> onesComplementedLst, int e, int l) {
        ArrayList<Integer> _cT = new ArrayList<>();

        for (int m : onesComplementedLst) {
            _cT.add(Utils.powerMod(m, e, l));
        }

        return _cT;
    }

    public static ArrayList<Integer> asymmetricDecryption(ArrayList<Integer> cTList, int d, int v) {
        ArrayList<Integer> _dT = new ArrayList<>();

        for (int i : cTList) {
            _dT.add(Utils.powerMod(i, d, v));
        }

        return _dT;
    }
}
