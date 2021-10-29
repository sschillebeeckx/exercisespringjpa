package be.abis.exercise.model.dto;

public class CompanyDTO {

    private String companyName;
    private String town;

    public CompanyDTO(String companyName, String town) {
        this.companyName = companyName;
        this.town = town;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
