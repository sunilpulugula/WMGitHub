package com.wm.github;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: Sunil Kumar
 */
public class WMGitHub {

    public static void main(String args[])throws ParseException,FileNotFoundException,IOException
    {
        WMGitHub wmGitHub = new WMGitHub();
        SendMail send_mail= new SendMail();
        send_mail.sendMail("psunil1278@gmail.com", "psunil1278@gmail.com", "WaveMaker Issue Daily report", wmGitHub.prepareHtmlBody());
    }

    private String prepareHtmlBody() throws ParseException
    {
       String body = "<!DOCTYPE html>\n" +
               "<html>\n" +
               "<head>\n" +
               "    <title>WaveMaker Issues Report</title>\n" +
               "</head>\n" +
               "<body>\n" +
               "<center>\n" +
               "    <h2>WaveMaker Issues Report</h2>\n" +
               "    <table border=\"1\" style=\"font-family: verdana,arial,sans-serif;\n" +
               "        font-size:15px;\n" +
               "        border-width: 1px;\n" +
               "        border-color: #666666;\n" +
               "        border-collapse: collapse;\">\n" +
               "        <tr>\n" +
               "            <th style=\"width:200px;border-width: 1px;\n" +
               "        padding: 8px;\n" +
               "        border-style: solid;\n" +
               "        border-color: #666666;\n" +
               "        background-color: #dedede;\">Severity\n" +
               "            </th>\n" +
               "            <th style=\"width:100px;border-width: 1px;\n" +
               "        padding: 8px;\n" +
               "        border-style: solid;\n" +
               "        border-color: #666666;\n" +
               "        background-color: #dedede;\">Count\n" +
               "            </th>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td>Open</td>\n" +
               "            <td style=\"text-align:center;\">"+GitHubIssues.noOfOpenIssues()+"</td>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td>UnAssigned</td>\n" +
               "            <td style=\"text-align:center;\">"+GitHubIssues.noOfUnassignedIssues()+"</td>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td>BackEnd(Including SaaS)</td>\n" +
               "            <td style=\"text-align:center;\">"+ GitHubIssues.listOfAllBackEndIssues().size()+"</td>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td>FrontEnd</td>\n" +
               "            <td style=\"text-align:center;\">"+GitHubIssues.noOfIssuesBasedOnSeverity(Severity.COSMETIC)+"</td>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td>Critical</td>\n" +
               "            <td style=\"text-align:center;\">"+GitHubIssues.noOfIssuesBasedOnSeverity(Severity.CRITICAL)+"</td>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td>Major</td>\n" +
               "            <td style=\"text-align:center;\">"+GitHubIssues.noOfIssuesBasedOnSeverity(Severity.MAJOR)+"</td>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td>Minor</td>\n" +
               "            <td style=\"text-align:center;\">"+GitHubIssues.noOfIssuesBasedOnSeverity(Severity.MINOR)+"</td>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td>Enhancement</td>\n" +
               "            <td style=\"text-align:center;\">"+GitHubIssues.noOfIssuesBasedOnSeverity(Severity.ENHANCEMENT)+"</td>\n" +
               "        </tr>\n" +
               "    </table>\n" +
               "    <br/><br/>\n" +
               "    <table>\n" +
               "        <tr>\n" +
               "            <td>\n" +
               "                <table border=\"1\" style=\"font-family: verdana,arial,sans-serif;\n" +
               "        font-size:15px;\n" +
               "        border-width: 1px;\n" +
               "        border-color: #666666;\n" +
               "        border-collapse: collapse;\">\n" +
               "                    <caption><b>Last Day("+new SimpleDateFormat("yyyy-MM-dd").format(getPreviousDate(1))+") update</b></caption>\n" +
               "                    <tr>\n" +
               "                        <th style=\"border-width: 1px;\n" +
               "        padding: 8px;\n" +
               "        border-style: solid;\n" +
               "        border-color: #666666;\n" +
               "        background-color: #dedede;\"></th>\n" +
               "                        <th style=\"border-width: 1px;\n" +
               "        padding: 8px;\n" +
               "        border-style: solid;\n" +
               "        border-color: #666666;\n" +
               "        background-color: #dedede;\">FrontEnd\n" +
               "                        </th>\n" +
               "                        <th style=\"border-width: 1px;\n" +
               "        padding: 8px;\n" +
               "        border-style: solid;\n" +
               "        border-color: #666666;\n" +
               "        background-color: #dedede;\">BackEnd\n" +
               "                        </th>\n" +
               "                        <th style=\"border-width: 1px;\n" +
               "        padding: 8px;\n" +
               "        border-style: solid;\n" +
               "        border-color: #666666;\n" +
               "        background-color: #dedede;\">Total\n" +
               "                        </th>\n" +
               "                    </tr>\n" +
               "                    <tr>\n" +
               "                        <td>Created</td>\n" +
               "                        <td>"+GitHubIssues.noOfFrontEndTicketsCreated(getPreviousDate(1),getPreviousDate(1))+"</td>\n" +
               "                        <td>"+GitHubIssues.noOfBackEndTicketsCreated(getPreviousDate(1),getPreviousDate(1))+"</td>\n" +
               "                        <td>"+(GitHubIssues.noOfFrontEndTicketsCreated(getPreviousDate(1),getPreviousDate(1)) + GitHubIssues.noOfBackEndTicketsCreated(getPreviousDate(1),getPreviousDate(1)))+"</td>\n" +
               "                    </tr>\n" +
               "                    <tr>\n" +
               "                        <td>Fixed</td>\n" +
               "                        <td>"+GitHubIssues.noOfFrontEndTicketsFixed(getPreviousDate(1),getPreviousDate(1))+"</td>\n" +
               "                        <td>"+GitHubIssues.noOfBackEndTicketsFixed(getPreviousDate(1),getPreviousDate(1))+"</td>\n" +
               "                        <td>"+(GitHubIssues.noOfFrontEndTicketsFixed(getPreviousDate(1),getPreviousDate(1)) + GitHubIssues.noOfBackEndTicketsFixed(getPreviousDate(1),getPreviousDate(1)))+"</td>\n" +
               "                    </tr>\n" +
               "                    <tr>\n" +
               "                        <td>Closed</td>\n" +
               "                        <td>"+GitHubIssues.noOfFrontEndTicketsClosed(getPreviousDate(1),getPreviousDate(1))+"</td>\n" +
               "                        <td>"+GitHubIssues.noOfBackEndTicketsClosed(getPreviousDate(1),getPreviousDate(1))+"</td>\n" +
               "                        <td>"+(GitHubIssues.noOfFrontEndTicketsClosed(getPreviousDate(1),getPreviousDate(1)) + GitHubIssues.noOfBackEndTicketsClosed(getPreviousDate(1),getPreviousDate(1)))+"</td>\n" +
               "                    </tr>\n" +
               "                    <tr>\n" +
               "                        <td>Top Contributor</td>\n" +
               "                        <td colspan=\"3\">"+GitHubIssues.getTopContributor(getPreviousDate(1),getPreviousDate(1))+"</td>\n" +
               "                    </tr>\n" +
               "                </table>\n" +
               "            </td>\n" +
               "            <td></td>\n" +
               "            <td></td>\n" +
               "            <td></td>\n" +
               "            <td>\n" +
               "                <table border=\"1\" style=\"font-family: verdana,arial,sans-serif;\n" +
               "        font-size:15px;\n" +
               "        border-width: 1px;\n" +
               "        border-color: #666666;\n" +
               "        border-collapse: collapse;\">\n" +
               "                    <caption><b>Last 7days("+new SimpleDateFormat("yyyy-MM-dd").format(getPreviousDate(7))+" to "+new SimpleDateFormat("yyyy-MM-dd").format(getPreviousDate(1))+") update</b></caption>\n" +
               "                    <tr>\n" +
               "                        <th style=\"border-width: 1px;\n" +
               "                padding: 8px;\n" +
               "                border-style: solid;\n" +
               "                border-color: #666666;\n" +
               "                background-color: #dedede;\"></th>\n" +
               "                <th style=\"border-width: 1px;\n" +
               "        padding: 8px;\n" +
               "        border-style: solid;\n" +
               "        border-color: #666666;\n" +
               "        background-color: #dedede;\">FrontEnd\n" +
               "                </th>\n" +
               "                <th style=\"border-width: 1px;\n" +
               "        padding: 8px;\n" +
               "        border-style: solid;\n" +
               "        border-color: #666666;\n" +
               "        background-color: #dedede;\">BackEnd\n" +
               "                </th>\n" +
               "                <th style=\"border-width: 1px;\n" +
               "        padding: 8px;\n" +
               "        border-style: solid;\n" +
               "        border-color: #666666;\n" +
               "        background-color: #dedede;\">Total\n" +
               "                </th>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td>Created</td>\n" +
               "                        <td>"+GitHubIssues.noOfFrontEndTicketsCreated(getPreviousDate(7),getPreviousDate(1))+"</td>\n" +
               "                        <td>"+GitHubIssues.noOfBackEndTicketsCreated(getPreviousDate(7),getPreviousDate(1))+"</td>\n" +
               "                        <td>"+(GitHubIssues.noOfFrontEndTicketsCreated(getPreviousDate(7),getPreviousDate(1)) + GitHubIssues.noOfBackEndTicketsCreated(getPreviousDate(7),getPreviousDate(1)))+"</td>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td>Fixed</td>\n" +
               "                        <td>"+GitHubIssues.noOfFrontEndTicketsFixed(getPreviousDate(7),getPreviousDate(1))+"</td>\n" +
               "                        <td>"+GitHubIssues.noOfBackEndTicketsFixed(getPreviousDate(7),getPreviousDate(1))+"</td>\n" +
               "                        <td>"+(GitHubIssues.noOfFrontEndTicketsFixed(getPreviousDate(7),getPreviousDate(1)) + GitHubIssues.noOfBackEndTicketsFixed(getPreviousDate(7),getPreviousDate(1)))+"</td>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td>Closed</td>\n" +
               "                        <td>"+GitHubIssues.noOfFrontEndTicketsClosed(getPreviousDate(7),getPreviousDate(1))+"</td>\n" +
               "                        <td>"+GitHubIssues.noOfBackEndTicketsClosed(getPreviousDate(7),getPreviousDate(1))+"</td>\n" +
               "                        <td>"+(GitHubIssues.noOfFrontEndTicketsClosed(getPreviousDate(7),getPreviousDate(1)) + GitHubIssues.noOfBackEndTicketsClosed(getPreviousDate(7),getPreviousDate(1)))+"</td>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td>Top Contributor</td>\n" +
               "            <td colspan=\"3\">"+GitHubIssues.getTopContributor(getPreviousDate(7),getPreviousDate(1))+"</td>\n" +
               "        </tr>\n" +
               "    </table>\n" +
               "    </td>\n" +
               "    </tr>\n" +
               "    </table>\n" +
               "</center>\n" +
               "</body>\n" +
               "</html>";
        return body;
    }

    private Date getPreviousDate(int  noOfDays) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -noOfDays);
        Date previousDate = calendar.getTime();
        return previousDate;
    }

}
