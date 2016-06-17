
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
    
    public Assignments(){
        
    }
    
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
    
    @Override
    public String toString(){
        return this.peer.toString() + "," + this.tutor.toString();
    }
    
}
