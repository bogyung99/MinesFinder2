package pt.technic.apps.minesfinder;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel Massadas
 */
public class RecordTable implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private transient final int MAX_CHAR = 10;
    
    private String name;

    private long score;
    
    private long Try;

    transient static ArrayList<RecordTableListener> listeners;

    public RecordTable() {	
        name = "Anonymous";
        score = 9999999;
        Try = 000000;
        listeners = new ArrayList<>();
    }

    public long gettrr() {
    	return Try;
    }
    
    public String getName() {
        return name.substring(0, Math.min(MAX_CHAR, name.length()));
    }

    public long getScore() {
        return score;
    }

    public void setRecord(String name, long score, long Try) {
        if (score < this.score) {
        	this.name = name;
            this.score = score;    
            notifyRecordTableUpdated();
        }        
        	this.Try = Try;
        
        	notifyRecordTableUpdated();
    }

    public void addRecordTableListener(RecordTableListener list) {
        if (listeners == null) {
            listeners = new ArrayList<>();
        }
        listeners.add(list);
    }

    public void removeRecordTableListener(RecordTableListener list) {
        if (listeners != null) {
            listeners.remove(list);
        }
    }

    private void notifyRecordTableUpdated() {
        if (listeners != null) {
            for (RecordTableListener list : listeners) {
                list.recordUpdated(this);
            }
        }
    }
}
