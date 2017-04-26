package Controller;
import DAO.*;
import Model.*;
import Controller.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author it353s723
 */
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
