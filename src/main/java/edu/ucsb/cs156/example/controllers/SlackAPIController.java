package edu.ucsb.cs156.example.controllers;

import edu.ucsb.cs156.example.collections.StudentCollection;
import edu.ucsb.cs156.example.documents.Student;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(description = "Slack API")
@RequestMapping("/api/slack")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@Slf4j
public class SlackAPIController extends ApiController {
    @Autowired
    ObjectMapper mapper;

    @ApiOperation(value = "Get all channels")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/conversations.list")
    public String conversationsList() {
        return "[]";
    }
}
