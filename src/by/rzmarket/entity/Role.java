package by.rzmarket.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Role {

    ADMIN ("ADMIN"), USER ("USER");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public static Role getByName(String name) {
        return Arrays.stream(values())
                .filter(it -> it.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}
