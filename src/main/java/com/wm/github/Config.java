package com.wm.github;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author sunilp
 */
public class Config {

    public static String userName;
    public static String repoName;
    public static String userId;
    public static String password;
    public static String githubtoken;
    public static String fromMailId;
    public static String fromMailIdPassword;
    public static String toMailIds;

    public static void loadConfiguration() throws FileNotFoundException,IOException
    {
        Properties config = new Properties();
        config.load(new FileInputStream("H:\\projects\\WMGitHub\\src\\main\\resources\\wmgithub.properties"));
        githubtoken = config.getProperty("gitHub-Token");
        repoName = config.getProperty("repoName");
        userName = config.getProperty("userName");
        userId = config.getProperty("userId");
        password = config.getProperty("password");
        fromMailId = config.getProperty("fromMailId");
        fromMailIdPassword = config.getProperty("fromMailIdPassword");
        toMailIds = config.getProperty("toMailIds");

    }

}
