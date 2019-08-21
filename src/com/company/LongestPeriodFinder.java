package com.company;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LongestPeriodFinder {
    private List<ProjectsParticipationData> sort(List<ProjectsParticipationData> list){
        list.sort(Comparator.comparing(ProjectsParticipationData::getEmpID));
        return list;
    }

    /**
     * Uses Empoyee.calculateDaysSpentWIthEmployee
     * @param emp1 first employee
     * @param emp2 second employee
     * @return days spent working on common projects
     */
    private long calculateDays(Employee emp1, Employee emp2){
        return emp1.calculateDaysSpentWIthEmployee(emp2);
    }

    /**
     * Compares every employee with every other employee with larger EmpID than its
     * @param dataList ProjectParticipationData gathered from .csv file
     * @return message containing both employees ids and the days they spent together working on common projects
     */
    public String findPairOfEmployeesWithMostHoursSpentTogether(List<ProjectsParticipationData> dataList){
        List<Employee> employeeList = sortParticipationDataOntoEmployees(dataList);
        long maxDays = 0;
        Employee tempEmp = null;
        Employee tempSecondEmp = null;
        long temp = 0;
        for (Employee emp:employeeList) {
            for (Employee secondEmp:employeeList) {
                if(emp.getEmpID() < secondEmp.getEmpID()) {
                    if (maxDays < (temp = calculateDays(emp, secondEmp))) {
                        maxDays = temp;
                        tempEmp = emp;
                        tempSecondEmp = secondEmp;
                    }
                }
            }
        }
        if(tempEmp!=null && tempSecondEmp!=null) {
            return "Employee ID:"+tempEmp.getEmpID()+" and employee ID:"+tempSecondEmp.getEmpID()+" spent "
                    +maxDays+" days working together";
        }
        return "0";
    }

    /**
     * Sorts ProjectParticipationData into seperate Employee classes by their ID
     * @param dataList list of data of employees participating in different projects
     * @return list of Employee's
     */
    private List<Employee> sortParticipationDataOntoEmployees(List<ProjectsParticipationData> dataList){
        dataList = this.sort(dataList);
        List<Employee> employees = new LinkedList<>();
        int currentID = dataList.get(0).getEmpID();
        employees.add(new Employee(currentID));
        int currentIndex = 0;
        for (ProjectsParticipationData data:dataList) {
            if(currentID == data.getEmpID()){
                employees.get(currentIndex).addParticipationData(data);
            } else {
                currentID = data.getEmpID();
                employees.add(new Employee(currentID));
                currentIndex++;
                employees.get(currentIndex).addParticipationData(data);
            }
        }
        return employees;
    }
}
