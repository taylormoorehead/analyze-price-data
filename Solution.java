import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class Solution {

    public String estimate(String date) throws FileNotFoundException {

        String[] dateArr = date.split("/");
        String answer = "";
        if(Integer.parseInt(dateArr[2].substring(0, 1)) > 24 || (Integer.parseInt(dateArr[0]) > 9 && Integer.parseInt(dateArr[2]) >= 24)) {
            if(Integer.parseInt(dateArr[2].substring(0, 1)) == 24) {
                answer = "1.0" + Double.toString(325 / 12 * (12 - Integer.parseInt(dateArr[0])) + 325 / 365 * Integer.parseInt(dateArr[1]));
            } else {
                answer = "1.0" + Double.toString(325 / 12 * (3 + Integer.parseInt(dateArr[0])) + 325 / 365 * Integer.parseInt(dateArr[1]));
            }
        } else {
            Scanner scanner = new Scanner(new FileInputStream("Nat_Gas.csv"));
            while(scanner.hasNextLine()) {
                try {
                    String s = scanner.nextLine();
                    String[] arr = s.split("/");
                    if(arr[0].equals(dateArr[0]) && arr[2].equals(dateArr[0])) {
                        double dif = Double.parseDouble(arr[2].split(",")[1].substring(0, 3)) 
                        - Double.parseDouble(scanner.nextLine().split("/")[2].split(",")[1].substring(0, 3));
                        double n = Double.parseDouble(scanner.nextLine().split("/")[1]);
                        answer = "1" + Double.toString((dif / n) * (Double.parseDouble(dateArr[1]) / n));
                        break;
                    }
                } catch(Exception e) {
                    continue;
                }
            }
        }

        return answer;
    }

}