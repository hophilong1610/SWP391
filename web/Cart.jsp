

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>

    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">

                    <div class="pb-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
                                    <!-- Shopping cart table -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>                                      
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Food</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Amount</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Price</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Total</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Delete</div>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${p}" var="o">
                                                <c:if test="${o.status == 0}">
                                                    <tr>
                                                        <c:forEach items="${product}" var="p">
                                                            <c:if test="${p.id == o.pid}">
                                                                <td class="align-middle"><strong>${p.name} </strong></td>
                                                            </c:if>
                                                        </c:forEach>    
                                                        <td class="align-middle">
                                                            <a href="minusAmount?pid=${o.pid}&cartid=${o.cartid}"><button class="btnSub">-</button></a> 
                                                                <strong>${o.amount}</strong>
                                                            <a href="addAmount?pid=${o.pid}&cartid=${o.cartid}"><button class="btnAdd">+</button></a>
                                                        </td>
                                                        <c:forEach items="${product}" var="p">
                                                            <c:if test="${p.id == o.pid}">
                                                                <td class="align-middle"><strong>$${p.price}</strong></td>
                                                                <td class="align-middle"><strong>$${p.price * o.amount}</strong></td>
                                                            </c:if>
                                                        </c:forEach> 
                                                        <td class="align-middle">
                                                            <a href="deleteItem?pid=${o.pid}&cartid=${o.cartid}" class="text-dark">
                                                                <button type="button" class="btn btn-danger">Delete</button>
                                                            </a>
                                                        </td>
                                                    </tr> 
                                                </c:if>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- End -->
                            </div>
                        </div>
                         <c:set var = "s" value = "1"/>    
                        <c:forEach items="${p}" var="o">
                            <c:if test="${o.status == 0}">
                                <c:set var = "s" value = "0"/>    
                            </c:if>
                        </c:forEach>    
                        <div class="row py-5 p-4 bg-white rounded shadow-sm">
                            <div class="col-lg-6">
                            <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Bill</div>
                                <div class="p-4">
                                    <c:if test="${s == 0}">
                                        <ul class="list-unstyled mb-4">
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Price</strong><strong>$${price}</strong></li>
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Shipping fee</strong><strong>Free ship</strong></li>
<!--                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">VAT(10%)</strong><strong>$${vat}</strong></li>-->
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong>
                                                <h5 class="font-weight-bold">$${bill}</h5>
                                            </li>
                                        </ul>
                                    </c:if>       
                                    
                                   <c:if test="${s == 1}">
                                        <ul class="list-unstyled mb-4">
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total price</strong><strong>$0</strong></li>
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Shipping fee</strong><strong>Free ship</strong></li>
<!--                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">VAT(10%)</strong><strong>$0</strong></li>-->
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong>
                                                <h5 class="font-weight-bold">$0</h5>
                                            </li>
                                        </ul>
                                    </c:if>     
                        
                                       
                                    <a href="Buy.jsp" class="btn btn-dark rounded-pill py-2 btn-block">Buy</a>
                                    <a href="home" class="btn btn-dark rounded-pill py-2 btn-block" style = "background-color: white; color: black">Back to Home</a>   
                                </div>
                            </div>
                        </div>                       
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>

</html>
</html>
