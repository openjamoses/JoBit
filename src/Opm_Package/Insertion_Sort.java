/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Opm_Package;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author john
 */
public class Insertion_Sort {
    /****
     * 
     * @param nList
     * @param eList
     * @param iList
     * @param input
     * @param cList 
     */
         
    public void insertionSort(List<String> nList, List<String> eList,List<String> iList, List<Double> input,List<Double> cList) {
        ///// Now we pass the all the list to the sorting class....
        
           /// Writting the header....
           //allobj.clear();
           /// Writting the header....
           Object[] datas = null;
          
           List<String> del_nList = new ArrayList<>();
           
        
           List< List<String> > allList = doInsertionSort(nList,eList,iList,input,cList);
           List <String> nnList = new ArrayList<>();
           List <String> eeList = new ArrayList<>();
           List <String> iiList = new ArrayList<>();
           List <String> ccList = new ArrayList<>();
           List <String> chhList = new ArrayList<>();
           /// Pick the corresponding list returned....
           ccList = allList.get(0);
           chhList = allList.get(1);
           nnList = allList.get(2);
           eeList = allList.get(3);
           iiList = allList.get(4);
           
           List <String> nList_i = new ArrayList<>(); 
           List <String> eList_i = new ArrayList<>(); 
           List <String> iList_i = new ArrayList<>(); 
           List <Double> comList_i = new ArrayList<>(); 
           List <Double> chaList_i = new ArrayList<>(); 
           
           List <String> nList_j = new ArrayList<>(); 
           List <String> eList_j = new ArrayList<>(); 
           List <String> iList_j = new ArrayList<>(); 
           List <Double> comList_j = new ArrayList<>(); 
           List <Double> chaList_j = new ArrayList<>(); 
           
           for(int j=0; j<ccList.size(); j++){
               double value = Double.parseDouble(chhList.get(j));
               if(value >=80){
                   chaList_i.add(value);
                   comList_i.add(Double.parseDouble(ccList.get(j)));
                   nList_i.add(nnList.get(j));
                   eList_i.add(eeList.get(j));
                   iList_i.add(iiList.get(j));
                   
               }else{
                   chaList_j.add(value);
                   comList_j.add(Double.parseDouble(ccList.get(j)));
                   nList_j.add(nnList.get(j));
                   eList_j.add(eeList.get(j));
                   iList_j.add(iiList.get(j));
                   
               }
           }
           //*********************************************************
           
    }
    
    public  List<List<String>> doInsertionSort(List<String> nList, List<String> eList,List<String> iList, List<Double> input,List<Double> cList){
         
        List<String> commitList = new ArrayList<>();
        List<String> changeList = new ArrayList<>();
        
        List< List<String> > allList = new ArrayList<>();
        double commit,temp;
        String login,name,email;
        for (int i = 1; i < input.size(); i++) {
            for(int j = i ; j > 0 ; j--){
                if(input.get(j) < input.get(j-1)){
                    /// Sort changed...
                    commit = cList.get(j);
                    cList.set(j, cList.get(j-1));
                    cList.set((j-1), commit);
                    /// Sort changed...
                    temp = input.get(j);
                    input.set(j, input.get(j-1));
                    input.set((j-1), temp);
                    /// Sort login
                    login = iList.get(j);
                    iList.set(j, iList.get(j-1));
                    iList.set((j-1), login);
                    /// Sort names...
                    name = nList.get(j);
                    nList.set(j, nList.get(j-1));
                    nList.set((j-1), name);
                    /// Sort names...
                    email = eList.get(j);
                    eList.set(j, eList.get(j-1));
                    eList.set((j-1), email);
                    
                }
            }
        }
        
        
        for(int i=0; i<cList.size(); i++){
            commitList.add( String.valueOf(cList.get(i)) );
            changeList.add( String.valueOf(input.get(i)) );
        }
         allList.add(commitList);
         allList.add(changeList);
         allList.add(nList);
         allList.add(eList);
         allList.add(iList);
        return allList;
    }
    
}
