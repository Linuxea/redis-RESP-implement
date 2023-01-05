package com.linuxea.rediscli.gui;

import com.linuxea.rediscli.CommandEvaluate;
import com.linuxea.rediscli.CommandEvaluateContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

public class GuiEntry {

    private final int basicX = 10;
    private final int basicY = 20;
    private final int basicWidth = 630;
    private final int basicHeight = 620;
    private final int fieldWidth = 350;

    private CommandEvaluateContext commandEvaluateContext;

    public GuiEntry() {


        JFrame jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setSize(basicWidth, basicHeight);
        jFrame.setTitle("redis RESP client");
        jFrame.setResizable(false);
        URL resource = this.getClass().getClassLoader().getResource("img/redis.png");
        Image icon = Toolkit.getDefaultToolkit().getImage(resource.getPath());
        jFrame.setIconImage(icon);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (commandEvaluateContext != null) {
                    try {
                        commandEvaluateContext.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        // redis host
        JLabel redisLabel = new JLabel("redis host:port:");
        redisLabel.setBounds(basicX, basicY, 120, 20);
        JTextField redisTextField = new JTextField("");
        redisTextField.setBounds(basicX + 120, basicY, fieldWidth, 20);

        // raw command
        JLabel rawCommandLabel = new JLabel("命令原文:");
        rawCommandLabel.setBounds(basicX, basicY + 30, 120, 20);
        JTextArea rawCommandTextField = new JTextArea(10, 10);
        rawCommandTextField.setBounds(basicX + 120, basicY + 20 + 10, fieldWidth, 110);

        // parse command RESP
        JLabel commandParseLabel = new JLabel("命令解析 RESP:");
        commandParseLabel.setBounds(basicX, basicY + 30 + 110 + 10, 120, 20);
        JTextArea commandRespTextArea = new JTextArea();
        commandRespTextArea.setEditable(false);
        commandRespTextArea.setBackground(Color.LIGHT_GRAY);
        JScrollPane commandRespTextAreaJScrollPane = new JScrollPane(commandRespTextArea);
        commandRespTextAreaJScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        commandRespTextAreaJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        commandRespTextAreaJScrollPane.setBounds(basicX + 120, basicY + 30 + 110 + 10, fieldWidth, 110);

        // 原始响应
        JLabel commandRespRawLabel = new JLabel("原始响应:");
        commandRespRawLabel.setBounds(basicX, basicY + 30 + 110 + 10 + 110 + 10, 120, 20);
        JTextArea commandRespRawTextArea = new JTextArea();
        commandRespRawTextArea.setEditable(false);
        commandRespRawTextArea.setBackground(Color.LIGHT_GRAY);
        JScrollPane commandRespRawTextAreaJScrollPane = new JScrollPane(commandRespRawTextArea);
        commandRespRawTextAreaJScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        commandRespRawTextAreaJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        commandRespRawTextAreaJScrollPane.setBounds(basicX + 120, basicY + 30 + 110 + 10 + 110 + 10, fieldWidth, 110);

        //响应解析
        JLabel commandRespParseLabel = new JLabel("响应解析:");
        commandRespParseLabel.setBounds(basicX, basicY + 30 + 110 + 10 + 110 + 10 + 110 + 10, 120, 20);
        JTextArea commandRespParseTextArea = new JTextArea();
        commandRespParseTextArea.setEditable(false);
        commandRespParseTextArea.setBackground(Color.LIGHT_GRAY);
        JScrollPane jScrollPane = new JScrollPane(commandRespParseTextArea);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setBounds(basicX + 120, basicY + 30 + 110 + 10 + 110 + 10 + 110 + 10, fieldWidth, 110);

        JButton sendButton = new JButton("发送命令");
        sendButton.setBackground(Color.green);
        sendButton.setBounds(basicX + 120 + 100, basicY + 30 + 110 + 10 + 110 + 10 + 110 + 10 + 110 + 30, 80, 20);
        sendButton.addActionListener(e -> {
            String redisHostPort = redisTextField.getText();
            if (redisHostPort.isEmpty()) {
                JOptionPane.showMessageDialog(jFrame, "redis host 跟 port 不可为空");
                return;
            }
            String[] hostInfo = redisHostPort.split(":");
            try {
                if (commandEvaluateContext == null) {
                    commandEvaluateContext = new CommandEvaluateContext(hostInfo[0], Integer.parseInt(hostInfo[1]), new CommandEvaluate());
                    redisTextField.setEditable(false);
                }
                String evaluate = commandEvaluateContext.evaluate(rawCommandTextField.getText());
                commandRespTextArea.setText(evaluate);
                String sendRESP = commandEvaluateContext.sendRESP(evaluate);
                commandRespRawTextArea.setText(sendRESP);
                commandRespParseTextArea.setText(String.join("\r\n", commandEvaluateContext.evaluateResp(sendRESP)));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(jFrame, ex.getMessage());
            }
        });


        jFrame.add(redisLabel);
        jFrame.add(redisTextField);
        jFrame.add(rawCommandLabel);
        jFrame.add(rawCommandTextField);
        jFrame.add(commandParseLabel);
        jFrame.add(commandRespTextAreaJScrollPane);
        jFrame.add(commandRespRawLabel);
        jFrame.add(commandRespRawTextAreaJScrollPane);
        jFrame.add(commandRespParseLabel);
        jFrame.add(sendButton);
        jFrame.add(jScrollPane);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new GuiEntry();
    }


}
