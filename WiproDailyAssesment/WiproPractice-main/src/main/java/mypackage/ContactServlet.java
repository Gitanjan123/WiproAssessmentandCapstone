package mypackage;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {

    static ArrayList<Contact> contactList = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("contacts", contactList);
        request.getRequestDispatcher("viewContacts.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name  = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Contact c = new Contact(name, phone, email);
        contactList.add(c);

        response.sendRedirect("ContactServlet");
    }
}