<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of menu-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="">Hello ${sessionScope.acc.user}</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
                <c:if test="${sessionScope.acc.role == 0}">
                    <li class="nav-item" style="display: flex">

                        <a class="nav-link" href="managerA">Manage Account</a>
                        <a class="nav-link" href="showOrder">Manage Order</a>
                        <a class="nav-link" href="manager">Manage Product</a>
                        <a class="nav-link" href="managerC">Manage Category</a>
                    </li>
                </c:if>  
                <c:if test="${sessionScope.acc != null}"> 
                    <li class="nav-item" style="display: flex" >
                        <a class="nav-link" href="editA">Edit Profile</a>
<!--                        <a class="nav-link" href="deleteA">Delete Account</a>-->
                        <a class="nav-link" href="logout">LogOut</a>
                    </li> 
                </c:if>
                <c:if test="${sessionScope.acc == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="Login.jsp">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Register.jsp">Register</a>
                    </li>
                </c:if>
            </ul>

            <form action="search" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input oninput="searchByName(this)" value="${txtS}" name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <c:if test="${sessionScope.acc != null}">     
                    <a class="btn btn-success btn-sm ml-3" href="show">
                        <i class="fa fa-shopping-cart"></i> Cart
                    </a>
                </c:if>   
                    
                   
            </form>
        </div>
    </div>
</nav>
<section class="jumbotron" style = "background-image: url(images/Logo.jpg); background-size: 100% 100%; height: 350px; border-radius: 0%;">
    <div class="container">
    </div>
</section>
<!--end of menu-->
