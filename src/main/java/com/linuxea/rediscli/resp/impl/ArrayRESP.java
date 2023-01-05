package com.linuxea.rediscli.resp.impl;

import com.linuxea.rediscli.CommandEvaluate;
import com.linuxea.rediscli.CommandList;
import com.linuxea.rediscli.StringUtil;
import com.linuxea.rediscli.TruncateString;
import com.linuxea.rediscli.resp.ComplexRESP;

import java.util.ArrayList;
import java.util.List;

/**
 * multiple string
 */
public class ArrayRESP implements ComplexRESP {

    private final CommandEvaluate commandEvaluate;

    public ArrayRESP(CommandEvaluate commandEvaluate) {
        this.commandEvaluate = commandEvaluate;
    }

    @Override
    public List<String> parse(List<String> params) {

        ArrayList<String> strings = new CommandList(params.size());

        strings.add(startFlag() + params.size());
        strings.addAll(params);
        return strings;
    }

    @Override
    public String reverseParse(String longCommands, List<String> results) {

        TruncateString truncateString = StringUtil.untilCRLF(longCommands);
        int arrLen = Integer.parseInt(truncateString.getBefore().replaceFirst(CRLF, "").substring(1));
        String after = truncateString.getAfter();
        // null array
        if(arrLen == -1) {
            results.add(null);
        } else {
            for (int i = 0; i < arrLen; i++) {
                //递归解析
                after = this.commandEvaluate.reverseParse(after, results);
            }
        }
        return after;
    }


    @Override
    public String startFlag() {
        return "*";
    }
}
