package com.linuxea.rediscli;

import static com.linuxea.rediscli.resp.RESP.CRLF;

/**
 * str util
 */
public class StringUtil {

    /**
     * split first CRLF with others
     *
     * @param raw raw string
     * @return {@link TruncateString}
     */
    public static TruncateString untilCRLF(String raw) {
        int indexOf = raw.indexOf(CRLF);
        String before = raw.substring(0, indexOf + CRLF.length());
        String after = raw.substring(indexOf + CRLF.length());
        return new TruncateString(before, after);
    }

}
