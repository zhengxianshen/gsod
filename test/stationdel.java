package test;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

public class stationdel {
    static Connection conn = null;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/gsod?rewriteBatchedStatements=true";
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws IOException {
        //Map<String,String> map=new HashMap<>();
        List<String> list=new ArrayList<>();
        readJSON(list);
        //readTXT(map);
        //re((ArrayList<String>) list,map);
      
        
    }
    
    public static void re(ArrayList<String> list,Map<String,String> map) throws IOException {
        BufferedWriter bw=null;
        BufferedWriter bw1=null;
        BufferedWriter bw2=null;
        bw2=new BufferedWriter(new FileWriter("no1.txt"));
        bw1=new BufferedWriter(new FileWriter("no.txt"));
        bw=new BufferedWriter(new FileWriter("result.txt"));
        ListIterator<String> it=list.listIterator();
         while(it.hasNext()){
             String s=it.next();
            if(map.containsKey(s)){
                
                it.remove();
                bw.write(map.get(s));
                bw.write("/");
                bw.write(s);
                bw.newLine();
                map.remove(s);
            }
        }
         bw.newLine();
         bw.newLine();
         it=list.listIterator();
         int com=0,tmp;
         String res="";
         while(it.hasNext()){
             com=0;
             String s=it.next();
             for(String m:map.keySet()){
                 if((tmp=compare(s,m))>com){
                     com=tmp;
                     res=m;
                 }
             }
             if(com>0) {
                 bw.write(map.get(res) + "/"  + s);
                 bw.newLine();
                 map.remove(res);
                 it.remove();
             }
         }
         
        for(String s:list){
            bw1.write(s);
            bw1.newLine();
        }
        bw1.close();
        
        for(String s:map.keySet()){
            bw2.write(map.get(s)+"  "+s);
            bw2.newLine();
        }
        bw2.close();
        bw.close();
    }
    
    public static int compare(String s1,String s2){
        int com=0;
        char a1[]=s1.toCharArray();
        char a2[]=s2.toCharArray();
        int[][] x=new int[a1.length+1][a2.length+1];
        
        for(int i=1;i<=a1.length;i++){
            for(int j=1;j<=a2.length;j++){
                if(a1[i-1]==a2[j-1])x[i][j]=x[i-1][j]+1;
                else x[i][j]=x[i-1][j]>x[i][j-1]?x[i-1][j]:x[i][j-1];
            }
        }
        com=x[a1.length][a2.length];
        if(com<(a1.length/4*3))return 0;
        return com;
    }
    
    public static void readJSON(List list){
        BufferedReader br = null;
        
        try {
            br=new BufferedReader(new FileReader("web/data/world.json"));
            String line = "";
            StringBuilder data=new StringBuilder();
            while ((line=br.readLine())!=null){
                data.append(line).append("/r/n");
            }
            
            JSONObject object=new JSONObject(data.toString());
           
            JSONArray array= object.getJSONArray("features");
            
            for(int i=0;i<array.length();i++){
                JSONObject country=array.getJSONObject(i);
                JSONObject property=country.getJSONObject("properties");
                
                String n=property.getString("name");
                System.out.println(n);
                list.add(n.toUpperCase());
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{
            try {
                if(br!=null)
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                br=null;
            }
        }
    }
    
    public static void readTXT(Map map){
        BufferedReader br= null;
        
        String line="";
        String[] str;
        try {
            br=new BufferedReader(new FileReader("web/data/country-list.txt"));
            while((line=br.readLine())!=null){
                if(line.length()==0||line.startsWith("FIPS"))continue;
                str=new String[2];
                str[0]=line.substring(0,9).trim();
                str[1]= line.substring(9).trim();
                
                map.put(str[1].toUpperCase(),str[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(br!=null)
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                br=null;
            }
        }
    
    }
}
