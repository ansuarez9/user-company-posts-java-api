package com.ansuarez.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ansuarez.models.CompanySo;
import com.ansuarez.services.CompanyService;

@RestController
@RequestMapping(path="api/company")
public class CompanyController {
	@Autowired
	CompanyService companyService;
	
	@GetMapping(path="/all")
	private List<CompanySo> getCompanies(){
		return companyService.getCompanies();
	}
	
	@GetMapping(path="/{companyId}")
	private CompanySo getCompany(@PathVariable Long companyId){
		return companyService.getCompany(companyId);
	}
	
	@PostMapping(path="/new")
	private Boolean createCompany(@RequestBody CompanySo company){
		return companyService.createCompany(company);
	}

}
