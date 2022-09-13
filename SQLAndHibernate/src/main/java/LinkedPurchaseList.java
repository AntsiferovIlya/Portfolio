import javax.persistence.*;

@Entity
@Table(name = "linkedpurchaselist")
public class LinkedPurchaseList {

    @EmbeddedId
    private LPLKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course courseID;

    public LPLKey getId() {
        return id;
    }

    public void setId(LPLKey id) {
        this.id = id;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Course getCourseID() {
        return courseID;
    }

    public void setCourseID(Course courseID) {
        this.courseID = courseID;
    }
}
