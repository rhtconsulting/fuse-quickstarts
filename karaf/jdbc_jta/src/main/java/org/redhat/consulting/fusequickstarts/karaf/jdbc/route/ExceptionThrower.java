package org.redhat.consulting.fusequickstarts.karaf.jdbc.route;

/**
 * Created by pantinor on 25/08/16.
 */
public class ExceptionThrower {

    public static void method() {
        System.out.println("_____About to throw an exception");
        if(true){
            throw new RuntimeException("thrown on purpose");
        }
    }
}
