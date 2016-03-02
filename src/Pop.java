import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class Pop{
	public static void main(String[] args) throws Exception{

		Scanner scn = new Scanner(System.in);
		String str;
		System.out.println("welcome");
		while(true){
			str = scn.next();
			if(str.equals("update")){
				scn.nextLine();
				System.out.println("Enter Country Year MalePop FemalePop");
				String cnt = scn.nextLine().replaceAll("\\s+", "");

				Integer y = scn.nextInt();
				Integer m = scn.nextInt();
				Integer f = scn.nextInt();
				if(!isProtected(cnt)){
					update_data(cnt,y,m,f);
					System.out.println("The information of this country has been updated!");
				}
				else{
					System.out.println("The information of this country is protected. You can not change it!");
				}
			}
			else if(str.equals("get")){
				scn.nextLine();
				System.out.println("Enter the country");
				String country = scn.nextLine().replaceAll("\\s+","");
				System.out.println("Enter the year");
				Integer year = scn.nextInt();
				System.out.println("Enter the Male or Female");
				String filename = scn.next();
				System.out.println(getPop(country, year,"Est_"+filename+".csv"));
				
			}
			else if(str.equals("populationChart")){
				String cntr = scn.next();
				BuildCharts(cntr);
			}
			else if(str.equals("setPermission")){
				scn.nextLine();
				String cnt, p;
				System.out.println("Enter the country i.e India");
				cnt = scn.nextLine().replaceAll("\\s+", "");
				System.out.println("the permission status(0 means not protected and 1 means protected)");
				p = scn.next().replaceAll("\\s+", "");
				setPermission(cnt, p);
				System.out.println("Permission has been updated!");
			}
			else
				System.out.println("command is not defiend!");

		}
	}
	/* write your functions here*/
	private static void update_data(String cnt, Integer y, Integer m , Integer f) throws IOException{
		BufferedReader fReader = new BufferedReader(new FileReader(new File("Data/Est_Female.csv")));
		
	    String rawData = "";
	    rawData +=fReader.readLine();
	    String header[] = rawData.split(",");
	    int yind=0;
	    for(int i=0; i<header.length; i++){
			if(header[i].replaceAll("\\s+","").equals(String.valueOf(y))){
				yind=i;
			}
		}
	    //added becasue 1950 got translated to 1952
	    yind=yind-2;
//	    System.out.println(yind);
	    String mydata = rawData;
	    while ((rawData = fReader.readLine()) != null) {
	    	String spl[] = rawData.split(",");
	    	if(spl[2].replaceAll("\\s+","").equals(cnt.replaceAll("\\s+",""))){
	    		spl[yind] = String.valueOf(f);
	    	}
	    	mydata+="\n";
	    	for(int i=0; i<spl.length; i++){
	    		mydata+=spl[i];
	    		if(i!=spl.length-1)
	    			mydata+=",";
	    	}
	    }
	    mydata+="\n";
	    fReader.close();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("Data/Est_Female.csv"), false)));
		pw.write(mydata);
		pw.close();
		fReader = new BufferedReader(new FileReader(new File("Data/Est_Male.csv")));
	    rawData = fReader.readLine();
	    header = rawData.split(",");
	    mydata = rawData;
	    while ((rawData = fReader.readLine()) != null) {
	    	String spl[] = rawData.split(",");
	    	if(spl[2].replaceAll("\\s+","").equals(cnt.replaceAll("\\s+",""))){
//	    		System.out.println(rawData);
	    		spl[yind] = String.valueOf(m);
	    	}
	    	mydata+="\n";
	    	for(int i=0; i<spl.length; i++){
	    		mydata+=spl[i];
	    		if(i!=spl.length-1)
	    			mydata+=",";
	    	}
	    }
	    mydata+="\n";
	    fReader.close();
		pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("Data/Est_Male.csv"), false)));
		pw.write(mydata);
		pw.close();
	}
	public static void BuildCharts(String cntry) throws IOException{
		String[] year=null;
		Integer[] mData=null;
		Integer[] fData=null;
		BufferedReader fReader = new BufferedReader(new FileReader(new File("Data/Est_Female.csv")));
	    String rawData = "";
	    rawData =fReader.readLine();
	    String header[] = rawData.split(",");
	    year = new String[header.length-7];
	    mData = new Integer[header.length-7];
	    fData = new Integer[header.length-7];
	    
	    for(int i=0; i<(header.length-7); i++){
	    	year[i] = header[i+7];
	    	//System.out.println(i+" "+year[i]);
	    }
	    String mydata = rawData;
	    while ((rawData = fReader.readLine()) != null) {
	    	String spl[] = rawData.split(",");
	    	if(spl[2].replaceAll("\\s+","").equals(cntry.replaceAll("\\s+",""))){
	    		for(int i=0; i<(spl.length-5); i++){
	    			fData[i] = Integer.valueOf(spl[i+5].replaceAll("\\s+",""));
	    			//System.out.println(i+" "+fData[i]);
	    		}
	    	}
	    }
	    //System.out.println("---------------");
	    fReader.close();
		fReader = new BufferedReader(new FileReader(new File("Data/Est_Male.csv")));
	    rawData = fReader.readLine();
	    header = rawData.split(",");
	    mydata = rawData;
	    while ((rawData = fReader.readLine()) != null) {
	    	String spl[] = rawData.split(",");
	    	if(spl[2].replaceAll("\\s+","").equals(cntry.replaceAll("\\s+",""))){
	    		for(int i=0; i<(spl.length-5); i++){
	    			mData[i] = Integer.valueOf(spl[i+5].replaceAll("\\s+",""));
	    			//System.out.println(i+" "+mData[i]);
	    		}
	    	}
	    }
		BarChart_AWT chart = new BarChart_AWT("World Population Statistics", cntry, "Year from"+year[0]+" to "+year[year.length-1], "Population", year, mData, fData);
		
		chart.pack( );        
	    RefineryUtilities.centerFrameOnScreen( chart );        
	    chart.setVisible( true );
	}
	public static boolean isProtected(String country) throws IOException{
		BufferedReader fReader = new BufferedReader(new FileReader("Data/Permission.csv"));
		String rawData = "";
		boolean ans = true;
	    while ((rawData = fReader.readLine()) != null) {
	    	String spl[] = rawData.split(",");
	    	if(spl[0].replaceAll("\\s+","").equals(country.replaceAll("\\s+",""))){
	    		if(spl[1].replaceAll("\\s+", "").equals("0")){
	    			ans = false;
	    			break;
	    		}
	    	}
	    }
	    fReader.close();
	    return ans;
	}
	public static void setPermission(String country, String p) throws IOException{
		BufferedReader fReader = new BufferedReader(new FileReader("Data/Permission.csv"));
		String rawData = "";
		String totData = "";
	    while ((rawData = fReader.readLine()) != null) {
	    	String spl[] = rawData.split(",");
	    	if(spl[0].replaceAll("\\s+","").equals(country.replaceAll("\\s+",""))){
	    		if(p.replaceAll("\\s+","").equals("1"))
	    			spl[1]="1";
	    		else
	    			spl[1]="0";
	    	}
	    	totData+=spl[0]+","+spl[1]+"\n";
	    }
	    fReader.close();
	    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("Data/Est_Male.csv"), false)));
		pw.write(totData);
		pw.close();
	}
	//this is where negar's function will be put
	public static String getPop(String country, int year,String filename) throws IOException{
		
		int ef_year = (year -1950);
		BufferedReader dataReader = new BufferedReader(new FileReader("Data/"+filename));
	    String line = "";
	    String val = "";
	    while ((line = dataReader.readLine()) != null) {
	    	String[] raw = line.split(",");
	    	ArrayList<String> rawArray = new ArrayList<String>();
	    	for (int i = 0; i < raw.length; i++) {
				rawArray.add(raw[i].replaceAll("\\s+",""));
			}
	    	if(rawArray.contains(country)){
	    		val =  rawArray.get(5+ef_year);
	    	}
	    }
	    dataReader.close();
	    return val;
	}

}

class BarChart_AWT extends ApplicationFrame
{
   public BarChart_AWT( String applicationTitle , String chartTitle, String xAxis, String yAxis, String[] xData, Integer[] mData, Integer[] fData)
   {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         xAxis,            
         yAxis,            
         createDataset(xData, mData, fData),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 1120 , 734 ) );        
      setContentPane( chartPanel ); 
   }
   private CategoryDataset createDataset(String[] xData, Integer[] mData, Integer[] fData )
   {
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  
      for(int i=0; i<mData.length; i++){
    	  dataset.addValue(fData[i], "Female", xData[i]);
    	  dataset.addValue(mData[i], "Male", xData[i]);
      }
      return dataset; 
   }
}