package ru.mtuci.shaa.simpleapi.conroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mtuci.shaa.simpleapi.dto.Status;

import java.net.UnknownHostException;

@Slf4j
@RestController
@RequestMapping( value = StatusController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE )
public class StatusController {
    public static final  String REST_URL = "/api/v1/status";

    @GetMapping
    public Status get() throws UnknownHostException {
        log.info( "get status" );
        return new Status(  );
    }
}
