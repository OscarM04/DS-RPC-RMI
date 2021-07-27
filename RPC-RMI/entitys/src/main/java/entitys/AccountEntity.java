package entitys;

public class AccountEntity {

    private Integer id;
    private Integer number;
    private Double current_balance;
    private UserEntity fk_user;

    public AccountEntity() {
    }

    public AccountEntity(Integer id, Integer number, Double current_balance, UserEntity fk_user) {
        this.id = id;
        this.number = number;
        this.current_balance = current_balance;
        this.fk_user = fk_user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(Double current_balance) {
        this.current_balance = current_balance;
    }

    public UserEntity getFk_user() {
        return fk_user;
    }

    public void setFk_user(UserEntity fk_user) {
        this.fk_user = fk_user;
    }
}
