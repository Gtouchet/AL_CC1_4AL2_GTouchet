package al.cc2.gtouchet.application.services;

public interface ServiceHandler<R, C>
{
    R handle(C command);
}
