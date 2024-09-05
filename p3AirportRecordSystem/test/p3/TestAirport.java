package p3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAirport {

	// input data

	String validnameRegionCountryCodeLow, validnameRegionCountryCodeHigh;
	Type commercial, military, unknown;
	int validAltLow, validAltHigh;
	Airport a;

	@BeforeEach
	void setUp() throws Exception {
		validnameRegionCountryCodeLow = "ABC";
		validnameRegionCountryCodeHigh = "ABC".repeat(10);

		commercial = Type.COMMERCIAL;
		military = Type.MILITARY;
		unknown = Type.UNKNOWN;

		validAltLow = 1;
		validAltHigh = 250;

		a = new Airport(validnameRegionCountryCodeHigh, validnameRegionCountryCodeHigh, validnameRegionCountryCodeHigh,
				validnameRegionCountryCodeHigh, validAltHigh, commercial);

	}

	@Test
	void testAirportConstructorNoArgsValid() {
		assertNotNull(a);
	}

	@Test
	void testAirportConstructorWithArgs() {
		assertEquals(validnameRegionCountryCodeHigh, a.getName());
		assertEquals(validnameRegionCountryCodeHigh, a.getCountry());
		assertEquals(validnameRegionCountryCodeHigh, a.getRegion());
		assertEquals(validnameRegionCountryCodeHigh, a.getCode());
		assertEquals(validAltHigh, a.getAlt());
		assertEquals(commercial, a.getType());
	}

	@Test
	void testSetGetNameValid() {
		a.setName(validnameRegionCountryCodeHigh);
		assertEquals(validnameRegionCountryCodeHigh, a.getName());
		
		a.setName(validnameRegionCountryCodeLow);
		assertEquals(validnameRegionCountryCodeLow, a.getName());
	}

	@Test
	void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	void testSetGetRegionValid() {
		a.setRegion(validnameRegionCountryCodeHigh);
		assertEquals(validnameRegionCountryCodeHigh, a.getRegion());
		
		a.setRegion(validnameRegionCountryCodeLow);
		assertEquals(validnameRegionCountryCodeLow, a.getRegion());
	}

	@Test
	void testSetRegion() {
		fail("Not yet implemented");
	}

	@Test
	void testSetGetCountryValid() {
		a.setCountry(validnameRegionCountryCodeHigh);
		assertEquals(validnameRegionCountryCodeHigh, a.getCountry());
		
		a.setCountry(validnameRegionCountryCodeLow);
		assertEquals(validnameRegionCountryCodeLow, a.getCountry());
	}

	@Test
	void testSetCountry() {
		fail("Not yet implemented");
	}

	@Test
	void testSetGetCodeValid() {
		a.setCode(validnameRegionCountryCodeHigh);
		assertEquals(validnameRegionCountryCodeHigh, a.getCode());
		
		a.setCode(validnameRegionCountryCodeLow);
		assertEquals(validnameRegionCountryCodeLow, a.getCode());
	}

	@Test
	void testSetCode() {
		fail("Not yet implemented");
	}

	@Test
	void testSetGetAlt() {
		a.setAlt(validAltHigh);
		assertEquals(validAltHigh, a.getAlt());
		
		a.setAlt(validAltLow);
		assertEquals(validAltLow, a.getAlt());
		
	}

	@Test
	void testSetAlt() {
		fail("Not yet implemented");
	}

	@Test
	void testSetGetTypeValid() {
		a.setType(commercial);
		assertEquals(commercial, a.getType());
		
		a.setType(military);
		assertEquals(military, a.getType());
		
		a.setType(unknown);
		assertEquals(unknown, a.getType());
	}

	@Test
	void testSetGetTypeInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, ()->{
			a.setType(null);
		});
		
		assertEquals("INVALID TYPE", exp.getMessage());
	}

}
