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
				String cnt = scn.next();
				Integer y = scn.nextInt();
				Integer m = scn.nextInt();
				Integer f = scn.nextInt();
				update_data(cnt,y,m,f);
			}
		}
	}
	/* write your functions here*/
	private static void update_data(String cnt, Integer y, Integer m , Integer f) throws IOException{
		BufferedReader fReader = new BufferedReader(new FileReader(new File("Data/Est_Female.csv")));
	    String rawData = fReader.readLine();
	    String header[] = rawData.split(",");
	    int yind=0;
	    for(int i=0; i<header.length; i++){
			if(header[i].trim().equals(String.valueOf(y))){
				yind=i;
			}
		}
	    
	    System.out.println(yind);
	    String mydata = rawData;
	    while ((rawData = fReader.readLine()) != null) {
	    	String spl[] = rawData.split(",");
	    	if(spl[2].equals(cnt)){
	    		spl[yind] = String.valueOf(f);
	    	}
	    	mydata+="\n";
	    	for(int i=0; i<spl.length; i++){
	    		mydata+=spl[i]+",";
	    	}
	    }
	    fReader.close();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("Data/Est_Female.csv"), true)));
		pw.write(mydata);
		pw.close();
		fReader = new BufferedReader(new FileReader(new File("Data/Est_Male.csv")));
	    rawData = fReader.readLine();
	    header = rawData.split(",");
	    mydata = rawData;
	    while ((rawData = fReader.readLine()) != null) {
	    	String spl[] = rawData.split(",");
	    	if(spl[2].equals(cnt)){
	    		System.out.println(rawData);
	    		spl[yind] = String.valueOf(f);
	    	}
	    	mydata+="\n";
	    	for(int i=0; i<spl.length; i++){
	    		mydata+=spl[i]+",";
	    	}
	    }
	    fReader.close();
		pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("Data/Est_Male.csv"), true)));
		pw.write(mydata);
		pw.close();
	}
}