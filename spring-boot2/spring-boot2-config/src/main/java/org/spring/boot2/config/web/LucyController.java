package org.spring.boot2.config.web;

import org.spring.boot2.config.bean.ConfigBean;
import org.spring.boot2.config.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LucyController {
	@Autowired
	ConfigBean configBean;

	@RequestMapping(value = "/lucy")
	public String miya() {
		return configBean.getGreeting() + " >>>>" + configBean.getName() + " >>>>" + configBean.getUuid() + " >>>>"
				+ configBean.getMax();
	}
	
	@Autowired
    User user;
    @RequestMapping(value = "/user")
    public String user(){
        return user.getName()+user.getAge();
    }

}
