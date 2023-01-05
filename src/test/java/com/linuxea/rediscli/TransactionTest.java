package com.linuxea.rediscli;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TransactionTest {

    @Test
    public void testTran() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());
        try {
            System.out.println(commandEvaluateContext.sendCommand("multi"));
            System.out.println(commandEvaluateContext.sendCommand("incr b"));
            System.out.println(commandEvaluateContext.sendCommand("incr b"));
            System.out.println(commandEvaluateContext.sendCommand("incr b"));
            System.out.println(commandEvaluateContext.sendCommand("incr b"));
            System.out.println(commandEvaluateContext.sendCommand("exec"));
        } finally {
            commandEvaluateContext.sendCommand("discard");
        }
    }

}
