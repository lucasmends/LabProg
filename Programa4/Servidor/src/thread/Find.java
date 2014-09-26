/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;


/**
 *
 * @author lucas
 */
public class Find {

    /**
     * Find a thread by name.
     * @param name thread's name
     * @return the thread
     * 
     * @see ThreadGroup
     */
    public static Thread thread(String name) {

        Thread currentThread = Thread.currentThread();
        
        Thread found = null;

        ThreadGroup group = currentThread.getThreadGroup();

        while (group.getParent() != null) {
            int activeCount = group.activeCount();

            Thread activeThreads[] = new Thread[activeCount + 5];
            int actualCount = group.enumerate(activeThreads);

            for (int i = 0; i < actualCount; i++) {
                if (name.equals(activeThreads[i].getName())) {
                    found = activeThreads[i];
                    break;
                }
            }
            if (found != null) {
                return found;
            }
        }
        return found;
    }
}
