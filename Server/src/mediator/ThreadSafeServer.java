package mediator;


/**
 * The type Thread safe server.
 */
public class ThreadSafeServer implements ServerAccess
{
  private int writers;
  private int readers;
  private int waitingWriters;

  /**
   * Instantiates a new Thread safe server.
   */
  public ThreadSafeServer()
  {
    this.waitingWriters = 0;
    this.writers = 0;
    this.readers = 0;
  }

  @Override public synchronized void acquireRead()
  {
    while (waitingWriters > 0 || writers > 0)
    {
      try
      {
        wait();
      }
      catch (Exception e)
      {
        e.printStackTrace();

      }
    }
    readers++;
  }

  @Override public synchronized void acquireWrite()
  {
    waitingWriters++;
    while (readers > 0 || writers > 0)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    waitingWriters--;
    writers++;
  }

  @Override public synchronized void releaseRead()
  {
    readers--;
    if (readers == 0)
    {
      notify();
    }
  }

  @Override public synchronized void releaseWrite()
  {
    writers--;
    notifyAll();
  }
}
