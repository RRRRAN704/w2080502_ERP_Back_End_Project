package com.erp.context;

public class BaseContext {

    public static ThreadLocal<Long> employeeLocal = new ThreadLocal<>();

    public static ThreadLocal<String> roleLocal = new ThreadLocal<>();

    public static ThreadLocal<Long> departmentLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        employeeLocal.set(id);
    }

    public static Long getCurrentId() {
        return employeeLocal.get();
    }

    public static void removeCurrentId() {
        employeeLocal.remove();
    }

    public static void setCurrentRole(String role) {
        roleLocal.set(role);
    }

    public static String getCurrentRole() {
        return roleLocal.get();
    }

    public static void removeCurrentRole() {
        roleLocal.remove();
    }

    public static void setCurrentDepartment(Long id) {
        departmentLocal.set(id);
    }

    public static Long getCurrentDepartment() {
        return departmentLocal.get();
    }

    public static void removeCurrentDepartment() {
        departmentLocal.remove();
    }

}
