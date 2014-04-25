/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package schedulemaker;

/**
 *
 * @author Connor
 */
public class Activity {
    private Section times;
    private String description;
    
    public Section getTimes() {
        return times;
    }
    
    public void setTimes(Section times) {
        this.times = times;
    }
    
    public void setDescription(String s) {
        description = s;
    }
    
    public String getDescription() {
        return description;
    }
    
}
