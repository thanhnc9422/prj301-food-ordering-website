<%-- 
    Document   : Profile
    Created on : Oct 20, 2022, 2:52:58 PM
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
                <div>THÔNG TIN KHÁCH HÀNG</div>
                <br>
                <form action="profile" method="post">  
                    <table border="0" style="width: 100%;">         
                        <tr>
                            <td>Tên đăng nhập:</td>
                            <td id="hide1">${sessionScope.account.userName}</td>
                            <td id="show1" style="display: none;"><input id="ipt1" type="text" name="username"></td>
                            <td><input id="btn1" onclick="hide(1)" type="button" value="Edit"></td>

                        </tr>
                        <tr>
                            <td>Họ:</td>
                            <td id="hide2"> ${sessionScope.account.lastName}</td>
                            <td id="show2" style="display: none;"><input id="ipt2" type="text" name="lastname"></td>
                            <td><input id="btn2" onclick="hide(2)" type="button" value="Edit"></td>
                        </tr>
                        <tr>
                            <td>Tên:</td>
                            <td id="hide3">${sessionScope.account.firstName}</td>
                            <td id="show3" style="display: none;"><input id="ipt3" type="text" name="firstname"></td>
                            <td><input id="btn3" onclick="hide(3)" type="button" value="Edit"></td>
                        </tr>
                        <tr>
                            <td>Số điện thoại:</td>
                            <td id="hide4">${sessionScope.account.phoneNumber}</td>
                            <td id="show4" style="display: none;"><input id="ipt4" type="text" name="phonenumber"></td>
                            <td><input id="btn4" onclick="hide(4)" type="button" value="Edit"></td>
                        </tr>
                        <tr>
                            <td>Mật khẩu:</td>
                            <td id="hide5">********</td>
                            <td id="show5" style="display: none;"><input id="ipt5" type="text" name="password"></td>
                            <td><input id="btn5" onclick="hide(5)" type="button" value="Edit"></td>
                        </tr>
                         <tr>
                            <td>Email:</td>
                            <td >${sessionScope.account.email}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Địa chỉ:</td>
                            <td></td>
                            <td><a href="url">Thêm địa chỉ</a></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td id="cp" style="display: none;">Mật khẩu hiện tại:<input type="password" name="cp"></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input id="btnsmt" type="submit" value="Xác nhận thay đổi" style="font-size: 15px; color: white; background-color: green; border: none;border-radius: 5px;display: none;width: 100%"></td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                </form>
                            <div style="color: red; text-align: center;">${alert}</div>
            </div>
        </div>
                            
                          
    </body>
    <script>
        let count1 = 0;
        let count2 = 0;
        let count3 = 0;
        let count4 = 0;
        let count5 = 0;
        function hide(a) {
            if (a == 1) {
                if (count1 % 2 == 0) {
                    document.getElementById('hide1').style.display = 'none';
                    document.getElementById('show1').style.display = 'block';
                    document.getElementById('btn1').value = 'Back';
                    document.getElementById('btnsmt').style.display = 'block';
                     document.getElementById('cp').style.display = 'block';
                    count1++;
                } else {
                    document.getElementById('hide1').style.display = 'block';
                    document.getElementById('show1').style.display = 'none';
                    document.getElementById('btn1').value = 'Edit';
                    document.getElementById('ipt1').value = '';
                    document.getElementById('btnsmt').style.display = 'none';
                       document.getElementById('cp').style.display = 'none';
                    count1++;
                }
            }
            if (a == 2) {
                if (count2 % 2 == 0) {
                    document.getElementById('hide2').style.display = 'none';
                    document.getElementById('show2').style.display = 'block';
                    document.getElementById('btn2').value = 'Back';
                    document.getElementById('btnsmt').style.display = 'block';
                       document.getElementById('cp').style.display = 'block';
                    count2++;
                } else {
                    document.getElementById('hide2').style.display = 'block';
                    document.getElementById('show2').style.display = 'none';
                    document.getElementById('btn2').value = 'Edit';
                    document.getElementById('ipt2').value = '';
                    document.getElementById('btnsmt').style.display = 'none';
                       document.getElementById('cp').style.display = 'none';
                    count2++;
                }
            }
            if (a == 3) {
                if (count3 % 2 == 0) {
                    document.getElementById('hide3').style.display = 'none';
                    document.getElementById('show3').style.display = 'block';
                    document.getElementById('btn3').value = 'Back';
                    document.getElementById('btnsmt').style.display = 'block';
                       document.getElementById('cp').style.display = 'block';
                    count3++;
                } else {
                    document.getElementById('hide3').style.display = 'block';
                    document.getElementById('show3').style.display = 'none';
                    document.getElementById('btn3').value = 'Edit';
                    document.getElementById('ipt3').value = '';
                    document.getElementById('btnsmt').style.display = 'none';
                       document.getElementById('cp').style.display = 'none';
                    count3++;
                }
            }
            if (a == 4) {
                if (count4 % 2 == 0) {
                    document.getElementById('hide4').style.display = 'none';
                    document.getElementById('show4').style.display = 'block';
                    document.getElementById('btn4').value = 'Back';
                    document.getElementById('btnsmt').style.display = 'block';
                       document.getElementById('cp').style.display = 'block';
                    count4++;
                } else {
                    document.getElementById('hide4').style.display = 'block';
                    document.getElementById('show4').style.display = 'none';
                    document.getElementById('btn4').value = 'Edit';
                    document.getElementById('ipt4').value = '';
                    document.getElementById('btnsmt').style.display = 'none';
                       document.getElementById('cp').style.display = 'none';
                    count4++;
                }
            }
              if (a == 5) {
                if (count5 % 2 == 0) {
                    document.getElementById('hide5').style.display = 'none';
                    document.getElementById('show5').style.display = 'block';
                    document.getElementById('btn5').value = 'Back';
                    document.getElementById('btnsmt').style.display = 'block';
                       document.getElementById('cp').style.display = 'block';
                    count5++;
                } else {
                    document.getElementById('hide5').style.display = 'block';
                    document.getElementById('show5').style.display = 'none';
                    document.getElementById('btn5').value = 'Edit';
                    document.getElementById('ipt5').value = '';
                    document.getElementById('btnsmt').style.display = 'none';
                       document.getElementById('cp').style.display = 'none';
                    count5++;
                }
            }

        }

    </script>

</html>

