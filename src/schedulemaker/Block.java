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
public class Block {
    public static final short SUNDAY = 1;
    public static final short MONDAY = 2;
    public static final short TUESDAY = 3;
    public static final short WEDNESDAY = 4;
    public static final short THURSDAY = 5;
    public static final short FRIDAY = 6;
    public static final short SATURDAY = 7;
    
    public final short day;
    public final short startTimeHours;
    public final short startTimeMinutes;
    public final short endTimeHours;
    public final short endTimeMinutes;
    public final String instructor;
    public final String room;
    
    private java.text.NumberFormat f = new java.text.DecimalFormat("00");
    
    public Block(short day, short startHours, short startMinutes,
            short endHours, short endMinutes, String instructor, String room) {
        this.day = day;
        startTimeHours = startHours;
        startTimeMinutes = startMinutes;
        endTimeHours = endHours;
        endTimeMinutes = endMinutes;
        this.instructor = instructor;
        this.room = room;
    }
    
    public Block(short day, float start, float end, String instructor, String room) {
        startTimeHours = (short) (start);
        startTimeMinutes = (short) ((start % 1) * 60);
        endTimeHours = (short) (end);
        endTimeMinutes = (short) ((end % 1) * 60);
        this.day = day;
        this.instructor = instructor;
        this.room = room;
    }
    
    public String getTimeString() {
        String s = "";
        switch(day) {
            case 1: s = "Su"; break;
            case 2: s = "M"; break;
            case 3: s = "T"; break;
            case 4: s = "W"; break;
            case 5: s = "R"; break;
            case 6: s = "F"; break;
            case 7: s = "Sa"; break;
        }
        s += " "+f.format(startTimeHours)+":"+f.format(startTimeMinutes)+"-";
        s += " "+f.format(endTimeHours)+":"+f.format(endTimeMinutes);
        return s;
    }
    
}
