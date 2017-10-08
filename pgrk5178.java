//HONGDA YANG cs610 5178 prp

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class pgrk5178 {
		public int iteration;
		public int initialvalue;
		public String filename;
		public int v;
		public int e;
		public int[][] Adj;	
		public double[] Pr;
		pgrk5178(){	
		}
		pgrk5178(int iteration, int initialvalue, String filename){
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
				Pr= new double[v];
				switch(initialvalue) {
				case 0:
					for(int i = 0;i < v;i++){
						Pr[i] = 0;
						
					}
					break;
				case 1:
					for(int i = 0;i < v;i++){
						Pr[i] = 1.0;
						
					}
					break;
				case -1:
					for(int i = 0;i < v;i++){
						Pr[i] = 1.0/v;
					}
					break;
				case -2:
					for(int i = 0;i < v;i++){
						Pr[i] = 1.0/Math.sqrt(v);
					
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
		final double Alpha = 0.85;
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
			
			pgrk5178 pgrk = new pgrk5178(iteration,initialvalue,filename);
			pgrk.pgrk5178Alg(pgrk,pgrk.v);	
		}
		
		
		
		public void pgrk5178Alg(pgrk5178 pgrk,int n){
			double[] Pr = new double[n];
			Pr = pgrk.Pr;
			double[] pre_Pr = new double[n];
			int max_iter = Integer.MAX_VALUE;
			double errorrate = Double.MIN_VALUE;
			if(pgrk.iteration>0){
				max_iter = pgrk.iteration;
			}else{
				switch(pgrk.iteration){
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
			int counter = 0;
			int[] C = new int[n];
			for(int i = 0;i < n;i++){
				int sum = 0;
				for(int j = 0;j < n;j++){
					sum = sum + pgrk.Adj[i][j];
				}
				C[i] = sum;
			}
      try {
        String filePath = "output_pgrk.txt";
        File file = new File(filePath);
        PrintStream ps = new PrintStream(new FileOutputStream(file));
        if(pgrk.v<=10){
        //System.out.print("Base   :"+counter+" :");
        ps.printf("%s%3d%2s","Base   :",counter,":");
       	for(int i = 0;i < n;i++){
	//System.out.printf(" Pr[%d]=%.7f",i,Math.round(Pr[i]*10000000.0)/10000000.0); 
            ps.printf(" Pr[%d]=%.7f",i,Math.round(Pr[i]*10000000.0)/10000000.0);
					}
					//System.out.println();
        ps.println(); 
		do{                   
					counter++;
					//System.out.print("Iter   :"+counter+" :");
          ps.printf("%s%3d%2s","Iter   :",counter,":");
					for(int i = 0;i < n;i++){
						pre_Pr[i] = Pr[i];
					}
					for(int i = 0;i < n;i++){
						Pr[i] = (1-Alpha)/n;
						for(int j = 0;j < n;j++){	
							if(pgrk.Adj[j][i]==1){
								Pr[i] =Pr[i] + Alpha*(pre_Pr[j]/C[j]);
							}
						}
						//System.out.printf(" Pr[%d]=%.7f",i,Math.round(Pr[i]*10000000.0)/10000000.0);
            ps.printf(" Pr[%d]=%.7f",i,Math.round(Pr[i]*10000000.0)/10000000.0);
					}
						//System.out.println();
            ps.println();
					}while((!converge(pre_Pr,Pr,n,errorrate))&&(counter<max_iter));		
		
			}else{
        max_iter = Integer.MAX_VALUE;
        errorrate = 0.00001;
        //System.out.println("Base   :"+counter+" :");
        ps.printf("%s%3d","Base  :",counter);
        ps.println();
					for(int i = 0;i < n;i++){
						//System.out.printf(" Pr[%2d]=%.6f\n",i,Math.round(Pr[i]*1000000.0)/1000000.0);
            ps.printf("Pr[%2d]=%.7f\n",i,Math.round(Pr[i]*10000000.0)/10000000.0); 
					}
					//System.out.println();
        ps.println();
				do{
					counter++;
					//System.out.println("Iter   :"+counter+" :");
          ps.printf("%s%3d","Iter  :",counter);
          ps.println();                            
					for(int i = 0;i < n;i++){
						pre_Pr[i] = Pr[i];
					}
					for(int i = 0;i < n;i++){
						Pr[i] = (1-Alpha)/n;
						for(int j = 0;j < n;j++){	
							if(pgrk.Adj[j][i]==1){
								Pr[i] =Pr[i] + Alpha*(pre_Pr[j]/C[j]);
							}
						}
						//System.out.printf(" Pr[%d]=%.7f\n",i,Math.round(Pr[i]*10000000.0)/10000000.0);
            ps.printf("Pr[%2d]=%.7f\n",i,Math.round(Pr[i]*10000000.0)/10000000.0);
					}
						//System.out.println();
            ps.println();
					}while((!converge(pre_Pr,Pr,n,errorrate))&&(counter<max_iter));
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
