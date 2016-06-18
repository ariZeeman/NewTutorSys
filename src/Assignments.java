
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * entirely done by Haydn
 */
public class Assignments {

    private Tutor tutor = new Tutor();
    private Peer peer = new Peer();
    private Teacher teacher = new Teacher();
    private boolean[] availability = new boolean[6];
    private boolean matched = false;

    /**
     *
     */
    public Assignments() {

    }

    /**
     *
     * @param newPeer
     * @param newTutor
     */
    public Assignments(Peer newPeer, Tutor newTutor) {
        this.peer = newPeer;
        this.tutor = newTutor;
    }

    /**
     *
     * @param pFirst first name of peer
     * @param pPhone phone number of peer
     * @param pLast last name of peer
     * @param tFirst of tutor
     * @param pEmail email of peer
     * @param tEmail email of tutor
     * @param tPhone phone number of tutor
     * @param tLast last name of tutor
     * @param available array of availability
     */
    public Assignments(String pFirst, String pLast, String pPhone, String pEmail, String tFirst, String tLast, String tPhone, String tEmail) {
        peer.setFirst(pFirst);
        peer.setLast(pLast);
        peer.setPhoneNumber(pPhone);
        peer.setEmail(pEmail);
        tutor.setFirstName(tFirst);
        tutor.setLastName(tLast);
        tutor.setPhoneNumber(tPhone);
        tutor.setEmail(tEmail);
    }

    public void setAvailabilityArray(boolean[] available) {
        for (int i = 0; i < available.length; i++) {
            availability[i] = available[i];
        }
    }

    /**
     * @return the tutor
     */
    public Tutor getTutor() {
        return tutor;
    }

    /**
     * @param tutor the tutor to set
     */
    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    /**
     * @return the peer
     */
    public Peer getPeer() {
        return peer;
    }

    /**
     * @param peer the peer to set
     */
    public void setPeer(Peer peer) {
        this.peer = peer;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String string = this.peer.getFirst() + "," + this.peer.getLast() + "," + this.peer.getPhoneNumber() + "," + this.peer.getEmail() + ","; //prints peer values
        string += this.tutor.getFirstName() + "," + this.tutor.getLastName() + "," + this.tutor.getPhoneNumber() + "," + this.tutor.getEmail(); //prints tutor values
        for (int j = 0; j < 6; j++) {
            string += "," + availability[j];
        }
        return string;
    }

    /**
     * @return the availability
     */
    public boolean[] getAvailability() {
        return availability;
    }

    /**
     * @param index index of array to set
     * @param b boolean value
     */
    public void setAvailability(int index, boolean b) {
        availability[index] = b;
    }

    /**
     * @return the matched
     */
    public boolean isMatched() {
        return matched;
    }

    /**
     * @param matched the matched to set
     */
    public void setMatched(boolean matched) {
        this.matched = matched;
    }

}
