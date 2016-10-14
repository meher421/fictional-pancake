package com.njk.backend;

import com.google.appengine.api.log.AppLogLine;
import com.google.appengine.api.log.LogQuery;
import com.google.appengine.api.log.LogServiceFactory;
import com.google.appengine.api.log.RequestLogs;
import com.google.appengine.repackaged.com.google.api.client.util.DateTime;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by meher on 2/10/16.
 */
public class LogsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<meta charset=\"utf-8\">");
        writer.println("<title>App Engine Logs Sample</title>");

        // We use this to break out of our iteration loop, limiting record
        // display to 5 request logs at a time.
        int limit = 5;

        // This retrieves the offset from the Next link upon user click.
        String offset = req.getParameter("offset");

        // We want the App logs for each request log
        LogQuery query = LogQuery.Builder.withDefaults();
        query.includeAppLogs(true);

        // Set the offset value retrieved from the Next link click.
        if (offset != null) {
            query.offset(offset);
        }

        // This gets filled from the last request log in the iteration
        String lastOffset = null;
        int count = 0;

        // Display a few properties of each request log.
        for (RequestLogs record : LogServiceFactory.getLogService().fetch(query)) {
            writer.println("<br>REQUEST LOG <br>");
            DateTime reqTime = new DateTime(record.getStartTimeUsec() / 1000);
            writer.println("IP: " + record.getIp() + "<br>");
            writer.println("Method: " + record.getMethod() + "<br>");
            writer.println("Resource " + record.getResource() + "<br>");
            writer.println(String.format("<br>Date: %s", reqTime.toString()));

            lastOffset = record.getOffset();

            // Display all the app logs for each request log.
            for (AppLogLine appLog : record.getAppLogLines()) {
                writer.println("<br>" + "APPLICATION LOG" + "<br>");
                DateTime appTime = new DateTime(appLog.getTimeUsec() / 1000);
                writer.println(String.format("<br>Date: %s", appTime.toString()));
                writer.println("<br>Level: " + appLog.getLogLevel() + "<br>");
                writer.println("Message: " + appLog.getLogMessage() + "<br> <br>");
            }

            if (++count >= limit) {
                break;
            }
        }

        // When the user clicks this link, the offset is processed in the
        // GET handler and used to cycle through to the next 5 request logs.
        writer.println(String.format("<br><a href=\"/?offset=%s\">Next</a>", lastOffset));
    }
}
