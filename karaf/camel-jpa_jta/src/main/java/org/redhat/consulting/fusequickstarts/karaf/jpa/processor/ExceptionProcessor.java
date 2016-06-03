package org.redhat.consulting.fusequickstarts.karaf.jpa.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by benja on 4/29/16.
 */
public class ExceptionProcessor implements Processor {

  public void process(Exchange exchange) throws Exception {
    throw new RuntimeException();
  }
}
