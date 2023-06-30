package org.javaeducation;

import org.javaeducation.tasks.SelectTaskCommand;
import org.javaeducation.tasks.TaskRunner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new TaskRunner(SelectTaskCommand.getInstance()).run();
    }
}
