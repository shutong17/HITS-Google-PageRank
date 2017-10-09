//HONGDA YANG cs610 5178 prp


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class hits5178 {
	public int initialvalue;
	public int iteration;
	public String filename;
	public int v;
	public int e;
	public int[][] Adj;
	public double[] h;
	public double[] a;
	hits5178(){	
	}
	hits5178(int iteration, int initialvalue, String filename){
		this.initialvalue = initialvalue;
		this.filename = filename;
		this.iteration = iteration;
		try{
			File file = new File(this.filename);
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			String[] temp = line.split(" ");
			this.v = Integer.parseInt(temp[0]);
			this.e = Integer.parseInt(temp[1]);
			Adj = new int[v][v];
			for(int i = 0;i<this.v;i++){
				for(int j = 0;j < this.v;j++){
					this.Adj[i][j] = 0;
				}
			}
			
			for(int i = 0;i < this.e;i++){
				line = in.readLine();
				String[] vertex = line.split(" ");
				int m = Integer.parseInt(vertex[0]);
				int n = Integer.parseInt(vertex[1]);
				this.Adj[m][n] = 1;
			}
			
			in.close();
			h = new double[v];
			a = new double[v];
			switch(initialvalue) {
			case 0:
				for(int i = 0;i < v;i++){
					h[i] = 0;
					a[i] = 0;
				}
				break;
			case 1:
				for(int i = 0;i < v;i++){
					h[i] = 1.0;
					a[i] = 1.0;
				}
				break;
			case -1:
				for(int i = 0;i < v;i++){
					h[i] = 1.0/v;
					a[i] = 1.0/v;
				}
				break;
			case -2:
				for(int i = 0;i < v;i++){
					h[i] = 1.0/Math.sqrt(v);
					a[i] = 1.0/Math.sqrt(v);
				}
				break;
			default:
				System.out.print("Please input 0 1 -1 -1 to initial the intialvalue.");
				break;
			}
		}
		catch(IOException e){
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length !=3){
			System.out.println("There are only three variables as inputs.");
			System.exit(-1);
		}
		int iteration = Integer.parseInt(args[0]);
		int initialvalue = Integer.parseInt(args[1]);
		String filename = args[2];
		if(initialvalue<-2||initialvalue>1){
			System.out.println("The range of initialvalue is between -2 and 1.");
			System.exit(-1);
		}
		
		hits5178 hits = new hits5178(iteration,initialvalue,filename);
		hits.hits5178Alg(hits,hits.v);	
	}
	public void hits5178Alg(hits5178 hits,int n){
		double[] h = new double[n];
		h = hits.h;
		double[] a = new double[n];
		a = hits.a;
		double[] pre_h = new double[n];
		double[] pre_a = new double[n];
		int max_iter = Integer.MAX_VALUE ;
		double errorrate = Double.MIN_VALUE;
    try {
    String filePath = "output_hits.txt";
		File file = new File(filePath);
 		PrintStream ps = new PrintStream(new FileOutputStream(file));
    
    
		if(hits.iteration>0){
			max_iter = hits.iteration;
		}else{
			switch(hits.iteration){
			case 0:
				errorrate = 0.00001;
				break;
			case -1:
				errorrate = 0.1;
				break;
			case -2:
				errorrate = 0.01;
				break;
			case -3:
				errorrate = 0.001;
				break;
			case -4:
				errorrate = 0.0001;
				break;
			case -5:
				errorrate = 0.00001;
				break;
			case -6:
				errorrate = 0.000001;
				break;
			default:
				errorrate = 0.000001;
			}
		}
		
		if(hits.v<=10){
			int counter = 0;
      //System.out.printf("%s%2d%2s","Base   ",counter,":");
			ps.printf("%s%3d%2s","Base   :",counter,":");
			for(int i = 0;i < n;i++){
				//System.out.printf(" A/H[%d]=%.6f/%.6f",i,Math.round(a[i]*1000000.0)/1000000.0,Math.round(h[i]*1000000.0)/1000000.0);
				ps.printf(" A/H[%d]=%.7f/%.7f",i,Math.round(a[i]*10000000.0)/10000000.0,Math.round(h[i]*10000000.0)/10000000.0); 
			}
			//System.out.println();
      ps.println();
			do{
				
				for(int i = 0;i < n;i++){
					pre_h[i] = h[i];
					pre_a[i] = a[i];	
				}
				
				
				counter++;
				//A step:
				int[][] Adj_T = new int[hits.v][hits.v];
				for(int i = 0;i < n;i++){
					for(int j = 0;j < n;j++){
						Adj_T[j][i] = hits.Adj[i][j];
					}
				}
				for(int row = 0;row < n;row++){
					double sum = 0.0;
					for(int i = 0;i < n;i++){
						sum = sum + h[i]*Adj_T[row][i]; 
					}
					//pre_a[row] = a[row];
					a[row] = sum;
				}
				//H step:
			
				for(int row = 0;row < n;row++){
					double sum = 0.0;
					for(int i = 0;i < n;i++){
						sum = sum + a[i]*hits.Adj[row][i];
					}
					//pre_h[row] = h[row];
					h[row] = sum;
				}
				//Scale:
				double sum1 = 0.0,sum2 = 0.0;
				for(int i = 0;i < n;i++){
					sum1 = sum1 + a[i]*a[i];
					sum2 = sum2 + h[i]*h[i];
				}
				for(int i = 0;i < n;i++){
					a[i] = a[i] / Math.sqrt(sum1);
					h[i] = h[i] / Math.sqrt(sum2);
				}
        //System.out.printf("%s%2d%2s","Iterat ",counter,":");
				ps.printf("%s%3d%2s","Iter   :",counter,":");
				for(int i = 0;i < n;i++){
					ps.printf(" A/H[%d]=%.7f/%.7f",i,Math.round(a[i]*10000000.0)/10000000.0,Math.round(h[i]*10000000.0)/10000000.0);
					//System.out.printf(" A/H[%d]=%.7f/%.7f",i,Math.round(a[i]*1000000.0)/1000000.0,Math.round(h[i]*1000000.0)/1000000.0); 
				}
				//System.out.println();
				ps.println();
			}while((!converge(pre_a,a,n,errorrate)||!converge(pre_h,h,n,errorrate))&&counter<max_iter);
		}else{
			for(int i = 0;i < hits.v;i++){
				a[i] = 1.0/hits.v;
				h[i] = 1.0/hits.v;
			}
			errorrate = 0.00001;
			max_iter = Integer.MAX_VALUE;
			int counter = 0;
			//System.out.println("Base   : "+counter+" :");
  	  ps.printf("%s%3d","Base   :",counter);
      ps.println();
			for(int i = 0;i < n;i++){
				//System.out.print(" A/H["+i+"]="+a[i]+"/"+h[i]);
				//System.out.printf(" A/H[%2d]=%.6f/%.6f\n",i,Math.round(a[i]*1000000.0)/1000000.0,Math.round(h[i]*1000000.0)/1000000.0);
        	ps.printf("A/H[%2d]=%.7f/%.7f\n",i,Math.round(a[i]*10000000.0)/10000000.0,Math.round(h[i]*10000000.0)/10000000.0); 
			}
			//System.out.println();
      ps.println();
			do{
				
				for(int i = 0;i < n;i++){
					pre_h[i] = h[i];
					pre_a[i] = a[i];	
				}
				
				
				counter++;
				//A step:
				int[][] Adj_T = new int[hits.v][hits.v];
				for(int i = 0;i < n;i++){
					for(int j = 0;j < n;j++){
						Adj_T[j][i] = hits.Adj[i][j];
					}
				}
				for(int row = 0;row < n;row++){
					double sum = 0.0;
					for(int i = 0;i < n;i++){
						sum = sum + h[i]*Adj_T[row][i]; 
					}
					//pre_a[row] = a[row];
					a[row] = sum;
				}
				//H step:
			
				for(int row = 0;row < n;row++){
					double sum = 0.0;
					for(int i = 0;i < n;i++){
						sum = sum + a[i]*hits.Adj[row][i];
					}
					//pre_h[row] = h[row];
					h[row] = sum;
				}
				//Scale:
				double sum1 = 0.0,sum2 = 0.0;
				for(int i = 0;i < n;i++){
					sum1 = sum1 + a[i]*a[i];
					sum2 = sum2 + h[i]*h[i];
				}
				for(int i = 0;i < n;i++){
					a[i] = a[i] / Math.sqrt(sum1);
					h[i] = h[i] / Math.sqrt(sum2);
				}
				
				//System.out.println("Iterat : "+counter+" :");
        ps.printf("%s%3d","Iter   :",counter);
        ps.println();
				for(int i = 0;i < n;i++){
					//System.out.print(" A/H["+i+"]="+a[i]+"/"+h[i]);
				 //System.out.printf(" A/H[%2d]=%.6f/%.6f\n",i,Math.round(a[i]*1000000.0)/1000000.0,Math.round(h[i]*1000000.0)/1000000.0); 
          ps.printf("A/H[%2d]=%.7f/%.7f\n",i,Math.round(a[i]*10000000.0)/10000000.0,Math.round(h[i]*10000000.0)/10000000.0);
				}
				//System.out.println();
        ps.println();
			}while((!converge(pre_a,a,n,errorrate)||!converge(pre_h,h,n,errorrate))&&counter<max_iter);
		}
    } catch (FileNotFoundException e) {
	            
	            e.printStackTrace();
    }
}
	boolean converge(double[] a,double[] b,int n,double errorrate){
		for(int i = 0;i < n;i++){
			if( Math.abs(a[i]-b[i])>errorrate){
				return false;
			}
		}
		return true;
	}
	
}
