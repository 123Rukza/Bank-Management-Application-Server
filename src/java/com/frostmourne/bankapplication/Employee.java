package com.frostmourne.bankapplication;

import com.frostmourne.exceptions.UsernamePasswordException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "Employee")
public class Employee {

    /**
     * Web service operation
     */
    Database db = new Database();
    boolean isLogged = false;

    @WebMethod(operationName = "login")
    public Boolean login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) throws UsernamePasswordException {
        isLogged = db.employeeLogin(username, password);
        if (isLogged) {
            System.out.println("Logged in " + username);
            return true;
        } else {
            Throwable t = new Exception("Invalid username or password");
            throw new UsernamePasswordException("Please check your username or password entered", t);
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createEmployee")
    public Boolean createEmployee(@WebParam(name = "name") String name, @WebParam(name = "position") String position, @WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        boolean isDone = db.createEmployee(name, position, username, password);
        return isDone;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateEmployee")
    public Boolean updateEmployee(@WebParam(name = "name") String name, @WebParam(name = "position") String position, @WebParam(name = "usernameNew") String usernameNew, @WebParam(name = "usernameOld") String usernameOld, @WebParam(name = "password") String password) {
        boolean isDone = db.updateEmployee(name, position, usernameNew, usernameOld, password);
        return isDone;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteEmployee")
    public Boolean deleteEmployee(@WebParam(name = "username") String username) {
        boolean isDone = db.deleteEmployee(username);
        return isDone;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkUser")
    public Boolean checkUser() {
        return isLogged;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "employeeLogout")
    public Boolean employeeLogout() {
        if(isLogged){
            isLogged = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkEmployeeExists")
    public Boolean checkEmployeeExists(@WebParam(name = "username") String username) {
        return db.checkEmployeeExists(username);
    }
}
