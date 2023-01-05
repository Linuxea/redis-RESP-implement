package com.linuxea.rediscli.resp.impl;

import com.linuxea.rediscli.resp.SingleRESP;

/**
 * simple string RESP protocol
 */
public class SimpleStringRESP extends SingleRESP {

    @Override
    public String startFlag() {
        return "+";
    }


}
