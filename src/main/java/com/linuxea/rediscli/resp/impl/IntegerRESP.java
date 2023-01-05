package com.linuxea.rediscli.resp.impl;

import com.linuxea.rediscli.resp.SingleRESP;

/**
 * single integer resp
 */
public class IntegerRESP extends SingleRESP {


    @Override
    public String startFlag() {
        return ":";
    }


}
