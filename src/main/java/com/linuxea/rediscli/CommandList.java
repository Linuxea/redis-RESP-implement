package com.linuxea.rediscli;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.linuxea.rediscli.resp.RESP.CRLF;

public class CommandList extends ArrayList<String> {


    public CommandList(int size) {
        super(size);
    }

    public CommandList() {
        super();
    }

    @Override
    public boolean add(String s) {
        if (Objects.nonNull(s) && s.endsWith(CRLF)) {
            return super.add(s);
        }
        return super.add(s + CRLF);
    }

    @Override
    public int size() {
        List<String> collect = new ArrayList<>(this);
        int size = 0;
        for (String s : collect) {
            if (s.replaceFirst(CRLF, "").length() > 1 && (s.startsWith("+") || s.startsWith("-") || s.startsWith("$") || s.startsWith("*"))) {
                continue;
            }
            size++;
        }
        return size;
    }
}
