package disburse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import disburse.dao.HouseDisburseDAO;
import disburse.vo.HouseDisburseDetail;

@RestController
public class CRMRestController {
	
	public HouseDisburseDAO hdDAO;
	
	@Autowired
	void setHouseDisburseDAO(HouseDisburseDAO hdDAO) {
		this.hdDAO = hdDAO;
	}
	
	HouseDisburseDAO getHouseDisburseDAO() {
		return hdDAO;
	}

	@PostMapping("/loadDisburse")
	public List<HouseDisburseDetail> loadList(@RequestBody final List<HouseDisburseDetail> hdList) {
		//System.out.println(hdList.get(0).toString());
		hdDAO.batchInsert(hdList);
		return hdDAO.findByYear("2020");
	}
}
