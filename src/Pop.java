import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Pop{
	public static void main(String[] args) throws Exception{
		Scanner scn = new Scanner(System.in);
		String str;
		while(true){
			str = scn.next();
			if(str.equals("update")){
				System.out.println("HOI");
				String cnt = scn.next();
				Integer m = scn.nextInt();
				Integer f = scn.nextInt();
				update_data(cnt,m,f);
			}
		}
	}
	/* write your functions here*/
	private static void update_data(String cnt, Integer m , Integer f) throws IOException{
		BufferedReader fReader = new BufferedReader(new FileReader(new File("../Data/Est_Female.csv")));
	    String rawData = fReader.readLine();
	    System.out.println(rawData);
	    String mydata = rawData;
	    while ((rawData = fReader.readLine()) != null) {
	    	String spl[] = rawData.split(",");
	    	mydata+=rawData;
	    }
	    fReader.close();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("Data/Est_female.csv"), true)));

	}
}