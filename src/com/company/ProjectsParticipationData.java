package com.company;

import java.util.Date;

public class ProjectsParticipationData {
    private int EmpID;
    private int ProjectID;
    private Date DateFrom;
    private Date DateTo;

    public ProjectsParticipationData(int empID, int projectID, Date dateFrom, Date dateTo) {
        EmpID = empID;
        ProjectID = projectID;
        DateFrom = dateFrom;
        DateTo = dateTo;
    }

    public int getEmpID() {
        return EmpID;
    }

    public int getProjectID() {
        return ProjectID;
    }

    public Date getDateFrom() {
        return DateFrom;
    }

    public Date getDateTo() {
        return DateTo;
    }
}
