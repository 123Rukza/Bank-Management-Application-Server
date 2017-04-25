package com.frostmourne.bankapplication;

import com.frostmourne.exceptions.InvalidDataException;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService(serviceName = "Customer")
public class Customer {

    Database db = new Database();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createCustomer")
    public Boolean createCustomer(@WebParam(name = "name") String name, @WebParam(name = "birthdate") String birthdate, @WebParam(name = "address") String address, @WebParam(name = "mobile") String mobile, @WebParam(name = "email") String email, @WebParam(name = "type") String type, @WebParam(name = "number") String number, @WebParam(name = "sortcode") String sortcode, @WebParam(name = "balance") String balance, @WebParam(name = "card") String card) throws InvalidDataException {
        try {
            return db.createCustomer(name, birthdate, address, mobile, email, type, number, sortcode, balance, card);
        } catch (Exception e) {
            throw new InvalidDataException("Invalid data inputs!");
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateCustomer")
    public Boolean updateCustomer(@WebParam(name = "name") String name, @WebParam(name = "birthdate") String birthdate, @WebParam(name = "address") String address, @WebParam(name = "mobile") String mobile, @WebParam(name = "email") String email, @WebParam(name = "type") String type, @WebParam(name = "number") String number, @WebParam(name = "numberOld") String numberOld, @WebParam(name = "sortcode") String sortcode, @WebParam(name = "balance") String balance, @WebParam(name = "card") String card) throws InvalidDataException {
        try {
            return db.updateCustomer(name, birthdate, address, mobile, email, type, number, numberOld, sortcode, balance, card);
        } catch (Exception e) {
            throw new InvalidDataException("Invalid data inputs!");
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllCustomer")
    public ArrayList<String> getAllCustomer() {
        return db.getAllCustomers();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCustomerDetails")
    public ArrayList<String> getCustomerDetails(@WebParam(name = "acciuntNumber") String acciuntNumber) {
        return db.getCustomerDetails(acciuntNumber);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteCustomer")
    public Boolean deleteCustomer(@WebParam(name = "accountNumber") String accountNumber) throws InvalidDataException {
        try {
            return db.deleteCustomer(accountNumber);
        } catch (Exception e) {
            throw new InvalidDataException("Customer not found!");
        }
    }

}
