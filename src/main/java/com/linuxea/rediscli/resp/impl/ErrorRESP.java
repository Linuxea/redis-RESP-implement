package com.linuxea.rediscli.resp.impl;

import com.linuxea.rediscli.resp.SingleRESP;

/**
 * single integer resp
 */
public class ErrorRESP extends SingleRESP {


    @Override
    public String startFlag() {
        return "-";
    }


}
