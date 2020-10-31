package ru.mtuci.shaa.simpleapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Data
@AllArgsConstructor
public class Status {
    private String hostName;
    private String hostAddress;

    public Status() throws UnknownHostException {
        this.hostName = InetAddress.getLocalHost().getHostName();
        this.hostAddress = InetAddress.getLocalHost().getHostAddress();
    }


}
