/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 349173815
 */
public class Teacher {

    private String email;
    private String lastName;
    private String firstName;
    private String password;
    private String subject;

    /**
     * Constructor for the Teacher class/object, is subject to change as we
     * progress through the project and determine what needs to stay or go.
     *
     * @param email the email the teacher is registering with
     * @param last the last name of the teacher
     * @param first the first name of the teacher
     * @param password
     * @param subject the subject that the teacher is registering for (new
     * teacher for each subject, ex RD that teachers computer science would be
     * different from one that teaches math)
     */
    public Teacher(String first, String last, String email, String password, String subject) {
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        this.password = password;
        this.subject = subject;
    }

    /**
     *
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Teacher with only a first and last name (for the purpose of seeing if we
     * can display them from a file).
     *
     * @param firstName
     * @param lastName
     */
    public Teacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        email = null;
    }

    /**
     * Default empty constructor for a teacher
     */
    public Teacher() {
    }

    /**
     * Method which retrieves the email of a teacher.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the Last name of the teacher
     *
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * The method which allows the First name to be retrieved
     *
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * The method that is used to set an email address for a teacher.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The method which sets the last name for a teacher
     *
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * The method which sets the first name for a teacher
     *
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method prints out a teacher's name as a string (for use in a
     * drop-down menu)
     *
     * @return the first name, last name, email, password and subject (comma
     * delimited) ex. : Mike, R-D, mikeR-D@example.ca, computerScienceIsCool123,
     * Computer Science.
     */
    @Override
    public String toString() {
        return firstName + "," + lastName + "," + email + "," + password + "," + subject;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
