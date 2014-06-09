package org.yarr;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 09.06.2014 at 12:56
 * Sessions of vitrain project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class Sessions
{
    final static Random r = new Random();
    final static Logger log = LoggerFactory.getLogger(Session.class);
    final static MessageDigest md;
    static
    {
        MessageDigest m = null;
        try
        {
            m = MessageDigest.getInstance("MD5");
        }
        catch(Exception ex)
        {
            log.error("Can't generate MD5, falling back to plain integers");
        }
        md = m;
    }

    Map<String,Session> bucket;

    public Session newSession()
    {

        String sid = String.valueOf(r.nextInt());
        Session s = new Session();
        if (md != null)
            s.hash = String.valueOf(Hex.encodeHex(md.digest(sid.getBytes())));
        else
            s.hash = String.valueOf(sid);
        bucket.put(s.hash,s);
        return s;
    }

    public Session getSession(String hash)
    {
        Session s = bucket.get(hash);
        return s == null ? newSession() : s;
    }
    public Sessions()
    {
        bucket = new HashMap<String, Session>();
    }
}
