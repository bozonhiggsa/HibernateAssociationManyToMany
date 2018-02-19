package com.hibernateApp.hibernate.association.manyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entry point
 * @author Ihor Savchenko
 * @version 1.0
 */
public class DeveloperRunner {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        DeveloperRunner developerRunner = new DeveloperRunner();

        System.out.println("Creating the set of projects.");
        HashSet<Project> projects1 = new HashSet<>();
        HashSet<Project> projects2 = new HashSet<>();
        Project project = new Project("Tutorial", "hibernateApp.com");
        projects1.add(project);
        projects2.add(new Project("Lib", "Soft"));
        projects2.add(project);

        System.out.println("Adding developer's records to the DB");

        int developerId1 = developerRunner.addDeveloper("Newcomer", "Developer", "Java Developer", 2, projects1);
        int developerId2 = developerRunner.addDeveloper("Peter", "UI", "UI Developer", 4, projects2);

        System.out.println("List of developers");
        developerRunner.listDevelopers();

        System.out.println("===================================");
        System.out.println("Updating Newcomer");
        developerRunner.updateDeveloper(developerId1, 3);

        System.out.println("Final list of developers");

        developerRunner.listDevelopers();
        System.out.println("===================================");
        sessionFactory.close();
    }

    public int addDeveloper(String firstName, String lastName, String specialty, int experience, Set<Project> projects) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Developer developer = new Developer(firstName, lastName, specialty, experience);
        developer.setProjects(projects);
        int developerId = (int)session.save(developer);
        transaction.commit();
        session.close();
        return developerId;
    }

    public void listDevelopers() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List<Developer> developers = session.createQuery("FROM Developer").list();
        for (Developer developer : developers) {
            System.out.println(developer);
            Set<Project> projects = developer.getProjects();
            for (Project project : projects) {
                System.out.println(project);
            }
            System.out.println("\n================\n");
        }
        session.close();
    }

    public void updateDeveloper(int developerId, int experience) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Developer developer = (Developer) session.get(Developer.class, developerId);
        developer.setExperience(experience);
        session.update(developer);
        transaction.commit();
        session.close();
    }

    public void removeDeveloper(int developerId) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Developer developer = (Developer) session.get(Developer.class, developerId);
        session.delete(developer);
        transaction.commit();
        session.close();
    }
}
