import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @EmbeddedId
    private SubscriptionKey id;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student studentId;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public SubscriptionKey getId() {
        return id;
    }

    public void setId(SubscriptionKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return studentId;
    }

    public void setStudent(Student studentId) {
        this.studentId = studentId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course courseId) {
        this.course = courseId;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public String toString() {
        return  "Таблица Subscription: " +
                "\n\tId студента: " + getStudent().getId() +
                "\n\tId курса: " + getCourse().getId() +
                "\n\tДата подписки: " + getSubscriptionDate() + "\n";
    }

}
