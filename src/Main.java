import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File fileInput = new File("ASDC_lab2/src/MOCK_DATA.csv");
        ArrayList<Competitor> array = Competitor.input(fileInput);
        

    }
}