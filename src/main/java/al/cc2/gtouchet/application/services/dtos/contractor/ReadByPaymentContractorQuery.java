package al.cc2.gtouchet.application.services.dtos.contractor;

import al.cc2.gtouchet.application.kernel.Query;
import al.cc2.gtouchet.domain.models.payment.PaymentMethod;

public final class ReadByPaymentContractorQuery implements Query
{
    public final PaymentMethod paymentMethod;

    public ReadByPaymentContractorQuery(PaymentMethod paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }
}
