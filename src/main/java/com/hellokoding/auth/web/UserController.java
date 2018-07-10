package com.hellokoding.auth.web;

import com.hellokoding.auth.model.User;
import com.hellokoding.auth.model.Weather;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.service.UserService;
import com.hellokoding.auth.service.WeatherService;
import com.hellokoding.auth.validator.UserValidator;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);	
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;
@Autowired
private WeatherService weatherService;
    @Autowired
    private UserValidator userValidator;

    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
    	logger.debug("Registration called");
        model.addAttribute("userForm", new User());

        return "registration";
    }
    /*
    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public String forgotpassword(Model model) {//,Principal p) {
   // 	String s=p.getName();
    //	User u=userService.findByUsername(s);//String username);
    //	model.addAttribute("question",u.getSecurityquestion());
        model.addAttribute("answer","");
        return "forgotpassword";
    }
    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public String checkforgotpassword(@ModelAttribute("userForm") String answer,@RequestParam("secanswer")String secanswer, Model model,Principal p) {
    	String s=p.getName();
    	User u=userService.findByUsername(s);//String username);
    	String a=u.getSecurityanswer();
    	System.out.println("Answer "+answer+" SecANswer "+secanswer);
    	//String aaa=("secanswer");
    	if(a.equals(secanswer))
    	{
    	securityService.autologin(u.getUsername(), u.getPassword());

        return "redirect:/welcome";
        }
    	else //to add a line highlighting wrong password
    		return "redirect:/forgotpassword";
    }
*/
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
    	logger.debug("Registration with post called with userform");
    	userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        logger.debug("autologin called");
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
    	logger.debug("Login (get )");
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model,Principal p) {
    	logger.debug("user logged in successfully");
    	String s=p.getName();
    	User u=userService.findByUsername(s);//String username);
    	model.addAttribute("defaultloc",u.getLocation());
        return "welcome";
    }
    

    @RequestMapping(value = "/getforecast/place", method = RequestMethod.GET)
    public String getforecast(@RequestParam("place")String loc,@RequestParam("days")String days,Model model) {
    	logger.debug("Getting weather forecast for "+loc);
    	 //Weather w=new Weather(loc,"sunny","28","24","29","89%");
    	Weather w= weatherService.findByPlace(loc);
    	if(w==null)
    		w=new Weather(loc,"sunny","28","24","29","89%");
    	model.addAttribute("temperature",w.getTemperature());
    	model.addAttribute("place",loc);
    	model.addAttribute("mintemp",w.getMintemp());
    	model.addAttribute("maxtemp",w.getMaxtemp());
    	model.addAttribute("humidity",w.getHumidity());
    	model.addAttribute("weather",w.getWeather());
    	//if(day)days=2;
    	int day=Integer.parseInt(days);
    	if(day>0)
    	{
    	for(int i=0;i<day;i++)
    	{
    		model.addAttribute("day"+i,w);
    	}
    	model.addAttribute("days",days);
    	}
        return "welcome";
        /*INSERT INTO `weather` (`id` ,`humidity`,`maxtemp`,`mintemp`,`place`,`temperature`,`weather`)
VALUES
(1,'45%','32','23','hyderabad','25','sunny');*/
    }
     
}
