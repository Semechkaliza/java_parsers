package controller;

import dao.ValidatorXML;
import service.AbstractOldCardsBuilder;
import service.CardBuilderFactory;
import service.Redirector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/")
public class Controller extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action=request.getParameter("command");
        if("getPage".equals(action)) {
            Redirector.redirectShow(request,response,Integer.parseInt(request.getParameter("number")));
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        String action=request.getParameter("command");

        CardBuilderFactory sFactory = new CardBuilderFactory();
        AbstractOldCardsBuilder builder = sFactory.createCardBuilder(request.getParameter("command"));
        String path=getServletContext().getRealPath("/data/");
        if(ValidatorXML.validatorXML(path+"postcards.xml",path+"postcards.xsd")){
            builder.buildSetOldCards(path+"postcards.xml");

            Set<entity.Card> cards=builder.getOldCards();

            Redirector.redirectShow(request,response,cards);
        }

    }

}
