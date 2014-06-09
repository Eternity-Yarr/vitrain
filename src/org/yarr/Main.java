package org.yarr;

import org.yarr.Pages.notFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.SimpleLogger;
import org.webbitserver.*;
import org.webbitserver.handler.StaticFileHandler;
import org.yarr.Pages.indexPage;
import org.yarr.ajax.Validator;

public class Main {
    private static Logger log;
    public static Sessions ss;
    private static int WS_PORT = 6633;

    public static void standardResponse(HttpResponse response)
    {
        response.header("Content-type","text/html; charset=UTF-8");
        response.header("Vary","Accept-Encoding");
    }

    public static void main(String[] args)
    {
        System.setProperty(SimpleLogger.SHOW_DATE_TIME_KEY,"true");
        System.setProperty(SimpleLogger.WARN_LEVEL_STRING_KEY,"!!!");
        System.setProperty(SimpleLogger.DATE_TIME_FORMAT_KEY, "yyyy-MM-dd HH:mm:ss:SSS");
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "debug");

        log = LoggerFactory.getLogger(Main.class);
        log.debug("Creating server");

        ss = new Sessions();


        WebServer ws = WebServers.createWebServer(WS_PORT);
        ws
                .add(new commonSystem())
                .add(new StaticFileHandler("./var"))
                .add("/", new indexPage())
                .add("/ajax", new Validator())
                .add(new notFound());

        log.debug("Starting server at port {}", WS_PORT);

        try
        {
            ws.start().get();
        }
        catch(Exception e){ log.error(e.toString(),e); }
    }
}
