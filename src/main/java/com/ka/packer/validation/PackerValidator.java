package com.ka.packer.validation;

import com.ka.packer.core.PropertyLoader;
import com.ka.packer.exception.APIException;
import com.ka.packer.model.Container;
import com.ka.packer.model.Item;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.List;
import java.util.Properties;

/**
 * Description: Packer application wide validator for marked methods
 * Project: packer
 * Package: com.ka.packer.validation
 * Author: kakyurek
 * Date: 2018.02.04
 */
@Aspect
public class PackerValidator {

    private static final String APPLICATION_PROPERTIES = "application.properties";
    private static double MAX_PACKAGE_WEIGHT;
    private static int MAX_ITEMS;
    private static int MAX_ITEM_COST;
    private static double MAX_ITEM_WEIGHT;

    /**
     * Loading validation configuration from classpath
     *
     * @throws Exception thrown if configuration file or keys are not exist
     */
    PackerValidator() throws Exception {
        Properties properties = PropertyLoader.getInstance().loadProperties(APPLICATION_PROPERTIES);
        MAX_PACKAGE_WEIGHT = Double.parseDouble(properties.getProperty("max.package.weight"));
        MAX_ITEM_WEIGHT = Double.parseDouble(properties.getProperty("max.item.weight"));
        MAX_ITEMS = Integer.parseInt(properties.getProperty("max.items"));
        MAX_ITEM_COST = Integer.parseInt(properties.getProperty("max.item.cost"));
    }

    /**
     * After containers load, validation occurs according to application configuration
     *
     * @param point Advise point
     * @return Result of the method
     * @throws Throwable thrown if validation fails or some random error occurs
     */
    @Around("@annotation(ContainerValidator)")
    public Object aroundDataLoader(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();

        try {
            Object result = point.proceed();

            @SuppressWarnings("unchecked")
            List<Container> containers = (List<Container>) result;

            // Validate each container
            containers.forEach(c -> {
                double packageLimit = c.getLimit();

                if (packageLimit <= 0 || packageLimit > MAX_PACKAGE_WEIGHT) {
                    throw new APIException("Max weight that a package can take should be lower than or equal to 100 and bigger than 0");
                } else if (c.getItems().size() > MAX_ITEMS) {
                    throw new APIException("There might be up to 15 items you need to choose from");
                }

                List<Item> items = c.getItems();
                double weight;
                int value;

                // Validate each item in the container
                for (Item item : items) {
                    weight = item.getWeight();
                    value = item.getCost();

                    if (weight < 0 || weight > MAX_ITEM_WEIGHT) {
                        throw new APIException("Weight of item should be less than or equal to 100 and bigger than 0");
                    } else if (value < 0 || value > MAX_ITEM_COST) {
                        throw new APIException("Value of item should be less than or equal to 100 and bigger than 0");
                    }

                }

            });

            return result;
        } catch (Exception exception) {
            throw new APIException(exception);
        }
    }

}