package org.yarr;

import com.sun.swing.internal.plaf.metal.resources.metal;

import java.util.Random;

/**
 * 09.06.2014 at 15:17
 * VimKeys of vitrain project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public enum VimKeys
{
    SAVE("w", "save command"),
    QUIT("q", "quit command"),
    OPEN_FILE("e", "open file command"),
    TOGGLE_CASE("~", "toggle case command"),
    EXTERNAL_FILTER("!","external filter operator"),
    PLAY_MACRO("@","play macro command"),
    HELP("h","help command"),
    EOL("$", "end of line motion"),
    GOTO_MATCH("%","goto match motion"),
    SOFT_BOL("^", "begin of line motion"),
    REPEAT("&","repeat command"),
    NEXT_IDET("*", "next ident motion"),
    BEGIN_SENTENCE("(", "begin sentence motion"),
    END_SENTENCE(")", "end sentence motion"),
    NEXT_LINE("+", "next line motion"),
    SOFT_BOL_NEXTLINE("_","soft begin of line down motion"),
    EXPERT_MODE("Q","expert mode command"),
    RECORD_MACRO("q", "record macro command"),
    UWORDS_FORWARD("W", "next WORD motion"),
    LWORDS_FORWARD("w", "next word motion"),
    UENDWORD("E", "end WORD motion"),
    LENDWORD("e", "end word motion"),
    REPLACE("R", "replace mode command"),
    REPLACE_CHAR("r", "replace char command"),
    BACK_TILL("T", "'back untill' motion"),
    TILL("t", "'untill' motion"),
    YANK_LINE("Y", "yank line command"),
    YANK("y","yank operator"),
    UNDO_LINE("U", "undo line command"),
    UNOD("u", "undo command"),
    INSERT_BOL("I", "insert at beginning of line command"),
    INSERT("i","insert mode command"),
    OPEN_ABOVE("O", "open above command"),
    OPEN_BELOW("o", "open below command"),
    PASTE_BEFORE("P", "paste before command"),
    PASTE_AFTER("p", "paste after"),
    BEGIN_PARAG("{", "begin paragraph morion"),
    END_PARAG("}", "end paragraph motion"),
    APPEND_EOL("A", "append at eol command"),
    APPEND("a", "append command"),
    SUBST_LINE("S", "substitute line command"),
    SUBST_CHAR("s", "substitute char command"),
    DELETE_EOL("D", "delete to end of line command"),
    DELETE("d", "delete operator"),
    BACK_FIND_CH("F", "backward find character motion"),
    FORWARD_FIND_CH("f", "forward find character motion"),
    GOTO_LINE("G", "goto eof or line number motion"),
    OPEN_FILE_UNDER_CURSOR("gf", "open file under cursor command"),
    SCREEN_TOP("H", "screen top motion"),
    LEFT("h", "move left motion"),
    DOWN("j", "move down motion"),
    UP("k", "move up motion"),
    RIGHT("l", "move right motion"),
    JOIN_LINES("J", "join lines command"),
    NEW_FILE("new", "new file command"),
    SCREEN_BOTTOM("L", "screen bottom motion");

    static Random r = new Random();

    public static <T extends Enum<?>> VimKeys randomEnum(){
        int x = r.nextInt(VimKeys.class.getEnumConstants().length);
        return VimKeys.class.getEnumConstants()[x];
    }
    static public VimKeys byCommand(String c)
    {
        for(VimKeys v: VimKeys.class.getEnumConstants())
        {
            if (v.command.equals(c)) return v;
        }
        return null;
    }
    final public String command;
    final public String description;
    VimKeys(String command, String description)
    {
        this.command = command;
        this.description = description;
    }
}
