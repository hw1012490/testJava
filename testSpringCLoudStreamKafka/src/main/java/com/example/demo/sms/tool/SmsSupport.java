package com.example.demo.sms.tool;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * <p>*********************************************************************</p>
 * <p>(类注释，类作用)</p>
 * <p>SmsSupport </p>
 *
 * <p>HIK所有，</p>
 * <p>受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。</p>
 *
 * @author zhouqingfei <br>
 * @copyright Copyright: 2015-2020
 * @date 2020-06-28 19:36:24
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user   :{修改人}
 * <p>**********************************************************************</p>
 * @since 1.0.0
 */
public class SmsSupport {

  private static final  String BR = "";

  public static String getHTML(String str) {
    String r = "";
    InputStream ins = null;
    BufferedReader breader = null;
    try {
      URL rTmp = new URL(str);
      ins = rTmp.openStream();

      breader = new BufferedReader(new InputStreamReader(ins));
      String b = null;
      while ((b = breader.readLine()) != null) {
        if (!r.equals("")) {
          r += BR;
        }
        r += b;
      }
      r = r.trim();
    } catch (Exception e) {
    }finally {
      if(breader != null){
        try {  breader.close(); } catch (Exception e) {}
      }
      if(ins != null){
        try {    ins.close(); } catch (Exception e) {}
      }
    }
    return r;
  }
}
