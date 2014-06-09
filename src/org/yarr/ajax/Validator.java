package org.yarr.ajax;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;
import org.yarr.Session;

/**
 * 09.06.2014 at 13:53
 * Validator of vitrain project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class Validator implements HttpHandler
{
    @Override
    public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control)
    {
        response.header("Content-type", "application/json; charset=utf-8");
        new Thread(new ValidatorHandler(request,response,control))
                .start()
        ;
    }
    enum Actions
    {
        checkAnswer, startGame, endGame, NoAction
    }
    public static class ValidatorHandler implements  Runnable
    {
        HttpRequest request;
        HttpResponse response;
        HttpControl control;
        final private static Logger log = LoggerFactory.getLogger(ValidatorHandler.class);

        public ValidatorHandler(HttpRequest request, HttpResponse response, HttpControl control)
        {
            this.request = request;
            this.response = response;
            this.control = control;
        }
        @Override
        public void run()
        {
            Session s = (Session)request.data("SESSION");
            Actions action = Actions.NoAction;
            try
            {
                if (request.queryParam("action") != null)
                    action = Actions.valueOf(request.queryParam("action"));
            }
            catch(Exception e) { log.info("Incorrect action request"); }

            String content;
            switch(action)
            {
                case checkAnswer:
                    if (s.gameStarted())
                        s.incrementScore(1);
                    break;
                case startGame:
                    if(!s.gameStarted())
                        s.startGame();
                    else
                    {
                        s.endGame();
                        s.startGame();
                    }
                    break;
                case endGame:
                    if(s.gameStarted())
                        s.endGame();
                    break;
                case NoAction:
                    break;
            }
            s.rate();
            Gson g = new Gson();
            content = g.toJson(s);
            response.content(content);
            response.end();
        }
    }
}
