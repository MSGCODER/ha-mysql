package lenn.db.mysql.web;

import lenn.db.mysql.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lenn on 16/6/1.
 */
@Controller
public class WebController {

    @Autowired
    private PeopleService peopleService;

    @RequestMapping(value = "")
    @ResponseBody
    public String index(){
        return "hello lenn.";
    }

    @RequestMapping(value = "/master")
    @ResponseBody
    public String master(){
        peopleService.create();
        return "hello master.";
    }

    @RequestMapping(value = "/slave")
    @ResponseBody
    public String slave(){
        peopleService.save();
        return "hello slave.";
    }


}
