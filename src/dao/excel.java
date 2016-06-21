package dao;
import jxl.*;
import java.io.*;

public class excel
{
      public static void main(String[] args)throws Exception
      {

       File ex=new File("D:/vidu1.xls");
       Workbook w= Workbook.getWorkbook(ex);
       Sheet s= w.getSheet(0);
       for(int i=0;i<s.getColumns();i++)
       {
         for(int j=0;j<s.getRows();j++)
         {
               Cell cell=s.getCell(i, j);
               System.out.println("     "+cell.getContents());
         }
         System.out.println("\n");
       }
      }
}