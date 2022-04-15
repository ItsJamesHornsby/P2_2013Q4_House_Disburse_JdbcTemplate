package disburse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import disburse.dao.HouseDisburseDAO;

@Controller
public class HouseDisburseController {
	
	private HouseDisburseDAO hdDAO;
	//Constructor Injection
	public HouseDisburseController (HouseDisburseDAO hdDAO) {
		this.hdDAO = hdDAO;
	}
	//Code Challenge 1
	@RequestMapping("all")
	public String getAllPage(Model model) {
		//System.out.println("Get all page.");
		model.addAttribute("hdList", hdDAO.getListofAllHDRowMapper());
		return "disburse.html";
	}
	//Code Challenge 2
	@GetMapping("bioGuideID/{id}")
	public String getHDbyID(@PathVariable String id, Model model) {
		model.addAttribute("hdList", hdDAO.getListbyId(id));
		return "disburse.html";
		
	}
	
	//CodeChallenge 3
	@RequestMapping("changeCategory")
	public String updateCategory(Model model) {
		model.addAttribute("hdList", hdDAO.updateCategory());
		return "disburse.html";
	}
	
	//Code Challenge 4
	@GetMapping("highestAmount")
	public String highestAmount(Model model) {
		model.addAttribute("hdList", hdDAO.highestAmount());
		return "disburse.html";
	}
	
	//Code Challenge 5
	@GetMapping("highestAmountByBioGuideID")
		public @ResponseBody String getHighestByID() throws JsonProcessingException {
			ObjectMapper om = new ObjectMapper();
			return om.writeValueAsString(hdDAO.getHighestByID());
		}
}
