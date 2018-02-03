package com.ka.packer.validation;

import com.ka.packer.exception.APIException;
import com.ka.packer.model.Container;
import com.ka.packer.model.Item;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.List;

/**
 * Description: Packer application wide validator for marked methods
 * Project: packer
 * Package: com.ka.packer.validation
 * Author: kakyurek
 * Date: 2018.02.04
 */
@Aspect
public class PackerValidator {

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

                if (packageLimit <= 0 || packageLimit > 100) {
                    throw new APIException("Max weight that a package can take should be lower than or equal to 100 and bigger than 0");
                } else if (c.getItems().size() > 15) {
                    throw new APIException("There might be up to 15 items you need to choose from");
                }

                List<Item> items = c.getItems();
                double weight;
                int value;

                // Validate each item in the container
                for (Item item : items) {
                    weight = item.getWeight();
                    value = item.getCost();

                    if (weight < 0 || weight > 100) {
                        throw new APIException("Weight of item should be less than or equal to 100 and bigger than 0");
                    } else if (value < 0 || value > 100) {
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