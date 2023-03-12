<%-- 
    Document   : Order
    Created on : Oct 22, 2022, 10:19:37 PM
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
                    <form action="successful" method="post">   
                        <table border="0"> 
                            <tr>
                                <td>Địa chỉ nhận hàng:</td>
                                <td>${diachi}</td>                                   
                            </tr>
                            <tr>
                                <td>Tên người nhận:</td>
                                <td>${sessionScope.account.lastName} ${sessionScope.account.firstName}</td>
                            </tr>
                            <tr>
                                <td>Số điện thoại:</td>
                                <td>${sessionScope.phoneNumber}</td>
                            </tr>
                            <tr>
                                <td>Tổng tiền thanh toán:</td>
                                <td style="color: red">${totalmoney} VND</td>
                            </tr>
                            </tbody>
                        </table>        
                        <input style="display: none;" type="text" name="iddiachi" value="${iddiachi}">
                        <input style="display: none;" type="text" name="uid" value="${sessionScope.account.userID}">                       
                        <input style="display: none;" type="text" name="datetime" value="${datetime}">
                        <div class="center">
                            <input type="submit" value="Xác nhận giao hàng">                          
                            <div style="color: red; position: absolute; text-align: center;bottom:10px; background-color:#ffc6c6; border-radius: 5px;">Thanh toán bằng tiền mặt hoặc chuyển cho người ship</div>
                        </div>
                    </form>            
                </div>           
            </div>

            <!--======================================================================================-->

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
