package runners;

import junit.runner.TestRunListener;

import java.util.Date;

public class Listener implements TestRunListener {
    long startTime;
    long endTime;

    @Override
    public void testRunStarted(String s, int i) {
        startTime = new Date().getTime();
        System.out.println("Tests started! Number of Test case: " + s + " " + i);
    }

    @Override
    public void testRunEnded(long l) {

    }

    @Override
    public void testRunStopped(long l) {

    }

    @Override
    public void testStarted(String s) {

    }

    @Override
    public void testEnded(String s) {

    }

    @Override
    public void testFailed(int i, String s, String s1) {

    }
}
