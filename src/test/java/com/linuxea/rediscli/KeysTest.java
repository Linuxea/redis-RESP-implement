package com.linuxea.rediscli;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class KeysTest {

    @Test
    public void testTran() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());
        System.out.println(commandEvaluateContext.sendCommand("keys *"));
    }

}
