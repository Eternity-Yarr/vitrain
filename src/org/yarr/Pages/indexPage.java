package org.yarr.Pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

import static org.yarr.Main.standardResponse;

/**
 * 09.06.2014 at 13:23
 * indexPage of vitrain project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class indexPage implements HttpHandler
{
    @Override
    public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control)
    {
        standardResponse(response);
        response.status(200);
        new Thread(new IndexPageHandler(request,response,control))
                .start()
        ;
    }

    public static class IndexPageHandler implements Runnable
    {
        HttpRequest request;
        HttpResponse response;
        HttpControl control;
        final private static Logger log = LoggerFactory.getLogger(IndexPageHandler.class);

        public IndexPageHandler(HttpRequest request, HttpResponse response, HttpControl control)
        {
            this.request = request;
            this.response = response;
            this.control = control;
        }
        @Override
        public void run()
        {
            response.content("OK");
            response.end();
        }
    }
}

