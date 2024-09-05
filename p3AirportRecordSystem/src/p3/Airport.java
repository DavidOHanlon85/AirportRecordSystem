/**
 * 
 */
package p3;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class Airport {
	
	// Instance Variable
	
	private String name;
	private String region;
	private String country;
	private String code;
	private int alt;
	private Type type; 
	
	// Constructors
	
	/**
	 * Default constructor
	 */
	public Airport() {
		
	}
	
	/**
	 * Constructor with args
	 * 
	 * @param name
	 * @param region
	 * @param country
	 * @param code
	 * @param alt
	 * @param type
	 */
	public Airport(String name, String region, String country, String code, int alt, Type type) {
		this.setName(name);
		this.setRegion(region);
		this.setCountry(country);
		this.setCode(code);
		this.setAlt(alt);
		this.setType(type);
	} 
	
	// Getters and Setters

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the alt
	 */
	public int getAlt() {
		return alt;
	}

	/**
	 * @param alt the alt to set
	 */
	public void setAlt(int alt) {
		this.alt = alt;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 * @throws IllegalArgumentException
	 */
	public void setType(Type type) throws IllegalArgumentException{
		if (type == null) {
			throw new IllegalArgumentException("INVALID TYPE");
		} else {
			this.type = type;
		}
		
	}
	
	// Methods
	
	public void displayAll() {
		
		System.out.println("Name           : " + getName()) ;
		System.out.println("City           : " + getRegion());
		System.out.println("Country        : " + getCountry());
		System.out.println("Code           : " + getCode());
		System.out.println("Alt            : " + getAlt());
		System.out.println("Type           : " + getType());
	}
	
	public void displayAirportAndCode() {
		System.out.printf("%s %s\n", getName(), getCode());
	}
	
	public void displayAirportCodeAndAlt() {
		System.out.printf("%s %s %d\n", getName(), getCountry(), getAlt());
	}
	
	
}
