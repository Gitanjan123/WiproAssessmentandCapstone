package com.wipro.ContactManager2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action.equals("add")) {
            // Add contact
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");

            Contact c = new Contact();
            c.setName(name);
            c.setPhone(phone);
            c.setEmail(email);

            boolean result = ContactDAO.addContact(c);

            if (result) {
                request.setAttribute("message", "✅ Contact added successfully!");
            } else {
                request.setAttribute("error", "❌ Error adding contact!");
            }
            request.getRequestDispatcher("viewContacts.jsp")
                   .forward(request, response);

        } else if (action.equals("update")) {
            // Update contact
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");

            Contact c = new Contact(id, name, phone, email);
            ContactDAO.updateContact(c);

            request.setAttribute("message", "✅ Contact updated successfully!");
            request.getRequestDispatcher("viewContacts.jsp")
                   .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.equals("view")) {
            // View all contacts
            List<Contact> contacts = ContactDAO.getAllContacts();
            request.setAttribute("contacts", contacts);
            request.getRequestDispatcher("viewContacts.jsp")
                   .forward(request, response);

        } else if (action.equals("delete")) {
            // Delete contact
            int id = Integer.parseInt(request.getParameter("id"));
            ContactDAO.deleteContact(id);
            request.setAttribute("message", "✅ Contact deleted successfully!");
            List<Contact> contacts = ContactDAO.getAllContacts();
            request.setAttribute("contacts", contacts);
            request.getRequestDispatcher("viewContacts.jsp")
                   .forward(request, response);

        } else if (action.equals("edit")) {
            // Edit contact
            int id = Integer.parseInt(request.getParameter("id"));
            Contact c = ContactDAO.getContactById(id);
            request.setAttribute("contact", c);
            request.getRequestDispatcher("editContact.jsp")
                   .forward(request, response);
        }
    }
}