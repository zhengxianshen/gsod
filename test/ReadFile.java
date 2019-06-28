package test;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * 将气象项目中的原始数据
 */
public class ReadFile {
        public static void main(String args[]){
//            定义读取数据流 --非文本文件使用字节流读取
            FileInputStream fis = null;
//            读取以zip格式压缩的文件
            GZIPInputStream gin = null;
//            用来读取纯文本文件的字符流
            InputStreamReader isr =null;
            BufferedReader br= null;
            File files=new File("");
            FileWriter fw=null;
            BufferedWriter bw = null;
            try {
                fw = new FileWriter(new File("gsod.txt"));
                bw = new BufferedWriter(fw);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {for(File file:files.listFiles()){

                    fis = new FileInputStream(file);
                    gin = new GZIPInputStream(fis);
                    isr = new InputStreamReader(gin);
                    br = new BufferedReader(isr);
                    String line= " ";

                    while ((line=br.readLine())!=null){
                        System.out.println(line);
                        if(line.endsWith("FIRST"))continue;
                        String[] strs=line.split(" ");
                        String s="";
                        for(int i=0;i<strs.length;i++){

                            if("9999.9".equals(strs[i])||"999.9".equals(strs[i])||"99.99".equals(strs[i])){
                                strs[i]="0.0";
                            }
                            if(strs[i].endsWith("*")){
                                strs[i]=strs[i].substring(0,strs[i].length()-1);
                            }
                            if(strs[i].matches(".*[A-I]")){
                                strs[i] = strs[i].substring(0,strs[i].length()-1);
                            }

                            s +=strs[i]+"/";
                        }
                        strs=s.split("/");
                        for(int i=0;i<strs.length;i++) {

                        }
                        bw.write(s);
                        bw.newLine();
                    }

            }
                } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    bw=null;
                    fw=null;
                }
            }
        }
}
