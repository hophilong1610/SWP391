

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
                                                        <div class="p-2 px-3 text-uppercase">Order ID</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="p-2 px-3 text-uppercase">Customer Name</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Price</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Status</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Address</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Phone Number</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">order Details</div>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${p}" var="o">
                                                <tr>
                                                    <td class="align-middle"><strong>${o.id}</strong></td>
                                                    <c:forEach items="${c}" var="c">
                                                        <c:if test="${c.cartId == o.cartid}">
                                                            <c:forEach items="${a}" var="a"> 
                                                                <c:if test="${c.id == a.id}">
                                                                    <td class="align-middle"><strong>${a.name}</strong></td>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:forEach>    
                                                    <td class="align-middle"><strong>$${o.bill} </strong></td>
                                                    <td><c:if test="${o.status == 0}"><a href="ready?id=${o.id}" class="btn btn-success" style = "background-color: red; color: white; height: 35px; width: 120px">Ready</a> </c:if>
                                                        <c:if test="${o.status == 1}">Delivered </c:if>
                                                        <c:if test="${o.status == 2}">Shipping </c:if>
                                                    </td>    
                                                    <td class="align-middle"><strong>${o.address} </strong></td>
                                                    <td class="align-middle"><strong>${o.phone} </strong></td>
                                                    <td class="align-middle"><strong><a href="OrderDetails?id=${o.cartid}" class="btn btn-success" style = "background-color: blue; color: white; height: 35px; width: 120px">View</a></strong></td>
                                                </tr> 
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <a href="home" class="btn btn-success" style = "background-color: white; color: black; height: 35px; width: 120px">Back to home</a>		    
                                </div>
                                <!-- End -->
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
