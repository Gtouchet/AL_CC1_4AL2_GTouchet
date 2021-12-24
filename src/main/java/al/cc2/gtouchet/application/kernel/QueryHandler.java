package al.cc2.gtouchet.application.kernel;

public interface QueryHandler<R, Q extends Query>
{
    R handle(Q query);
}
