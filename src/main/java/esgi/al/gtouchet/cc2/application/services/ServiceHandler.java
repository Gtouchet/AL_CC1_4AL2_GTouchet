package esgi.al.gtouchet.cc2.application.services;

public interface ServiceHandler<R, C>
{
    R handle(C command);
}
