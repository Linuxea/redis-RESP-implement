package com.linuxea.rediscli;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class CommandBasicTest {

    @Test
    public void sendCommandGet() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());
        List<String> sendCommand = commandEvaluateContext.sendCommand("incr a");
        System.out.println(sendCommand);
        commandEvaluateContext.close();
    }

    @Test
    public void sendCommandIncr() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());
        List<String> sendCommand = commandEvaluateContext.sendCommand("incr a");
        System.out.println(sendCommand);
        commandEvaluateContext.close();
    }

    @Test
    public void sendCommandExp() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());
        List<String> sendCommand = commandEvaluateContext.sendCommand("expire a 11");
        System.out.println(sendCommand);
        commandEvaluateContext.close();
    }

    @Test
    public void sendCommandIncrBy() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());
        List<String> sendCommand = commandEvaluateContext.sendCommand("incrby a 100");
        System.out.println(sendCommand);
        commandEvaluateContext.close();
    }

    @Test
    public void sendCommandLType() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());
        List<String> sendCommand = commandEvaluateContext.sendCommand("type game");
        System.out.println(sendCommand);
        commandEvaluateContext.close();
    }

    @Test
    public void sendCommandLPush() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());
        List<String> sendCommand = commandEvaluateContext.sendCommand("lpush game d e f g h");
        System.out.println(sendCommand);
        commandEvaluateContext.close();
    }

    @Test
    public void sendCommandLLen() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());
        List<String> sendCommand = commandEvaluateContext.sendCommand("llen game");
        System.out.println(sendCommand);
        commandEvaluateContext.close();
    }

    @Test
    public void sendCommandLLenNotExists() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());
        List<String> sendCommand = commandEvaluateContext.sendCommand("llen game2");
        System.out.println(sendCommand);
        commandEvaluateContext.close();
    }


    @Test
    public void sendCommandLRange() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());
        List<String> sendCommand = commandEvaluateContext.sendCommand("lrange game 0 100");
        System.out.println("======================");
        System.out.println(sendCommand);
        commandEvaluateContext.close();
    }

    @Test
    public void sendCommandLGet() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());

        List<String> setNameLinuxea = commandEvaluateContext.sendCommand("set name linuxea");
        System.out.println(setNameLinuxea);
        System.out.println("======================");

        List<String> sendCommand = commandEvaluateContext.sendCommand("get name");
        System.out.println("======================");
        System.out.println(sendCommand);

        commandEvaluateContext.close();
    }

    @Test
    public void sendCommandSetNX() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());

        List<String> setNameLinuxea = commandEvaluateContext.sendCommand("setnx name linuxea");
        System.out.println(setNameLinuxea);


        setNameLinuxea = commandEvaluateContext.sendCommand("setnx name2 linuxeaaaa");
        System.out.println(setNameLinuxea);
        commandEvaluateContext.close();
    }


    @Test
    public void sendCommandMGet() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());
        List<String> sendCommand = commandEvaluateContext.sendCommand("mget a x y z");
        System.out.println(sendCommand);
        commandEvaluateContext.close();
    }

    @Test
    public void sendCommandError() throws IOException {
        String redisServerHost = System.getenv("redisServerHost");
        CommandEvaluateContext commandEvaluateContext = new CommandEvaluateContext(redisServerHost, 6379, new CommandEvaluate());
        System.out.println(commandEvaluateContext.sendCommand("set radfdf ffff"));
        List<String> sendCommand = commandEvaluateContext.sendCommand("incr radfdf");
        System.out.println(sendCommand);
        commandEvaluateContext.close();
    }

}
