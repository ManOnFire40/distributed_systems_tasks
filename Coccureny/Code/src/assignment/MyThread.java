package assignment;
import java.util.concurrent.*;

class MyThread extends Thread
{
    Semaphore sem;
    String threadName;
    public MyThread(Semaphore sem, String threadName) 
    {
        super(threadName);
        this.sem = sem;
        this.threadName = threadName;
    }
  
    @Override
    public void run() {
          
        // run by thread A
    	
        if(this.getName().equals("A"))
        {
            System.out.println("Starting " + threadName);
            try 
            {
                System.out.println(threadName + " is waiting for a permit.");
              
                //TODO acquire the lock
                sem.acquire();
                 
                System.out.println(threadName + " gets a permit.");
          
                for(int i=0; i < 5; i++)
                {
                	//TODO Update the CumSum Value as described for thread A then Display it
                	Shared.CumSum=Shared.x+Shared.Y+Shared.CumSum;
                	System.out.println("thread "+threadName+" "+Shared.CumSum);
                    Thread.sleep(10);
                }
            } catch (InterruptedException exc) {
                    System.out.println(exc);
                }
          
                //TODO Release the lock.
                sem.release();
                System.out.println(threadName + " releases the permit.");
        }
          
        // run by thread B
        else
        {
            System.out.println("Starting " + threadName);
            try 
            {
                // First, get a permit.
                System.out.println(threadName + " is waiting for a permit.");
              
                //TODO acquire the lock
                sem.acquire();
              
                System.out.println(threadName + " gets a permit.");

                for(int i=0; i < 5; i++)
                {
                	//TODO Update the CumSum Value as described for thread B then Display it
                	Shared.CumSum=Shared.CumSum-(Shared.x+Shared.Y);
                    System.out.println("thread "+threadName+" "+Shared.CumSum);
                	Thread.sleep(10);
                }
                
            } catch (InterruptedException exc) {
                    System.out.println(exc);
                }
                //TODO Release the lock.
                System.out.println(threadName + " releases the permit.");
                sem.release();
        }
    }
}