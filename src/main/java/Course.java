import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "currency_from")
    @Enumerated(value = EnumType.STRING)
    private Currency from;

    @Column(name = "currency_to")
    @Enumerated(value = EnumType.STRING)
    private Currency to;

    @Column(name = "coefficient")
    private double coefficient;

    public Course() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Currency getFrom() {
        return from;
    }

    public void setFrom(Currency from) {
        this.from = from;
    }

    public Currency getTo() {
        return to;
    }

    public void setTo(Currency to) {
        this.to = to;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", coefficient=" + coefficient +
                '}';
    }
}
