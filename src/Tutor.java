/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** Made byAri, edited by Haydn/Ari
 *
 * @author 349173815
 */
public class Tutor implements Comparable {

    private String subject;
    private String firstName, lastName;
    private String phoneNumber;
    private String email;
    private int numPeers = 0;
    private String password;
    private boolean[] availability = new boolean[6];
    private boolean visibility = false;

    /**
     * Default constructor for the tutor object
     *
     */
    public Tutor() {
    }

    /**
     * Constructor for the tutor with Subject, FirstName, LastName, PhoneNumber.
     *
     * @param subject subject for tutor to teach
     * @param phoneNumber phoneNumber of tutor
     * @param firstName tutors first name
     * @param lastName tutors last name
     */
    public Tutor(String subject, String firstName, String lastName, String phoneNumber, String email, int numPeers, String password) {
        this.subject = subject;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.numPeers = numPeers;
        this.password = password;
    }

    /**
     * The order this is written in!!!!!!!!v important!!!!!!
     * Subject,FirstName,LastName,PhoneNumber,Email,numPeers,password,Availability[6],Visibility
     *
     * @return String value to write to files
     */
    @Override
    public String toString() {
        String string;
        string = subject + "," + firstName + "," + lastName + "," + phoneNumber + "," + email + "," + numPeers + "," + password;
        for (int j = 0; j < 6; j++) {
            string += "," + availability[j];
        }
        string += "," + visibility;
        return string;
    }
    
    /** compares this tutors names with the parameter's. 
     * Made by Haydn
     * @param temp tutor to be compared
     * @return 1 if names are equal, 0 if not
     */
    public int compareNames(Tutor temp) {
        if (firstName.equals(temp.getFirstName()) && lastName.equals(temp.getLastName())) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Method which checks if two tutors are the same.
     *
     * @param tutor
     * @return if names, email, password and subject are the same
     */
    public boolean equals(Tutor tutor) {
        return subject.equals(tutor.getSubject()) && firstName.equals(tutor.getFirstName()) && lastName.equals(tutor.getLastName()) && email.equals(tutor.getEmail()) && password.equals(tutor.getPassword());
    }

    /**
     * Method which allows visibility (meaning they can be found by the search
     * program, this is set to true when a teacher signs off on a student).
     *
     * @param b the value of visibility
     */
    public void setVisibility(boolean b) {
        visibility = b;
    }

    /**
     * This method returns whether the tutor is visible to the search algorithm
     * or not.
     *
     * @return whether or not the tutor can be "seen" by the search algorithm
     */
    public boolean getVisibility() {
        return visibility;
    }

    /**
     *
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * This methods allows you to retrieve the entire array of availability.
     *
     * @return the availability
     */
    public boolean[] getAvailability() {
        return availability;
    }

    /**
     * This getter method returns the value of a single index in the
     * availability array.
     *
     * @param i the index value of the boolean array
     * @return availability[i]
     */
    public boolean getAvailability(int i) {
        if (i >= availability.length || i < 0) {
            throw new ArrayIndexOutOfBoundsException("Array bounds exceeded");
        }
        return availability[i];
    }

    /**
     * This getter method returns the value of a single index in the
     * availability array.
     *
     * @param i the index value of the boolean array
     * @param b the boolean value being assigned
     */
    public void setAvailability(int i, boolean b) {
        if (i >= availability.length || i < 0) {
            throw new ArrayIndexOutOfBoundsException("Array bounds exceeded");
        }
        availability[i] = b;
    }

    /**
     * This setter allows you to create an entire 2d boolean array from one that
     * already exists.
     *
     * @param availability the entire boolean value being written
     */
    public void setAvailability(boolean[] availability) {
        this.availability = availability;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * When a
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Tutor b = (Tutor) o;
        return this.subject.compareTo(b.subject);
    }

    /**
     * @return the numPeers
     */
    public int getNumPeers() {
        return numPeers;
    }

    /**
     * @param numPeers the numPeers to set
     */
    public void setNumPeers(int numPeers) {
        this.numPeers = numPeers;
    }

}
