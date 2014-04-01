package com.wm.github;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;

/**
 * User: Sunil Kumar
 */
public class GitHubIssues {

    private static GHRepository ghRepository;
    private static List<GHIssue> openIssues;
    private static List<GHIssue> closedIssues;
    private static List<GHIssue> allIssues = new ArrayList<GHIssue>();

    static {
        try {
            ghRepository = GitHubRepository.loadGitHubConfiguration();
            openIssues = ghRepository.getIssues(GHIssueState.OPEN);
            closedIssues = ghRepository.getIssues(GHIssueState.CLOSED);
            allIssues.addAll(openIssues);
            allIssues.addAll(closedIssues);
        } catch (IOException ex) {
            System.out.println("An IOException had occured while loading the configuration!");
            ex.printStackTrace();
        }

    }

    public static int noOfOpenIssues() {
        return openIssues.size();
    }

    public static int noOfClosedIssues() {
        return closedIssues.size();
    }

    public static int noOfIssuesBasedOnSeverity(Severity severity) {
        int count = 0;
        for (GHIssue ghIssue : openIssues) {
            for (GHIssue.Label label : ghIssue.getLabels()) {
                if (label.getName().equals(severity.getValue())) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int noOfIssuesCreated(Date date) {
        int count = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (GHIssue ghIssue : allIssues) {
            String createdDate = sdf.format(ghIssue.getCreatedAt());
            String formattedDate = sdf.format(date);
            if (createdDate.equals(formattedDate)) {
                count++;
            }
        }
        return count;
    }

    public static Set<GHIssue> listOfAllBackEndIssues() {
        Set<GHIssue> issues = new HashSet<GHIssue>();
        for (GHIssue issue : openIssues) {
            for (GHIssue.Label label : issue.getLabels()) {
                if (label.getName().equals(Severity.BACK_END_TASK.getValue()) || label.getName().equals(Severity.SAAS.getValue())) {
                    issues.add(issue);
                }
            }
        }
        return issues;
    }

    public static List<GHIssue> listOfIssuesCreated(Date fromDate, Date toDate) throws ParseException {
        List<GHIssue> issues = new ArrayList<GHIssue>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date from = sdf.parse(sdf.format(fromDate));
        Date to = sdf.parse(sdf.format(toDate));
        for (GHIssue ghIssue : allIssues) {
            Date createdDate = sdf.parse(sdf.format(ghIssue.getCreatedAt()));
            if (createdDate.compareTo(from) >= 0 && createdDate.compareTo(to) <= 0) {
                issues.add(ghIssue);
            }
        }
        return issues;
    }

    public static List<GHIssue> listOfIssuesFixed(Date fromDate, Date toDate) throws ParseException {
        List<GHIssue> issues = new ArrayList<GHIssue>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date from = sdf.parse(sdf.format(fromDate));
        Date to = sdf.parse(sdf.format(toDate));
        for (GHIssue ghIssue : allIssues) {
            Date createdDate = sdf.parse(sdf.format(ghIssue.getCreatedAt()));
            for (GHIssue.Label label : ghIssue.getLabels()) {
                if (label.getName().equals(Severity.FIXED.getValue()) && createdDate.compareTo(from) >= 0 && createdDate.compareTo(to) <= 0) {
                    issues.add(ghIssue);
                }
            }
        }
        return issues;
    }

    public static List<GHIssue> listOfIssuesClosed(Date fromDate, Date toDate) throws ParseException {
        List<GHIssue> issues = new ArrayList<GHIssue>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date from = sdf.parse(sdf.format(fromDate));
        Date to = sdf.parse(sdf.format(toDate));
        for (GHIssue ghIssue : closedIssues) {
            Date createdDate = sdf.parse(sdf.format(ghIssue.getClosedAt()));
            if (createdDate.compareTo(from) >= 0 && createdDate.compareTo(to) <= 0) {
                issues.add(ghIssue);
            }
        }
        return issues;
    }

    public static int noOfFrontEndTicketsFixed(Date fromDate, Date toDate) throws ParseException {
        Set<GHIssue> frontEndIssues = new HashSet<GHIssue>();
        List<GHIssue> issues = listOfIssuesFixed(fromDate, toDate);
        for (GHIssue ghIssue : issues) {
            for (GHIssue.Label label : ghIssue.getLabels()) {
                if (label.getName().equals(Severity.COSMETIC.getValue())) {
                    frontEndIssues.add(ghIssue);
                }
            }
        }
        return frontEndIssues.size();
    }

    public static int noOfBackEndTicketsFixed(Date fromDate, Date toDate) throws ParseException {
        Set<GHIssue> backEndIssues = new HashSet<GHIssue>();
        List<GHIssue> issues = listOfIssuesFixed(fromDate, toDate);
        for (GHIssue ghIssue : issues) {
            for (GHIssue.Label label : ghIssue.getLabels()) {
                if (label.getName().equals(Severity.BACK_END_TASK.getValue()) || label.getName().equals(Severity.SAAS.getValue())) {
                    backEndIssues.add(ghIssue);
                }
            }
        }
        return backEndIssues.size();
    }

    public static int noOfFrontEndTicketsClosed(Date fromDate, Date toDate) throws ParseException {
        Set<GHIssue> frontEndIssues = new HashSet<GHIssue>();
        List<GHIssue> issues = listOfIssuesClosed(fromDate, toDate);
        for (GHIssue ghIssue : issues) {
            for (GHIssue.Label label : ghIssue.getLabels()) {
                if (label.getName().equals(Severity.COSMETIC.getValue())) {
                    frontEndIssues.add(ghIssue);
                }
            }
        }
        return frontEndIssues.size();
    }

    public static int noOfBackEndTicketsClosed(Date fromDate, Date toDate) throws ParseException {
        Set<GHIssue> backEndIssues = new HashSet<GHIssue>();
        List<GHIssue> issues = listOfIssuesClosed(fromDate, toDate);
        for (GHIssue ghIssue : issues) {
            for (GHIssue.Label label : ghIssue.getLabels()) {
                if (label.getName().equals(Severity.BACK_END_TASK.getValue()) || label.getName().equals(Severity.SAAS.getValue())) {
                    backEndIssues.add(ghIssue);
                }
            }
        }
        return backEndIssues.size();
    }

    public static int noOfFrontEndTicketsCreated(Date fromDate, Date toDate) throws ParseException {
        Set<GHIssue> frontEndIssues = new HashSet<GHIssue>();
        List<GHIssue> issues = listOfIssuesCreated(fromDate, toDate);
        for (GHIssue ghIssue : issues) {
            for (GHIssue.Label label : ghIssue.getLabels()) {
                if (label.getName().equals(Severity.COSMETIC.getValue())) {
                    frontEndIssues.add(ghIssue);
                }
            }
        }
        return frontEndIssues.size();
    }

    public static int noOfBackEndTicketsCreated(Date fromDate, Date toDate) throws ParseException {
        Set<GHIssue> backEndIssues = new HashSet<GHIssue>();
        List<GHIssue> issues = listOfIssuesCreated(fromDate, toDate);
        for (GHIssue ghIssue : issues) {
            for (GHIssue.Label label : ghIssue.getLabels()) {
                if (label.getName().equals(Severity.BACK_END_TASK.getValue()) || label.getName().equals(Severity.SAAS.getValue())) {
                    backEndIssues.add(ghIssue);
                }
            }
        }
        return backEndIssues.size();
    }

    public static int noOfIssuesClosed(Date date) {
        int count = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (GHIssue ghIssue : allIssues) {
            String closedDate = sdf.format(ghIssue.getClosedAt());
            String formattedDate = sdf.format(date);
            if (closedDate.equals(formattedDate)) {
                count++;
            }
        }
        return count;
    }

    public static int noOfUnassignedIssues() {
        int count = 0;
        for (GHIssue ghIssue : openIssues) {
            if (ghIssue.getAssignee() == null) {
                count++;
            }
        }
        return count;
    }

    public static String getTopContributor(Date fromDate, Date toDate) throws ParseException {
        Map<String, Integer> contributors = new LinkedHashMap<String, Integer>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date from = sdf.parse(sdf.format(fromDate));
        Date to = sdf.parse(sdf.format(toDate));
        for (GHIssue ghIssue : allIssues) {
            for (GHIssue.Label label : ghIssue.getLabels()) {
                Date updatedDate = sdf.parse(sdf.format(ghIssue.getUpdatedAt()));
                if (label.getName().equals(Severity.FIXED.getValue()) && updatedDate.compareTo(fromDate) >= 0 && updatedDate.compareTo(toDate) <= 0 && ghIssue.getAssignee() != null) {
                    String assignee = ghIssue.getAssignee().toString();
                    if (contributors.keySet().contains(assignee)) {
                        Integer count = contributors.get(assignee);
                        contributors.put(assignee, count++);
                    } else {
                        contributors.put(assignee, 1);
                    }
                }
            }

        }
        contributors = sortByComparator(contributors);
        if (contributors.entrySet().iterator().hasNext()) {
            Map.Entry entry = contributors.entrySet().iterator().next();
            if (entry != null) {
                return entry.getKey().toString().substring(5);
            }
        }
        return null;
    }

    private static Map sortByComparator(Map unsortMap) {

        List list = new LinkedList(unsortMap.entrySet());

        // sort list based on comparator
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // put sorted list into map again
        //LinkedHashMap make sure order in which keys were inserted
        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }


}
