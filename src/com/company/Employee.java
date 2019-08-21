package com.company;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Employee {
    private List<ProjectsParticipationData> participations = new LinkedList<>();
    private int EmpID;

    public int getEmpID() {
        return EmpID;
    }

    public Employee(int empID) {
        EmpID = empID;
    }

    public List<ProjectsParticipationData> getParticipations() {
        return participations;
    }

    /**
     * Adds new ProjectsParticipationData onto this employee
     * @param data new data being added
     */
    public void addParticipationData(ProjectsParticipationData data){
        participations.add(data);
    }

    /**
     * Calculates difference between two dates
     * @param firstDate first date
     * @param secondDate second date
     * @return difference in days between both dates
     */
    private long calculateDayDifference(Date firstDate, Date secondDate){
        return (firstDate.getTime() / (1000 * 60 * 60 * 24)) - (secondDate.getTime() / (1000 * 60 * 60 * 24));
    }

    /**
     * Determines if and how many days this employee and another employee spent together working on the same projects
     * @param employee different employee
     * @return days spent working on the same project
     */
    public long calculateDaysSpentWIthEmployee(Employee employee){
        long daysWithThisEmployee = 0;
        for (ProjectsParticipationData data:this.participations) {
            for (ProjectsParticipationData empData:employee.getParticipations()) {
                if(data.getProjectID() == empData.getProjectID()){
                    if(data.getDateFrom().after(empData.getDateFrom()) && data.getDateFrom().before(empData.getDateTo())) {
                        if(data.getDateTo().after(empData.getDateTo())){
                            daysWithThisEmployee+= calculateDayDifference(empData.getDateTo(),data.getDateFrom());
                        } else {
                            daysWithThisEmployee+= calculateDayDifference(data.getDateTo(),data.getDateFrom());
                        }
                    }else if(empData.getDateFrom().after(data.getDateFrom()) && empData.getDateFrom().before(data.getDateTo())){
                            if(empData.getDateTo().after(data.getDateTo())){
                                daysWithThisEmployee+= calculateDayDifference(data.getDateTo(),empData.getDateFrom());
                            } else {
                                daysWithThisEmployee+= calculateDayDifference(empData.getDateTo(),empData.getDateFrom());
                            }
                    } else if(data.getDateFrom().equals(empData.getDateFrom())){
                            if(data.getDateTo().after(empData.getDateTo())){
                                daysWithThisEmployee+= calculateDayDifference(empData.getDateTo(),data.getDateFrom());
                            } else {
                                daysWithThisEmployee+= calculateDayDifference(data.getDateTo(),data.getDateFrom());
                            }
                    }
                }
            }
        }
        return daysWithThisEmployee;
    }
}
