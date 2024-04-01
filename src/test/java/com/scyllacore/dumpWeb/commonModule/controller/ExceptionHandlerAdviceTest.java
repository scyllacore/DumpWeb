package com.scyllacore.dumpWeb.commonModule.controller;

import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionHandlerAdviceTest {

    @Test
    void test1(){
        Exception e = Assertions.assertThrows(Exception.class , () ->{
            System.out.println(5/0);
        });

        Assertions.assertEquals(e.getMessage(),"test");
    }

}