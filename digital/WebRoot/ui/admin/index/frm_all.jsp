<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head id="Head1">
    <title>數碼網-後臺管理系統V1.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <style type="text/css">
        .navPoint {COLOR: white; CURSOR: hand; FONT-FAMILY: Webdings; FONT-SIZE: 9pt;}
    </style>
    <script language="javascript">
    function switchSysBar(){
        if (switchPoint.innerText==3){
            switchPoint.innerText=4
            document.getElementById("fleft").style.display = "none";
        }else{
            switchPoint.innerText=3
            document.getElementById("fleft").style.display = "";
        }
    }
    </script>
</head>
<body scroll="no" topmargin="0" rightmargin="0" leftmargin="0" onresize="javascript:parent.carnoc.location.reload()">
    <table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
	    <tr>
		    <td id="fleft" width="170px" align="center" vAlign="middle" class="xx">
			    <iframe frameborder="0" name="carnoc" scrolling="no" src="/nimdaMenu" style="HEIGHT: 100%; VISIBILITY: inherit; WIDTH: 170px; Z-INDEX: 2"></iframe>
		    </td>
		    <td id="fcenter" style="width: 9pt;border: #808080 1px solid;background-color:#c9c9c9">
			    <table border="0" cellpadding="0" cellspacing="0" height="100%">
				    <tr>
					    <td style="HEIGHT: 100%" onclick="switchSysBar()">
						    <font style="FONT-SIZE: 9pt; CURSOR: default; COLOR: #ffffff">
						    <br>
						    <br>
						    <br>
						    <br>
						    <br>
						    <br>
						    <br>
						    <br>
						    <br>
						    <br>
						    <br>
						    <br>
						    <br>
						    <span class="navPoint" id="switchPoint" title="打開/關閉側欄">3</span><br>
						    <br>
						    <br>
						    <br>
						    <br>
						    <br>
						    <br>
						    <br>
						    切換屏幕</font>
					    </td>
				    </tr>
			    </table>			
		    </td>
		    <td id="fright">
			    <iframe scroll="no" frameborder="0" id="main" name="main"  src="/nimdaAbout" style="HEIGHT: 100%; WIDTH: 100%; Z-INDEX: 1">
			    </iframe>
		    </td>
	    </tr>
    </table>
</body>
</html>