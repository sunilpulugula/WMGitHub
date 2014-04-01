package com.wm.github;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Sunil Kumar
 * Date: 30/3/14
 * Time: 11:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class GitHubRepository {

    public static GHRepository loadGitHubConfiguration() throws FileNotFoundException, IOException {
        String userName = "sunilpulugula";
        String repoName = "TitanApp";
        String userId = "sunil.pulugula@imaginea.com";
        String password = "Sunil243809";
        String githubtoken = "6c64f9b28f4df0adcb4f38d62d6a315867e5cb28";

        //load Configuration
        if(githubtoken == null || githubtoken.isEmpty())
        {
            return getGitRepository(userName, userId, password, repoName);
        }

        return getGitRepository(githubtoken,userName,repoName);

    }

    private static GHRepository getGitRepository(String userName, String userId, String password, String repoName) {
        org.kohsuke.github.GHRepository repository = null;
        try {
            GitHub github = GitHub.connectUsingPassword(userId, password);
            repository = github.getUser(userName).getRepository(repoName);
        } catch (IOException ex) {
            System.out.println("An IOException while connecting to your git repository");
            ex.printStackTrace();
        }
        return repository;
    }

    private static GHRepository getGitRepository(String gitHubToken, String userId, String repoName) {
        org.kohsuke.github.GHRepository repository = null;
        try {
            GitHub github = GitHub.connectUsingOAuth(gitHubToken);
            repository = github.getUser(userId).getRepository(repoName);
        } catch (IOException ex) {
            System.out.println("An IOException while connecting to your git repository");
            ex.printStackTrace();
        }
        return repository;
    }

}
