package org.yarr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

import java.net.HttpCookie;

import static org.yarr.Main.ss;
import static org.yarr.Main.standardResponse;

/**
 * 09.06.2014 at 13:23
 * commonSystem of vitrain project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class commonSystem implements HttpHandler
{
    final private static Logger log = LoggerFactory.getLogger("HTTP:");
    @Override
    public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control)
    {
        String real_ip = request.header("X-Real-IP");
        if (real_ip == null || real_ip.isEmpty())
            real_ip = request.remoteAddress().toString();
        log.info(String.format("[%s] %s %s", real_ip, request.method(), request.uri()));

        Session s;
        if (request.cookie("vitrain") != null)
            s = ss.getSession(request.cookieValue("vitrain"));
        else
            s = ss.newSession();
        request.data("SESSION", s);
        log.debug("Session set to {}", s.hash);

        HttpCookie c = new HttpCookie("vitrain",s.hash);
        c.setMaxAge(12*30*24*60*60); // About an year of MaxAge
        c.setPath("/");
        c.setVersion(0);
        response.cookie(c);

        control.nextHandler();
    }
}
