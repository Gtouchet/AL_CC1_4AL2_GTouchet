package esgi.al.cc1.domain.builders;

import esgi.al.cc1.domain.models.Contractor;
import esgi.al.cc1.domain.models.PaymentMethod;
import esgi.al.cc1.domain.valueObjects.Date;
import esgi.al.cc1.domain.valueObjects.Id;
import esgi.al.cc1.domain.valueObjects.Password;

import java.util.Objects;

public class ContractorBuilder implements Builder<Contractor> // todo implements
{
    private final Id id;
    private final String login;
    private Password password;
    private String name;
    private PaymentMethod paymentMethod;
    private boolean isPaymentValidated;
    private final Date creationDate;

    private ContractorBuilder(Id id, String login, Date creationDate)
    {
        this.id = id;
        this.login = login;
        this.creationDate = creationDate;
    }

    @Override
    public Contractor build()
    {
        return Contractor.of(
                this.id,
                this.login,
                Objects.requireNonNull(this.password),
                Objects.requireNonNull(this.name),
                Objects.requireNonNull(this.paymentMethod),
                this.isPaymentValidated,
                this.creationDate
        );
    }

    public static ContractorBuilder init(Id id, String login, Date creationDate)
    {
        return new ContractorBuilder(
                Objects.requireNonNull(id),
                Objects.requireNonNull(login),
                Objects.requireNonNull(creationDate)
        );
    }

    public static ContractorBuilder init(Contractor contractor)
    {
        ContractorBuilder builder = new ContractorBuilder(
                Objects.requireNonNull(contractor.getId()),
                Objects.requireNonNull(contractor.getLogin()),
                Objects.requireNonNull(contractor.getCreationDate())
        );

        builder.password = contractor.getPassword();
        builder.name = contractor.getName();
        builder.paymentMethod = contractor.getPaymentMethod();
        builder.isPaymentValidated = contractor.isPaymentValidated();

        return builder;
    }

    public ContractorBuilder setPassword(Password password)
    {
        ContractorBuilder builder = this;
        builder.password = password;
        return builder;
    }

    public ContractorBuilder setName(String name)
    {
        ContractorBuilder builder = this;
        builder.name = name;
        return builder;
    }

    public ContractorBuilder setPaymentMethod(PaymentMethod paymentMethod)
    {
        ContractorBuilder builder = this;
        builder.paymentMethod = paymentMethod;
        return builder;
    }

    public ContractorBuilder setIsPaymentValidated(boolean isPaymentValidated)
    {
        ContractorBuilder builder = this;
        builder.isPaymentValidated = isPaymentValidated;
        return builder;
    }
}
