<%-- 
    Document   : Manager
    Created on : Oct 23, 2022, 8:47:47 PM
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
        <link rel="stylesheet" href="CSS/stylecart.css">
        <link rel="stylesheet" href="CSS/styledisplay.css">
        <title>Document</title>
    </head>

    <body>
        <div class="header">
            <div class="header_logo">
                <a href="home"> <img src="images/restaurant.png" alt="" srcset="" height="50px" width="50px"> </a>
            </div>
            <c:if test = '${sessionScope.account.manager == 1}'> 
                <div>             
                    <a href="url">MANAGER</a>            
                </div>
            </c:if>    
            <div class="header_account_cart">
                <c:if test = '${sessionScope.account != null}'> 
                    <div class="item_header_account_cart">
                        <a href="history"><img src="images/point.png" alt="" height="50px" width="50px"></a></div>
                    <div  class="item_header_account_cart">
                        Đơn hàng
                    </div>
                </c:if> 
                <div class="item_header_account_cart">
                    <img src="images/user.png" alt="" height="50px" width="50px"></div>
                <div  class="item_header_account_cart">   
                    <c:if test = '${sessionScope.account == null}'> 
                        Account
                    </c:if>
                    <c:if test = '${sessionScope.account != null}'>
                        ${sessionScope.account.lastName} ${sessionScope.account.firstName}          
                    </c:if>
                    <br>
                    <c:if test = '${sessionScope.account == null}'> 
                        <a href="Login">(Đăng nhập , đăng ký)</a>
                    </c:if>
                    <c:if test = '${sessionScope.account != null}'>
                        <a href="logout">(Đăng xuất)</a>                                        
                    </c:if>
                    </a>
                </div>
                <div class="item_header_account_cart">
                    <a href="loadcart"> <img src="images/carts.png" alt="" height="50px" width="50px"></a></div>
                <div class="item_header_account_cart">
                    Cart
                    <br>
                    <span>(ship toàn quốc)</span>
                </div>
            </div>
        </div>
        <div class="navbar">
            <ul> 
                <c:forEach var ="cate" items="${dao.category}"> 
                    <c:if test = '${sessionScope.account == null}'>
                        <li><a href="category?cid=${cate.subcategoryID}&uid=${sessionScope.account.userID}">${cate.name}</a></li>   
                        </c:if>
                        <c:if test = '${sessionScope.account != null}'>
                        <li><a href="category?cid=${cate.subcategoryID}">${cate.name}</a></li>   
                        </c:if>
                    </c:forEach>
            </ul>     
        </div>
        <div class="detail_cart" style="height:1000px;">
            <div class="thongtinmuahang" style="margin-right: 5px;text-align: center; width: 1000px;">
                <div>Lịch sử mua hàng</div>
                <table border="0" style="width: 100%;">
                    <thead>
                        <tr>
                            <th>Thời gian đặt hàng</th>
                            <th>Tên người nhận</th>
                            <th>Số điện thoại</th>
                            <th>Địa chỉ</th>
                            <th>ID đơn hàng</th>
                            <th></th>
                            <th>Trạng thái</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var ="m" items="${dao.manager}"> 
                            <tr>
                                <td>${m.datetime}</td>
                                <c:if test="${m.priority <= 0&&m.priority > -10}">
                                    <td>${m.fullname}</td>
                                </c:if>
                                <c:if test="${m.priority > 0}">
                                    <td style="color: red">${m.fullname}</td>
                                </c:if>
                                     <c:if test="${m.priority <= -10}">
                                    <td style="color: blue">${m.fullname}</td>
                                </c:if>
                                <td>${m.phonenumber}</td>                     
                                <td>${m.location}</td>
                                <td>${m.orderid}</td>
                                <td><a href="detailmanager?orderid=${m.orderid}">detail</a></td>
                                <c:if test = '${m.status.equals("0")}'>
                                    <td>Đang giao hàng</td> 
                                </c:if>
                                <c:if test = '${m.status.equals("1")}'>
                                    <td>Giao hàng thành công</td> 
                                </c:if>
                                     <c:if test = '${m.status.equals("-1")}'>
                                    <td>Bom hàng</td> 
                                </c:if>

                                <c:if test = '${m.status.equals("0")}'>
                                    <td> <a href="updatemanager?orderid=${m.orderid}&userid=${m.userid}&th=1">Đã nhận được hàng</a></td> 
                                    <td> <a href="updatemanager?orderid=${m.orderid}&userid=${m.userid}&th=0">Bom hang</a></td> 
                                </c:if>

                            </tr>
                        </c:forEach>

                    </tbody>
                </table>

            </div>
        </div>


    </body>

</html>


