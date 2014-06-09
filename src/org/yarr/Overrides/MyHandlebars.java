package org.yarr.Overrides;

import com.github.jknack.handlebars.Handlebars;

/**
 * 09.06.2014 at 15:02
 * MyHandlebars of vitrain project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class MyHandlebars extends Handlebars
{
    public MyHandlebars()
    {
        super(new MyFileTemplateLoader());
    }
}
