package com.linuxea.rediscli.resp.impl;

import com.linuxea.rediscli.CommandList;
import com.linuxea.rediscli.StringUtil;
import com.linuxea.rediscli.TruncateString;
import com.linuxea.rediscli.resp.ComplexRESP;

import java.util.ArrayList;
import java.util.List;

/**
 * Bulk Strings are used in order to represent a single binary-safe string up to 512 MB in length.
 */
public class BulkStringRESP implements ComplexRESP {


    @Override
    public List<String> parse(List<String> params) {

        ArrayList<String> strings = new CommandList(params.size());

        for (String param : params) {
            strings.add(startFlag() + param.length());
            strings.add(param);
        }

        return strings;
    }

    @Override
    public String reverseParse(String longCommands, List<String> results) {
        TruncateString truncateString = StringUtil.untilCRLF(longCommands);
        if (truncateString.getBefore().equals("$-1\r\n")) {
            results.add(null);
            return truncateString.getAfter();
        }

        TruncateString untilCRLF = StringUtil.untilCRLF(truncateString.getAfter());
        results.add(untilCRLF.getBefore().replaceFirst(CRLF, ""));

        return untilCRLF.getAfter();
    }


    @Override
    public String startFlag() {
        return "$";
    }
}
