/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package schedulemaker;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Connor
 */
public class Course {
    private ArrayList<Section> sections = new ArrayList<>();
    private String description;
    private String instructor;
    private String courseNumber;
//    private String room;
    
    public void setCourseNumber(String s) {
        courseNumber = s;
    }
    
    public String getCourseNumber() {
        return courseNumber;
    }
    
    public void setDescription(String s) {
        description = s;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setInstructor(String s) {
        instructor = s;
    }
    
    public String getInstructor() {
        return instructor;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Course) {
            return ((Course) obj).description.equals(description);
        } else {
            return false;
        }
    }
//    public void setRoom(String s) {
//        room = s;
//    }
//    
//    public String getRoom() {
//        return room;
//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.description);
        return hash;
    }
    
    public void addSection(Section section) {
        sections.add(section);
    }
    
    public ArrayList<Section> getSections() {
        return sections;
    }
    
    public void addMeetingTime(String section, Block b) {
        for(Section s : sections) {
            if(s.getSectionNumber().equals(section)) {
                s.addBlock(b);
                return;
            }
        }
        Section s = new Section(section);
        s.addBlock(b);
        sections.add(s);
    }
    
    public Course duplicateBasicInfo() {
        Course c = new Course();
        c.description = description;
//        c.instructor = instructor;
//        c.room = room;
        return c;
    }
    
}
