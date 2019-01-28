package com.baidu.ueditor.define;

import java.util.HashMap;
import java.util.Map;

public final class ActionMap
{
  public static final Map<String, Integer> mapping = new HashMap() {};
  public static final int CONFIG = 0;
  public static final int UPLOADIMAGE = 1;
  public static final int UPLOADSCRAWL = 2;
  public static final int UPLOADVIDEO = 3;
  public static final int UPLOADFILE = 4;
  public static final int CATCHIMAGE = 5;
  public static final int LISTFILE = 6;
  public static final int LISTIMAGE = 7;
  
  
  static {
	  mapping.put("config", CONFIG);
	  mapping.put("uploadimage", UPLOADIMAGE);
	  mapping.put("uploadscrawl", UPLOADSCRAWL);
	  mapping.put("uploadvideo", UPLOADVIDEO);
	  mapping.put("uploadfile", UPLOADFILE);
	  mapping.put("catchimage", CATCHIMAGE);
	  mapping.put("listfile", LISTFILE);
	  mapping.put("listimage", LISTIMAGE);
  }
  
  public static int getType(String key)
  {
    return ((Integer)mapping.get(key)).intValue();
  }
}
