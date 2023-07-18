

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Manage <b>Account</b></h2>
                        </div>
                        <div class="col-sm-6">	
                            <a href="#addEmployee"  class="btn btn-success" data-toggle="modal" style = "height: 35px; vertical-align: middle"><i class="material-icons">&#xE147;</i> <span>Add New Account</span></a>
                            <a href="home" class="btn btn-success" style = "background-color: white; color: black; height: 35px; width: 108px">Back to home</a>		
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>UserName</th>
                            <th>Password</th>
                            <th>Role</th>
                            <th>Function</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listA}" var="o">
                            <tr>
                                <td>${o.id}</td>
                                <td>${o.name}</td>
                                <td>${o.user}</td>
                                <td>${o.pass}</td>
                                <td>
                                    <c:if test = "${o.role == 2}"> 
                                        Seller
                                    </c:if>  
                                    <c:if test = "${o.role == 1}"> 
                                        Customer
                                    </c:if>    
                                    <c:if test = "${o.role == 0}"> 
                                        Admin
                                    </c:if> 
                                </td>        
                                <td >
                                    <a class="text-decoration-none btn btn-success" style="color: white" href="EditAccountMangerControl?id=${o.id}">Edit</a> 
                                    <a class="text-decoration-none btn btn-success" style="color: white" href="deleteA?id=${o.id}">Delete</a>
                                    
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div id="addEmployee" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="addA" method="post" enctype="multipart/form-data">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Account</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Name</label>
                                    <input name="name" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>UserName</label>
                                    <input name="username" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>PassWord</label>
                                    <input name="password" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <select name="role" class="form-select" aria-label="Default select example">
                                        <option value="0">Admin</option>
                                        <option value="1">Customer</option>
                                    </select>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>