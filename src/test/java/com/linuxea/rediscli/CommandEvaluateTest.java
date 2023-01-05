package com.linuxea.rediscli;

import org.junit.jupiter.api.Test;

public class CommandEvaluateTest {

    private final CommandEvaluate commandEvaluate = new CommandEvaluate();

    @Test
    public void evaluateTest() {
        String longCommands = "get a";
        String evaluate = this.commandEvaluate.evaluate(longCommands);
        System.out.println(evaluate);

    }

    @Test
    public void testPing() {
        String longCommands = "PING";
        String evaluate = this.commandEvaluate.evaluate(longCommands);
        System.out.println(evaluate);
    }

    @Test
    public void testReverseEvaluate() {
        String longCommands = "PING";
        String evaluate = this.commandEvaluate.evaluate(longCommands);
        System.out.println(evaluate);
    }

    @Test
    public void testReverseEvaluate2() {
        String longCommands = "lrange a";
        String evaluate = this.commandEvaluate.evaluate(longCommands);
        System.out.println(evaluate);
    }

    @Test
    public void evaluateSetNX() {
        String longCommands = "setnx name linuxea";
        String evaluate = this.commandEvaluate.evaluate(longCommands);
        System.out.println(evaluate);
    }

}
