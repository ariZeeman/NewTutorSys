
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 341167849
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
    public Assignments(){
        
    }
    
    /**
     *
     * @param newPeer
     * @param newTutor
     */
    public Assignments(Peer newPeer, Tutor newTutor){
        this.peer = newPeer;
        this.tutor = newTutor;
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
    public String toString(){
        String string = this.peer.toString() + "," + this.tutor.toString(); //change to be only names, phones, emails, availability?
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
