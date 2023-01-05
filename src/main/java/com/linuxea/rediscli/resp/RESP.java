package com.linuxea.rediscli.resp;

import java.util.List;

/**
 * Redis serialization protocol (RESP) specification
 */
public interface RESP {

    /**
     * RESP CRLF
     */
    String CRLF = "\r\n";


    /**
     * 开始标识
     *
     * @return start flag
     */
    String startFlag();

    /**
     * 解析
     *
     * @param params 参数
     * @return 解析后命令
     */
    List<String> parse(List<String> params);


    /**
     * 逆向解析
     *
     * @param longCommands resp protocol
     * @return commands
     */
    String reverseParse(String longCommands, List<String> results);

}
