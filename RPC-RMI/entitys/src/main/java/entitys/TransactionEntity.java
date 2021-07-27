package entitys;

import java.sql.Date;

public class TransactionEntity {
    private Integer id;
    private Integer number;
    private Double amount;
    private Date date;
    private String description;
    private AccountEntity fk_source_account;
    private AccountEntity fk_destination_account;

    public TransactionEntity() {
    }

    public TransactionEntity(Integer id, Integer number, Double amount, Date date, String description, AccountEntity fk_source_account, AccountEntity fk_destination_account) {
        this.id = id;
        this.number = number;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.fk_source_account = fk_source_account;
        this.fk_destination_account = fk_destination_account;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountEntity getFk_source_account() {
        return fk_source_account;
    }

    public void setFk_source_account(AccountEntity fk_source_account) {
        this.fk_source_account = fk_source_account;
    }

    public AccountEntity getFk_destination_account() {
        return fk_destination_account;
    }

    public void setFk_destination_account(AccountEntity fk_destination_account) {
        this.fk_destination_account = fk_destination_account;
    }
}
