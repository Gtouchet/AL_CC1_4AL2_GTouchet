package esgi.al.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Payment
{
    private final String id;
    private final String userId;
    private final float amount;
    private final String reason;
    private final String date;

    private Payment(
            String id,
            String userId,
            float amount,
            String reason,
            String date
    ) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.reason = reason;
        this.date = date;
    }

    public static Payment of(
            String id,
            String userId,
            float amount,
            String reason,
            String date
    ) {
        return new Payment(
                id,
                userId,
                amount,
                reason,
                date
        );
    }

    public static Payment of(
            String userId,
            float amount,
            String reason
    ) {
        return new Payment(
                UUID.randomUUID().toString(),
                userId,
                amount,
                reason,
                new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date())
        );
    }

    public String getId()
    {
        return this.id;
    }

    public String getUserId()
    {
        return this.userId;
    }

    public float getAmount()
    {
        return this.amount;
    }

    public String getReason()
    {
        return this.reason;
    }

    public String getDate()
    {
        return this.date;
    }

    @Override
    public String toString()
    {
        return "ID: " + this.id +
                "\nUser ID: " + this.userId +
                "\nAmount: " + this.amount +
                "\nReason: " + this.reason +
                "\nDate: " + this.date;
    }
}
