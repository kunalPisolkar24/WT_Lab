package com.example.demo2;

import java.io.*;

import jakarta.ejb.EJB;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "middleWare", value = "/middle-ware")
public class MiddleWare extends HttpServlet {
    private String message;
    @EJB
    Bank b;
    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        int amnt = Integer.parseInt(request.getParameter("amount"));
        String operation = (String) request.getParameter("operation"), alertType;

        if(operation.equals("deposit")) {
            int totalAmount = b.getAmount() + amnt;
            b.setAmount( totalAmount);
            message = "Deposit performed Successfully";
            alertType = "success";
        }else {
            boolean f = b.widthDraw(amnt);
            if(f) {
               alertType = "success";
               message = "Withdrawal performed Successfully";
            }
            else{
                alertType = "danger";
                message = "Withdrawal Failed ! Not enough funds";
            }
        }

        String op = "<html> <head> <title>Summary Page</title> <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\"> <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script> </head> <body> <div class=\"container my-5\"> <h1 class=\"display-3\"> Funds Remaining: " + b.getAmount() + " </h1> <div class=\"alert alert-" + alertType + "\" role=\"alert\">" + message + "</div> </div> </body> </html>";
        response.getWriter().print(op);
    }

    public void destroy() {
    }
}