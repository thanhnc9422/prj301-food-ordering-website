
<%-- 
    Document   : Cart
    Created on : Oct 17, 2022, 10:06:33 AM
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
                    Lịch sử mua hàng
                     </div>
                </c:if> 

                <div class="item_header_account_cart">
                    <a
                        <c:if test = '${sessionScope.account != null}'> 
                            href="profile"
                        </c:if>
                        ><img src="images/user.png" alt="" height="50px" width="50px"></a></div>
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
                    <img src="images/carts.png" alt="" height="50px" width="50px"></div>
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
            <div class="thongtinmuahang" style="margin-right: 5px;text-align: center;">
                <div>THÔNG TIN MUA HÀNG</div>
                <br>

                <div style="margin-bottom: 5px;"> ${sessionScope.account.lastName} ${sessionScope.account.firstName}</div>
                <div style="margin-bottom: 5px;"> ${sessionScope.account.phoneNumber}</div>
                <form action="loadaddress">                    
                    <input type="text" name="address" placeholder="Địa chị nhận hàng">
                    <input type="submit" value="ADD">                   
                </form>
                <form action="order" method="post">
                     <table border="0" style="width: 100%;">       
                    <c:forEach var ="l" items="${dao.location}"> 
                                
                                <tr>
                                    <td><input type="radio" id="${l.locationID}" name="diachi" value="${l.locationID}"></td>
                                    <td><label for="${l.locationID}">${l.location}</label><br></td>
                                    <td><a href="dellocation?lid=${l.locationID}">Delete</a></td>
                                </tr>
                            </tbody>
                           
                    </c:forEach>
                             </table>
                    <input type="submit" value="Order">
                </form>
            </div>
            <div class="donhang" style="text-align: center;margin-left: 5px;">
                <div>ĐƠN HÀNG(${dao.getCart().size()})</div>
                <div style="border-bottom: 1px dashed black "></div>

                <table border="0" style="width: 100%">
                    <thead>
                        <tr>
                            <th>tên món</th>
                            <th>đơn giá</th>
                            <th>số lượng</th> 
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var ="c" items="${dao.cart}"> 
                            <tr>
                                <td>${c.name}</td>
                                <td>${c.price}</td>
                                <td style="position: relative;">  
                                    <a href="cart?btn=giamsl&uid=${sessionScope.account.userID}&fid=${c.productId}" style="position: absolute; left: 15px;text-decoration: none;color: black;"/>-</a> 
                                    ${c.amount}
                                    <a href="cart?btn=tangsl&uid=${sessionScope.account.userID}&fid=${c.productId}" style="position: absolute; right: 15px;text-decoration: none;color: black;"/>+</a></td> 
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div style="border-bottom: 1px dashed black "></div>
                <div style="font-size: 20px; margin-top: 5px">THÀNH TIỀN: <span style="color: red">${totalmoney}</span></div>
                <br>

            </div>
        </div>
    </body>

</html>
