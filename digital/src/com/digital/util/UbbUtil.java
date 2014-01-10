package com.digital.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UbbUtil {
	public static String clearHtmlTag(String s, int much) {
        try {
            Matcher m = null;
            m = Pattern.compile("<([^>]*)>", Pattern.DOTALL).matcher(s);
            while (m.find()) {
                for (int i = 1; i <= m.groupCount(); i++) {
                //    System.out.println("找到 = " + m.group());
                    s = s.replaceAll(m.group(), "");
                }
            }
            if (much != 0 && s.length() > much) {
                s = s.substring(0, much);
            }
       //     System.out.println("结果 = " + s);
        } catch (Exception e) {
        }
        return s;
    }

    public static String delHtmlTag(String str, int length) {
        int index1 = -1;
        int index2 = -1;
        while (true) {
            index1 = str.indexOf("<");
            index2 = str.indexOf(">", index1);
            if (index1 == -1) {
                break;
            }
            if (index2 == -1) {
                index2 = str.length();
            } else {
                index2 = index2 + 1;
            }
            str = str.substring(0, index1)
                  + str.substring(index2, str.length());

        }
        if (length != 0) {
            if (str.length() > length) {
                str = str.substring(0, length);
            }
        }
        str = str.replaceAll("\r", "<br/>");
        str = str.replaceAll("\t", "    ");
        return str;
    }
    //超链接转义
    private static String URL = "<a href='$2' target=_blank>$3</a>";
    //无参数图片转义
    private static String IMG = "<img src='$2'></img>";
    //参数图片转义
    private static String IMG1 =
            "<img src='$4' width=\"$2\" hight=\"$3\"></img>";

   
    private static String ALIGN = "<p align=\"$2\">$3</a>";

    private static String I = "<i>$2</i>";

    private static String LI = "<li>$2</li>";

    private static String B = "<b>$2</b>";

    private static String U = "<u>$2</u>";

    private static String H1 = "<h1>$2</h1>";

    private static String H2 = "<h2>$2</h2>";

    private static String H3 = "<h3>$2</h3>";

    private static String H4 = "<h4>$2</h4>";

    private static String H5 = "<h5>$2</h5>";

    private static String H6 = "<h6>$2</h6>";

    /**
     * 转换UBB
     *
     * @param text
     * @return
     */
    public static String ubbDecode(String text) {
      //  text = replace(text, "align=(.+?)","align",ALIGN);
       
      //  text = replace(text, "url=(.+?)","url", URL);
       
        text = replace(text, "img", IMG);
        text = replace(text, "img=(.+?),(.+?)", "img", IMG1);
      /*  text = replace(text, "u", U);
        text = replace(text, "i", I);
        text = replace(text, "li", LI);
        text = replace(text, "b", B);
        text = replace(text, "h1", H1);
        text = replace(text, "h2", H2);
        text = replace(text, "h3", H3);
        text = replace(text, "h4", H4);
        text = replace(text, "h5", H5);
        text = replace(text, "h6", H6);
        */
        return text;
    }

    /**
     * 将 从数据库 取出来的文章，ubb语法转换成正常的html
     *
     * @param text
     * @param length
     * @return
     */
    public static String replace(String text, String reg, String replaceStr) {
        Matcher m = null;
        m = Pattern.compile(
                "(\\[" + reg + "\\])(.[^\\[]*)(\\[/" + reg + "\\])",
                Pattern.DOTALL | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE)
            .matcher(text);
        text = m.replaceAll(replaceStr);
        return text;
    }

    /**
     * 将 从数据库 取出来的文章，ubb语法转换成正常的html
     *
     * @param text
     * @param length
     * @return
     */
    public static String replace(String text, String reg, String regEnd,
                                 String replaceStr) {
        Matcher m = null;
        m = Pattern.compile(
                "(\\[" + reg + "\\])(.[^\\[]*)(\\[/" + regEnd + "\\])",
                Pattern.DOTALL | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE)
            .matcher(text);
        text = m.replaceAll(replaceStr);
        return text;
    }

    public static void main(String arg[]) {
        //String f = "[img=2,200]sds[/img]";
        String f="[align=center][img]images/pic.gif[/img][/align]";
        String d="[img]'http://localhost:8081/ui/admin/js/xheditor/zh-tw/xheditor_emot/default/shy.gif' alt='Shy'[/img]asdfa<strong>sdf</strong>a";
        System.out.print(ubbDecode(d));

    }
}
