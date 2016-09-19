<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <title>BangBang</title>
    <script type="text/javascript">
        var xmlhttp;
        function getAllUsers(url)
        {
            xmlhttp=null;
            if (window.XMLHttpRequest)
            {// code for IE7, Firefox, Opera, etc.
                xmlhttp=new XMLHttpRequest();
            }
            else if (window.ActiveXObject)
            {// code for IE6, IE5
                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            if (xmlhttp!=null)
            {
                var num=document.getElementById('num').value;
//                alert(url+num);
                xmlhttp.onreadystatechange=state_Change;
                xmlhttp.open("GET",url+num,true);
                xmlhttp.send(null);
            }
            else
            {
                alert("Your browser does not support XMLHTTP.");
            }
        }

        function state_Change()
        {
            if (xmlhttp.readyState==4)
            {// 4 = "loaded"
                if (xmlhttp.status==200)
                {// 200 = "OK"
                    document.getElementById('A3').innerHTML=xmlhttp.responseText;
                }
                else
                {
                    alert("Problem retrieving XML data:" + xmlhttp.status);
                }
            }
        }
    </script>
</head>

<body>
<h2>Here is BangBang</h2>

<button onclick="getAllUsers('/BangBang/getUsers.ajax?num=')">UserInfos</button>
num: <input type="text" name="num" id="num" /><br />
<p><b>Response:</b>
    <br /><span id="A3"></span>
</p>

</body>
</html>
