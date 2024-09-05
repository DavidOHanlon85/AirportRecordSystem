package p3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Start point for the app. Reads in data from csv file and then presents a menu
 * with several functions - searches and a thread based write to file.
 * 
 * @author Aidan
 *
 */
public class StartApp {

	// name of the file to be read in.. could also include a file path if needed
	private final static String FILE_IN = "airport_data.csv";

	// container that holds the airports (of type airport) - note this is static
	public static List<Airport> airports = new ArrayList<Airport>();

	/**
	 * Start point for app
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// note the use of generalised exception handling.
		try {
			readData();
			showMenu();
			// note use of general exception catch all here ..
		} catch (Exception exception) {
			// output specific message to user - also could be logged for developers
			System.out.println("Problem - please contact admin");
			System.out.println(exception.getLocalizedMessage());
		}
	}

	/**
	 * Shows the menu and Coordinates the searches and file write
	 * 
	 * @throws Exception
	 */
	public static void showMenu() throws Exception {
		try (Scanner scanner = new Scanner(System.in);) { // try catch with resource management
			System.out.println();

			int option;

			do {
				System.out.println("1. Display all airports");
				System.out.println("2. Display all airport in IRL");
				System.out.println("3. Display the airport with highest alt ");
				System.out.println(
						"4. Display each region (in alphabetical order) with total number of airports in the region ");
				System.out.println(
						"5. Display all heliports a. ordered by alt (descending) b. ordered bt alt (ascending) *recursive call ");
				System.out.println(
						"6. Generate export/write to a new file in a new thread in the format name, <CODE><COUNTRY><ALT><PARITY BIT> eg BFSUK2680 ");
				System.out.println("7. Quit");
				System.out.println("Enter option ...");
				try {
					option = scanner.nextInt();
				} catch (Exception exception) {
					// use input issue... default to zero and carry on (would be reasonable to
					// expect the system to recover from this)
					option = 0;
					// need to flush buffer
					scanner.nextLine();
				}

				// menu options processing
				switch (option) {
				// Note in general here the use of Single Responsibility (SOLID) - ie a method
				// does one main thing .. eg process a search etc and another method is used to
				// display - note both in the same method
				case 1:
					System.out.println("All airports");
					System.out.println();
					displayAllDetailsInList(airports);
					break;
				case 2:
					displayAirportAndCodes(filterByCountry(airports, "IRL"));
					break;
				case 3:
					Airport air = Collections.max(airports, new CompareByAltitude());
					System.out.println(air.getName() + " " + air.getCountry() + " " + air.getAlt());
					break;
				case 4:
					Map<String, Integer> cityRegion = new TreeMap<String, Integer>();

					createMapRegionAndFrquency(cityRegion);
					displayRegionAndFreqnuency(cityRegion);
					break;
				case 5:

					List<Airport> commercialHeliports = filterByCharAtIndex2AndType(airports, 'H', Type.COMMERCIAL);

					Collections.sort(commercialHeliports, new CompareByAltitude().reversed());

					System.out.println("Helipads sorted by alt DESCENDING");
					displayAirportCodeAndAlt(commercialHeliports);

					System.out.println();

					List<Airport> results = reverse(commercialHeliports);
					System.out.println("Helipads sorted by alt ASCENDING");
					displayAirportCodeAndAlt(results);
					break;
				case 6:
					WriteToFile w = new WriteToFile();
					Thread t1 = new Thread(w);
					
					t1.start();
					break;
				case 7:
					System.out.println("Quitting");
					break; // rem. need the break here too !
				default:
					System.out.println("Try options again...");
				}
			} while (option != 7);
			// scanner will close automatically - try - catch with resource management
		} catch (Exception exception) {
			// general exception - not able to recover therefore ending gracefully - passing
			// back to main method to deal with
			throw exception;
		}
	}

	/**
	 * This method filter by character of index 3 and type
	 * @param airports
	 * @param c
	 * @param commercial
	 * @return
	 */
	public static List<Airport> filterByCharAtIndex2AndType(List<Airport> airports, char c, Type commercial) {

		List<Airport> commercialHeliports = new ArrayList<Airport>();

		for (Airport a : airports) {
			if (a.getCode().charAt(2) == 'H' && a.getType() == Type.COMMERCIAL) {
				commercialHeliports.add(a);
			}
		}
		
		return commercialHeliports;
		
	}

	/**
	 * This metod displays Airport, Code and Alt
	 * 
	 * @param commercialHeliports
	 */
	public static void displayAirportCodeAndAlt(List<Airport> commercialHeliports) {
		for (Airport a : commercialHeliports) {
			a.displayAirportCodeAndAlt();
		}
	}

	/**
	 * @param cityRegion
	 */
	public static void displayRegionAndFreqnuency(Map<String, Integer> cityRegion) {
		for (String key : cityRegion.keySet()) {
			System.out.printf("%s : %d\n", key, cityRegion.get(key));
		}
	}

	/**
	 * This map create a map of region vs frequency
	 * @param cityRegion
	 */
	public static void createMapRegionAndFrquency(Map<String, Integer> cityRegion) {
		for (Airport a : airports) {
			if (cityRegion.containsKey(a.getRegion())) {
				int currentNumber = cityRegion.get(a.getRegion());
				cityRegion.put(a.getRegion(), (currentNumber + 1));
			} else {
				cityRegion.put(a.getRegion(), 1);
			}
		}
	}

	public static List<Airport> reverse(List<Airport> commercialHeliports) {

			if (commercialHeliports.size() > 1) {
				Airport a = commercialHeliports.remove(0);
				reverse(commercialHeliports);
				commercialHeliports.add(a);
			}
		
		return commercialHeliports;
	}

	/**
	 * This method display airport and code of list
	 * 
	 * @param input
	 * @throws IllegalArgumentException
	 */
	private static void displayAirportAndCodes(List<Airport> input) throws IllegalArgumentException {

		if (input == null || input.size() == 0) {
			throw new IllegalArgumentException("LIST NULL OR VOID");
		}

		for (Airport a : input) {
			a.displayAirportAndCode();
		}

	}

	/**
	 * This method filters by country and returns a list with the results
	 * 
	 * @param input
	 * @param country
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static List<Airport> filterByCountry(List<Airport> input, String country) throws IllegalArgumentException {

		if (input == null || input.size() == 0) {
			throw new IllegalArgumentException("LIST NULL OR VOID");
		}

		List<Airport> results = new ArrayList<Airport>();

		for (Airport a : input) {
			if (a.getCountry().equalsIgnoreCase(country)) {
				results.add(a);
			}
		}
		return results;

	}

	public static void displayAllDetailsInList(List<Airport> input) {

		for (Airport a : input) {
			a.displayAll();
			System.out.println();
		}

	}

	/**
	 * Reads in the data from the csv and maps to the Player class
	 */
	public static void readData() throws Exception {

		System.out.println("Loading data...");
		File file = new File(FILE_IN);

		FileReader fileReader;
		BufferedReader bufferedReader;
		String airportInfo;
		String[] data;
		int recordsReadAttempts = 0;

		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			// parse each data point - by comma

			airportInfo = bufferedReader.readLine();
			// ignore the first line (header values)
			airportInfo = bufferedReader.readLine();

			do {
				try {
					recordsReadAttempts++;
					// create a airport and add the stats
					try {
						String[] splitDetails = airportInfo.split(",");

						Airport a = new Airport();

						a.setName(splitDetails[0]);
						a.setRegion(splitDetails[1]);

						if (splitDetails[2].equalsIgnoreCase("IRE")) {
							a.setCountry("IRL");
						} else {
							a.setCountry(splitDetails[2]);
						}

						a.setCode(splitDetails[3].substring(0, 3).toUpperCase());

						a.setAlt(Integer.parseInt(splitDetails[4]));

						int code = Integer.parseInt(splitDetails[6]);

						if (code == 1) {
							a.setType(Type.COMMERCIAL);
						} else if (code == 2) {
							a.setType(Type.MILITARY);
						} else if (code == 3) {
							a.setType(Type.UNKNOWN);
						} else {
							throw new Exception("INVALID CODE");
						}

						airports.add(a);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (Exception exception) {
					// if in here there something went wrong went parsing the input data for this
					// record.
					// going to skip this record and carry on reading
					System.err.println("Bad record ");
				} finally {
					// read the next line
					airportInfo = bufferedReader.readLine();
				}
			} while (airportInfo != null);

			// close all
			fileReader.close();
			bufferedReader.close();

			System.out.println("\nAttempted to read airports data " + recordsReadAttempts);
			System.out.println("Airports data read successfully : " + airports.size());

		} catch (FileNotFoundException e) {
			throw new Exception("Unable to find the file " + FILE_IN);
		} catch (IOException e) {
			throw new Exception("Other issues with file read  ");
			// could log these or use own defined exception type
		} catch (Exception e) {
			throw new Exception("General issue on data read / processing  ");
		}
	}

}
