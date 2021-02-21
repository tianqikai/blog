package com.tqk.blog.utils;

import sun.misc.BASE64Encoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

 public class Base64Util {

     /**
      * 将文件转化为Base64字符串
      * @param file
      * @return Base64Str
      */
     public static String getBase64(File file) throws IOException {
         InputStream in = null;
         byte[] data = null;
         try{
             in = new FileInputStream(file);
             data = new byte[in.available()];
             in.read(data);
         }catch (IOException e){
             e.printStackTrace();
         }finally {
             if (in != null) {
                 try {
                   in.close();
                 } catch (IOException e) {
                   throw  e;
                 }
             }
         }
         //Base64编码
         BASE64Encoder encoder = new BASE64Encoder();
         return encoder.encode(data);
     }
     /**
     * 将文件转化为Base64字符串
     * @param filePath
     * @return Base64Str
     */
     public static String getFileBase64(String  filePath) throws IOException {
       File file=new File(filePath);
       System.out.println(       file.getName());
       return getBase64(file);
     }
 }