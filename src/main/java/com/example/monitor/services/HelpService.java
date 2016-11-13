package com.example.monitor.services;

import com.example.monitor.domain.models.Help;
import com.example.monitor.repositories.HelpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpService {

    @Autowired
    private HelpRepository helpRepository;

    public List<Help> getHelpList() {

        List<Help> helps = helpRepository.findAll();
        return helps;
    }

}
