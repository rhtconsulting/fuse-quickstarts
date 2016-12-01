package com.redhat.consulting.fusequickstarts.karaf.rest.beanvalidation.resolver;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ValidationProviderResolver;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * OSGi-friendly implementation of {@code javax.validation.ValidationProviderResolver} returning
 * {@code org.hibernate.validator.HibernateValidator} instance.
 *
 */
public class HibernateValidationProviderResolver implements ValidationProviderResolver
{
   @SuppressWarnings("unchecked")
   @Override
   public List getValidationProviders()
   {
      return singletonList(new HibernateValidator());
   }
}
