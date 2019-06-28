package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
/*
	如果使用MapReduce处理气象数据时，会比较慢，且可能会存在内存溢出现象，所以，下面的代码是
	利用Java程序将gsod中所有站点的所有处理进行 合并 和 处理，最终在当前项目下生成一个gsod.txt文档
*/

public class FileCombine {

	public static void main(String[] args) {
		FileInputStream fis = null;
		GZIPInputStream gis = null;
		InputStreamReader isr = null;
		BufferedReader bufr = null;
		//把gsod_2016文件的路径复制到下面的路径中即可
		String inputPath = "/home/zhengxs/Downloads/01/gsod_2016";
		
		FileWriter fw = null;
		BufferedWriter bufw = null;
		String outPath = "gsod.txt";
		int fileCount = 0;
		int dataCount = 0;
		try {
			File fileDir = new File(inputPath);
			fw = new FileWriter(outPath);
			bufw = new BufferedWriter(fw);
			for(File file : fileDir.listFiles()) {
				fis = new FileInputStream(file);
				gis = new GZIPInputStream(fis);
				isr = new InputStreamReader(gis);
				bufr = new BufferedReader(isr);
				
				String line = bufr.readLine();
				String str = "";
				while(line != null) {
					if(line.startsWith("STN")) {
						str = "";
						line = bufr.readLine();
						continue ;
					}
					
					String[] strs = line.split(" ");
					String word = "";
					for(int i=0; i<strs.length; i++) {
						if(strs[i].equals("")) {
							continue;
						}
						
						if(strs[i].equals("9999.9") || strs[i].equals("999.9") || strs[i].equals("99.99")) {
							strs[i] = "0.0";
						}
						
						if(strs[i].endsWith("*")) {
							strs[i] = strs[i].replace("*", "");
						}
						
						if(strs[i].matches(".*[A-I]")) {
							strs[i] = strs[i].substring(0, strs[i].length()-1);
						}
						
						if(i == 0) {
							word += strs[i];
						}else {
							word += "/"+strs[i];
						}
					}
					
					String[] newStrs = word.split("/");
					for(int i=0; i<newStrs.length; i++) {
						if(i==4 || i==6 || i==8 || i==10 || i==12 || i==14) {
							continue;
						}else {
							if(i != (newStrs.length-1)) {
								str += newStrs[i]+"/";
							}else {
								str += newStrs[i];
							}
						}
					}
					
					bufw.write(str);
					bufw.newLine();
					dataCount++;
					str = "";
					line = bufr.readLine();
				}
				if(bufr!=null){
					try {
						bufr.close();
					} catch (IOException e) {
					}
				}
				if(isr!=null){
					try {
						isr.close();
					} catch (IOException e) {
					}
				}
				if(gis!=null){
					try {
						gis.close();
					} catch (IOException e) {
					}
				}
				if(fis!=null){
					try {
						fis.close();
					} catch (IOException e) {
					}
				}
				fileCount++;
				if(fileCount % 50 == 0) {
					System.out.println();
				}else {
					System.out.print(".");
				}
			}
			bufw.flush();
			System.out.println("共处理:"+fileCount+"个文件,和"+dataCount+"条数据");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bufw!=null){
				try {
					bufw.close();
				} catch (IOException e) {
				}
			}
			if(fw!=null){
				try {
					fw.close();
				} catch (IOException e) {
				}
			}
			
		}
	}

}
