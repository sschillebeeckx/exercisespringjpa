package be.abis.exercise.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="aid")
	private int id;
	@Column(name="astreet")
	private String street;
	@Column(name="astrno")
	private String nr;
	@Column(name="atownno")
	private String zipcode;
	@Column(name="atown")
	private String town;
	@Column(name="acountr")
	private String countryCode;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Address)) return false;
		Address address = (Address) o;
		return street.equals(address.street) &&
				nr.equals(address.nr) &&
				zipcode.equals(address.zipcode) &&
				town.equals(address.town);
	}

	@Override
	public int hashCode() {
		return Objects.hash(street, nr, zipcode, town);
	}
}
