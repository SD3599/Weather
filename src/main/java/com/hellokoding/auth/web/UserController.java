package com.hellokoding.auth.web;

import com.hellokoding.auth.model.User;
import com.hellokoding.auth.model.Weather;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.service.UserService;
import com.hellokoding.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    

    @RequestMapping(value = "/getforecast/place", method = RequestMethod.GET)
    public String getforecast(@RequestParam("place")String loc,@RequestParam("days")String days,Model model) {
    	
    	 Weather w=new Weather(loc,"sunny","28","24","29","89%");
    	model.addAttribute("temperature",w.getTemperature());
    	model.addAttribute("place",loc);
    	model.addAttribute("mintemp",w.getMintemp());
    	model.addAttribute("maxtemp",w.getMaxtemp());
    	model.addAttribute("humidity",w.getHumidity());
    	model.addAttribute("weather",w.getWeather());
    	//if(day)days=2;
    	int day=Integer.parseInt(days);
    	for(int i=0;i<day;i++)
    	{
    		model.addAttribute("day"+i,w);
    	}
    	model.addAttribute("days",days);
        return "welcome";
    }
     
}
