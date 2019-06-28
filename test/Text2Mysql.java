package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * ����JDBC�ķ�ʽ������������(���浽gsod.txt�ļ���)ת�浽mysql���ݿ���
 */
public class Text2Mysql {
    static Connection conn = null;
    static {
        try {
            //1-��������
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/gsod?rewriteBatchedStatements=true";
            String user = "root";
            String password = "root";
            //2-�������Ӷ���
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PreparedStatement ps = null;
        Reader in = null;
        BufferedReader bufr = null;
        try {

            in = new FileReader(new File("result.txt"));
            bufr = new BufferedReader(in);
            String line = bufr.readLine();

            String sql = "insert into CLIST values (?,?)";

            ps = conn.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            int count = 0;
      
            long startTime = System.currentTimeMillis();

            conn.setAutoCommit(false);
            
            while(line != null) {
                
                String[] strs = line.split("/");
                
                ps.setString(1, strs[0]);//stn
                ps.setString(2,  strs[1]);//wban

                count++;//��������1

                ps.addBatch();//��������

                line = bufr.readLine();//��ȡ��һ������
            }

            ps.executeBatch();//ִ�в���

            conn.commit();//�ֶ��ύ����

            //��ȡִ�����֮���ʱ��
            long endTime = System.currentTimeMillis();
            System.out.println("ִcount==="+count+"time===ʱ"+(endTime-startTime)+"===");

        } catch (Exception e) {
            e.printStackTrace();
        }finally {//finally����飬����try-catch����û���׳��쳣�����ᱻִ�е�
            //ͨ����finally������У��ر���Դ
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    ps = null;
                }
            }
            if(bufr != null) {
                try {
                    bufr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    bufr = null;
                }
            }
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    in = null;
                }
            }
        }
    }

}
