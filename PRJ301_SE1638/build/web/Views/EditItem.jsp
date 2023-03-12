<%-- 
    Document   : EditItem
    Created on : Oct 26, 2022, 3:02:19 PM
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
                <form action="confirmedit" method="post"> 
                    <input style="display: none;" id="ipt1" type="text" name="fid" value="${dao.product.get(0).productID}">
                    <table border="0" style="width: 100%;">         
                        <tr>
                            <td>Tên sản phẩm:</td>
                            <td id="hide1">${dao.product.get(0).name}</td>
                            <td id="show1" style="display: none;"><input id="ipt1" type="text" name="name"></td>
                            <td><input id="btn1" onclick="hide(1)" type="button" value="Edit"></td>

                        </tr>
                        <tr>
                            <td>Giá sản phẩm:</td>
                            <td id="hide2"> ${dao.product.get(0).price}</td>
                            <td id="show2" style="display: none;"><input id="ipt2" type="text" name="price"></td>
                            <td><input id="btn2" onclick="hide(2)" type="button" value="Edit"></td>
                        </tr>
                        <tr>
                            <td>Thể loại:</td>
                            <td id="hide3">${dao.product.get(0).subcategoryID}</td>
                            <td id="show3" style="display: none;"><input id="ipt3" type="text" name="cid"></td>
                            <td><input id="btn3" onclick="hide(3)" type="button" value="Edit"></td>
                        </tr>
                        <tr>
                            <td>img:</td>
                            <td id="hide4">${dao.product.get(0).images}</td>
                            <td id="show4" style="display: none;"><input id="ipt4" type="file" name="img"></td>
                            <td><input id="btn4" onclick="hide(4)" type="button" value="Edit"></td>
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

        }

        

    </script>

</html>

