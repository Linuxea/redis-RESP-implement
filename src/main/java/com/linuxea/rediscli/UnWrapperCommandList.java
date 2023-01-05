package com.linuxea.rediscli;

import java.util.ArrayList;
import java.util.Objects;

import static com.linuxea.rediscli.resp.RESP.CRLF;

public class UnWrapperCommandList extends ArrayList<String> {


    public UnWrapperCommandList(int size) {
        super(size);
    }

    public UnWrapperCommandList() {
        super();
    }

    @Override
    public boolean add(String s) {
        if (Objects.nonNull(s) && s.endsWith(CRLF)) {
            return super.add(s.replaceFirst(CRLF, ""));
        }
        return super.add(s);
    }

}
