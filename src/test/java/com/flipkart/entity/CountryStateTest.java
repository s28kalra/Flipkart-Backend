package com.flipkart.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.flipkart.repository.CountryRepository;

@SpringBootTest
class CountryStateTest {

	@Autowired
	private CountryRepository countryRepository;

	@Test
	void addCountriesAndStates() {
		Country country = new Country();
		country.setCountryId(4);
		country.setCode("IN");
		country.setName("India");
		int size = 36;
		State[] states = new State[size];
		states[0] = new State(57, "Andhra Pradesh");
		states[1] = new State(58, "Arunachal Pradesh");
		states[2] = new State(59, "Assam");
		states[3] = new State(60, "Bihar");
		states[4] = new State(61, "Chhattisgarh");
		states[5] = new State(62, "Goa");
		states[6] = new State(63, "Gujarat");
		states[7] = new State(64, "Haryana");
		states[8] = new State(65, "Himachal Pradesh");
		states[9] = new State(66, "Jammu & Kashmir");
		states[10] = new State(67, "Jharkhand");
		states[11] = new State(68, "Karnataka");
		states[12] = new State(69, "Kerala");
		states[13] = new State(70, "Madhya Pradesh");
		states[14] = new State(71, "Maharashtra");
		states[15] = new State(72, "Manipur");
		states[16] = new State(73, "Meghalaya");
		states[17] = new State(74, "Mizoram");
		states[18] = new State(75, "Nagaland");
		states[19] = new State(76, "Odisha");
		states[20] = new State(77, "Punjab");
		states[21] = new State(78, "Rajasthan");
		states[22] = new State(79, "Sikkim");
		states[23] = new State(80, "Tamil Nadu");
		states[24] = new State(81, "Telangana");
		states[25] = new State(82, "Tripura");
		states[26] = new State(83, "Uttar Pradesh");
		states[27] = new State(84, "Uttarakhand");
		states[28] = new State(85, "West Bengal");
		states[29] = new State(86, "Andaman and Nicobar Islands");
		states[30] = new State(87, "Chandigarh");
		states[31] = new State(88, "Dadra and Nagar Haveli");
		states[32] = new State(89, "Daman & Diu");
		states[33] = new State(90, "Lakshadweep");
		states[34] = new State(91, "Puducherry");
		states[35] = new State(92, "The Government of NCT of Delhi");

		for (int i = 0; i < size; i++)
			country.add(states[i]);

		countryRepository.save(country);

	}

}
