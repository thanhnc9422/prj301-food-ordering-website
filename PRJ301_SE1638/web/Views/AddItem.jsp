<%-- 
    Document   : AddItem
    Created on : Oct 26, 2022, 10:18:39 PM
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
            <div class="header_account_cart">
                <c:if test = '${sessionScope.account != null}'> 
                    <div class="item_header_account_cart">
                        <a href="history"><img src="images/point.png" alt="" height="50px" width="50px"></a></div>
                    <div  class="item_header_account_cart">
                        Lịch sử mua hàng
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
            <div class="thongtinmuahang" style="margin-right: 5px;text-align: center; width: 500px;">
                <div>CHỈNH SỬA SẢN PHẨM</div>
                <br>
                <form action="confirmadd" method="post"> 
                    <input style="display: none;" type="text" name="cid" value="${cid}">
                   
                    <table border="0" style="width: 100%;">         
                        <tr>
                            <td>Tên sản phẩm:</td>       
                            <td><input type="text" name="name"></td>
                        </tr>
                        <tr>
                            <td>Giá sản phẩm:</td>                         
                            <td><input type="text" name="price"></td>                         
                        </tr>
                        
                        <tr>
                            <td>img:</td>             
                            <td><input type="file" name="img"></td>                        
                        </tr>                                                                 
                        <tr>
                            <td>Mật khẩu hiện tại:</td>
                            <td><input type="password" name="cp"></td>
                          
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Xác nhận thêm sản phẩm" style="font-size: 15px; color: white; background-color: green; border: none;border-radius: 5px;width: 100%; width: fit-content"></td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                </form><br>
                    
                <div style="color: red; text-align: center;">${alert}</div>
            </div>
        </div>


    </body>
   

</html>

