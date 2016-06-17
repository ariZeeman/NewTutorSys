/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 341167849
 */
public class Peer {

    private String subject; //subject to be taught
    private String phoneNumber;
    private String email;
    private String password;
    //private String oneOnOne;
    private Boolean[] availability = new Boolean[6];
    private String firstName;
    private String lastName;
    private Boolean taken; //represents whether the student has a tutor

    public Peer() {
    }

    public Peer(String subject, String firstName, String lastName, String phoneNumber, String password, String email) {
        this.subject = subject;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
    }

    /**
     *
     * @return2
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     *
     * @param newSubject
     */
    public void setSubject(String newSubject) {
        this.subject = newSubject;
    }

    /**
     *
     * @return
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     *
     * @param newNumber
     */
    public void setPhoneNumber(String newNumber) {
        this.phoneNumber = newNumber;
    }

    /**
     *
     * @return
     */
    public String getFirst() {
        return this.firstName;
    }

    /**
     *
     * @param newName
     */
    public void setFirst(String newName) {
        this.firstName = newName;
    }

    /**
     *
     * @return
     */
    public String getLast() {
        return this.lastName;
    }

    /**
     *
     * @param newName
     */
    public void setLast(String newName) {
        this.lastName = newName;
    }

    /**
     * This method returns a value at [index] from the boolean array of
     * availability.
     *
     * @param index
     * @return the availability
     */
    public Boolean getAvailability(int index) {
        return availability[index];
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
     * @return the email of student
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        String toReturn = this.subject + "," + this.firstName + "," + this.lastName + "," + this.phoneNumber + "," + this.password + "," + this.email;
        for (int y = 0; y < availability.length; y++) {
            toReturn += "," + availability[y];
        }
        return toReturn;
    }

}
