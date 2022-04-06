package disburse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import disburse.dao.HouseDisburseDAO;

@Controller
public class HouseDisburseController {
	
	private HouseDisburseDAO hdDAO;
	//Constructor Injection
	public HouseDisburseController (HouseDisburseDAO hdDAO) {
		this.hdDAO = hdDAO;
	}
	@RequestMapping("all")
	public String getAllPage(Model model) {
		//System.out.println("Get all page.");
		model.addAttribute("hdList", hdDAO.getListofAllHDRowMapper());
		return "disburse.html";
	}
	

}
