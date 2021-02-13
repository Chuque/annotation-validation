package com.chuque.annotationprocessing.core;

import com.chuque.annotationprocessing.annotation.TimedMillis;
import com.chuque.annotationprocessing.annotation.TimedSeconds;

public class Foo {

    public void bar(){
        System.out.println("bar called");
    }

    @TimedMillis
    public void baz(){
        System.out.println("baz called");
    }

    @TimedSeconds
    public void qux(){
        System.out.println("qux called");
    }

    //comment/remove any of the following annotations and the application will run
    @TimedMillis
    @TimedSeconds
    public void quux(){
        System.out.println("quux called");
    }
}
