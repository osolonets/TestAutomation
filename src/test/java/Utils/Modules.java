package Utils;

import PageObjects.EXAMPREP.LoginPage;
import PageObjects.EXAMPREP.SelectFinalExamPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Modules {

    public static int numOfClicks(String text) {
        String[] splited = text.split(" ");
        String first = splited[0];
        int f = Integer.parseInt(first);
        String last = splited[splited.length - 2];
        int l = Integer.parseInt(last);
        return l - f;
    }

    public static int numberOfQuestionOn(String text) {
        String[] splited = text.split(" ");
        String first = splited[0];
        int f = Integer.parseInt(first);
        String last = splited[splited.length - 2];
        int l = Integer.parseInt(last);
        return f;
    }

    public static int totalNumberOfQuestions(String text) {
        String[] splited = text.split(" ");
        String first = splited[0];
        int f = Integer.parseInt(first);
        String last = splited[splited.length - 2];
        int l = Integer.parseInt(last);
        return l;
    }
    private static String getCurrentDateAndTime()
    {
        return getCurrentDate()+getCurrentTime();
    }

    private static String getCurrentDate()
    {
        LocalDate localDate = LocalDate.now();
        return ""+localDate.getMonthValue()+localDate.getDayOfMonth();
    }

    private static String getCurrentTime()
    {
        LocalTime time = LocalTime.now();

        return  ""+time.getHour() +
                time.getMinute() +
                time.getSecond();
    }

    public static String generateUserName( String site)
    {
        return  "Test" + site + getCurrentDateAndTime();

    }
    public static int generateRamdom(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

}