import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "card_to")
    private String cardTo;

    @Column(name = "transaction_sum")
    private long transactionSum;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Transaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardTo() {
        return cardTo;
    }

    public void setCardTo(String cardTo) {
        this.cardTo = cardTo;
    }

    public long getTransactionSum() {
        return transactionSum;
    }

    public void setTransactionSum(long transactionSum) {
        this.transactionSum = transactionSum;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", cardTo='" + cardTo + '\'' +
                ", transactionSum=" + transactionSum +
                ", currency=" + currency +
                ", user=" + user +
                '}';
    }
}
