<%-- 
    Document   : Verify
    Created on : Oct 22, 2022, 3:48:28 PM
    Author     : 84961
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="CSS/stylelogin.css">
        <title>Document</title>
    </head>

    <body>
        <div class="container">                
            <div class="form_login form_create" id="createacc" style="position: relative;">
                <div>
                    <form action="verify" method="post">           
                        <div>
                            <input type="text" name="pin" placeholder="Enter your pin code">
                            <br>                           
                        </div>
                        <div class="center">
                            <input type="submit" value="Check Verify">
                            <div style="color: red; position: absolute; text-align: center;bottom:10px; background-color:#ffc6c6; border-radius: 5px;">${msg}</div>
                        </div>
                    </form>            
                </div>           
            </div>
                        
            <!--        ======================================================================================-->
           
        </div>

        <div class="icon_food">
            <div class="icon_login"><img src="images/iconlogin1.png" alt="" srcset=""></div>
            <div class="icon_login"><img src="images/iconlogin2.png" alt="" srcset=""></div>
            <div class="icon_login"><img src="images/iconlogin3.png" alt="" srcset=""></div>
            <div class="icon_login"><img src="images/iconlogin4.png" alt="" srcset=""></div>
            <div class="icon_login"><img src="images/iconlogin5.png" alt="" srcset=""></div>
        </div>
        <script src="JS/mycode.js"></script>             
    </body>

</html>