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
import com.objectfrontier.training.ws.pojo.Address;
import com.objectfrontier.training.ws.service.AddressService;
import com.objectfrontier.training.ws.util.JsonUtil;

/**
 * @author mohammed.mohammed
 * @since  Nov 2, 2018
 */
public class AddressServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        AddressService addressService = new AddressService();

        BufferedReader reader = request.getReader();
        List<String> jsonLines = reader.lines().collect(Collectors.toList());
        String addressJson = String.join("", jsonLines);
        Address addressDetails = JsonUtil.toObject(addressJson, Address.class);

        PrintWriter out = response.getWriter();

        Address createdAddressDetails = addressService.createAddress(addressDetails);
        out.write(JsonUtil.toJson(createdAddressDetails));

        out.close();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");

        AddressService addressService = new AddressService();
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

            List<Address> searchResultList = addressService.searchAddress(searchFieldArray, searchKeyword, resultSetArray);
            out.print(JsonUtil.toJson(searchResultList));

        } else {
            String id = request.getParameter("id");

            if(Objects.isNull(id)) {
                List<Address> addressList = addressService.readAllAddress();
                out.print(JsonUtil.toJson(addressList));

            } else {
                Address addressId = new Address();
                long addressIdValue = 0;

                try {
                    addressIdValue = Long.parseLong(id);
                } catch (Exception numExcp) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    throw new AppException(AppErrorCode.ERROR_INVALID_DATA_FORMAT);
                }

                addressId.setId(addressIdValue);
                Address addressDetails = addressService.readAddress(addressId);
                out.print(JsonUtil.toJson(addressDetails));
            }
        }

        out.close();
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        AddressService addressService = new AddressService();

        BufferedReader reader = request.getReader();
        List<String> jsonLines = reader.lines().collect(Collectors.toList());
        String addressJson = String.join("", jsonLines);
        Address addressDetails = JsonUtil.toObject(addressJson, Address.class);
        PrintWriter out = response.getWriter();

        Address updatedAddressDetails = addressService.updateAddress(addressDetails);
        out.write(JsonUtil.toJson(updatedAddressDetails));

        out.close();
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AddressService addressService = new AddressService();
        Address addressDetails = new Address();
        String id = request.getParameter("id");
        addressDetails.setId(Long.parseLong(id));
        PrintWriter out = response.getWriter();

        Address deletedAddress = addressService.deleteAddress(addressDetails);
        out.print(JsonUtil.toJson(deletedAddress));

        out.close();
    }

}
