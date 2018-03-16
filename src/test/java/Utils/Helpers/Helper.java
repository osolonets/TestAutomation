package Utils.Helpers;

import PageObjects.REX.Helium.Helium;
import PageObjects.REX.MarketingSite.WordPressREX;
import Utils.FileParsers.TextFileParser;
import Utils.FileParsers.INIParser;
import Utils.FileParsers.XMLParser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Helper
{
    private static ThreadLocal<EliteProperties> threadLocal = new ThreadLocal<>();
    private static Map<String, String> userInfo;
    private static String environment;
    private static String testType;
    private static String URL = "";

    private static String rex_stagingUsers =
            "D://McKissock/autoUsers/rex_stagingUsers.txt";
    private static String rex_productionUsers =
            "D://McKissock/autoUsers/rex_productionUsers.txt";
    private static String elite_Users =
            "D://McKissock/autoUsers/elite_Users.txt";
    private static String itemsToPurchase =
            "src/main/resources/CoursesContent.xml";
    private static String userTemplateFile =
            "src/main/resources/userTemplate.ini";

    static
    {
        setProperties();
        setUserInfo(userTemplateFile);
    }

    private static void setProperties()
    {
        setEnvironment();
        setTestType();
    }

    public static String getEnvironment()
    {
        return environment;
    }

    private static void setEnvironment()
    {
        environment = System.getProperty("environment");
        if (!environment.equalsIgnoreCase("Production") && !environment.equalsIgnoreCase("Staging"))
        {
            environment = "Staging";
        }
    }

    public static String getTestType()
    {
        return testType;
    }

    private static void setTestType()
    {
        testType = System.getProperty("testType");
        if (!testType.equalsIgnoreCase("Regression") && !environment.equalsIgnoreCase("Sanity"))
        {
            testType = "Sanity";
        }
    }

    public static Map<String, String> getUserInfo()
    {
        return userInfo;
    }

    private static void setUserInfo(String fileName)
    {
        INIParser iniParser = new INIParser();
        userInfo = iniParser.parseINIFile(fileName);
    }

    public static String getURL(String site)
    {
        if(site.equalsIgnoreCase("REX"))
        {            setREX_URL();

            return URL;
        }
        else if (site.equalsIgnoreCase("Elite"))
        {
            setElite_URL();
            return URL;
        }
        else throw new RuntimeException("Site must be specified");
    }

    private static void setREX_URL()
    {
        URL = "realestateexpress.com";
    }

    private static void setElite_URL()
    {
        URL = "elitecme.com";
    }

    public static List<String> getBillingAddress(String state)
    {
        List<String> list = new ArrayList<>();
        list.add(userInfo.get("firstName"));
        list.add(userInfo.get("lastName"));
        list.add(userInfo.get("address"));
        list.add(userInfo.get("city"));
        list.add(userInfo.get("zip"));
        list.add(state);
        return list;
    }

    public static void rememberNewUser(String userName)
    {
        String fileName = getFileName(userName);

        new TextFileParser().appendLineToTheFile(fileName, userName);
    }

    private static String getFileName(String userName)
    {
        if (userName.contains("_rex"))
        {
            return environment.equalsIgnoreCase("Staging") ? rex_stagingUsers : rex_productionUsers;
        } else if (userName.contains("_elite"))
        {
            return elite_Users;
        }
        throw new RuntimeException("Make sure username contains site and environment is specified");
    }

    public static String generateUserName(String state, String site)
    {
        return  userInfo.get("userName") +
                site +
                getStateAbbreviation(state) +
                getCurrentDateAndTime() +
                userInfo.get("domain");
    }

    public static List<String> getStates()
    {
        List<String> list = new LinkedList<String>(){{
            add("California");
            add("Florida");
            add("Texas");
            add("New York");
            add("Missouri");
            }};

        if (testType.equalsIgnoreCase("Regression"))
        {
            return list;
        }
        else
        {
            return new LinkedList<String>(){{add(list.get(new Random().nextInt(list.size())));}};
        }
    }

    public static Map<String, String> getProfessionAndState()
    {
        Map<String, String> map = new LinkedHashMap<String, String>(){{
            put("Nursing", "Pennsylvania");
            put("Dental", "California");
            put("SocialWork", "Massachusetts");
            put("Massage", "Illinois");
            put("Cosmetology", "Florida");
            put("ChildAbuse", "Pennsylvania");
        }};

        if (testType.equalsIgnoreCase("Regression"))
        {
            return map;
        }
        else
        {
            String profession = System.getProperty("profession");
            String state = System.getProperty("state");
            if (profession == null || profession.isEmpty() || state == null || state.isEmpty())
            {
                List<String> list = new ArrayList<>();
                list.addAll(map.keySet());
                String randomProfession = list.get(new Random().nextInt(list.size()));
                return new HashMap<String, String>()
                {{
                    put(randomProfession, map.get(randomProfession));
                }};
            }
            else
            {
                return new HashMap<String, String>(){{put(profession, state);}};
            }
        }
    }

    private static String getCurrentDateAndTime()
    {
        return getCurrentDate()+"/"+getCurrentTime();
    }

    private static String getCurrentDate()
    {
        LocalDate localDate = LocalDate.now();
        return localDate.getMonthValue()+"/"+localDate.getDayOfMonth();
    }

    private static String getCurrentTime()
    {
        LocalTime time = LocalTime.now();

        return  time.getHour() + "_" +
                time.getMinute() + "_" +
                time.getSecond() + "PST";
    }

    public static String[][] getUsersForRegression()
    {
        String [][] prodUsers = {
                                {"caautoregression@gmail.com"},
                                {"flautoregression@gmail.com"},
                                {"txautoregression@gmail.com"},
                                {"nyautoregression@gmail.com"},
                                {"moautoregression@gmail.com"}
        };

        if (testType.equalsIgnoreCase("Sanity"))
        {
            return new String[][] {{prodUsers [new Random().nextInt(prodUsers.length)][0]}};
        }
        else return prodUsers;
    }

    public static String getStateAbbreviation(String state)
    {
        switch (state)
        {
            case "California": return "CA";
            case "Florida": return "FL";
            case "Texas": return "TX";
            case "New York": return "NY";
            case "Missouri": return "MO";
            case "Pennsylvania": return "PA";
            case "Massachusetts": return "MA";
            case "Illinois": return "IL";
        }
        throw new RuntimeException("State must be specified");
    }

    //TODO create class bonded with xml
    public static Map<String, Map<String, String>> getItemsToPurchase()
    {
        XMLParser xmlParser = new XMLParser();
        return xmlParser.getREXPurchaseDetails(itemsToPurchase);
    }

    public static String getREX_URLStart(Class c)
    {
        if (c == Helium.class)
        {
            if (environment.equalsIgnoreCase("production"))
                return "my";
            else if (environment.equalsIgnoreCase("staging"))
                return "qa-red-60";
        }
        else if (c == WordPressREX.class)
        {
            if (environment.equalsIgnoreCase("production"))
                return "www";
            else if (environment.equalsIgnoreCase("staging"))
                return "rex:devsonly@bv";
        }
        throw new RuntimeException("Failed setting up environment");
    }

    public static String getEliteURLStart(String profession)
    {
        if (environment.equalsIgnoreCase("production"))
        {
            if (profession.isEmpty())
                return "www";
            else if (profession.equalsIgnoreCase("childabuse"))
                return "nursing";
            else return profession;
        }
        else if (environment.equalsIgnoreCase("staging"))
        {
            if (profession.isEmpty())
                return "www.stage";
            else if (profession.equalsIgnoreCase("childabuse"))
                return "nursing.stage";
            else return profession+".stage";
        }
        throw new RuntimeException("Failed setting up environment");
    }

    public static EliteProperties getEliteProperties()
    {
        return threadLocal.get();
    }

    public static void setEliteProperties(String state, String profession)
    {
        threadLocal.set(new EliteProperties(state, profession));
    }

    public static class EliteProperties {

        private String eliteState;
        private String eliteProfession;

        private EliteProperties(String state, String profession)
        {
            eliteState = state;
            eliteProfession = profession;
        }

        public String getEliteState()
        {
            return eliteState;
        }

        public String getEliteProfession()
        {
            return eliteProfession;
        }
    }
}