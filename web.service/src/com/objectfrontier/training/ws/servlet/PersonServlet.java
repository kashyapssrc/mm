/**
 *
 */
package com.objectfrontier.training.ws.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.objectfrontier.training.ws.exception.AppErrorCode;
import com.objectfrontier.training.ws.exception.AppException;
import com.objectfrontier.training.ws.pojo.Person;
import com.objectfrontier.training.ws.service.PersonService;
import com.objectfrontier.training.ws.util.JsonUtil;

/**
 * @author mohammed.mohammed
 * @since  Nov 2, 2018
 */
public class PersonServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        PersonService personService = new PersonService();

        BufferedReader reader = request.getReader();
        List<String> jsonLines = reader.lines().collect(Collectors.toList());
        String personJson = String.join("", jsonLines);
        Person personDetails = JsonUtil.toObject(personJson, Person.class);
        PrintWriter out = response.getWriter();

        Person createdPersonDetails = personService.createPerson(personDetails);
        out.write(JsonUtil.toJson(createdPersonDetails));

        out.close();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        PersonService personService = new PersonService();
        PrintWriter out = response.getWriter();

        String searchRequestValue = request.getParameter("search");
        boolean searchRequest = new Boolean(searchRequestValue);

        if(searchRequest == true) {
            String searchField = request.getParameter("searchField");
            String[] searchFieldArray = searchField.split(",");
            String searchKeyword = request.getParameter("searchKeyword");
            String resultSetField = request.getParameter("resultSetField");
            String[] resultSetArray = resultSetField.split(",");

            for(String arrayElements : searchFieldArray) {
                if(arrayElements.equalsIgnoreCase("id")) {
                    try {
                        Long.parseLong(searchKeyword);
                    } catch (Exception numExcp) {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        throw new AppException(AppErrorCode.ERROR_INVALID_DATA_FORMAT);
                    }
                }
            }

            List<Person> searchResultList = personService.searchPerson(searchFieldArray, searchKeyword, resultSetArray);
            out.write(JsonUtil.toJson(searchResultList));

        } else {
            String includeAddressValue = request.getParameter("includeAddress");
            boolean includeAddress = new Boolean(includeAddressValue);
            String id = request.getParameter("id");

            if(Objects.isNull(id)) {
                List<Person> personList = personService.readAllPerson(includeAddress);
                out.write(JsonUtil.toJson(personList));

            } else {
                Person personId = new Person();
                long personIdValue = 0;

                try {
                    personIdValue = Long.parseLong(id);
                } catch (Exception numExcp) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    throw new AppException(AppErrorCode.ERROR_INVALID_DATA_FORMAT);
                }

                personId.setId(personIdValue);
                Person personDetails = personService.readPerson(personId, includeAddress);
                out.write(JsonUtil.toJson(personDetails));
            }
        }

        out.close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        PersonService personService = new PersonService();

        BufferedReader reader = request.getReader();
        List<String> jsonLines = reader.lines().collect(Collectors.toList());
        String personJson = String.join("", jsonLines);
        Person personDetails = JsonUtil.toObject(personJson, Person.class);

        String includeAddressValue = request.getParameter("includeAddress");
        boolean includeAddress = new Boolean(includeAddressValue);
        PrintWriter out = response.getWriter();

        Person updatedPersonDetails = personService.updatePerson(personDetails, includeAddress);
        out.write(JsonUtil.toJson(updatedPersonDetails));

        out.close();
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PersonService personService = new PersonService();
        Person personDetails = new Person();

        String id = request.getParameter("id");
        personDetails.setId(Long.parseLong(id));
        PrintWriter out = response.getWriter();

        Person deletedPerson = personService.deletePerson(personDetails);
        out.write(JsonUtil.toJson(deletedPerson));

        out.close();
    }

}
