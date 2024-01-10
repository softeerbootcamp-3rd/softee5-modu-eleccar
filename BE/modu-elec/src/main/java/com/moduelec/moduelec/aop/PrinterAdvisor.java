package com.moduelec.moduelec.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PrinterAdvisor {

  private final String target = "";

  @Pointcut("execution(void printSampleString())")
  public void pointCut(){}

  public void printSampleString(){
    System.out.println("hello world");
  }
}
