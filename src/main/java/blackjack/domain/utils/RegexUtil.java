package blackjack.domain.utils;

import java.util.regex.Pattern;

public class RegexUtil {
    private static final Pattern NAME_PATTERN = Pattern.compile("[가-힣ㄱ-ㅎㅏ-ㅣ|a-zA-Z]+");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-*\\d+");

    public static boolean isAlphaOrKorean(String nameValue) {
        return NAME_PATTERN.matcher(nameValue).matches();
    }

    public static boolean isLong(String value) {
        return NUMBER_PATTERN.matcher(value).matches();
    }

}
