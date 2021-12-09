package esgi.al.gtouchet.cc2.domain.builders;

import esgi.al.gtouchet.cc2.domain.models.Contractor;
import esgi.al.gtouchet.cc2.domain.models.PaymentMethod;
import esgi.al.gtouchet.cc2.domain.valueObjects.Date;
import esgi.al.gtouchet.cc2.domain.valueObjects.Id;
import esgi.al.gtouchet.cc2.domain.valueObjects.Password;

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
    public Contractor build() throws NullPointerException
    {
        return Contractor.of(
                Objects.requireNonNull(this.id),
                Objects.requireNonNull(this.login),
                Objects.requireNonNull(this.password),
                Objects.requireNonNull(this.name),
                Objects.requireNonNull(this.paymentMethod),
                this.isPaymentValidated,
                Objects.requireNonNull(this.creationDate)
        );
    }

    public static ContractorBuilder init(Id id, String login, Date creationDate)
    {
        return new ContractorBuilder(id, login, creationDate);
    }

    public static ContractorBuilder init(Contractor contractor)
    {
        ContractorBuilder builder = new ContractorBuilder(
                contractor.getId(),
                contractor.getLogin(),
                contractor.getCreationDate()
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