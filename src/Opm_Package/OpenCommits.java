package Opm_Package;

import java.util.ArrayList;

public class OpenCommits {

	public void nameMergerFromExcel_2Col_Coparison(ArrayList<String> namesMergedC1, ArrayList<String> namesMergedC2){
		

		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> email = new ArrayList<String>();
		
		ArrayList<String> login = new ArrayList<String>();
		
		ArrayList<String> newNamesMerged = new ArrayList<String>();
		
		String[] nameMerger_i = null;
		String[] nameMerger_j = null;
		String[][] nameMerger_new = null;
		int x = 0;
		
		String name_i = "", name_j = "", email_prefix_i = "", email_prefix_j = "", login_i = "", login_j = "", location_i = "", location_j = "", created_at_i = "", created_at_j = "";
		
		
		
		for (int i = 0; i < namesMergedC1.size();i++ ){
			//System.out.println("email_prefix_C1-"+i+" = "+namesMergedC1.get(10));
			//System.out.println("email_prefix_C22-"+i+" = "+namesMergedC2.get(10));
			if (!namesMergedC1.get(i).equals("")){
				nameMerger_i = namesMergedC1.get(i).split("/");
				//System.out.println("email_prefix_C1-"+i+" = "+namesMergedC1.get(i));
				
				name_i = nameMerger_i[0];
				if (nameMerger_i[1].contains("@")){
					email_prefix_i = nameMerger_i[1].substring(0, nameMerger_i[1].indexOf("@"));
					//System.out.println("email_prefix_i = "+email_prefix_i);
				}
				else{
					email_prefix_i = nameMerger_i[1];
					//System.out.println("email_prefix_i = "+email_prefix_i);
				}
				login_i = nameMerger_i[2];
				location_i = nameMerger_i[3];
				created_at_i = nameMerger_i[4];
				for (int j=0; j < namesMergedC2.size(); j++){
					if (!namesMergedC2.get(j).equals("")){
						//System.out.println("email_prefix_C2-"+i+" = "+namesMergedC2.get(i));
						nameMerger_j = namesMergedC2.get(j).split("/");
						
						name_j = nameMerger_j[0];
						if (nameMerger_j[1].contains("@")){
							email_prefix_j = nameMerger_j[1].substring(0, nameMerger_j[1].indexOf("@"));
						}
						else
							email_prefix_j = nameMerger_j[1];
						login_j = nameMerger_j[2];
						location_j = nameMerger_j[3];
						created_at_j = nameMerger_j[4];
						
						String str1 = "";
						String str2 = "";
						
						//
						if (login_i.equals(login_j) && !login_i.equals("login####") && location_j.equals("location") && !location_i.equals("location")){// || name_i.equals(name_j) || email_prefix_i.equals(email_prefix_j)){
							str1 = namesMergedC1.get(i).substring(0,namesMergedC1.get(i).lastIndexOf("/"));
							str2 = namesMergedC2.get(j).substring(namesMergedC2.get(j).lastIndexOf("/"));
	//						System.out.println("created_at_i = "+str1);
	//						System.out.println("created_at_i = "+str2);
							namesMergedC2.set(j, str1+str2);
						}
						else if (login_i.equals(login_j) && !login_i.equals("login####") && !location_j.equals("location") && location_i.equals("location")){// || name_i.equals(name_j) || email_prefix_i.equals(email_prefix_j)){
							str1 = namesMergedC2.get(j).substring(0,namesMergedC2.get(j).lastIndexOf("/"));
							str2 = namesMergedC1.get(i).substring(namesMergedC1.get(i).lastIndexOf("/"));
							namesMergedC1.set(i, str1+str2);
						}
						if (login_i.equals(login_j) && !login_i.equals("login####")){// || name_i.equals(name_j) || email_prefix_i.equals(email_prefix_j)){
							str1 = namesMergedC1.get(i).substring(0,namesMergedC1.get(i).lastIndexOf("/"));
							str2 = namesMergedC2.get(j).substring(namesMergedC2.get(j).lastIndexOf("/"));
							namesMergedC2.set(j, str1+str2);
	//						System.out.println("created_at_i = "+str1);
	//						System.out.println("created_at_i = "+str2);
						}
						else if(name_i.equals(name_j) && !login_j.equals("login####")){
							str1 = namesMergedC2.get(j).substring(0,namesMergedC2.get(j).lastIndexOf("/"));
							str2 = namesMergedC1.get(i).substring(namesMergedC1.get(i).lastIndexOf("/"));
							namesMergedC1.set(i, str1+str2);
						}
						else if(name_i.equals(name_j) && !login_i.equals("login####")){
							str1 = namesMergedC1.get(i).substring(0,namesMergedC1.get(i).lastIndexOf("/"));
							str2 = namesMergedC2.get(j).substring(namesMergedC2.get(j).lastIndexOf("/"));
							namesMergedC2.set(j, str1+str2);
	//						System.out.println("created_at_i = "+str1);
	//						System.out.println("created_at_i = "+str2);
							//namesMerged.set(j, namesMerged.get(i));
						}
						else if(email_prefix_i.equals(email_prefix_j) && !login_j.equals("login####")){
							str1 = namesMergedC2.get(j).substring(0,namesMergedC2.get(j).lastIndexOf("/"));
							str2 = namesMergedC1.get(i).substring(namesMergedC1.get(i).lastIndexOf("/"));
							namesMergedC1.set(i, str1+str2);
	//						System.out.println("created_at_i = "+str1);
	//						System.out.println("created_at_i = "+str2);
							//namesMergedC1.set(i, namesMergedC2.get(i));
						}
						else if(email_prefix_i.equals(email_prefix_j) && !login_i.equals("login####")){
							str1 = namesMergedC1.get(i).substring(0,namesMergedC1.get(i).lastIndexOf("/"));
							str2 = namesMergedC2.get(j).substring(namesMergedC2.get(j).lastIndexOf("/"));
							namesMergedC2.set(j, str1+str2);
	//						System.out.println("created_at_i = "+str1);
	//						System.out.println("created_at_i = "+str2);
							//namesMergedC1.set(i, namesMergedC2.get(i));
						}
						else if(name_i.equals(name_j)){
							str1 = namesMergedC1.get(i).substring(0,namesMergedC1.get(i).lastIndexOf("/"));
							str2 = namesMergedC2.get(j).substring(namesMergedC2.get(j).lastIndexOf("/"));
							namesMergedC2.set(j, str1+str2);
	//						System.out.println("created_at_i = "+str1);
	//						System.out.println("created_at_i = "+str2);
							//namesMerged.set(j, namesMerged.get(i));
						}
						else if(email_prefix_i.equals(email_prefix_j)){
							str1 = namesMergedC1.get(i).substring(0,namesMergedC1.get(i).lastIndexOf("/"));
							str2 = namesMergedC2.get(j).substring(namesMergedC2.get(j).lastIndexOf("/"));
							namesMergedC2.set(j, str1+str2);
							//System.out.println("created_at_i = "+str1);
							//System.out.println("created_at_i = "+str2);
							//namesMergedC1.set(i, namesMergedC2.get(i));
						}
	
						
						
					}
				}
			}
				
			
		}
		
	
	}
}
