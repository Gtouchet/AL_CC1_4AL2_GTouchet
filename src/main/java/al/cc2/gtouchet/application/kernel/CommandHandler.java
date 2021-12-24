package al.cc2.gtouchet.application.kernel;

public interface CommandHandler<R, C extends Command>
{
    R handle(C command);
}
