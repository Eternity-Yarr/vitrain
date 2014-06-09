package org.yarr.Overrides;

import com.github.jknack.handlebars.io.FileTemplateLoader;

/**
 * 09.06.2014 at 14:52
 * MyFileTemplateLoader of vitrain project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */

public class MyFileTemplateLoader extends FileTemplateLoader
{
    public MyFileTemplateLoader()
    {
        super("./");
        this.setPrefix("assets/");
        this.setSuffix(".hbs");
    }
}
