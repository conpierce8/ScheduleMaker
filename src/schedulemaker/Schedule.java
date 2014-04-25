/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package schedulemaker;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author Connor
 */
public class Schedule {
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Activity> otherActivities = new ArrayList<>();
    private DecimalFormat format = new DecimalFormat("00");
//    private final int id;
//    private static int count = 0;
    
    private Schedule() {
//        id = count++;
    }
    
    public void addCourse(Course c) {
        courses.add(c);
    }
    
    public void addActivity(Activity a) {
        otherActivities.add(a);
    }
    
    public float earliestClass() {
        float earliest = 24;
        for(Course c : courses) {
            for(Section s : c.getSections()) {
                for(Block b : s.getBlocks()) {
                    earliest = Math.min(earliest, b.startTimeHours+b.startTimeMinutes/60f);
                }
            }
        }
        return earliest;
    }
    
    public float latestClass() {
        float latest = 0;
        for(Course c : courses) {
            for(Section s : c.getSections()) {
                for(Block b : s.getBlocks()) {
                    latest = Math.max(latest, b.endTimeHours+b.endTimeMinutes/60f);
                }
            }
        }
        return latest;
    }
    
    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    public Schedule duplicate() {
        Schedule s = new Schedule();
        for(Course c : courses) {
            s.addCourse(c);
        }
        return s;
    }
    
    public static ArrayList<Schedule> schedule(ArrayList<Course> courses) {
        Schedule s = new Schedule();
        return schedule(s, courses.toArray(new Course[0]), 0);
    }
    
    private static ArrayList<Schedule> schedule(Schedule s, Course[] courses,
            int level) {
        ArrayList<Schedule> scheds = new ArrayList<>();
        for(Section m : courses[level].getSections()) {
            boolean fits = true;
            for(Course c: s.getCourses()) {
                for(Section m2: c.getSections()) {
                    if(m.overlaps(m2)){
                        fits = false;
                    }
                }
            }
            if(fits) {
                Schedule s2 = s.duplicate();
                Course temp = courses[level].duplicateBasicInfo();
                temp.addSection(m);
                s2.addCourse(temp);
                if(level == courses.length - 1) {
                    scheds.add(s2);
                } else {
                    scheds.addAll(schedule(s2, courses, level+1));
                }
            }
        }
        return scheds;
    }
    
    public void createImage(Canvas canvas) {
        double WIDTH = canvas.getWidth();
        double HEIGHT = canvas.getHeight();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        int earliest = (int) Math.floor(this.earliestClass());
        int latest = (int) Math.ceil(this.latestClass());
        int range = latest - earliest;
        if(range <= 0){
            return;
        }
        gc.setFill(Color.LIGHTGRAY.brighter());
        gc.fill();
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        gc.strokeLine(0, 0, 0, HEIGHT);
        for(double d = 50; d <= WIDTH; d += (WIDTH-50)/7) {
            gc.strokeLine(d, 0, d, HEIGHT);
        }
        for(int i = 1; i <= 7; i++) {
            String s = "";
            switch(i){
                case Block.SUNDAY: s = "Su"; break;
                case Block.MONDAY: s = "M"; break;
                case Block.TUESDAY: s = "T"; break;
                case Block.WEDNESDAY: s = "W"; break;
                case Block.THURSDAY: s = "R"; break;
                case Block.FRIDAY: s = "F"; break;
                case Block.SATURDAY: s = "Sa"; break;
            }
            gc.fillText(s, (i-1) * (WIDTH-50)/7 + 55, 15);
        }
        gc.setFont(new javafx.scene.text.Font(9));
        for(int i = earliest; i <= latest; i++) {
            gc.fillText(format.format(i)+":00", 5,(i-earliest)*(HEIGHT-20)/range+32);
        }
        gc.strokeLine(0, 0, WIDTH, 0);
        gc.strokeLine(0, 20, WIDTH, 20);
        for(double d = 20; d <= HEIGHT; d += (HEIGHT-21)/range) {
            gc.strokeLine(0, d, WIDTH, d);
        }
        ArrayList items = new ArrayList();
        items.addAll(courses);
        items.addAll(otherActivities);
        System.out.println("we have "+items.size()+" items");
        int colorsNeeded = items.size();
        if(colorsNeeded % 2 == 0) {
            colorsNeeded ++;
        }
        int colorIdx = 0;
        for(Object o : items) {
            Section times;
            String description;
            if(o instanceof Course) {
                times = ((Course) o).getSections().get(0);
                description = ((Course) o).getDescription() + "\n";
                if(!times.getSectionNumber().equals("")) {
                    description += "Section " + times.getSectionNumber();
                }
            } else {
                times = ((Activity) o).getTimes();
                description = ((Activity) o).getDescription();
            }
            for(Block block : times.getBlocks()) {
                double x1 = 50+ (WIDTH-50)/7 * (block.day-1);
                double startTime = block.startTimeHours+block.startTimeMinutes/60.0;
                double y1 = ((startTime-earliest)/range)*(HEIGHT-20) + 20;
                double endTime = block.endTimeHours+block.endTimeMinutes/60.0;
                double y2 = ((endTime-earliest)/range)*(HEIGHT-20) + 20;
                gc.setFill(Color.WHITE);
                gc.fillRect(x1, y1, (WIDTH-50)/7, y2-y1);
                gc.setFill(getColor(colorIdx, colorsNeeded));
                gc.fillRect(x1, y1, (WIDTH-50)/7, y2-y1);
                gc.strokeRect(x1, y1, (WIDTH-50)/7, y2-y1);
                gc.setFill(Color.BLACK);
                String displayValue = description;
                if(!block.room.equals("")){
                    displayValue += "\n" + block.room;
                }
                if(!block.instructor.equals("")){
                    displayValue += "\n" + block.instructor;
                }
                gc.fillText(displayValue, x1+2, y1+12, (WIDTH-50)/7-4);
            }
            colorIdx++;
        }
    }

    private Color getColor(int colorIdx, int colorsNeeded) {
        if(colorIdx > colorsNeeded - 1) {
            throw new IllegalArgumentException("Index ("+colorIdx+") must be less than length ("+colorsNeeded+")!");
        } else if(colorIdx < 0) {
            throw new IllegalArgumentException("Index ("+colorIdx+") must be greater than 0!");
        }
        double r = 0, g = 0, b = 0;
        if(colorIdx == 0) {
            r = 1;
        } else if(colorIdx == colorsNeeded / 2) {
            g = 1;
        } else if(colorIdx == colorsNeeded - 1) {
            b = 1;
        } else if(colorIdx < colorsNeeded / 2) {
            r = 1 - ((double) colorIdx) / (colorsNeeded / 2);
            g = (colorIdx / (colorsNeeded / 2));
        } else {
            g = 1 - ((double) (colorIdx - colorsNeeded/2)) / (colorsNeeded / 2);
            b = ((double) (colorIdx - colorsNeeded /2)) / (colorsNeeded / 2);
        }
        return new Color(r, g, b, 0.5);
    }
    
}
