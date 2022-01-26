package framework.utilities;

import java.util.ArrayList;
import java.util.List;

public class CommonUtilities {

    public static List<String> getListOfNumber(List<String> num){
        List<String> list = new ArrayList<>();
        if(num.size()>=1) {
            for (String s : num) {
                s=s.replaceAll("[^0-9.00]", "");
                if (s != "") {
                    list.add(s.trim());
                }

            }
        }        return list;
    }
}
