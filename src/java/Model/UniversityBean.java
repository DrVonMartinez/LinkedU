
package Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "University")
@SessionScoped
public class UniversityBean 
{
    private int id;
    private String universityName;
    private String USState;
    private String picture;
    private String bio;
    private double idealGPA;
    private int idealACT;
    private int idealSAT;
    private String websiteLink;
    private String applicationLink;
    private boolean highlighted;
    private String notableMajors;
    private int timeSubscribed;
    
    public UniversityBean(){
        
    }
    
    public UniversityBean(int id, String universityName, String USState, String picture, String bio, double idealGPA, int idealACT, int idealSAT, String websiteLink, String applicationLink, boolean highlighted, String notableMajors, int timeSubscribed)
    {
        this.id = id;
        this.universityName = universityName;
        this.USState = USState;
        this.picture = picture;
        this.bio = bio;
        this.idealGPA = idealGPA;
        this.idealACT = idealACT;
        this.idealSAT = idealSAT;
        this.websiteLink = websiteLink;
        this.applicationLink = applicationLink;
        this.highlighted = highlighted;
        this.notableMajors = notableMajors;
        this.timeSubscribed = timeSubscribed;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the universityName
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * @param universityName the universityName to set
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    /**
     * @return the USState
     */
    public String getUSState() {
        return USState;
    }

    /**
     * @param USState the USState to set
     */
    public void setUSState(String USState) {
        this.USState = USState;
    }

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @return the bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio the bio to set
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * @return the idealGPA
     */
    public double getIdealGPA() {
        return idealGPA;
    }

    /**
     * @param idealGPA the idealGPA to set
     */
    public void setIdealGPA(double idealGPA) {
        this.idealGPA = idealGPA;
    }

    /**
     * @return the idealACT
     */
    public int getIdealACT() {
        return idealACT;
    }

    /**
     * @param idealACT the idealACT to set
     */
    public void setIdealACT(int idealACT) {
        this.idealACT = idealACT;
    }

    /**
     * @return the idealSAT
     */
    public int getIdealSAT() {
        return idealSAT;
    }

    /**
     * @param idealSAT the idealSAT to set
     */
    public void setIdealSAT(int idealSAT) {
        this.idealSAT = idealSAT;
    }

    /**
     * @return the websiteLink
     */
    public String getWebsiteLink() {
        return websiteLink;
    }

    /**
     * @param websiteLink the websiteLink to set
     */
    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    /**
     * @return the applicationLink
     */
    public String getApplicationLink() {
        return applicationLink;
    }

    /**
     * @param applicationLink the applicationLink to set
     */
    public void setApplicationLink(String applicationLink) {
        this.applicationLink = applicationLink;
    }

    /**
     * @return the highlighted
     */
    public boolean isHighlighted() {
        return highlighted;
    }

    /**
     * @param highlighted the highlighted to set
     */
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    /**
     * @return the notableMajors
     */
    public String getNotableMajors() {
        return notableMajors;
    }

    /**
     * @param notableMajors the notableMajors to set
     */
    public void setNotableMajors(String notableMajors) {
        this.notableMajors = notableMajors;
    }

    /**
     * @return the timeSubscribed
     */
    public int getTimeSubscribed() {
        return timeSubscribed;
    }

    /**
     * @param timeSubscribed the timeSubscribed to set
     */
    public void setTimeSubscribed(int timeSubscribed) {
        this.timeSubscribed = timeSubscribed;
    }
 
    public String formatMajors()
    {
        String list ="";
        String[] brokenMajors = notableMajors.split(";");
        for(String i: brokenMajors)
        {
            list+=i;
        }
        return list;
    }
    
    public String formatAcademics()
    {
        String academics="";
        academics += "GPA: " + idealGPA + "<br/>ACT: " + idealACT + "<br/>SAT" + idealSAT;
        return academics;
    }
}
