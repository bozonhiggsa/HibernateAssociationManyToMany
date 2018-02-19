package com.hibernateApp.hibernate.association.manyToMany;

/**
 * Model
 * @author Ihor Savchenko
 * @version 1.0
 */
public class Project {

    private int id;
    private String projectName;
    private String companyName;

    public Project() {
    }

    public Project(String projectName, String companyName) {
        this.projectName = projectName;
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCompanyName() {
        return companyName;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!this.getClass().equals(object.getClass())) {
            return false;
        }

        Project object2 = (Project) object;
        if ((this.id == object2.getId()) && (this.projectName == object2.getProjectName()) && (this.companyName == object2.getCompanyName())) {
            return true;
        }
        return false;
    }

    public int hasCode() {
        int code = 0;
        code = (id + projectName + companyName).hashCode();
        return code;
    }

    @Override
    public String toString() {
        return "Project:\n" +
                "id: " + id +
                "\nProject Name: " + projectName +
                "\nCompany Name: " + companyName + "\n";
    }
}
