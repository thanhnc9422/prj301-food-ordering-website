<%-- 
    Document   : Home
    Created on : Oct 4, 2022, 12:58:58 PM
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
        <link rel="stylesheet" href="CSS/styledisplay.css">
        <title>Document</title>
    </head>
    <body>
        <div class="header">
            <div class="header_logo">
                <img src="images/restaurant.png" alt="" srcset="" height="50px" width="50px">
            </div>
            <c:if test = '${sessionScope.account.manager == 1}'> 
            <div>             
                <a href="manager">MANAGER</a>            
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
        <div class="banner">
            <img src="images/istockphoto-1089759056-170667a.jpeg" alt="" srcset="">
        </div>
        <div class="item">
       
        <h2>Top món ăn được lựa chọn nhiều nhất</h2>

        <div style="display: flex;flex-wrap: wrap;">   
            <c:forEach var ="pr" items="${dao.product}"> 
                <div style="width:31.3%; padding: 10px 10px; position: relative;">
                      <c:if test = '${sessionScope.account.manager == 1}'> 
                    <div style="background-color: red;position: absolute;top: 10px; right: 10px;"><a href="deleteitem?fid=${pr.productID}" style="color: white;"><img style="width: 30px" src="images/delete.png"/></a></div>
                    </c:if>
                    <div style="width: 100%;">
                        <img src="images/${pr.images}" style="width: 100%;height: 150px;border-radius: 5px;" alt="">
                        <div class="item_new_text">
                            <div>${pr.name}<br>${pr.price} VND</div>
                            <div class="item_new_text_add_cart">

                                 <c:if test = '${sessionScope.account.manager == 0||sessionScope.account == null}'> 
                                    <form action="cart" method="post">
                                        <input style="display: none;" type="text" name="fid" value="${pr.productID}">
                                          <input style="display: none;" type="text" name="uid" value="${sessionScope.account.userID}">
                                        <button type="submit">ADD TO CART</button>
                                    </form>
                                </c:if>
                                 <c:if test = '${sessionScope.account.manager == 1}'> 
                                    <form action="edititem" method="post">
                                        <input style="display: none;" type="text" name="fid" value="${pr.productID}">
                                        <button type="submit"><a>EDIT</a></button>  
                                    </form>
                                </c:if>

                            </div>              
                        </div>                                     
                    </div> 
                </div>
                           

            </c:forEach>
            
        </div>
        <div class="footer">
            <div class="footer_item">Liên hệ đặt hàng: 0961835886<br>Gmail: thanhnche160277@fpt.edu.vn<br>Mở cửa: (7h00 - 23h00)</div>

            <div style="font-family:Impact, Haettens chweiler, 'Arial Narrow Bold', sans-serif; font-style: italic;"><h1>HEALTHY FOOD MAKES FEEL GOOD!</h1></div>

        </div>

    </body>
</html>