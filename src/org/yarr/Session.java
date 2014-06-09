package org.yarr;

/**
 * 09.06.2014 at 12:54
 * Session of vitrain project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class Session
{
    public String hash;
    private int score;
    public int top_score;
    public double top_rate;
    private long started_at;
    private boolean game_started;
    public VimKeys last_question;
    private int answered;
    private String name;
    public double rate;
    public String error;
    public int tries;
    public void setScore(int score)
    {
        this.score = score;
    }
    public int getScore()
    {
        return score;
    }
    public void incrementScore(int i)
    {
        score += i;
    }
    public void startGame()
    {
        score = 0;
        game_started = true;
        started_at = System.currentTimeMillis();
        answered = 0;
    }
    public void answer()
    {
        answered++;
    }
    public boolean gameStarted()
    {
        return game_started;
    }
    public void endGame()
    {
        if (score>top_score)
            top_score = score;
        if (rate>top_rate)
            top_rate = rate;
        game_started = false;
        started_at = 0;
    }
    public double rate()
    {
        if (game_started)
        {
        double ct = System.currentTimeMillis();
        double st = started_at;
        if (ct != st)
            rate = (float)score * 1000 / (ct - st);
        else
            rate = 0;
        }
        return rate;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    Session(String hash)
    {
        this.hash = hash;
        score = 0;
        top_score = 0;
        game_started = false;
        name = "";
        answered = 0;
    }
}
