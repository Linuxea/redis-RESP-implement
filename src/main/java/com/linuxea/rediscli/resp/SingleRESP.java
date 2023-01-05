package com.linuxea.rediscli.resp;

import com.linuxea.rediscli.StringUtil;
import com.linuxea.rediscli.TruncateString;

import java.util.List;

/**
 * 单个结果协议
 */
public abstract class SingleRESP implements ComplexRESP {


    @Override
    public List<String> parse(List<String> params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String reverseParse(String longCommands, List<String> results) {
        TruncateString truncateString = StringUtil.untilCRLF(longCommands);
        results.add(truncateString.getBefore().substring(1));
        return truncateString.getAfter();
    }
}
