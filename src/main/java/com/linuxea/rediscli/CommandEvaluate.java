package com.linuxea.rediscli;

import com.linuxea.rediscli.resp.*;
import com.linuxea.rediscli.resp.impl.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandEvaluate {

    private final Map<String, RESP> respMap = new HashMap<>();

    public CommandEvaluate() {
        ArrayRESP arrayRESP = new ArrayRESP(this);
        respMap.putIfAbsent(arrayRESP.startFlag(), arrayRESP);

        BulkStringRESP bulkStringRESP = new BulkStringRESP();
        respMap.putIfAbsent(bulkStringRESP.startFlag(), bulkStringRESP);

        IntegerRESP integerRESP = new IntegerRESP();
        respMap.putIfAbsent(integerRESP.startFlag(), integerRESP);

        SimpleStringRESP simpleStringRESP = new SimpleStringRESP();
        respMap.putIfAbsent(simpleStringRESP.startFlag(), simpleStringRESP);

        ErrorRESP errorRESP = new ErrorRESP();
        respMap.putIfAbsent(errorRESP.startFlag(), errorRESP);

    }

    public String evaluate(String longCommands) {

        List<String> params = Arrays.stream(longCommands.split("\\s+")).collect(Collectors.toList());

        RESP bulkStringRESP = respMap.get("$");
        List<String> bulkStringParse = bulkStringRESP.parse(params);

        RESP arrayRESP = respMap.get("*");
        List<String> parse = arrayRESP.parse(bulkStringParse);

        return String.join("", parse);
    }

    public List<String> evaluateResp(String longCommands) {
        List<String> results = new UnWrapperCommandList();
        reverseParse(longCommands, results);
        return results;
    }

    public String reverseParse(String longCommands, List<String> results) {
        char startFlag = longCommands.charAt(0);
        return this.respMap.get(Character.toString(startFlag)).reverseParse(longCommands, results);
    }


}
