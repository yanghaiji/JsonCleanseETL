package com.javayh.jsoncleanseetl.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JSONPathFinderController {

    @GetMapping("/json-path-finder")
    public String jsonPathFinder(Model model) {
        return "json-path-finder";
    }
}
