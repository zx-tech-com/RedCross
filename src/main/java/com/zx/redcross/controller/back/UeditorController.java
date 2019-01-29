package com.zx.redcross.controller.back;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.ueditor.ActionEnter;
import com.zx.redcross.annotation.BackEnd;

@RestController
@RequestMapping("/ueditor")
public class UeditorController {
	
    @RequestMapping(value="/config")
    @BackEnd
    public void config(HttpServletRequest request, HttpServletResponse response) {
    	response.setHeader("Content-Type" , "text/html");
 
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            System.err.println(exec);
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
