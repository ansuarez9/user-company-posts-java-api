package com.ansuarez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ansuarez.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
}
