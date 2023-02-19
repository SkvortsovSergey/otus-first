package com.example.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.util.Objects.isNull;

@PropertySource("classpath:application.properties")
@Slf4j
public class Sl4jCustomConfig {
    private static final ObjectMapper DEFAULT_OBJECTMAPPER;
    /**
     * Для отладки поставить значение IS_CONSUMER_ENABLED = true
     */
    @Getter
    private static final boolean IS_CONSUMER_ENABLED = false;

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        DEFAULT_OBJECTMAPPER = objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        /*
         for ignore null values add set this
         "objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);"
         */
        // для корректной работы с датой
        objectMapper.registerModule(new JavaTimeModule());
        // для корректной работы с Optional объектами
        objectMapper.registerModule(new Jdk8Module());
    }

    private Sl4jCustomConfig() {
    }

    public static Supplier<Object> toJson(Object object) {
        if (object instanceof Optional<?>) {
            log.info("Optional object = {}", object);
        }
        Supplier<Object> jsonSupplier = () -> {
            try {
                return DEFAULT_OBJECTMAPPER.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                log.debug(String.valueOf(e));
                log.info("toJson не стработал, вывод toString");

                return object.toString();
            }
        };
        return new ObjectSupplierWrapper(jsonSupplier);
    }

    public static Supplier<Object> delayed(Supplier<Object> original) {
        return new ObjectSupplierWrapper(original);
    }

    public static final void aLog(Consumer<Object> original) {
        if (IS_CONSUMER_ENABLED) {
            ObjectConsumerWrapper objectConsumerWrapper = new ObjectConsumerWrapper(original);
            objectConsumerWrapper.accept(original);
        }
    }

    private static class ObjectSupplierWrapper implements Supplier<Object> {
        private final Supplier<Object> original;

        private ObjectSupplierWrapper(Supplier<Object> original) {
            this.original = original;
        }

        @Override
        public Object get() {
            return original.get();
        }

        @Override
        public String toString() {
            Object o = get();
            return isNull(o) ? "null" : o.toString();
        }
    }

    private static class ObjectConsumerWrapper implements Consumer<Object> {
        private final Consumer<Object> original;

        private ObjectConsumerWrapper(Consumer<Object> original) {
            this.original = original;
        }

        @Override
        public void accept(final Object o) {
            if (IS_CONSUMER_ENABLED) {
                original.accept(o);
            }
        }
    }
}
