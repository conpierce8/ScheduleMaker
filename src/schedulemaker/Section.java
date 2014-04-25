/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package schedulemaker;

import java.util.ArrayList;

/**
 *
 * @author Connor
 */
public class Section {
    private ArrayList<Block> blocks = new ArrayList<>();
    private String number;
    
    public Section(String num) {
        number = num;
    }
    
    public String getSectionNumber() {
        return number;
    }
    
    public void setSectionNumber(String num) {
        number = num;
    }
    
    public void addBlock(Block b) {
        blocks.add(b);
    }
    
    public void removeBlock(Block b) {
        blocks.remove(b);
    }
    
    public ArrayList<Block> getBlocks() {
        return blocks;
    }
    
    public boolean overlaps(Section m) {
        for(Block  b: m.getBlocks()) {
            float otherStart = b.startTimeHours + b.startTimeMinutes/60f;
            float otherEnd = b.endTimeHours + b.endTimeMinutes/60f;
            for(Block b2: blocks) {
                if(b2.day == b.day) {
                    float start = b2.startTimeHours + b2.startTimeMinutes/60f;
                    float end = b2.endTimeHours + b2.endTimeMinutes/60f;
                    if((otherStart < end && otherEnd > start) || 
                            (otherEnd > start && otherStart < end)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public String getTimeString() {
        String s = "";
        for(Block b : blocks) {
            s += b.getTimeString();
        }
        return s;
    }
    
}
