
import java.awt.CardLayout;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ari Zeeman, Haydn Brown, Len Wu
 */
public class Utility {

    /**
     * Construct a Utility object (in case you need one)
     */
    public Utility() {
    }

    /**
     * Bubble/Sinking sort that is for the StockInfo objects.
     *
     * @param array the array to be sorted
     * @return the sorted array
     */
    public static Tutor[] bubbleSort(Tutor[] array) {
        boolean swapped;
        do {
            //nothing has been swapped, will stay that way until something is
            swapped = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i].compareTo(array[i - 1]) < 0) {
                    //empty variable for a swap
                    Tutor t = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = t;
                    //something has been swapped
                    swapped = true;
                }
            }
            //run until nothing has to be swapped
        } while (swapped);
        return array;
    }

    /**
     * The method which creates a tutor from a file.
     *
     * @param s the scanner that is reading from the file
     * @return the tutor that has been created from the file.
     */
    public Tutor createTutorFromFile(Scanner s) {
        String[] array = null; //array of info for peer
        array = s.nextLine().split(",");
        Tutor temp = new Tutor(array[0], array[1], array[2], array[3], array[4], Integer.parseInt(array[5]), array[6]);

        for (int i = 7; i < 13; i++) { //prints in the availabilities from the file line
            temp.setAvailability(i - 7, Boolean.parseBoolean(array[i]));
        }

        if (Boolean.parseBoolean(array[13]) == true) {
            return temp;
        } else {
            return null; //if the tutor hasnt been approved, do not return them.
        }

    }

    /**
     *
     * @param s scanner using file of peers
     * @return
     */
    public Peer createPeerFromFile(Scanner s) {
        String[] array = null; //array of info for peer
        array = s.nextLine().split(",");
        Peer temp = new Peer(array[0], array[1], array[2], array[3], array[4], array[5]);
        for (int i = 6; i < 13; i++) { //prints in the availabilities from the file line
            temp.setAvailability(i - 6, Boolean.parseBoolean(array[i]));
        }
        temp.setTaken();

        return temp;
    }

    /**
     *
     * @param s
     * @param firstName
     * @param pass
     * @return
     */
    public Teacher createTeacherFromFile(Scanner s, String firstName, String pass) {
        //array of info for peer
        while (s.hasNext()) {
            String temo = s.nextLine();
            System.out.println(temo);
            String[] array = temo.split(",");
            Teacher temp = new Teacher(array[0], array[1], array[2], array[3], array[4]);
            if ((temp.getFirstName().equals(firstName)) && (temp.getPassword().equals(pass))) {
                return temp;
            }
        }
        return null;
    }

    /**
     *
     * @param s
     * @return
     */
    public Teacher createTeacherFromFile(Scanner s) {
        String[] array = null; //array of info for teacher
        array = s.nextLine().split(",");
        Teacher temp = new Teacher(array[0], array[1], array[2], array[3], array[4]);
        return temp;

    }

    /**
     * Method which prints out an object to a file
     *
     * @param o the object that is being created
     * @param pw PrintWriter which prints the object to a file
     */
    public void addObjectToFile(Object o, PrintWriter pw) {
        pw.println(o.toString());
    }

    /**
     *
     * @param peer peer to be matched
     * @param s scanner of tutors
     * @throws IOException
     */
    public void createAssignment(Peer peer, Scanner s) throws IOException {
        Assignments assignment = null; //new assignment
        ArrayList ar = new ArrayList(); //arraylist of tutors
        ArrayList bools = new ArrayList(); //arraylist of the availabilities for each assignment
        File assignments = new File("Assignment.txt"); //file of assignments
        PrintWriter pw = new PrintWriter(new FileWriter(assignments, false)); //writes assignments to file
        Tutor temp = new Tutor(); //temporary stores tutor for comparison to peer's subject
        while (s.hasNext()) {
            Assignments temporary = null; //
            temp = createTutorFromFile(s);
            if (peer.getSubject().equals(temp.getSubject())) { //if the peer is looking for the subject the tutor teaches
                for (int i = 0; i < temporary.getAvailability().length; i++) { //goes through each index, checking for common true
                    if ((peer.getAvailability(i) && temp.getAvailability(i)) == true) { //if they both have true availability
                        if (temporary.isMatched() == false) { //confirms the match, if not already done
                            temporary.setMatched(true);
                        }
                        temporary.setAvailability(i, true); //shows when they're both free
                    }else{
                        temporary.setAvailability(i, false); //shows they're not both free
                    }
                }
                if (temporary.isMatched() == true) {
                    ar.add(temp); //adds temp Tutor to ar to be sorted, b/c it meets peers requirements
                    bools.add(temporary.getAvailability()); //adds the array of common availabilities, indices correspond between AR's
                }
            }

        }
        Tutor[] tutorArray = (Tutor[]) ar.toArray();
        Boolean[][] availables = (Boolean[][])bools.toArray();
        int index = fewestPeers(tutorArray);
        //Tutor tutor = fewestPeers(tutorArray); //can get rid o, probably

        assignment = new Assignments(peer, tutorArray[index]); //creates new assignment
        for (int i = 0; i < availables[index].length; i++){
            assignment.setAvailability(i, availables[index][i]); //sets new assignment availabilities to be the same as the second dimension 
        }
        addObjectToFile(assignment, pw); //prints the assignment to the file
    }

    /**
     *
     * @param peer peer in an assignment
     * @param s scanners of assignments
     * @return
     */
    public Assignments returnPeerMatch(Peer peer, Scanner s) {
        ArrayList list = new ArrayList();
        Assignments assignment;
        while (s.hasNext()) {
            String[] array = s.nextLine().split(",");
            //this returns peers: subject, fname, lname, phone#, password, email,
            Peer tempPeer = new Peer(array[0], array[1], array[2], array[3], array[4], array[5]);
            for (int i = 6; i < 12; i++) {
                tempPeer.setAvailability(i - 6, Boolean.parseBoolean(array[i]));
            }
            //gets ????????
            Tutor tempTutor = new Tutor(array[12], array[13], array[14], array[15], array[16], Integer.parseInt(array[17]), array[18]); //skips past the availability portion of peer
            for (int i = 19; i < 25; i++) {
                tempTutor.setAvailability(i - 19, Boolean.parseBoolean(array[i]));
            }
            assignment = new Assignments(tempPeer, tempTutor);
            if (assignment.getPeer().equals(peer)) {
//                for (int i = 0; i < assignment.getAvailability().length; i++){
//                    if (){
//                        
//                    }
//                }
                list.add(assignment);
            }
        }
        return null;
    }

    /**
     * Method which returns the tutor with the lowest amount of people
     * registered with them.
     *
     * @param tutors
     * @return
     */
    public int fewestPeers(Tutor[] tutors) {
        Tutor lowest = tutors[0];
        int temp = 0; //stores index where lowest tutor is found
        for (int i = 1; i < tutors.length; i++) {
            if (tutors[i].getNumPeers() < lowest.getNumPeers()) {
                lowest = tutors[i];
                temp = i; //saves index of lowest numPeers
            }
        }
        return temp;
    }

    /**
     * This is the method which creates an array of tutors that require
     * verification.
     *
     * @param teacher the teacher who is looking for students to verify
     * @return an array of students that require verification from this teacher
     */
    public Tutor[] needVerification(Teacher teacher) { //param = teacher who tutors have asked for verification from
        //create a new array of tutors that contains every tutor
        Tutor[] tutors = findAllTutors();
        //new arraylist since we aren't sure how many tutors we are going to need
        ArrayList<Tutor> list = new ArrayList();
        for (Tutor tutor : tutors) {
            //if the tutor is NOT visible (not verified) AND their subject is the same as the teacher's subject
            if (!tutor.getVisibility() && tutor.getSubject().equals(teacher.getSubject())) {
                list.add(tutor);
            }
        }
        //pump the list into the new needsVerification array
        Tutor[] needsVerification = new Tutor[list.size()];
        needsVerification = list.toArray(tutors);
        return null;
    }

    /**
     * This is the method which generates an array of tutors to be used by the
     * application, which uses the individual createTutorFromFile method.
     *
     * @return an array of tutors
     */
    public Tutor[] generateTutors() {
        try {
            //open the tutor file and put a scanner on it
            File f = new File("Tutor.txt");
            Scanner s = new Scanner(f);
            ArrayList<Tutor> list = new ArrayList();
            Tutor[] me;
            int counter = 0;
            //as long as there is a new line in the file, it will cycle through it
            while (s.hasNext()) {
                //add the createTutorFromFile result
                list.add(createTutorFromFile(s));
                counter++;
            }
            me = new Tutor[counter];
            me = list.toArray(me);
            s.close();
            return me;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Method which returns an array of teachers, will probably be called to
     * fill out info in the drop menu.
     *
     * @return an array of teachers read from the teacher file
     */
    public Teacher[] generateTeachers() {
        try {
            //teacher file
            File f = new File("Teacher.txt");
            Scanner s = new Scanner(f);
            ArrayList<Teacher> list = new ArrayList();
            int counter = 0;
            Teacher[] teachers;
            while (s.hasNext()) {
                list.add(createTeacherFromFile(s));
                counter++;
            }
            teachers = new Teacher[counter];
            teachers = list.toArray(teachers);
            s.close();
            return teachers;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Method which returns an array of Peer objects, same as the other
     * generateObject() methods.
     *
     * @return an array of peers from the peer file.
     */
    public Peer[] generatePeer() {
        try {
            File f = new File("Peer.txt");
            Scanner s = new Scanner(f);
            ArrayList<Peer> list = new ArrayList();
            int counter = 0;
            Peer[] peers;
            while (s.hasNext()) {
                list.add(createPeerFromFile(s));
                counter++;
            }
            peers = new Peer[counter];
            peers = list.toArray(peers);
            s.close();
            return peers;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     *
     * @param t the tutor being committed to a file
     */
    public void printTutorToFile(Tutor t) {
        try {
            PrintWriter pw;
            File f = new File("Tutor.txt");
            pw = new PrintWriter(new FileWriter(f, true));
            addObjectToFile(t, pw);
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());

        }
    }

    /**
     *
     * @param t the teacher being committed to a file
     */
    public void printTeacherToFile(Teacher t) {
        try {
            PrintWriter pw;
            File f = new File("Teacher.txt");
            pw = new PrintWriter(new FileWriter(f, true));
            addObjectToFile(t, pw);
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param p the peer being committed to a file
     */
    public void printPeerToFile(Peer p) {

        try {
            PrintWriter pw;
            File f = new File("Peer.txt");
            pw = new PrintWriter(new FileWriter(f, true));
            addObjectToFile(p, pw);
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param firstName first name used to log in
     * @param password password used to log in
     * @return array of the object of the person signing in, multiple returned
     * if they have multiple objects for various subjects
     */
    public Peer[] peerLoginCheck(String firstName, String password) {
        ArrayList temp = new ArrayList();
        Peer[] peers = generatePeer(); //all the peers in the peer file
        for (int i = 0; i < peers.length; i++) {
            if (peers[i].getFirst().equals(firstName) && peers[i].getPassword().equals(password)) { //if the firstname and password match (if its that person, regardless of subject)
                temp.add(peers[i]);
            }
        }
        Peer[] checked = (Peer[]) temp.toArray();
        return checked; //all the occurances of the person
    }

    /**
     *
     * @param firstName first name used to log in
     * @param password password used to log in
     * @return array of the object of the person signing in, multiple returned
     * if they have multiple objects for various subjects
     */
    public Tutor[] tutorLoginCheck(String firstName, String password) {
        ArrayList<Tutor> temp = new ArrayList();
        Tutor[] tutors = findAllTutors(); //all the tutors in the tutor file
        int counter = 0;
        for (Tutor tutor : tutors) {
            if (tutor.getFirstName().equals(firstName) && tutor.getPassword().equals(password)) {
                //if the firstname and password match (if its that person, regardless of subject)
                temp.add(tutor);
                counter++;
            }
        }
        Tutor[] checked = new Tutor[counter];
        checked = temp.toArray(checked);
        if (checked == null) {
            System.out.println("No login info retrieved");
            return null;
        }
        return checked; //all the occurances of the person
    }

    /**
     *
     * @param firstName first name used to log in
     * @param password password used to log in
     * @return array of the object of the person signing in, multiple returned
     * if they have multiple objects for various subjects
     */
    public Teacher[] teacherLoginCheck(String firstName, String password) {
        ArrayList<Teacher> temp = new ArrayList();
        int counter = 0;
        Teacher[] teachers = generateTeachers(); //all the teachers in the teachers file
        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i].getFirstName().equals(firstName) && teachers[i].getPassword().equals(password)) { //if the firstname and password match (if its that person, regardless of subject)
                temp.add(teachers[i]);
                counter++;
            }
        }
        Teacher[] checked = new Teacher[counter];
        temp.toArray(checked);
        //
        return checked; //all the occurances of the person
    }

    /**
     * Method which finds every last tutor with the purpose of updating a tutor
     * to verify it.
     *
     * @return the array of tutors
     */
    public Tutor[] findAllTutors() {
        try {
            File f = new File("Tutor.txt");
            Scanner s = new Scanner(f);
            ArrayList<Tutor> list = new ArrayList();
            Tutor[] me;
            int counter = 0;
            //as long as there is a new line in the file, it will cycle through it
            while (s.hasNext()) {
                //add the createTutorFromFile result
                list.add(getEveryTutor(s));
                counter++;
            }
            me = new Tutor[counter];
            me = list.toArray(me);
            s.close();
            return me;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * This method sets the visibility of a Tutor to true so they can be seen by
     * the matches. It generates an array of *every* tutor which it then
     * searches through to verify the tutor before re-writing the entire list.
     *
     * @param tutor being verified
     */
    public void verify(Tutor tutor) {
        PrintWriter pw = null;
        try {
            Tutor[] array = findAllTutors();
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(tutor)) {
                    verify(tutor);
                }
            }
            File f = new File("Tutor.txt");
            pw = new PrintWriter(f);
            tutor.setVisibility(true);
            //re-write the file (hence no new FileWriter as a parameter)
            for (int i = 0; i < array.length; i++) {
                addObjectToFile(array[i], pw);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } finally {
            pw.close();
        }
    }

    /**
     * Re-hashed version of create from file which just gets all tutors
     *
     * @param s
     * @return
     */
    public Tutor getEveryTutor(Scanner s) {
        String[] array = null; //array of info for peer
        array = s.nextLine().split(",");
        Tutor temp = new Tutor(array[0], array[1], array[2], array[3], array[4], Integer.parseInt(array[5]), array[6]);
        for (int i = 7; i < 13; i++) { //prints in the availabilities from the file line
            temp.setAvailability(i - 7, Boolean.parseBoolean(array[i]));
        }
        temp.setVisibility(Boolean.parseBoolean(array[13]));
        return temp;
    }

    /**
     * Matches tutors to peers if any of their availability is the same and they
     * have the same subject. Returns an array of tutors who match.
     *
     * @param p
     */
    public Tutor[] matchTutors(Peer p) {
        Tutor[] firstArray = generateTutors();
        //arraylist of tutors with the same availablity as the peer and same
        ArrayList<Tutor> list = new ArrayList();
        ArrayList<Tutor> finalList = new ArrayList();
        for (int i = 0; i < firstArray.length; i++) {
            if (firstArray[i].getSubject().equals(p.getSubject())) {
                boolean sMatch = false;
                for (int j = 0; j < p.getAvailability().length; j++) {
                    if (firstArray[i].getAvailability(j) == true && p.getAvailability(j) == true) {
                        sMatch = true;
                    }
                }
                if (sMatch) {
                    finalList.add(firstArray[i]);
                }
            }
        }
        Tutor[] finalArray = new Tutor[0];
        finalList.toArray(finalArray);
        return finalArray;
    }

    public Peer generatePeerFromLogin(Scanner s, String firstname, String password) {
        while (s.hasNext()) {
            String temo = s.nextLine();
            System.out.println(temo);
            String[] array = temo.split(",");
            Peer temp = new Peer();
            temp.setFirst(array[1]);
            temp.setPassword(array[4]);
            if ((temp.getFirst().equals(firstname)) && (temp.getPassword().equals(password))) {
                //they are the same, initialize the rest of the Peer
                temp.setSubject(array[0]);
                temp.setLast(array[2]);
                temp.setPhoneNumber(array[3]);
                
                return temp;
            }
        }
        return null;
    }
}
