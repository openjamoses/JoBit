package Opm_Package;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class CheckingRateLimit {

	public static boolean checkingRateLimit(String projectname, String[] token) {

		int count=0;;
    	//Github github = new RtGithub("Personal Token b8b44c99bc659ed954234db7cfcd1ae6348b0217");
        try {
 
            //URL obj = new URL("https://api.github.com/repos/atom/atom/commits?per_page=50&since=2016-04-05T00:18:59Z&until=2016-04-18T22:20:18Z");
        	//int i = 0;
            for (int i = 0; i < token.length; i++){
	        	URL obj = new URL("https://api.github.com/repos/"+projectname+"/commits?access_token="+token[i]);
	        	//URL obj = new URL("https://api.github.com/repos/BilingualAphasia/AndroidBilingualAphasiaTest/commits?page="+p+"&per_page=100");
	            
	            URLConnection conn = obj.openConnection();
	            Map<String, List<String>> map = conn.getHeaderFields();
	 
	            //System.out.println("Printing All Response Header for URL: "+ obj.toString() + "\n");
	            String str = "";
	            int x = 0;
	            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
	            	if (entry.getKey() != null && entry.getKey().equals("X-RateLimit-Remaining")){
	            		x = Integer.parseInt(entry.getValue().get(0));
	            		if (x < 60)
	            			count++;
	            		System.out.println(entry.getKey() + " : " + entry.getValue().get(0));
	            		break;
	            	}
	            	
	            	
	            	
	            		
	            }
//	            if (i == 0)
//	            	break;
            }
// 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (count==0)
        	return true;
        else{
        	for (int i = 0; i < 100000; i++)
        		;
        	
        	return true;
        }
	}
	
	public static Long repeat(){
		long total = 0;
		long x = 10000;
		while (x > 1){
		for (int i = 0; i < 1000000000; i++) {
	         total += i;
	    }
		x--;
		}
		
		return total;
	}

	public static void main(String[] args) {
		
		
		String projectname = "BilingualAphasia/AndroidBilingualAphasiaTest";
		String[] tokens = {"d9fa524eb803729fff1a327ad670f5615fefb822",
	            "727b16f55df64b49c961fb25784bd760cd9fd986",
	            "8a124ae16017a27bff55dba50e349d34b9c6e37e",
	            "da571ada56a1bff128274e53ccf7158b9af45ddc",
	            "3dff8fb276e625a5f13320b68b02785fd120d237",
	            "53df99027c4313db2fc4b4c02b413c0f9546a42e",
	            "d6a809e9a3b7f3cb31e23d257a40ae460a69505e",
	            "eb4a0e8b58b54f47ee7ebde3469baeaa1e53169c",
	            "ebc94d20b0e526b832e6fcf2407a1414115081b5",
	            "d87ddaaffaabe5e558fa7b65f5cadd85786663e5",
	            "4815cf21389f42bf07d8f523c7f0f2130b7db4b6",
	            "99dedffa7529ca10ee947d7509f97f7c4054cd0b",
	            "025d6c2df83c14621a9346d0002f0e5b71e8257a",
	            "bfd9c16567a847212a7c944cbf6e081d5a8d6f5b",
	            "a62c1f8a4348cc1467da1e2b12ee71d0b097b5b6",
	            "62d85139565c08ef24c81e9820c874846e2d9134",
	            "c08e7511725d91d1fe5b98ca10e7c6b07b8e171b",
	            "f87f413aa690ee7545dd71f346b3dc45d6e82838"
             };
		
		boolean check = checkingRateLimit(projectname, tokens);
		
		

	}

}
