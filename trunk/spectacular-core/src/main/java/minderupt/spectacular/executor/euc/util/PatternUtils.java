package minderupt.spectacular.executor.euc.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public final class PatternUtils {


    public static Pattern convertToPattern(String s) {

        // change variable pieces to regex groups
        // doing this with regex cuz im lazy
        Pattern p = Pattern.compile("\\$\\{(.*)\\}");
        Matcher m = p.matcher(s);
        String regexString = m.replaceAll("(.*?)");
        regexString = regexString.replaceAll(System.getProperty("line.separator"), "");

        Pattern realPattern = Pattern.compile(regexString);
        return (realPattern);

    }
}