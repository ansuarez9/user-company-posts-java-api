package com.ansuarez.services;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ansuarez.models.CompanySo;
import com.ansuarez.shared.SharedDelegate;

@Service
public class CompanyService extends SharedDelegate {

	public ArrayList<CompanySo> getCompanies(){
		return getAllCompanies();
	}
	
	public CompanySo getCompany(Long companyId){
		CompanySo company = getCompanyById(companyId);
		
		if(company != null) {
			return company;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with id " + companyId + " not found");
		}
		
	}
	
	public Boolean createCompany(CompanySo company){
		return createNewCompany(company);
	}
}
