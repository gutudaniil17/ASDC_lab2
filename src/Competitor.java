import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.lang.String;

/*4. участники соревнования
        а) фамилия
        б) имя
        в) страна
        г) категория (IV, III, II, I, NM, IM)
        д) год рождения
        е) номер в таблице*/
public class Competitor implements Comparable {
    private String name;
    private String surname;
    private String country;
    private int yearOfBirthday;
    private Category category;
    private int numberInTable;

    ///Constructor with no args
    public Competitor() {
    }

    ///Fabric method
    ///Instead of copying constructor
    public static Competitor createCompetitorFromObject(Object obj){
        Competitor competitor = new Competitor();
        Competitor competitor1 = (Competitor) obj;
        String name = competitor1.name;
        String surname = competitor1.surname;
        String country = competitor1.country;
        int yearOfBirthday = competitor1.yearOfBirthday;
        int numberInTable = competitor1.numberInTable;
        Category category = competitor1.category;
        competitor = new Competitor();
        competitor.setAllParam(name,surname,country,yearOfBirthday,category,numberInTable);

        return competitor;
    }
    ///Fabric method
    ///Instead of constructor with parameter String
    public static Competitor createCompetitorFromString(Object obj){
        Competitor competitor;
        String csvLine = (String) obj;
        String[] arrParam = csvLine.split(";");
        String name = arrParam[0];
        String surname = arrParam[1];
        String country = arrParam[2];
        int yearOfBirthday = Integer.parseInt(arrParam[3]);
        int numberInTable = Integer.parseInt(arrParam[4]);
        Category category = Category.valueOf(arrParam[5]);
        competitor = new Competitor();
        competitor.setAllParam(name,surname,country,yearOfBirthday,category,numberInTable);
        return competitor;
    }

    ///Sets all parameters, instead of constructor with args
    public void setAllParam(String name, String surname, String country, int dateOfBirthday, Category category, int numberInTable){
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.yearOfBirthday = dateOfBirthday;
        this.category = category;
        this.numberInTable = numberInTable;
    }


    ///Method that is used for sorting of competitor's list
    public int getNumberInTable() {
        return numberInTable;
    }

    ///Constructor from string
/*    public Competitor(String csvLine) {
        String[] arrParam = csvLine.split(";");
        name = arrParam[0];
        surname = arrParam[1];
        country = arrParam[2];
        yearOfBirthday = Integer.parseInt(arrParam[3]);
        numberInTable = Integer.parseInt(arrParam[4]);
        category = Category.valueOf(arrParam[5]);
    }*/

    ///Constructor copy
    public Competitor(Competitor comp) {
        this.name = comp.name;
        this.surname = comp.surname;
        this.country = comp.country;
        this.category = comp.category;
        this.yearOfBirthday = comp.yearOfBirthday;
        this.numberInTable = comp.numberInTable;
    }

    ///Copying method
    ///Using fabric method
    public Competitor copy() {
        return createCompetitorFromObject(this);
    }

    ///Equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competitor that = (Competitor) o;
        return yearOfBirthday == that.yearOfBirthday &&
                numberInTable == that.numberInTable &&
                name.equals(that.name) &&
                surname.equals(that.surname) &&
                country.equals(that.country) &&
                category == that.category;
    }

    ///Input method
    ///Reads data from file
    ///Using fabric method
    public static ArrayList<Competitor> input(File resource) {
        ArrayList<Competitor> competitorList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(resource))) {
            String st;
            while ((st = br.readLine()) != null) {
                if (st.isEmpty()) {
                } else {
                    competitorList.add(createCompetitorFromString(st));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return competitorList;
    }

    ///Input overloaded method
    ///Reads data from the string
    ///Using fabric method
    public static ArrayList<Competitor> input(String resource) {
        ArrayList<Competitor> competitorList = new ArrayList<>();
        String[] array = resource.split("/n");
        for (String str : array) {
            competitorList.add(createCompetitorFromString(str));
        }
        return competitorList;
    }

    ///Object to csv format
    ///Chickie;Hoggan;Madagascar;1989;47;IM
    public String competitorToCSV() {
        String name = this.name;
        String surname = this.surname;
        String country = this.country;
        String year = String.valueOf(this.yearOfBirthday);
        String number = String.valueOf(this.numberInTable);
        String category = String.valueOf(this.category);
        return new String(name + ";" + surname + ";" + country + ";" + year + ";" + number + ";" + category);
    }

    ///Output method
    ///Overloaded method, that output info to the file
    public static void output(ArrayList<Competitor> competitors, File resources) {
        try {
            FileWriter fileWriter = new FileWriter(resources);
            for (Competitor comp : competitors) {
                fileWriter.write(comp.competitorToCSV() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ///Output overloaded method
    ///Output info to the console
    public static void output(ArrayList<Competitor> competitors) {
        for (Competitor comp : competitors) {
            System.out.println(comp.toString());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, country, yearOfBirthday, category, numberInTable);
    }

    @Override
    public int compareTo(Object competitor) {
        int comparNumber = ((Competitor) competitor).getNumberInTable();
        return this.numberInTable - comparNumber;
    }

    @Override
    public String toString() {
        return "Competitor{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", country='" + country + '\'' +
                ", yearOfBirthday=" + yearOfBirthday +
                ", category=" + category +
                ", numberInTable=" + numberInTable +
                '}';
    }
}