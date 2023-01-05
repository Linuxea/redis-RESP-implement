package com.linuxea.rediscli;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class CommandEvaluateContext {

    private final Socket socket;
    private final CommandEvaluate commandEvaluate;

    public CommandEvaluateContext(String host, int port, CommandEvaluate commandEvaluate) throws IOException {
        this.commandEvaluate = commandEvaluate;
        socket = new Socket(host, port);
    }

    public String evaluate(String longCommands) {
        return this.commandEvaluate.evaluate(longCommands);
    }

    public String sendRESP(String resp) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        outputStream.write(resp.getBytes());
        //big enough to meet all scenes
        byte[] bytes = new byte[1024 * 1024 * 1024];
        int read = inputStream.read(bytes);
        return new String(bytes, 0, read);
    }

    public List<String> evaluateResp(String resp) {
        return this.commandEvaluate.evaluateResp(resp);
    }


    public List<String> sendCommand(String longCommands) throws IOException {
        String evaluate = this.evaluate(longCommands);
        String sendRESP = this.sendRESP(evaluate);
        return this.evaluateResp(sendRESP);
    }

    public void close() throws IOException {
        socket.close();
    }


}
