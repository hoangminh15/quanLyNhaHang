package Helper;

public class DateFormatter {


    public String formatDate(String date) {
        String[] elements = date.split("-");
        String output = elements[1] + "/" + elements[2] + "/" + elements[0];
        return output;
    }
}
