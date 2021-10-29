package be.abis.exercise.mapper;

import be.abis.exercise.model.Company;
import be.abis.exercise.model.dto.CompanyDTO;

public class CompanyMapper {

    public static CompanyDTO toDTO(Company company){
        return new CompanyDTO(company.getName(), company.getAddress().getTown());
    }
}
