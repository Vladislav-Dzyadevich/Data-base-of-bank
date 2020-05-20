import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "card")
    private String card;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Column(name = "sum")
    private double sum;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Account() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", card='" + card + '\'' +
                ", currency=" + currency +
                ", sum=" + sum +
                ", user=" + user +
                '}';
    }
}
