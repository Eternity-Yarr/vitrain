package org.yarr.Pages;

import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

import static org.yarr.Main.standardResponse;

/**
 * 09.06.2014 at 13:23
 * notFound of vitrain project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class notFound implements HttpHandler
{
    @Override
    public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control)
    {
        standardResponse(response);
        response.status(404);
        response.content("NOT FOUND"); //TODO:
        response.end();
    }
}
