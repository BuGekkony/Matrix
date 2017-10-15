package com.example.patricia.matrix.StopExecutors;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StopExecutors {

    private static final String TAG = Executors.class.getSimpleName();
    private static boolean taskIsTerminated = false;

    public static void stopTaskExecutorsScheduled(ScheduledExecutorService task, String taskName) {

        if ( task != null ) {
            Log.d(TAG, "MÉTODO STOP TASK EXECUTOR SCHEDULED: " + taskName);
            try {
                task.shutdown();
                task.awaitTermination(1, TimeUnit.SECONDS);
            }
            catch (InterruptedException exception) {
                exception.printStackTrace();
                Log.e(TAG, "EXCEÇÃO AO EXECUTAR STOP TASK EXECUTOR SCHEDULED " + taskName);
                Log.e(TAG, "ERRO: " + exception.getMessage(), exception);
            }
            finally {
                if (!task.isTerminated()) {
                    Log.d(TAG, "STOP TASK EXECUTOR SCHEDULED " + taskName + "NÃO ENCERRADA.");
                }
                task.shutdownNow();
                taskIsTerminated =  task.isShutdown();
                Log.d(TAG, "EXECUTOR SERVICE SCHEDULED " + taskName + " IS SHUTDOWN " + String.valueOf(taskIsTerminated));
                taskIsTerminated = task.isTerminated();
                Log.d(TAG, "EXECUTOR SERVICE SCHEDULED " + taskName + " IS TERMINATED " + String.valueOf(taskIsTerminated));
            }
        }
    }

    public static void stopTaskExecutorService(ExecutorService task, String taskName) {

        if ( task != null ) {
            Log.d(TAG, "MÉTODO STOP TASK EXECUTOR SCHEDULED " + taskName);
            try {
                task.shutdown();
                task.awaitTermination(1,TimeUnit.SECONDS);
            }
            catch (InterruptedException exception) {
                exception.printStackTrace();
                Log.e(TAG, "EXCEÇÃO AO EXECUTAR STOP TASK EXECUTOR SCHEDULED " + taskName);
                Log.e(TAG, "ERRO: " + exception.getMessage(), exception);
            }
            finally {
                if (!task.isTerminated()) {
                    Log.d(TAG, "STOP TASK EXECUTOR SCHEDULED " + taskName + "NÃO ENCERRADA.");
                }
                task.shutdownNow();
                taskIsTerminated =  task.isShutdown();
                Log.d(TAG, "EXECUTOR SERVICE SCHEDULED " + taskName + " IS SHUTDOWN " + String.valueOf(taskIsTerminated));
                taskIsTerminated = task.isTerminated();
                Log.d(TAG, "EXECUTOR SERVICE SCHEDULED " + taskName + " IS TERMINATED " + String.valueOf(taskIsTerminated));
            }
        }
    }
}