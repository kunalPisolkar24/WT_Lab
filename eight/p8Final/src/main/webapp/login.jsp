<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .center-form {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 30%;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="center-form">
            <s:form action="login" method="post">
                <s:textfield name="fullName" label="Full Name" cssClass="form-control my-4" placeholder="Enter Full Name" required="true" />
                <s:textfield name="mobileNumber" label="Mobile Number" cssClass="form-control my-4" placeholder="Enter Mobile Number" required="true" />
                <s:textfield name="emailId" label="Email ID" cssClass="form-control my-4" placeholder="Enter Email ID" required="true" />
                <s:submit value="Submit" cssClass="btn btn-primary mt-4 px-4" />
                <s:reset value="Reset" cssClass="btn btn-secondary mt-4 px-4" />
            </s:form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <s:if test="hasActionErrors()">
        <script>
            alert('<s:actionerror/>');
        </script>
    </s:if>
</body>

</html>