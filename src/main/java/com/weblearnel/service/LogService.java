package com.weblearnel.service;

import com.weblearnel.model.Log;
import com.weblearnel.model.Word;
import com.weblearnel.repository.LogRepository;
import com.weblearnel.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    //add log
    public Log addLog(Log log) {
        if (log != null) {
            return logRepository.save(log);
        }
        return null;
    }

    // get all logs
    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    //find log
    public Log getOneLog(long id) {
        return logRepository.findById(id).get();
    }

}
