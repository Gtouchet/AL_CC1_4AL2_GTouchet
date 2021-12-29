package al.cc2.gtouchet.domain.builders;

import al.cc2.gtouchet.domain.models.user.Contractor;
import al.cc2.gtouchet.domain.models.user.Credentials;
import al.cc2.gtouchet.domain.models.payment.PaymentMethod;
import al.cc2.gtouchet.domain.valueObjects.Clock;
import al.cc2.gtouchet.domain.valueObjects.EntityId;
import al.cc2.gtouchet.domain.valueObjects.Password;

import java.util.Objects;

public final class ContractorBuilder implements Builder<Contractor>
{
    private final EntityId id;
    private final String login;
    private Password password;
    private String name;
    private PaymentMethod paymentMethod;
    private boolean isPaymentValidated;
    private final Clock creationClock;

    private ContractorBuilder(EntityId id, String login, Clock creationClock)
    {
        this.id = id;
        this.login = login;
        this.creationClock = creationClock;
    }

    @Override
    public Contractor build() throws NullPointerException
    {
        return Contractor.of(
                Objects.requireNonNull(this.id),
                new Credentials(
                        Objects.requireNonNull(this.login),
                        Objects.requireNonNull(this.password)
                ),
                Objects.requireNonNull(this.name),
                Objects.requireNonNull(this.paymentMethod),
                this.isPaymentValidated,
                Objects.requireNonNull(this.creationClock)
        );
    }

    public static ContractorBuilder init(EntityId id, String login, Clock creationClock)
    {
        return new ContractorBuilder(id, login, creationClock);
    }

    public static ContractorBuilder init(Contractor contractor)
    {
        ContractorBuilder builder = new ContractorBuilder(
                contractor.getId(),
                contractor.getCredentials().getLogin(),
                contractor.getCreationDate()
        );

        builder.password = contractor.getCredentials().getPassword();
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
