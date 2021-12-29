package al.cc2.gtouchet.domain.valueObjects;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class Clock
{
    private final String value;

    private Clock()
    {
        this.value = LocalDateTime.of(LocalDate.now(), LocalTime.now())
                .format(DateTimeFormatter.ofPattern("dd-MM-uuuu_HH-mm-ss"));
    }

    public static Clock now()
    {
        return new Clock();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clock clock = (Clock) o;
        return Objects.equals(value, clock.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(value);
    }

    @Override
    public String toString()
    {
        return this.value;
    }
}
