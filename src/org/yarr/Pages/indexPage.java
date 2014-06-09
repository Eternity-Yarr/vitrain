package org.yarr.Pages;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;
import org.yarr.Overrides.MyHandlebars;
import org.yarr.Session;
import org.yarr.VimKeys;

import java.util.HashMap;

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
            HashMap<String,Object> data = new HashMap<>();
            Handlebars h = new MyHandlebars();
            Context co = Context
                    .newBuilder(data)
                    .resolver(FieldValueResolver.INSTANCE, MapValueResolver.INSTANCE, MethodValueResolver.INSTANCE)
                    .build();
            Session session = (Session)request.data("SESSION");
            try
            {
                Template t = h.compile("index");
                VimKeys quiz;
                if(session.tries > 3)
                {
                    session.error = String.format("Correct answer was: '%s' for '%s'", session.last_question.command, session.last_question.description);
                    session.last_question = null;
                    session.tries = 0;
                }

                if (session.last_question == null)
                {
                    quiz = VimKeys.randomEnum();
                    session.last_question = quiz;
                }
                else
                    quiz = session.last_question;
                data.put("question", quiz.description);
                data.put("session",session);
                String s = t.apply(co);
                response.content(s);
            }
            catch(Exception e)
            {
                response.content("PANIC:" + e.toString());
            }
            response.end();
        }
    }
}

