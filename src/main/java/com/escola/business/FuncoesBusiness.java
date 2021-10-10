package com.escola.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class FuncoesBusiness {

  private FuncoesBusiness() {
  }
  
  public static boolean naoContemValor(String st) {
    return st == null || st.length() == 0;
  }

  public static boolean naoContemValorData(String st) {
    return (naoContemValor(st) || strToDate(st) == null);
  }
  
  public static boolean interDatasErrado(String dataIni, String dataFim) {
    java.util.Date dt1 = strToDate(dataIni);
    java.util.Date dt2 = strToDate(dataFim);
    if (dt1 == null || dt2 == null) {
      return false;
    }
    return (dt2.getTime() < dt1.getTime());
  }
  
  // Conversões de Data

  public static java.util.Date strToDate(String dataStr) {
    try {
      java.util.Date dt = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
      if (!new SimpleDateFormat("dd/MM/yyyy").format(dt).equals(dataStr)) {
        return null;
      }
      return dt;
    } catch (ParseException e) {
      return null;
    }
  }
  
  public static java.sql.Date strToDateSQL(String dataStr) {
    java.util.Date dt = strToDate(dataStr);
    if (dt == null) {
      return null;
    }
    return new java.sql.Date(dt.getTime());
  }
  
}
