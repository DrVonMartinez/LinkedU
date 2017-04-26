package Controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.StudentBean;

@ManagedBean
@SessionScoped
public class StudentController 
{
	private StudentBean student;

    public StudentController() 
	{
        student = new StudentBean();
    }

    public StudentBean getStudent() 
	{
        return student;
    }

    public void setStudent(StudentBean student) 
	{
        this.student = student;
    }
}