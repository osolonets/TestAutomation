package Mckissock;

import Utils.FileParsers.INIParser;

import java.util.Map;

public class UtilMckissock {

    private static final String TEST_DATA_FILE_PATH = "src/test/resources/testdata/Mckissock/TestData.xlsx";
    //private static String examPrepUserTemplateFile ="src/main/resources/testdata/Mckissock/userTemplate.ini";
    private static final String BASE_URL = "https://www.mckissock.com/";
    private static final String BASE_URL_DEV_ENV = "http://common.mckissock.com";
    private static final String ADMIN_TOOL_URL = "http://admin.mckissock.com/Students/Default.aspx";
    private static final String TARGET_URL_START_VC5 = "http://stage-vc5up.colibrigroup.com/";
    private static final String TARGET_URL_START_CHECK_OUT = "http://stage-vc5up.colibrigroup.com/";
    private static final String USER_NAME = "testalex";
    private static final String PASSWD = "password1";
    private static final String PROMO_CODE_100 = "mckcommon100";
    private static final String LOGIN_DEV_ENV_USER_NAME = "mck";
    private static final String LOGIN_DEV_ENV_USER_PASSWD = "devsonly";
    private static final String USER_NAME_ADM_TOOL = "osolonets";
    private static final String PASSWD_ADM_TOOL = "testing";
    private static final int WAIT_TIME = 5;


    public static String getTestDataFilePath() {
        return TEST_DATA_FILE_PATH;
    }
    public static String getBaseUrl() {
        return BASE_URL;
    }
    public static String getTargetUrlStartVc5() {
        return TARGET_URL_START_VC5;
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
    public static String getTargetUrlStartCheckOut() {
        return TARGET_URL_START_CHECK_OUT;
    }
    public static String getAdminToolUrl() {
        return ADMIN_TOOL_URL;
    }
    public static String getUserNameAdmTool() {
        return USER_NAME_ADM_TOOL;
    }
    public static String getPasswdAdmTool() {
        return PASSWD_ADM_TOOL;
    }
    public static String getLoginDevEnvUserName() {
        return LOGIN_DEV_ENV_USER_NAME;
    }
    public static String getLoginDevEnvUserPasswd() {
        return LOGIN_DEV_ENV_USER_PASSWD;
    }
    public static String getBaseUrlDevEnv() {
        return BASE_URL_DEV_ENV;
    }
    public static String getPromoCode100() {
        return PROMO_CODE_100;
    }

//    public static Map<String, String> getUserInfo() {
//        INIParser iniParser = new INIParser();
//        return iniParser.parseINIFile(examPrepUserTemplateFile);
//
//    }


}
