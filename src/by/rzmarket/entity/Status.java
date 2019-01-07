package by.rzmarket.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Status {

    PROCESSED("Обрабатывается"),
    RESERVED("Зарезервирован"),
    FINISHED("Завершён");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public static Status getByName(String name) {
        return Arrays.stream(values())
                .filter(it -> it.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Status{" +
                "name='" + name + '\'' +
                '}';
    }
}
