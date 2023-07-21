package com.weblearnel.controller;

import com.weblearnel.model.Log;
import com.weblearnel.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired

    private LogService logService;

    // add log
    public Log addLog(@RequestBody Log log) {
        return logService.addLog(log);
    }

    //Get All Logs
    @GetMapping("/")
    public List<Log> findAllLogs() {
        return logService.getAllLogs();
    }

    // Get One Word
    @GetMapping("/{id}")
    public Log findLog(@PathVariable long id) {
        return logService.getOneLog(id);
    }

}
