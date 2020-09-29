package com.ranrings.statedisplay.aspects;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ActivityLifecycleLogAspect {
    private static final String TAG = ActivityLifecycleLogAspect.class.getSimpleName();

    @Pointcut("execution(* com.ranrings.statedisplay.MainActivity.on*(..))")
    public void logActivityLifecycle() {}

    @Before("logActivityLifecycle()")
    public void log(final JoinPoint joinPoint) {
        Log.v(TAG, "Kevin: " + joinPoint.toLongString());
    }



}
