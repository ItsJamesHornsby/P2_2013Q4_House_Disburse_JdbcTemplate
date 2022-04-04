package disburse.controller;

import org.springframework.stereotype.Controller;

import disburse.dao.HouseDisburseDAO;

@Controller
public class HouseDisburseController {
	
	private HouseDisburseDAO hpDAO;
	//Constructor Injection
	public HouseDisburseController (HouseDisburseDAO hpDAO) {
		this.hpDAO = hpDAO;
	}

}
