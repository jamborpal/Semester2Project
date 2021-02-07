package mediator;

import java.rmi.RemoteException;

/**
 * The interface Server access.
 */
public interface ServerAccess
{
  /**
   * Acquire read.
   *
   * @throws RemoteException the remote exception
   */
  void acquireRead() throws RemoteException;

  /**
   * Acquire write.
   *
   * @throws RemoteException the remote exception
   */
  void acquireWrite() throws RemoteException;

  /**
   * Release read.
   *
   * @throws RemoteException the remote exception
   */
  void releaseRead() throws RemoteException;

  /**
   * Release write.
   *
   * @throws RemoteException the remote exception
   */
  void releaseWrite() throws RemoteException;
}
