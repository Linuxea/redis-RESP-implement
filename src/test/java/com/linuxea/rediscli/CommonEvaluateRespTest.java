package com.linuxea.rediscli;

import org.junit.jupiter.api.Test;

public class CommonEvaluateRespTest {

    private final CommandEvaluate commandEvaluate = new CommandEvaluate();

    @Test
    public void testEvaluateResp() {
        String resp = "*2\r\n" +
                "*3\r\n" +
                ":1\r\n" +
                ":2\r\n" +
                ":3\r\n" +
                "*2\r\n" +
                "+Hello\r\n" +
                "-World\r\n";
        System.out.println(commandEvaluate.evaluateResp(resp));

    }

}
