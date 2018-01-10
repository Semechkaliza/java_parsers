package service;

import entity.Card;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Redirector {
    private static final String showRedirectPage = "showUploadedPage.jsp";
    private static final int pageSize = 4;

    public static void redirectShow(HttpServletRequest req, HttpServletResponse resp, Set<Card> cards) throws ServletException, IOException {

        ArrayList<Card> cardsList = new ArrayList<>(cards);
        req.getSession().setAttribute("entities", cardsList);

        List<Integer> pages = getPagesList(cardsList);

        List<Card> tempList = getTempList(cardsList,1);

        req.setAttribute("pageNumber", 1);
        req.setAttribute("entities", tempList);
        req.setAttribute("pages", pages);

        req.getRequestDispatcher(showRedirectPage).forward(req, resp);
    }

    public static void redirectShow(HttpServletRequest req, HttpServletResponse resp, int page) throws ServletException, IOException{

        ArrayList<Card> cardsList = (ArrayList<Card>) req.getSession().getAttribute("entities");

        List<Integer> pages = getPagesList(cardsList);

        List<Card> tempList = getTempList(cardsList,page);

        req.setAttribute("pageNumber", page);
        req.setAttribute("entities", tempList);
        req.setAttribute("pages", pages);

        req.getRequestDispatcher(showRedirectPage).forward(req, resp);
    }
    private static List<Integer> getPagesList(ArrayList<Card> cardsList){
        List<Integer> pages = new ArrayList<>();
        int pagesNumber = (int) Math.ceil(cardsList.size()/pageSize);
        for (int i = 0; i < pagesNumber;i++){
            pages.add(i+1);
        }
        return pages;
    }

    private static List<Card> getTempList(ArrayList<Card> cardsList, int page){
        List<Card> tempList = new ArrayList<>();
        for (int i = (page-1)*pageSize; i < (page)*pageSize; i++){
            tempList.add(cardsList.get(i));
        }
        return tempList;
    }

}
