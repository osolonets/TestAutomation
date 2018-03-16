package ExamPrep;

import Utils.FileParsers.INIParser;

import java.util.HashMap;
import java.util.Map;

public class UtilExamPrep {

    private static final String TEST_DATA_FILE_PATH = "src/main/resources/testdata/ExamPrep/TestData.xlsx";
    private static String examPrepUserTemplateFile ="src/main/resources/testdata/ExamPrep/userTemplate.ini";
    private static final String BASE_URL = "https://my.stcinteractive.com/#/login";
    private static final String TARGET_URL_START = "http://stage-vc5up.colibrigroup.com/";
    private static final String USER_NAME = "QATestUser77";
    private static final String PASSWD = "Password1";
    private static final int WAIT_TIME = 5;
    public static String getTestDataFilePath() {
        return TEST_DATA_FILE_PATH;
    }
    public static String getBaseUrl() {
        return BASE_URL;
    }
    public static String getTargetUrlStart() {
        return TARGET_URL_START;
    }
    public static String getUserName() {
        return USER_NAME;
    }
    public static int getWaitTime() {
        return WAIT_TIME;
    }
    public static String getPASSWD() {
        return PASSWD;
    }


    public static Map<String, String> getUserInfo() {
        INIParser iniParser = new INIParser();
        return iniParser.parseINIFile(examPrepUserTemplateFile);

    }
}
