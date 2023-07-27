package com.weblearnel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weblearnel.model.Log;
import com.weblearnel.service.LogService;

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
