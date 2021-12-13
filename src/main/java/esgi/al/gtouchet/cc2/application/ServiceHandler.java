package esgi.al.gtouchet.cc2.application;

public interface ServiceHandler<R, C>
{
    R handle(C command);
}
