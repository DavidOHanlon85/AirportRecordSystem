/**
 * 
 */
package p3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.naming.directory.InvalidSearchFilterException;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class WriteToFile implements Runnable {
	
	List<Airport> localCopy = StartApp.airports;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		File file = new File("airport_data_recoded.csv");
		
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("name, new_code");
			bw.newLine();
			
			for (Airport a : localCopy) {
				
				int oddOrEven;
				
				if (a.getAlt() % 2 == 0) {
					oddOrEven = 0;
				} else {
					oddOrEven = 1;
				}
				
				String newCode = String.format("%s, %s%s%d%d", a.getName().toUpperCase(),a.getCode(), a.getCountry(),a.getAlt(), oddOrEven);
				
				bw.write(newCode);
				bw.newLine();
			}
			
			if(Thread.currentThread().isInterrupted()) {
				bw.close();
				fw.close();
			}
			
			bw.close();
			fw.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
